Luke Cole Advanced Programming Task 2

Due to my Windows work laptop not compiling C++ projects, I will be doing this in Scala instead.

Scala is a powerful language that runs on the JVM, far more expressive and offers more concise code than Java.

I have taken a functional programming approach where most things are immutable, which offers less memory need, higher efficiency and removes the risk of null pointers or OOM exceptions if done correctly. Var's should only be used in a local context and defaulted, this is where null pointers can become a problem, otherwise use val's for everything to be set to immutable.

This is a Scala based solution, to run, you require:
- java version "1.8.0_221"
- Java(TM) SE Runtime Environment (build 1.8.0_221-b11)

In scala, there is no main method like Java. All applications must run from an Object that extends App (which runs the application in the main thread).

In this instance, you will be required to start the Server first, followed by starting the client.

The client will open a GUI, a text box is used to input a message, send to publish to the server, and finally close that sends a disconnection message to the server to shut it down.

From the server side, the server console should allow the user to input in the console and send a message back to Client, from a console perspective.

