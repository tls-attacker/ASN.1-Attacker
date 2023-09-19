/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
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

    private static final Logger LOGGER = LogManager.getLogger();

    public static DateTime decodeGeneralizedTimeUtc(String generalizedTimeString) {
        LOGGER.debug("Decoding GeneralizedTimeUtc: {}", generalizedTimeString);

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
                // Ignore. We are bruteforcing the format here. An illegal argument exception
                // is only a problem when every format throws one.
            }
        }
        throw new ParserException("Cannot parse time" + generalizedTimeString);
    }

    public static DateTime decodeUtc(String utcTimeString) {
        LOGGER.debug("Decoding UTC time: {}", utcTimeString);
        String[] formats =
                new String[] {"yyMMddHHmm", "yyMMddHHmmss", "yyMMddHHmm'Z'", "yyMMddHHmmss'Z'"};
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withZoneUTC();
                DateTime dateTime = formatter.parseDateTime(utcTimeString);
                return dateTime;
            } catch (IllegalArgumentException E) {
                // Ignore. We are bruteforcing the format here. An illegal argument exception
                // is only a problem when every format throws one.
            }
        }
        throw new ParserException("Cannot parse time: " + utcTimeString);
    }
}
