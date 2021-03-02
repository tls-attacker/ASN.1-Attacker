/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.util;

import de.rub.nds.asn1.Asn1Encodable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttributeParser {

    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean parseBooleanAttribute(final Asn1Encodable asn1Encodable, final String attributeName) {
        boolean result;
        if (asn1Encodable.hasAttribute(attributeName)) {
            result = Boolean.parseBoolean(asn1Encodable.getAttribute(attributeName));
        } else {
            throw new RuntimeException("Attribute " + attributeName + " not available!");
        }
        return result;
    }

    public static boolean parseBooleanAttributeOrDefault(final Asn1Encodable asn1Encodable, final String attributeName,
        final boolean defaultValue) {
        boolean result = defaultValue;
        try {
            result = parseBooleanAttribute(asn1Encodable, attributeName);
        } catch (Throwable e) {

        }
        return result;
    }

    public static int parseIntAttribute(final Asn1Encodable asn1Encodable, final String attributeName) {
        int result;
        if (asn1Encodable.hasAttribute(attributeName)) {
            result = Integer.parseInt(asn1Encodable.getAttribute(attributeName));
        } else {
            throw new RuntimeException("Attribute " + attributeName + " not available!");
        }
        return result;
    }

    public static int parseIntAttributeOrDefault(final Asn1Encodable asn1Encodable, final String attributeName,
        final int defaultValue) {
        int result = defaultValue;
        try {
            result = parseIntAttribute(asn1Encodable, attributeName);
        } catch (Throwable e) {

        }
        return result;
    }

    public static long parseLongAttribute(final Asn1Encodable asn1Encodable, final String attributeName) {
        long result;
        if (asn1Encodable.hasAttribute(attributeName)) {
            result = Long.parseLong(asn1Encodable.getAttribute(attributeName));
        } else {
            throw new RuntimeException("Attribute " + attributeName + " not available!");
        }
        return result;
    }

    public static long parseLongAttributeOrDefault(final Asn1Encodable asn1Encodable, final String attributeName,
        final long defaultValue) {
        long result = defaultValue;
        try {
            result = parseLongAttribute(asn1Encodable, attributeName);
        } catch (Throwable e) {

        }
        return result;
    }

    public static String parseStringAttribute(final Asn1Encodable asn1Encodable, final String attributeName) {
        String result;
        if (asn1Encodable.hasAttribute(attributeName)) {
            result = asn1Encodable.getAttribute(attributeName);
        } else {
            throw new RuntimeException("Attribute " + attributeName + " not available!");
        }
        return result;
    }

    public static String parseStringAttributeOrDefault(final Asn1Encodable asn1Encodable, final String attributeName,
        final String defaultValue) {
        String result = defaultValue;
        try {
            result = parseStringAttribute(asn1Encodable, attributeName);
        } catch (Throwable e) {

        }
        return result;
    }
}
