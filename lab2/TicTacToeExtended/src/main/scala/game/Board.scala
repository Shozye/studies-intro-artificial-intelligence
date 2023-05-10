package studies.wsi.tictactoe
package game

class Board(var board: Array[Player]) {
  private val SIZE = 5

  def get(row: Int, col: Int): Player = board(row * SIZE + col)
  def setTile(row: Int, col: Int, player: Player): Unit = board.update(row*SIZE+col, player)
}
