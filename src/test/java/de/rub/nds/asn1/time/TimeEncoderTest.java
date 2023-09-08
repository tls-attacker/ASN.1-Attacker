/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.time;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TimeAccurracy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class TimeEncoderTest {

    private static final Logger LOGGER = LogManager.getLogger();

    public TimeEncoderTest() {}

    /** Test of encodeGeneralizedTimeUtc method, of class TimeEncoder. */
    @Test
    public void testEncodeGeneralizedTimeUtc() {
        DateTime date = new DateTime(0);
        LOGGER.info("Encoding target: {}", date.toString());
        String time = TimeEncoder.encodeGeneralizedTimeUtc(date, TimeAccurracy.HOURS);
        assertEquals("1970010100Z", time);
        LOGGER.info("GeneralizedTime UTC Hours: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeUtc(date, TimeAccurracy.MINUTES);
        assertEquals("197001010000Z", time);
        LOGGER.info("GeneralizedTime UTC Minutes: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeUtc(date, TimeAccurracy.SECONDS);
        assertEquals("19700101000000Z", time);
        LOGGER.info("GeneralizedTime UTC Seconds: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeUtc(date, TimeAccurracy.MILLISECONDS);
        assertEquals("19700101000000.000Z", time);
        LOGGER.info("GeneralizedTime UTC MS: {}", time);
    }

    @Test
    public void testEncodeGeneralizedTimeLocalTime() {
        DateTime date = new DateTime(0);
        LOGGER.info("Encoding target: {}", date.toString());
        String time = TimeEncoder.encodeGeneralizedTimeLocalTime(date, TimeAccurracy.HOURS);
        assertEquals("1970010100", time);
        LOGGER.info("GeneralizedTime localtime Hours: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeLocalTime(date, TimeAccurracy.MINUTES);
        assertEquals("197001010000", time);
        LOGGER.info("GeneralizedTime localtime Minutes: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeLocalTime(date, TimeAccurracy.SECONDS);
        assertEquals("19700101000000", time);
        LOGGER.info("GeneralizedTime localtime Seconds: {}", time);
        time = TimeEncoder.encodeGeneralizedTimeLocalTime(date, TimeAccurracy.MILLISECONDS);
        assertEquals("19700101000000.000", time);
        LOGGER.info("GeneralizedTime UTC MS: {}", time);
    }

    /** Test of encodeGeneralizedTimeUtcWithDifferential method, of class TimeEncoder. */
    @Test
    public void testEncodeGeneralizedTimeUtcWithDifferential() {
        DateTime date = new DateTime(0);
        LOGGER.info("Encoding target: {}", date.toString());
        String time =
                TimeEncoder.encodeGeneralizedTimeUtcWithDifferential(
                        date, TimeAccurracy.HOURS, 240);
        assertEquals("1970010100+0400", time);
        LOGGER.info("GeneralizedTime UTC with differential Hours: {}", time);
        time =
                TimeEncoder.encodeGeneralizedTimeUtcWithDifferential(
                        date, TimeAccurracy.MINUTES, -240);
        assertEquals("197001010000-0400", time);
        LOGGER.info("GeneralizedTime UTC with differential Minutes: {}", time);
        time =
                TimeEncoder.encodeGeneralizedTimeUtcWithDifferential(
                        date, TimeAccurracy.SECONDS, 59);
        assertEquals("19700101000000+0059", time);
        LOGGER.info("GeneralizedTime UTC with differential Seconds: {}", time);
        time =
                TimeEncoder.encodeGeneralizedTimeUtcWithDifferential(
                        date, TimeAccurracy.MILLISECONDS, 0);
        assertEquals("19700101000000.000+0000", time);
        LOGGER.info("GeneralizedTime UTC with differential MS: {}", time);
    }

    @Test
    public void testEncodeFullUtc() {
        DateTime date = new DateTime(0);
        LOGGER.info("Encoding target: {}", date.toString());
        String time = TimeEncoder.encodeFullUtc(date, TimeAccurracy.HOURS);
        assertEquals("7001010000Z", time);
        LOGGER.info("GeneralizedTime UTC with differential Hours: {}", time);
        time = TimeEncoder.encodeFullUtc(date, TimeAccurracy.MINUTES);
        assertEquals("7001010000Z", time);
        LOGGER.info("GeneralizedTime UTC with differential Minutes: {}", time);
        time = TimeEncoder.encodeFullUtc(date, TimeAccurracy.SECONDS);
        assertEquals("700101000000Z", time);
        LOGGER.info("GeneralizedTime UTC with differential Seconds: {}", time);
        time = TimeEncoder.encodeFullUtc(date, TimeAccurracy.MILLISECONDS);
        assertEquals("700101000000Z", time);
        LOGGER.info("GeneralizedTime UTC with differential MS: {}", time);
    }

    @Test
    public void testEncodeUtcWithDifferential() {
        DateTime date = new DateTime(0);
        LOGGER.info("Encoding target: {}", date.toString());
        String time = TimeEncoder.encodeUtcWithDifferential(date, TimeAccurracy.HOURS, 240);
        assertEquals("7001010000+0400", time);
        LOGGER.info("GeneralizedTime UTC with differential Hours: {}", time);
        time = TimeEncoder.encodeUtcWithDifferential(date, TimeAccurracy.MINUTES, -240);
        assertEquals("7001010000-0400", time);
        LOGGER.info("GeneralizedTime UTC with differential Minutes: {}", time);
        time = TimeEncoder.encodeUtcWithDifferential(date, TimeAccurracy.SECONDS, 59);
        assertEquals("700101000000+0059", time);
        LOGGER.info("GeneralizedTime UTC with differential Seconds: {}", time);
        time = TimeEncoder.encodeUtcWithDifferential(date, TimeAccurracy.MILLISECONDS, 0);
        assertEquals("700101000000+0000", time);
        LOGGER.info("GeneralizedTime UTC with differential MS: {}", time);
    }
}
