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

import java.io.Closable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class Io {

    private Io() { }

    public static InputStream openInputStream(String location) {

        InputStream inputStream =
            Io.class.getClassLoader().getResourceAsStream(location);

        if (inputStream != null) {
            return inputStream;
        }

        try {
            URL configUrl = new URL(location);
            return configUrl.openStream();
        } catch (MalformedURLException e) {
            // Ignore, the config file string is not a valid URL
        } catch (IOException e) {
            // The config file was a valid URL but it could not be read
            throw new LoginatorException(
                    "Unable to read configuration from URL " + location, e);
        }

        try {
            return new FileInputStream(location);
        } catch (FileNotFoundException e) {
            // Ignore
        }

        throw new LoginatorException(
                "Unable to find configuration file " + location);
    }

    public static void close(Closable closable) {
        if (closable == null) {
            return;
        }

        try {
            closable.close();
        } catch (IOException e) {
            // Ignore
        }
    }
}
