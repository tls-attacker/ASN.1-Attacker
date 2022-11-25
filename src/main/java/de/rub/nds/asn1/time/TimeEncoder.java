/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.time;

import de.rub.nds.asn1.constants.TimeAccurracy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimeEncoder {

    private static final Logger LOGGER = LogManager.getLogger();

    public static String encodeGeneralizedTimeUtc(DateTime date, TimeAccurracy accurracy) {
        return encodeGeneralizedTimeLocalTime(date, accurracy) + "Z";
    }

    public static String encodeGeneralizedTimeLocalTime(DateTime date, TimeAccurracy accurracy) {
        DateTime utcTime = date.toDateTime(DateTimeZone.UTC);
        switch (accurracy) {
            case HOURS:
                return utcTime.toString("yyyyMMddHH");
            case MINUTES:
                return utcTime.toString("yyyyMMddHHmm");
            case SECONDS:
                return utcTime.toString("yyyyMMddHHmmss");
            case MILLISECONDS:
                return utcTime.toString("yyyyMMddHHmmss.SSS");
            default:
                throw new UnsupportedOperationException(
                        "Unsupported Accuracy: " + accurracy.name());
        }
    }

    public static String encodeGeneralizedTimeUtcWithDifferential(
            DateTime date, TimeAccurracy accurracy, int minuteOffset) {
        boolean positive = minuteOffset >= 0;
        int minutes = Math.abs(minuteOffset);
        String time = encodeGeneralizedTimeLocalTime(date, accurracy);
        return time.concat(positive ? "+" : "-")
                + String.format("%02d", minutes / 60)
                + String.format("%02d", minutes % 60);
    }

    private static String encodeUtc(DateTime date, TimeAccurracy accurracy) {
        DateTime utcTime = date.toDateTime(DateTimeZone.UTC);
        switch (accurracy) {
            case HOURS:
                LOGGER.warn("Hour accuracy is not allowed in UTC. Encoding with minutes");
                return utcTime.toString("yyMMddHHmm");
            case MINUTES:
                return utcTime.toString("yyMMddHHmm");
            case SECONDS:
                return utcTime.toString("yyMMddHHmmss");
            case MILLISECONDS:
                LOGGER.warn("Millisecond accuracy is not allowed in UTC. Encoding with seconds");
                return utcTime.toString("yyMMddHHmmss");
            default:
                throw new UnsupportedOperationException(
                        "Unsupported Accuracy: " + accurracy.name());
        }
    }

    public static String encodeFullUtc(DateTime date, TimeAccurracy accurracy) {
        return encodeUtc(date, accurracy) + "Z";
    }

    public static String encodeUtcWithDifferential(
            DateTime date, TimeAccurracy accurracy, int offsetInMinutes) {
        boolean positive = offsetInMinutes >= 0;
        int minutes = Math.abs(offsetInMinutes);
        String time = encodeUtc(date, accurracy);
        return time.concat(positive ? "+" : "-")
                + String.format("%02d", minutes / 60)
                + String.format("%02d", minutes % 60);
    }
}
