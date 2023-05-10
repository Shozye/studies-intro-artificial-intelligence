package studies.wsi.tictactoe
package strategies.minimax

import game.Player.{NONE, O, X}
import game.{Game, Player}

case class Goal(weights: List[Int], val maximizingPlayer: Player) {
  val heuristics: Seq[Int] = List[Int]()

  def calculate(game: Game): Int = {
      heuristics
        .zip(weights)
        .map((a, b) => a * b)
        .sum
  }
  
  def calculateTerminal(winner: Player): Int = {
    10_000_000 * (if winner == maximizingPlayer then 1 else -1)
  }
}
