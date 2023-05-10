package studies.wsi.tictactoe
package strategies

import game.Game

trait TTTStrategy {
  def getBestMove(game: Game): (Int, Int)
}
