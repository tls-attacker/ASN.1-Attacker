/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.modifiablevariable.ModifiableVariable;
import de.rub.nds.modifiablevariable.util.ReflectionHelper;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlType(name = "ModVarHolder")
public abstract class ModifiableVariableHolder implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Lists all the modifiable variables declared in the class
     *
     * @return List of all modifiableVariables declared in this class
     */
    public List<Field> getAllModifiableVariableFields() {
        return ReflectionHelper.getFieldsUpTo(this.getClass(), null, ModifiableVariable.class);
    }

    /**
     * Returns a random field representing a modifiable variable from this class
     *
     * @param  random
     *                The RandomNumber generator that should be used
     * @return        A random ModifiableVariableField
     */
    public Field getRandomModifiableVariableField(Random random) {
        List<Field> fields = getAllModifiableVariableFields();
        int randomField = random.nextInt(fields.size());
        return fields.get(randomField);
    }

    /**
     * Returns a list of all the modifiable variable holders in the object, including this instance
     *
     * @return All ModifiableVariableHolders
     */
    public List<ModifiableVariableHolder> getAllModifiableVariableHolders() {
        List<ModifiableVariableHolder> holders = new LinkedList<>();
        holders.add(this);
        return holders;
    }

    /**
     * Returns a random modifiable variable holder
     *
     * @param  random
     *                The RandomNumberGenerator that should be used
     * @return        A Random ModifiableVariableHolder
     */
    public ModifiableVariableHolder getRandomModifiableVariableHolder(Random random) {
        List<ModifiableVariableHolder> holders = getAllModifiableVariableHolders();
        int randomHolder = random.nextInt(holders.size());
        return holders.get(randomHolder);
    }
}
