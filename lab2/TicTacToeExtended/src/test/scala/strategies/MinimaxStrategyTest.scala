package studies.wsi.tictactoe
package strategies

import game.Player.*
import game.{Board, Game}

import org.scalatest.funsuite.AnyFunSuite

class MinimaxStrategyTest extends AnyFunSuite  {
  test("Board.isTerminal lose horizontal") {
    val game =
      Game(
        X,
        Board(
          List(
            X, NONE, NONE, NONE, NONE,
            X, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )

  }

}
