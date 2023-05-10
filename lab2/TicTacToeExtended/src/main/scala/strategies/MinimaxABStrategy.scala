package studies.wsi.tictactoe
package strategies

import game.{Game, Player}
import strategies.minimax.{Goal, Variation}

import scala.util.Random

class MinimaxABStrategy(val goal: Goal, val start_depth: Int) extends TTTStrategy {


  def minimax(game: Game, move: (Int, Int), depth: Int, alpha: Int, beta: Int, maximizingPlayer: Boolean): Variation = {
    val winner = game.isTerminalBest(move)
    var new_alpha = alpha
    var new_beta = beta

    if (winner != Player.NONE) {
      Variation(goal.calculateTerminal(winner)*(depth+1), (-1, -1))
    }
    else if (depth == 0) {
      Variation(goal.calculate(game), (-1, -1))
    }
    else {
      val children = game.children()
      var break = false
      var i = 0
      var value = Variation(if maximizingPlayer then Int.MinValue else Int.MaxValue, (-1, -1))

      while {
        i < children.length && !break
      } do {
        val move = children(i)
        game.makeMove(move)
        val minimax_value = minimax(game, move, depth - 1, new_alpha, new_beta, !maximizingPlayer).value
        game.removeMove(move)

        if (maximizingPlayer) {
          if minimax_value > value.value then value = Variation(minimax_value, move)
          if value.value > beta then break = true
          else new_alpha = Seq(new_alpha, value.value).max
        }
        else {
          if minimax_value < value.value then value = Variation(minimax_value, move)
          if value.value < alpha then break = true
          else new_beta = Seq(new_beta, value.value).min
        }

        i += 1
      }
      value

    }

  }

  override def getBestMove(game: Game): (Int, Int) = {
    val best_variation = minimax(game, (-1, -1), start_depth, Int.MinValue, Int.MaxValue, true)
    println(s"Picking ${best_variation.toString}")
    best_variation.move
  }
}