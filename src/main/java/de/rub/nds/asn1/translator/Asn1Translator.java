/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1AnonymousFieldFT;
import de.rub.nds.asn1.translator.fieldtranslators.FieldTranslator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1Translator {

    private static final Logger LOGGER = LogManager.getLogger();

    private final List<IntermediateAsn1Field> intermediateAsn1Fields;

    private final Context context;

    private final boolean isStrictMode;

    public Asn1Translator(final String contextName, List<IntermediateAsn1Field> intermediateAsn1Fields,
        final boolean strictMode) {
        this.intermediateAsn1Fields = intermediateAsn1Fields;
        this.context = ContextRegister.getInstance().createContext(contextName);
        this.isStrictMode = strictMode;
    }

    public List<Asn1Encodable> translate() {
        List<Asn1Encodable> asn1Encodables;
        if (this.isStrictMode == true) {
            asn1Encodables = this.translateStrict();
        } else {
            asn1Encodables = this.translateBestEffort();
        }
        return asn1Encodables;
    }

    private List<Asn1Encodable> translateStrict() {
        List<Asn1Encodable> asn1Encodables = new LinkedList<>();
        for (IntermediateAsn1Field intermediateAsn1Field : this.intermediateAsn1Fields) {
            boolean isHandled = false;
            while (this.context.hasCurrent() && isHandled == false) {
                ContextComponent contextComponent = this.context.getCurrent();
                if (contextComponent.hasMatch(intermediateAsn1Field)) {
                    asn1Encodables.add(this.translateSingleIntermediateField(contextComponent, intermediateAsn1Field));
                    isHandled = true;
                    if (contextComponent.isRepetitive == false) {
                        this.context.consume();
                    }
                } else if (contextComponent.isRepetitive == true || contextComponent.isOptional == true) {
                    this.context.consume();
                } else {
                    throw new RuntimeException("Context " + this.context.getClass() + " cannot handle "
                        + asn1Encodables.getClass().toString() + "!");
                }
            }
            if (isHandled == false) {
                throw new RuntimeException("Context " + this.context.getClass() + " cannot handle "
                    + asn1Encodables.getClass().toString() + "!");
            }
        }
        if (this.context.canBeFinished() == false) {
            throw new RuntimeException("Context " + this.context.getClass() + " expects more components!");
        }
        return asn1Encodables;
    }

    private List<Asn1Encodable> translateBestEffort() {
        List<Asn1Encodable> asn1Encodables = new LinkedList<>();
        for (IntermediateAsn1Field intermediateAsn1Field : this.intermediateAsn1Fields) {
            boolean contextComponentsOptionalUntilMatch = false;
            boolean isHandled = false;
            do {
                if (this.context.hasCurrent() == false) {
                    break;
                }
                ContextComponent contextComponent = this.context.getCurrent();
                if (contextComponent.hasMatch(intermediateAsn1Field)) {
                    asn1Encodables.add(this.translateSingleIntermediateField(contextComponent, intermediateAsn1Field));
                    isHandled = true;
                    if (contextComponent.isRepetitive == false) {
                        this.context.consume();
                    }
                } else if (contextComponentsOptionalUntilMatch == true) {
                    this.context.consume();
                } else if (contextComponent.isRepetitive == true || contextComponent.isOptional == true) {
                    contextComponentsOptionalUntilMatch =
                        this.contextComponentsOptionalUntilMatch(intermediateAsn1Field);
                }
            } while (contextComponentsOptionalUntilMatch == true && isHandled == false);
            if (isHandled == false) {
                asn1Encodables.add(this.translateSingleIntermediateField(intermediateAsn1Field));
            }
        }
        if (this.context.canBeFinished() == false) {
            LOGGER.warn("Context " + this.context.getClass() + " expects more components!");
        }
        return asn1Encodables;
    }

    private boolean contextComponentsOptionalUntilMatch(final IntermediateAsn1Field intermediateAsn1Field) {
        boolean result = false;
        for (int i = 1; result == false && this.context.has(i); i++) {
            if (this.context.peek(i).hasMatch(intermediateAsn1Field)) {
                result = true;
            }
        }
        return result;
    }

    private Asn1Encodable translateSingleIntermediateField(final IntermediateAsn1Field intermediateAsn1Field) {
        Asn1AnonymousFieldFT anonymousFieldFT = new Asn1AnonymousFieldFT(intermediateAsn1Field);
        return anonymousFieldFT.translate("", "");
    }

    private Asn1Encodable translateSingleIntermediateField(final ContextComponent contextComponent,
        final IntermediateAsn1Field intermediateAsn1Field) {
        ContextComponentOption<?> contextComponentOption = contextComponent.getMatch(intermediateAsn1Field);
        FieldTranslator<? extends Asn1Encodable> fieldTranslator =
            this.invokeFieldTranslator(contextComponentOption.fieldTranslatorClass, intermediateAsn1Field);
        Asn1Encodable result = fieldTranslator.translate(contextComponent.identifier, contextComponent.type);
        if (result instanceof Asn1Container) {
            Asn1Container container = (Asn1Container) result;
            Asn1Translator childTranslator =
                new Asn1Translator(contextComponentOption.subContextName, intermediateAsn1Field.getChildren(),
                    this.isStrictMode);
            container.setChildren(childTranslator.translate());
        }
        return result;
    }

    private <T extends Asn1Encodable> FieldTranslator<T> invokeFieldTranslator(
        Class<? extends FieldTranslator<T>> fieldTranslatorClass, final IntermediateAsn1Field intermediateAsn1Field) {
        try {
            Constructor<? extends FieldTranslator<T>> constructor =
                fieldTranslatorClass.getDeclaredConstructor(IntermediateAsn1Field.class);
            return constructor.newInstance(intermediateAsn1Field);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
