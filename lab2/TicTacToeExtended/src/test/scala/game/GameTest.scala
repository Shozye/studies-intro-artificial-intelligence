package studies.wsi.tictactoe
package game

import collection.mutable.Stack
import Player.*
import org.scalatest.funsuite.AnyFunSuite

class GameTest extends AnyFunSuite {

  test("Board.isTerminal lose horizontal") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            O, NONE, NONE, NONE, NONE,
            O, NONE, NONE, NONE, NONE,
            O, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == X)
  }
  test("Board.isTerminal lose diagonal") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, NONE, NONE, NONE, NONE,
            NONE, X, NONE, NONE, NONE,
            NONE, NONE, X, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == O)
  }
  test("Board.isTerminal lose vertical") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, X, X, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == O)
  }

  test("Board.isTerminal win horizontal") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, NONE, NONE, NONE, NONE,
            X, NONE, NONE, NONE, NONE,
            X, NONE, NONE, NONE, NONE,
            X, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == X)
  }
  test("Board.isTerminal win diagonal") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, NONE, NONE, NONE, NONE,
            NONE, X, NONE, NONE, NONE,
            NONE, NONE, X, NONE, NONE,
            NONE, NONE, NONE, X, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == X)
  }
  test("Board.isTerminal win vertical") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, X, X, X, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE
          )
        )
      )
    assert(game.isTerminal == X)
  }
  test("Board.isTerminal complex") {
    val game =
      Game(
        Player.X,
        Board(
          List(
            X, O, O, X, X,
            X, NONE, NONE, NONE, NONE,
            X, NONE, NONE, NONE, NONE,
            NONE, NONE, O, NONE, O,
            NONE, NONE, O, NONE, NONE)
        )
      )
    assert(game.isTerminal == X)
  }
}