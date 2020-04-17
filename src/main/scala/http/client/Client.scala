package http.client

import java.io.PrintStream
import java.net.{InetAddress, Socket}
import http.ServerUtils
import scala.io.BufferedSource
import scala.swing.event.KeyTyped
import scala.swing.{Action, BorderPanel, Button, Dimension, FlowPanel, MainFrame, TextField}

object Client extends MainFrame with ServerUtils with App {
  title = "University HTTP Client"
  preferredSize = new Dimension(500, 500) //builds GUI to click and send and close buttons

  override val serverPort: Int = 8080
  val socket = createConnection("localhost", serverPort)

  val inputStream = readFromStream(socket)
  val outputStream = getPrintStream(socket)

  println("Client initialized......") //initial message.

  contents = new BorderPanel //create a gui
  {
    var rating = ""
    add(new FlowPanel //add a panel box to organize content horizontally on the GUI.
    {
      contents += new TextField(6) //button to send messages, appended to sequence of contents
      {
        listenTo(keys) // A reactor to listen to publisher events, in this case key inputs
        reactions += { case input: KeyTyped => //validates the input as a KeyTyped instance
            rating = input.char.toString //only store the numerical chars across
        }
      }
      contents += new Button(new Action("Send") //button to send messages
      {
        def apply //apply method acts as a constructor, it implicitly is called to construct a scala object (Scala has a different type structure to other languages, statically typed)
        {
          outputStream.println(rating) //apply the message when the send button has instantiated an action.
          outputStream.flush
        }
      })

      contents += new Button(new Action("Close")  //button to close the application, sends a disconnect message utilising the apply method explained above.
      {
        def apply
        {
          outputStream.println("Disconnect")
          outputStream.flush
          socket.close //closes the socket
        }
      })
    }, BorderPanel.Position.Center)
  }

  pack //causes the interface to scale to the preferred size of the interface and subcomponents (buttons).
  visible = true //makes the gui visible to the user.

  //implemented methods from trait class
  def createConnection(hostname: String, serverPort: Int): Socket = {
    val socket = new Socket(InetAddress.getByName(host), serverPort) //listens to the server host e.g. http://localhost:8080
    socket //return socket
  }

  def getPrintStream(socket: Socket): PrintStream = {
    val outputStream: PrintStream = new PrintStream(socket.getOutputStream) //output stream to be able to print out messages and flush the buffer.
    outputStream
  }

  def readFromStream(socket: Socket): Iterator[String] = {
    val inputStream: Iterator[String] = new BufferedSource(socket.getInputStream).getLines //creates an input stream to get message.
    inputStream
  }
}

