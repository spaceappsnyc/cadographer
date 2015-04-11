Java Web Starter 

This Java Web Starter application demonstrates how to use IBM Data Cache Java Native APIs in a Java Web application and deploy it on Bluemix.

Files

The Java Web Starter application contains the following contents:

*   cacheSampleJavaNativeAPIs.war

    This WAR file is actually the application itself. It is the only file that'll be pushed and run on the Bluemix. Every time your application code is updated, you'll need to regenerate this WAR file and push to Bluemix again. See the next section on detailed steps.
    
*   WebContent/

    This directory contains the client side code (HTML/CSS/JavaScript) of your application as well as compiled server side java classes and necessary JAR libraries.
    
*   src/

    This directory contains the server side code (JAVA) of your application.
    
*   build.xml

    This file allows you to easily build your application using Ant.