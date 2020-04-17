Luke Cole Advanced Programming Task 2

Scala is a powerful language that runs on the JVM, far more expressive and offers more concise code than Java. 
I have taken a functional programming approach where most things are immutable, which offers less memory need, higher efficiency and removes the risk of null pointers or OOM exceptions if done correctly. Var's should only be used in a local context and defaulted, this is where null pointers can become a problem, otherwise use val's for everything to be set to immutable.
This is a Scala based solution, to run, you require:
-	java version "1.8.0_221"
-	Java(TM) SE Runtime Environment (build 1.8.0_221-b11)

In scala, there is no main method like Java. All applications must run from an Object that extends App (which runs the application in the main thread).
Ensure you have the latest JDK and JRE as seen above, as well as the latest version of Scala. The application requires the JVM as it requires both the JVM’s compiler and runtime environment. Scala, like Java runs on the JVM and is compiled to Java byte code. Both Classes, extend App which is Scala’s equivalent of the main method and allows you to run that inside its own runtime environment, only, Scala treats it like an Object. Both applications run in their own runtime or can be both instantiated in one common ‘run object’ that extends App and instantiates both objects in the same runtime, but for this practice, it was best to go for the micro-service approach.

Please note: Server requires to be started first as client relies on the target port to be in the listening state, in-active server will mean no port can be used to connect to anything, rendering the service useless.

1.	Start up the Server First by right clicking and selecting run against the Server object class;
2.	Then proceed to do the same again to start up Client.
3.	Chat box will appear for the client as a Java application, send messages and go to the server console to see them published.
