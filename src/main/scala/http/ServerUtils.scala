package http

import java.io.{Closeable, PrintStream}
import java.net.Socket

trait ServerUtils {
  /*
   in scala, a trait is a Interface that can be treated like an object.
   All classes extending trait will be able to abstract the def's and values defined here
   and provide a concrete implementation in sub members.
   */
  val host = "localhost"
  val serverPort: Int
  def createConnection(hostname: String, serverPort: Int): Closeable //socket and serverSocket both extend closeable, which made implementing in both sub classes easier.
  def getPrintStream(socket: Socket): PrintStream //stream buffer to print to.
  def readFromStream(socket: Socket): Any //Any is the highest type in the type system. It's the highest upper bound found in the object reference in Scala. Any reference is different to primitive data types.
}
