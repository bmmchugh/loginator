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
package com.frdna.loginator;

import com.frdna.loginator.dummy.DummyLogger;
import com.frdna.loginator.jdk.JdkLogger;

public class Log {

    private static Logger logger = null;

    static {

        try {

            if (JdkLogger.initialize()) {
                Log.logger = new JdkLogger();
            }

        } catch (LoginatorException e) {
            System.err.println(
                    "Logging could not be initialized " + e.getMessage());
        }

        if (Log.logger == null) {
            Log.logger = new DummyLogger();
        }
    }

    private Log() { }

    /**
     * Logs a trace message if trace logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void trace(Object source, String message, Object... args) {
        Log.logger.trace(source, message, args);
    }

    /**
     * Logs a trace message if trace logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void trace(Class<?> source, String message, Object... args) {
        Log.logger.trace(source, message, args);
    }

    /**
     * Logs a debug message if debug logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void debug(Object source, String message, Object... args) {
        Log.logger.debug(source, message, args);
    }

    /**
     * Logs a debug message if debug logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void debug(Class<?> source, String message, Object... args) {
        Log.logger.debug(source, message, args);
    }

    /**
     * Logs an info message if info logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void info(Object source, String message, Object... args) {
        Log.logger.info(source, message, args);
    }

    /**
     * Logs an info message if info logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void info(Class<?> source, String message, Object... args) {
        Log.logger.info(source, message, args);
    }

    /**
     * Logs a warn message if warn logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void warn(Object source, String message, Object... args) {
        Log.logger.warn(source, message, args);
    }

    /**
     * Logs a warn message if warn logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void warn(Class<?> source, String message, Object... args) {
        Log.logger.warn(source, message, args);
    }

    /**
     * Logs a warn message if warn logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param cause
     *     The cause of the message that should be logged.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void warn(
            Object source, Throwable cause, String message, Object... args) {
        Log.logger.warn(source, cause, message, args);
    }

    /**
     * Logs a warn message if warn logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param cause
     *     The cause of the message that should be logged.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void warn(
            Class<?> source, Throwable cause, String message, Object... args) {
        Log.logger.warn(source, cause, message, args);
    }

    /**
     * Logs an error message if error logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void error(Object source, String message, Object... args) {
        Log.logger.error(source, message, args);
    }

    /**
     * Logs an error message if error logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void error(Class<?> source, String message, Object... args) {
        Log.logger.error(source, message, args);
    }

    /**
     * Logs an error message if error logging is enabled.
     *
     * @param source
     *     The source of the message.
     * @param cause
     *     The cause of the message that should be logged.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void error(
            Object source, Throwable cause, String message, Object... args) {
        Log.logger.error(source, cause, message, args);
    }

    /**
     * Logs an error message if error logging is enabled.
     *
     * @param source
     *     The source class of the message.
     * @param cause
     *     The cause of the message that should be logged.
     * @param message
     *     The log message.  This message may be formatted.
     * @param args
     *     Optional arguments for the formatted message.
     * @see String#format
     */
    public static void error(
            Class<?> source, Throwable cause, String message, Object... args) {
        Log.logger.error(source, cause, message, args);
    }
}
