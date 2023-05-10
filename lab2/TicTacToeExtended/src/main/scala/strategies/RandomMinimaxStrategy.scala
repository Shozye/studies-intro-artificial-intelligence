package studies.wsi.tictactoe
package strategies

import game.{Game, Player}
import strategies.minimax.{Goal, Variation}

import scala.util.Random

class RandomMinimaxStrategy(val goal: Goal, val start_depth: Int) extends TTTStrategy {

  def minimax(game: Game, move: (Int, Int), depth: Int, maximizingPlayer: Boolean): Variation = {
    val winner = game.isTerminalBest(move)

    if (winner != Player.NONE) {
      Variation(goal.calculateTerminal(winner)*(depth+1), (-1, -1))
    } else if (depth == 0) {
      Variation(goal.calculate(game), (-1, -1))
    } else {
      val temp = game
        .children()
        .map(move =>
          game.makeMove(move)
          val value = minimax(game, move, depth - 1, !maximizingPlayer).value
          game.removeMove(move)
          Variation(value, move)
        )
      if (maximizingPlayer) {
        temp.maxBy(variation => variation.value)
      } else {
        temp.minBy(variation => variation.value)
      }

    }
  }

  override def getBestMove(game: Game): (Int, Int) = {
    val best_variation = minimax(game, (-1, -1), start_depth, true)
    println(s"Picking ${best_variation.toString}")
    best_variation.move
  }
}