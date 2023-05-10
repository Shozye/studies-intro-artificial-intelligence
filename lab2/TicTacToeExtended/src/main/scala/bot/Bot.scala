package studies.wsi.tictactoe
package bot

import communication.{Client, Protocol}
import game.Game
import strategies.TTTStrategy

object Bot {
  def run(ip: String, port: Int, player: Int, depth: Int, strategy: TTTStrategy): Unit = {
    val client = Client.fromIP(ip, port)
    Protocol.startConnection(client, player)
    val game = Game.Empty()
    var msg: String = ""
    while {
      msg = client.getMessage
      !Protocol.isTerminateMessage(msg)
    } do {

      if !Protocol.isFirstMoveMessage(msg) then {
        // it means that it is not the first move and i need to update game
        game.makeMove(Protocol.unpackMove(msg))
      }

      val currentTimestamp = System.currentTimeMillis()
      val move = strategy.getBestMove(game)
      game.makeMove(move)
      Protocol.sendMove(client, move)
      println(s"Move took ${(System.currentTimeMillis() - currentTimestamp)}ms")
    }
    // Protocol.handleTerminationMessage(msg)
  }
}
