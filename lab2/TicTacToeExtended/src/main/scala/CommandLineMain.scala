package studies.wsi.tictactoe

import bot.Bot
import communication.{Client, Protocol}
import game.Game
import strategies.{RandomLegalStrategy, TTTStrategy}

import java.net.ConnectException

object CommandLineMain {

  def main(args: Array[String]): Unit = {
    Runner.runSingleBot(
      args(0),
      args(1).toInt,
      args(2).toInt,
      args(3).toInt,
      RandomLegalStrategy()
    )
  }

}