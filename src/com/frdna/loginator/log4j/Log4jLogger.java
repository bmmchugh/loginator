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

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.frdna.loginator.AbstractLogger;
import com.frdna.loginator.Io;
import com.frdna.loginator.LoginatorException;

public class Log4jLogger extends AbstractLogger {

    public static boolean initialize() {
        if (Log4jLogger.class.getClassLoader().getResource("log4j.properties")
                != null) {
            return true;
        }

        if (System.getProperty("log4j.configuration") != null) {
            return true;
        }

        if (System.getProperty("log4j.configurationClass") != null) {
            return true;
        }

        String configFile = System.getProperty("loginator.log4j.configuration");
        if (configFile != null) {
            return Log4jLogger.initialize(configFile);
        }

        return false;
    }

    public static boolean initialize(String configFile) {
        InputStream inputStream = Io.openInputStream(configFile);

        if (inputStream == null) {
            return false;
        }

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            throw new LoginatorException("Unable configure Log4J ", e);
        } finally {
            Io.close(inputStream);
        }

        return true;
    }

    protected boolean isLevelEnabled(Class<?> source, Level level) {
        return this.getLogger(source).isEnabledFor(
                this.getLog4jLevel(level));
    }

    protected void log(
        Level level,
        Class<?> source,
        Throwable cause,
        String message) {

        Logger log4jLogger = this.getLogger(source);
        org.apache.log4j.Level log4jLevel = this.getLog4jLevel(level);

        log4jLogger.log(log4jLevel, message, cause);
    }

    private Logger getLogger(Class<?> source) {
        return Logger.getLogger(source);
    }

    private org.apache.log4j.Level getLog4jLevel(Level level) {

        switch (level) {
            case TRACE:
                return org.apache.log4j.Level.TRACE;
            case DEBUG:
                return org.apache.log4j.Level.DEBUG;
            case INFO:
                return org.apache.log4j.Level.INFO;
            case WARN:
                return org.apache.log4j.Level.WARN;
            case ERROR:
                return org.apache.log4j.Level.ERROR;
            default:
                throw new LoginatorException("Unhandled log level " + level);
        }
    }
}
