package studies.wsi.puzzle

import board.Board
import game.SearchResult.Failure
import game.{SearchResult, Solver}
import heuristics.*
import neighbourings.SingleMoveNeighbouring

object Main extends App{
  val heurs: Vector[Heuristic] = Vector(
    MisplacedTilesHeuristics,
    ManhattanDistanceHeuristics,
    ManhattanLinearConflictsHeuristics,
    ManhattanUpgraded
  )
  run_tests(MManhattanLinearConflictsHeuristics)

  //for heur <- heurs do run_tests(heur)

  private def run_tests(heuristic: Heuristic): Unit = {
    println(s"Running tests for ${heuristic.toString}")
    for (filename <- Vector(
      "simple10", "simple26",
      "easy24", "easy36",
      "medium38", "medium48",
      "hard58", "hard60"
    )) do {
      print(s"$filename: ")
      run(Board.fromResource(filename), heuristic)
    }

  }

  private def run(board: Board, heuristics: Heuristic): Unit = {
    val Result = Solver.solve(
      board,
      heuristics,
      SingleMoveNeighbouring
    )
    Result match
      case SearchResult.Success(distance, path, visited, time) =>
        report(board, distance, path, visited, time)
      case Failure => println(s"${board.board.toString()} not solvable")
  }

  private def report(board: Board, distance: Int, path: Seq[(Int, Int)], visited: Int, time: Long): Unit = {
    val timeDouble = time.toDouble
    print(s"For ${board.board.toString()} ")
    print(s"visited: $visited | ${time}ms => ${math.floor(visited/(timeDouble/1000))}/s ")
    println(s"Path: ${toDirectionString(path)} len=${path.length}")
  }

  private def toDirectionString(path: Seq[(Int, Int)]): String = {
    (for {
      move <- path
    } yield
      move match
        case (num, 0) =>
          (if (num < 0) "U" else "D")* math.abs(num)
        case (0, num) =>
          (if (num < 0) "L" else "R")* math.abs(num)
      ).mkString
  }


}
