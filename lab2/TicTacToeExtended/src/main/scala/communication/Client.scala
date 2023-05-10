package studies.wsi.tictactoe
package communication

import java.net.{ConnectException, Socket}
import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import scala.util.Try

class Client(
  val socket: Socket
) {

  val out: PrintWriter = PrintWriter(socket.getOutputStream, true)
  val in: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream))
  def getMessage: String = {
    val buffer = new Array[Char](16)
    in.read(buffer)
    val text = buffer.mkString("").trim
    println(s"""Received server message: $text""")
    text
  }
  def sendMessage(msg: String): Unit = {
    println(s"Sending message to server: $msg")
    out.println(msg)
    out.flush()
  }
}

object Client{
  def fromIP(host: String, port: Int): Client = {
      val socket = Socket(host, port)
      Client(socket)
  }
}
