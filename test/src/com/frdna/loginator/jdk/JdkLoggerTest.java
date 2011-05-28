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
package com.frdna.loginator.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.frdna.loginator.test.Assert;
import com.frdna.loginator.test.TestCase;

public class JdkLoggerTest extends TestCase {

    private static TestHandler handler = null;
    private static Logger logger       = null;
    private JdkLogger jdkLogger        = null;

    @BeforeClass
    public static void beforeAll() {
        JdkLoggerTest.handler = new JdkLoggerTest.TestHandler();
        JdkLoggerTest.logger  = Logger.getLogger(JdkLoggerTest.class.getName());
        JdkLoggerTest.logger.addHandler(JdkLoggerTest.handler);
        JdkLoggerTest.logger.setUseParentHandlers(false);
    }

    @AfterClass
    public static void afterAll() {
        JdkLoggerTest.logger.removeHandler(JdkLoggerTest.handler);
        JdkLoggerTest.logger.setUseParentHandlers(true);
    }

    @Before
    public void before() {
        JdkLoggerTest.handler.reset();
        JdkLoggerTest.logger.setLevel(Level.ALL);
        this.jdkLogger = new JdkLogger();
    }

    @Test
    public void shouldLogATraceMessage() {
        String message = "The log message";
        this.jdkLogger.trace(this, message);
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.FINER, message, logRecord);
    }

    @Test
    public void shouldLogAFormattedTraceMessage() {
        this.jdkLogger.trace(this, "The %s message", "log");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.FINER, "The log message", logRecord);
    }

    @Test
    public void shouldNotLogATraceMessageWhenTraceIsNotEnabled() {
        JdkLoggerTest.logger.setLevel(Level.FINE);
        this.jdkLogger.trace(this, "The log message");
        Assert.assertSize(0, JdkLoggerTest.handler.getLogRecords());
    }

    @Test
    public void shouldLogADebugMessage() {
        String message = "The log message";
        this.jdkLogger.debug(this, message);
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.FINE, message, logRecord);
    }

    @Test
    public void shouldLogAFormattedDebugMessage() {
        this.jdkLogger.debug(this, "The %s message", "log");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.FINE, "The log message", logRecord);
    }

    @Test
    public void shouldNotLogADebugMessageWhenDebugIsNotEnabled() {
        JdkLoggerTest.logger.setLevel(Level.CONFIG);
        this.jdkLogger.debug(this, "The log message");
        Assert.assertSize(0, JdkLoggerTest.handler.getLogRecords());
    }

    @Test
    public void shouldLogAnInfoMessage() {
        String message = "The log message";
        this.jdkLogger.info(this, message);
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.INFO, message, logRecord);
    }

    @Test
    public void shouldLogAFormattedInfoMessage() {
        this.jdkLogger.info(this, "The %s message", "log");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.INFO, "The log message", logRecord);
    }

    @Test
    public void shouldNotLogAnInfoMessageWhenInfoIsNotEnabled() {
        JdkLoggerTest.logger.setLevel(Level.WARNING);
        this.jdkLogger.info(this, "The log message");
        Assert.assertSize(0, JdkLoggerTest.handler.getLogRecords());
    }

    @Test
    public void shouldLogAWarnMessage() {
        String message = "The log message";
        this.jdkLogger.warn(this, message);
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.WARNING, message, logRecord);
    }

    @Test
    public void shouldLogAWarnMessageWithACause() {
        Throwable cause = new Exception();
        this.jdkLogger.warn(this, cause, "The log message");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertSame(cause, logRecord.getThrown());
    }

    @Test
    public void shouldLogAFormattedWarnMessage() {
        this.jdkLogger.warn(this, "The %s message", "log");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.WARNING, "The log message", logRecord);
    }

    @Test
    public void shouldNotLogAWarnMessageWhenWarnIsNotEnabled() {
        JdkLoggerTest.logger.setLevel(Level.SEVERE);
        this.jdkLogger.warn(this, "The log message");
        Assert.assertSize(0, JdkLoggerTest.handler.getLogRecords());
    }

    @Test
    public void shouldLogAnErrorMessage() {
        String message = "The log message";
        this.jdkLogger.error(this, message);
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.SEVERE, message, logRecord);
    }

    @Test
    public void shouldLogAnErrorMessageWithACause() {
        Throwable cause = new Exception();
        this.jdkLogger.error(this, cause, "The log message");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertSame(cause, logRecord.getThrown());
    }

    @Test
    public void shouldLogAFormattedErrorMessage() {
        this.jdkLogger.error(this, "The %s message", "log");
        Assert.assertSize(1, JdkLoggerTest.handler.getLogRecords());
        LogRecord logRecord = JdkLoggerTest.handler.getLogRecords().get(0);
        Assert.assertLogRecord(Level.SEVERE, "The log message", logRecord);
    }

    @Test
    public void shouldNotLogAnErrorMessageWhenErrorIsNotEnabled() {
        JdkLoggerTest.logger.setLevel(Level.OFF);
        this.jdkLogger.error(this, "The log message");
        Assert.assertSize(0, JdkLoggerTest.handler.getLogRecords());
    }

    public static class TestHandler extends Handler {

        private ArrayList<LogRecord> logRecords = new ArrayList<LogRecord>();

        public TestHandler() {
        }

        public void reset() {
            this.logRecords.clear();
        }

        public List<LogRecord> getLogRecords() {
            return this.logRecords;
        }

        public void publish(LogRecord logRecord) {
            this.logRecords.add(logRecord);
        }

        public void close() {
        }

        public void flush() {
        }
    }
}
