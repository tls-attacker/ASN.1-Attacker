/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.time;

import de.rub.nds.protocol.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeDecoder {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger();

    public static DateTime decodeGeneralizedTimeUtc(String generalizedTimeString) {
        String[] formats =
                new String[] {
                    "yyyyMMddHH",
                    "yyyyMMddHHmm",
                    "yyyyMMddHHmmss",
                    "yyyyMMddHHmmss.SSS",
                    "yyyyMMddHH'Z'",
                    "yyyyMMddHHmm'Z'",
                    "yyyyMMddHHmmss'Z'",
                    "yyyyMMddHHmmss.SSS'Z'"
                };
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
                DateTime dateTime = formatter.parseDateTime(generalizedTimeString);
                return dateTime;
            } catch (IllegalArgumentException E) {
                // Ignore
            }
        }
        throw new ParserException("Cannot parse time" + generalizedTimeString);
    }

    public static DateTime decodeUtc(String utcTimeString) {
        String[] formats =
                new String[] {"yyMMddHHmm", "yyMMddHHmmss", "yyMMddHHmm'Z'", "yyMMddHHmmss'Z'"};
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
                DateTime dateTime = formatter.parseDateTime(utcTimeString);
                return dateTime;
            } catch (IllegalArgumentException E) {
                // Ignore
            }
        }
        throw new ParserException("Cannot parse time: " + utcTimeString);
    }
}
