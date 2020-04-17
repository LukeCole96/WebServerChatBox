package http.server

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.{ServerSocket, Socket}
import http.ServerUtils

object Server extends ServerUtils with App {
  override val serverPort: Int = 8080 //creates a server port
  override val host: String = ""
  try {
    val server = createConnection(hostname = host, serverPort = serverPort)//creates a socket that exposes the port
    val clientAccept = server.accept //listens for a connection to be made to the socket to accept connections.

    println("Server initialized......") //initial message.

    while(clientAccept.isConnected) { //while the server is connected to a client connection keep running the below.
      val inputStream = readFromStream(clientAccept)
      val outputStream = getPrintStream(clientAccept)

      val myInput = scala.io.StdIn.readLine()
      outputStream.println(myInput)

      inputStream match { //pattern matching is very powerful in scala, it is used to match in certain areas to control logic flow, similar to a switch/select case statement.
        case Some("Disconnect") => clientAccept.close; server.close; println("Server shutting down...") //Some is an option object, which means if some( value is here ) then continue; otherwise;
        case Some(value) => println(s"Server received a message: ${value.toString}"); // continue to the next case, if that isn't satisfied, continue...
          outputStream.flush;
        case _ => println("Something went wrong... Still open for connection though.") // a wildcard is used to say, any other case do this. Ideal for exception handling.
      }
    }
  }
  catch { //catch any exception, and cause the system to shut down.
    case err: Exception => {
      println(s"SHUTTING DOWN SERVER AS ERROR FOUND: ${err.getMessage}")
      System.exit(1) // status code 1 is a sigterm to tell the system to kill all processes
    }
  }

  //implemented methods from trait class
  def createConnection(hostname: String, serverPort: Int): ServerSocket  = {
    if(hostname.isEmpty) {
    new ServerSocket(serverPort)
    } else {
      println("No hostname inputted... Creating socket for server...")
      new ServerSocket(8181)
    }
  }

  def getPrintStream(socket: Socket): PrintStream = {
    val outputStream: PrintStream = new PrintStream(socket.getOutputStream)
    outputStream
  }

  def readFromStream(socket: Socket): Some[String] = {
    val inputStream = Some(new BufferedReader(new InputStreamReader(socket.getInputStream)).readLine)
    inputStream
  }
}

