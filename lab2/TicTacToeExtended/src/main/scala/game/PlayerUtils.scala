package studies.wsi.tictactoe
package game

import game.Player.{NONE, O, X}

object PlayerUtils {
  def fromInt(num: Int): Player = {
    num match
      case 1 => Player.X
      case 2 => Player.O
      case _ => Player.NONE

  }

  def opposite(player: Player): Player = {
    player match
      case X => O
      case O => X
      case NONE => NONE
  }
}
