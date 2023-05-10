package studies.wsi.tictactoe
package strategies
import game.Game

import scala.util.Random

class RandomStrategy extends TTTStrategy {
  override def getBestMove(game: Game): (Int, Int) = {
    val row = Random.nextInt(5)
    val col = Random.nextInt(5)

    (row, col)
  }
}
