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
package com.frdna.loginator.log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.frdna.loginator.test.Assert;
import com.frdna.loginator.test.TestCase;

public class Log4jLoggerTest extends TestCase {

    private static TestAppender appender = null;
    private static Logger logger         = null;
    private Log4jLogger log4jLogger      = null;

    @BeforeClass
    public static void beforeAll() {
        Log4jLoggerTest.appender = new Log4jLoggerTest.TestAppender();
        Log4jLoggerTest.logger  = Logger.getLogger(
                Log4jLoggerTest.class.getName());
        Log4jLoggerTest.logger.addAppender(Log4jLoggerTest.appender);
    }

    @AfterClass
    public static void afterAll() {
        Log4jLoggerTest.logger.removeAppender(Log4jLoggerTest.appender);
    }

    @Before
    public void before() {
        Log4jLoggerTest.appender.reset();
        Log4jLoggerTest.logger.setLevel(Level.ALL);
        this.log4jLogger = new Log4jLogger();
    }

    @Test
    public void shouldLogATraceMessage() {
        String message = "The log message";
        this.log4jLogger.trace(this, message);
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.TRACE, message, loggingEvent);
    }

    @Test
    public void shouldLogAFormattedTraceMessage() {
        this.log4jLogger.trace(this, "The %s message", "log");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.TRACE, "The log message", loggingEvent);
    }

    @Test
    public void shouldNotLogATraceMessageWhenTraceIsNotEnabled() {
        Log4jLoggerTest.logger.setLevel(Level.DEBUG);
        this.log4jLogger.trace(this, "The log message");
        Assert.assertSize(0, Log4jLoggerTest.appender.getLoggingEvents());
    }

    @Test
    public void shouldLogADebugMessage() {
        String message = "The log message";
        this.log4jLogger.debug(this, message);
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.DEBUG, message, loggingEvent);
    }

    @Test
    public void shouldLogAFormattedDebugMessage() {
        this.log4jLogger.debug(this, "The %s message", "log");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.DEBUG, "The log message", loggingEvent);
    }

    @Test
    public void shouldNotLogADebugMessageWhenDebugIsNotEnabled() {
        Log4jLoggerTest.logger.setLevel(Level.INFO);
        this.log4jLogger.debug(this, "The log message");
        Assert.assertSize(0, Log4jLoggerTest.appender.getLoggingEvents());
    }

    @Test
    public void shouldLogAnInfoMessage() {
        String message = "The log message";
        this.log4jLogger.info(this, message);
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.INFO, message, loggingEvent);
    }

    @Test
    public void shouldLogAFormattedInfoMessage() {
        this.log4jLogger.info(this, "The %s message", "log");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.INFO, "The log message", loggingEvent);
    }

    @Test
    public void shouldNotLogAnInfoMessageWhenInfoIsNotEnabled() {
        Log4jLoggerTest.logger.setLevel(Level.WARN);
        this.log4jLogger.info(this, "The log message");
        Assert.assertSize(0, Log4jLoggerTest.appender.getLoggingEvents());
    }

    @Test
    public void shouldLogAWarnMessage() {
        String message = "The log message";
        this.log4jLogger.warn(this, message);
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.WARN, message, loggingEvent);
    }

    @Test
    public void shouldLogAWarnMessageWithACause() {
        Throwable cause = new Exception();
        this.log4jLogger.warn(this, cause, "The log message");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertSame(
                cause,
                loggingEvent.getThrowableInformation().getThrowable());
    }

    @Test
    public void shouldLogAFormattedWarnMessage() {
        this.log4jLogger.warn(this, "The %s message", "log");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.WARN, "The log message", loggingEvent);
    }

    @Test
    public void shouldNotLogAWarnMessageWhenWarnIsNotEnabled() {
        Log4jLoggerTest.logger.setLevel(Level.ERROR);
        this.log4jLogger.warn(this, "The log message");
        Assert.assertSize(0, Log4jLoggerTest.appender.getLoggingEvents());
    }

    @Test
    public void shouldLogAnErrorMessage() {
        String message = "The log message";
        this.log4jLogger.error(this, message);
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(Level.ERROR, message, loggingEvent);
    }

    @Test
    public void shouldLogAnErrorMessageWithACause() {
        Throwable cause = new Exception();
        this.log4jLogger.error(this, cause, "The log message");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertSame(
                cause,
                loggingEvent.getThrowableInformation().getThrowable());
    }

    @Test
    public void shouldLogAFormattedErrorMessage() {
        this.log4jLogger.error(this, "The %s message", "log");
        Assert.assertSize(1, Log4jLoggerTest.appender.getLoggingEvents());
        LoggingEvent loggingEvent
            = Log4jLoggerTest.appender.getLoggingEvents().get(0);
        Assert.assertLoggingEvent(
                Level.ERROR,
                "The log message",
                loggingEvent);
    }

    @Test
    public void shouldNotLogAnErrorMessageWhenErrorIsNotEnabled() {
        Log4jLoggerTest.logger.setLevel(Level.OFF);
        this.log4jLogger.error(this, "The log message");
        Assert.assertSize(0, Log4jLoggerTest.appender.getLoggingEvents());
    }

    public static class TestAppender extends AppenderSkeleton {

        private ArrayList<LoggingEvent> loggingEvents
            = new ArrayList<LoggingEvent>();

        public TestAppender() {
        }

        public void reset() {
            this.loggingEvents.clear();
        }

        public List<LoggingEvent> getLoggingEvents() {
            return this.loggingEvents;
        }

        public void append(LoggingEvent loggingEvent) {
            this.loggingEvents.add(loggingEvent);
        }

        public boolean requiresLayout() {
            return false;
        }

        public void close() {
        }
    }
}
