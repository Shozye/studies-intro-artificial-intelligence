package studies.wsi.tictactoe

import game.{Player, PlayerUtils}
import strategies.{MinimaxABStrategy, RandomLegalStrategy, RandomMinimaxStrategy, RandomStrategy, TTTStrategy}
import strategies.minimax.Goal

object SimulationMain {
  private def getStrategy(args: Array[String], player: Player, start_depth: Int): TTTStrategy = {
    args(0) match {
      case "random" => RandomStrategy()
      case "random-legal" => RandomLegalStrategy()

      case "minimax-random" =>
        val heuristic_arguments = args.slice(1, args.length).map(weight => weight.toInt)
        RandomMinimaxStrategy(Goal(heuristic_arguments.toList, player), start_depth)

      case "minimax-random-ab" =>
        val heuristic_arguments = args.slice(1, args.length).map(weight => weight.toInt)
        MinimaxABStrategy(Goal(heuristic_arguments.toList, player), start_depth)
    }

  }

  def main(args: Array[String]): Unit = {
    val ip = args(0)
    val port = args(1).toInt
    val player = args(2).toInt
    val depth = args(3).toInt

    Runner.runSingleBot(
      ip,
      port,
      player,
      depth,
      getStrategy(
        args.slice(4, args.length),
        PlayerUtils.fromInt(player),
        depth
      )
    )
  }

}
