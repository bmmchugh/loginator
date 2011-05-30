# Loginator

## DESCRIPTION

Simplified logging proxy for Java with support for
[log4j](http://logging.apache.org/log4j/1.2/index.html" "log4j") and
[Java Logging](http://download.oracle.com/javase/6/docs/technotes/guides/logging/overview.html "JDK").

## EXAMPLE

    package example;

    import com.frdna.loginator.Log;

    public class MyExample {

        // Instance method use
        public void doSomething() {
            Log.trace(this, "Getting ready to do some stuff.");
            int count = 0;

            try {
                // Do some stuff for some things.
                // ...
                Log.debug(this, "Did some stuff for %s things.", count);
            } catch (Exception e) {
                Log.error(
                    this,
                    e,
                    "Caught an exception doing some stuff for some things.");
            }

            Log.info(this, "Finished doing things.");
        }

        // Class method use
        public static void doSomeOtherStuff() {
            Log.trace(MyExample.class, "Getting ready to do some other stuff.");

            // Do some other stuff
            // ...

            Log.info(MyExample.class, "You get the idea.");
        }
    }

### Configuring Log4J

Include the [log4j JAR](http://logging.apache.org/log4j/1.2/download.html "log4j Download")
and [log4j.properties](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PropertyConfigurator.html#doConfigure%28java.lang.String,%20org.apache.log4j.spi.LoggerRepository%29 "log4j.properties")
in your classpath.  If you want to specify a configuration, you can use the
"log4j.configuration" or "loginator.log4j.configuration" system property.  If
you want to specify a class to configure log4j, you can use the
"log4j.configurationClass" system property.

### Configuring Java Logging

Java Logging is enabled by default with the configuration in
JAVA_HOME/lib/logging.properties unless a specified configuration can't be read.
Java Logging configuration can be specified by setting the
"java.util.logging.config.file" or "loginator.jdk.configuration" system
property.  A configuration class can be specified using the
"java.util.logging.config.class" system property.

## DEPENDENCIES

  * [Ruby](http://www.ruby-lang.org/ "Ruby") (>1.8.7)
  * [Rake](http://rake.rubyforge.org/ "Rake")
  * ZenTest (optional for the autotest task)
  * Java SE Development Kit (>1.4)
  * JUnit (4.8.2)
  * Checkstyle (5.3)
  * log4j (1.2)

## BUILD

    git clone https://github.com/freerangedata/loginator.git
    cd loginator
    git submodule update --init
    rake dependencies            # if you have curl
    rake                         # build and test

## LICENSE

Copyright (c) 2011 Free Range Data, LLC, released under the MIT license
