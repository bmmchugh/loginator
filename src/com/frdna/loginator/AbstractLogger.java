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

public abstract class AbstractLogger implements Logger {

    protected enum Level {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    protected abstract boolean isLevelEnabled(Class<?> source, Level level);

    protected abstract void log(
            Level level,
            Class<?> source,
            Throwable cause,
            String message);

    protected void log(
            Level level,
            Class<?> source,
            Throwable cause,
            String message,
            Object... args) {
        if (!this.isLevelEnabled(source, level)) {
            return;
        }

        String logMessage = message;

        if (args.length > 0) {
            logMessage = String.format(message, args);
        }

        this.log(level, source, cause, logMessage);
    }

    protected void log(
            Level level,
            Class<?> source,
            String message,
            Object... args) {
        this.log(level, source, null, message, args);
    }

    public void trace(Class<?> source, String message, Object... args) {
        this.log(AbstractLogger.Level.TRACE, source, message, args);
    }

    public void trace(Object source, String message, Object... args) {
        this.trace(source.getClass(), message, args);
    }

    public void debug(Class<?> source, String message, Object... args) {
        this.log(AbstractLogger.Level.DEBUG, source, message, args);
    }

    public void debug(Object source, String message, Object... args) {
        this.debug(source.getClass(), message, args);
    }

    public void info(Class<?> source, String message, Object... args) {
        this.log(AbstractLogger.Level.INFO, source, message, args);
    }

    public void info(Object source, String message, Object... args) {
        this.info(source.getClass(), message, args);
    }

    public void warn(Class<?> source,
            Throwable cause,
            String message,
            Object... args) {
        this.log(AbstractLogger.Level.WARN, source, cause, message, args);
    }

    public void warn(Object source,
            Throwable cause,
            String message,
            Object... args) {
        this.warn(source.getClass(), cause, message, args);
    }

    public void warn(Class<?> source,
            String message,
            Object... args) {
        this.log(AbstractLogger.Level.WARN, source, message, args);
    }

    public void warn(Object source,
            String message,
            Object... args) {
        this.warn(source.getClass(), message, args);
    }

    public void error(Class<?> source,
            Throwable cause,
            String message,
            Object... args) {
        this.log(AbstractLogger.Level.ERROR, source, cause, message, args);
    }

    public void error(Object source,
            Throwable cause,
            String message,
            Object... args) {
        this.error(source.getClass(), cause, message, args);
    }

    public void error(Class<?> source,
            String message,
            Object... args) {
        this.log(AbstractLogger.Level.ERROR, source, message, args);
    }

    public void error(Object source,
            String message,
            Object... args) {
        this.error(source.getClass(), message, args);
    }
}
