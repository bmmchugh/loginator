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

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.frdna.loginator.AbstractLogger;
import com.frdna.loginator.Io;
import com.frdna.loginator.LoginatorException;

public class JdkLogger extends AbstractLogger {

    public static boolean initialize() {
        String configFile = System.getProperty("loginator.jdk.configuration");
        if (configFile != null) {
            return JdkLogger.initialize(configFile);
        }

        // Use default configuration
        return true;
    }

    public static boolean initialize(String configFile) {

        InputStream inputStream = Io.openInputStream(configFile);

        if (inputStream == null) {
            return false;
        }

        try {
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (Exception e) {
            throw new LoginatorException(
                    "Unable to configure JdkLogger from " + configFile, e);
        } finally {
            Io.close(inputStream);
        }

        return true;
    }

    protected boolean isLevelEnabled(Class<?> source, Level level) {
        return this.getLogger(source).isLoggable(
                this.getJdkLevel(level));
    }

    protected void log(
            Level level,
            Class<?> source,
            Throwable cause,
            String message) {

        Logger jdkLogger = this.getLogger(source);
        java.util.logging.Level jdkLevel   = this.getJdkLevel(level);

        if (cause == null) {
            jdkLogger.log(jdkLevel, message);
        } else {
            jdkLogger.log(jdkLevel, message, cause);
        }
    }

    private Logger getLogger(Class<?> source) {
        return this.getLogger(source.getName());
    }

    private Logger getLogger(String name) {
        return Logger.getLogger(name);
    }

    private java.util.logging.Level getJdkLevel(Level level) {

        switch (level) {
            case TRACE:
                return java.util.logging.Level.FINER;
            case DEBUG:
                return java.util.logging.Level.FINE;
            case INFO:
                return java.util.logging.Level.INFO;
            case WARN:
                return java.util.logging.Level.WARNING;
            case ERROR:
                return java.util.logging.Level.SEVERE;
            default:
                throw new LoginatorException("Unhandled log level " + level);
        }
    }
}
