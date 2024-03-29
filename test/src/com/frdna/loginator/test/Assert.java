/* Copyright (c) 2011 Free Range Data, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.frdna.loginator.test;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.apache.log4j.spi.LoggingEvent;

/**
 * Custom assertions.
 */
public class Assert extends org.junit.Assert {

    private Assert() {
    }

    /**
     * Assert a <code>LogRecord</code> has the correct message and
     * <code>Level</code>.
     */
    public static void assertLogRecord(
            Level expectedLevel,
            String expectedMessage,
            LogRecord logRecord) {
        Assert.assertEquals(expectedLevel, logRecord.getLevel());
        Assert.assertEquals(expectedMessage, logRecord.getMessage());
    }

    /**
     * Assert a <code>LoggingEvent</code> has the correct message and
     * <code>org.apache.log4j.Level</code>.
     */
    public static void assertLoggingEvent(
            org.apache.log4j.Level expectedLevel,
            String expectedMessage,
            LoggingEvent loggingEvent) {
        Assert.assertEquals(expectedLevel, loggingEvent.getLevel());
        Assert.assertEquals(expectedMessage, loggingEvent.getMessage());
    }

    /**
     * Assert a <code>Collection</code> has the expected number of elements.
     */
    public static void assertSize(int expectedSize, Collection<?> collection) {
        Assert.assertEquals(expectedSize, collection.size());
    }
}
