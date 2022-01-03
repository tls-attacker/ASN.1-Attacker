/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.translator.fieldtranslators.FieldTranslator;

public class ContextComponentOption<T extends Asn1Encodable> {

    public final int tag;

    public final int tagClass;

    public final boolean tagConstructed;

    public final int tagNumber;

    public final boolean hasChildren;

    public final Class<? extends FieldTranslator<T>> fieldTranslatorClass;

    public final String subContextName;

    public ContextComponentOption(final int tagClass, final boolean tagConstructed, final int tagNumber,
        final boolean hasChildren, final Class<? extends FieldTranslator<T>> fieldTranslatorClass,
        final String subContextName) {
        this.tag = 0;
        this.tagClass = tagClass;
        this.tagConstructed = tagConstructed;
        this.tagNumber = tagNumber;
        this.hasChildren = hasChildren;
        this.fieldTranslatorClass = fieldTranslatorClass;
        this.subContextName = subContextName;
    }

    public ContextComponentOption(final int tag, final int tagClass, final boolean tagConstructed, final int tagNumber,
        final boolean hasChildren, final Class<? extends FieldTranslator<T>> fieldTranslatorClass,
        final String subContextName) {
        this.tag = tag;
        this.tagClass = tagClass;
        this.tagConstructed = tagConstructed;
        this.tagNumber = tagNumber;
        this.hasChildren = hasChildren;
        this.fieldTranslatorClass = fieldTranslatorClass;
        this.subContextName = subContextName;
    }

    public int computeScore(final int tag, final int tagClass, final boolean tagConstructed, final int tagNumber,
        final boolean hasChildren) {
        int score = 0;

        if (this.tag != 0 && this.tag == tag) {
            score = score + 5;
        }

        if (this.tagClass == tagClass) {
            score++;
        }

        if (this.tagConstructed == tagConstructed) {
            score++;
        }

        if (this.tagNumber == tagNumber) {
            score++;
        }

        if (this.hasChildren == hasChildren) {
            score++;
        }

        return score;
    }
}
