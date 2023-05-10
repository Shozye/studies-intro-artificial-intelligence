package studies.wsi.tictactoe

import bot.Bot
import game.Player
import strategies.{RandomLegalStrategy, TTTStrategy}

import java.net.ConnectException

object Runner {
  def runSingleBot(ip: String, port: Int, player: Int, depth: Int, strategy: TTTStrategy): Unit = {
    println(s""" Config: ip=$ip port=$port player$player depth=$depth""")
    try {
      Bot.run(ip, port, player, depth, strategy)
    } catch {
      case _: ConnectException => println("Socket refused connection")
    }
  }

  private class RunThread(ip:String, port: Int, player: Int, depth: Int) extends Runnable {
    override def run(): Unit = {
      runSingleBot(ip, port, player, depth, RandomLegalStrategy())
    }
  }


  def runTwoBots(ip: String, port: Int, depth: Int): Unit = {
    val threads: Seq[Thread] = for {
      player <- 1 until 3
    } yield {
      Thread(RunThread(ip, port, player, depth))
    }
    threads.foreach(thread => thread.start())
    threads.foreach(thread => thread.join())
  }

}
