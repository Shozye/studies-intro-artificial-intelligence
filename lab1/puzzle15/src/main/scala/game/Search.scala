package studies.wsi.puzzle
package game

import board.Board
import heuristics.Heuristic
import neighbourings.Neighbouring

import scala.collection.mutable
import game.SearchResult

object Search {

  def reconstructPath(
                     parentTree: mutable.HashMap[Board, (Board, (Int, Int))],
                     board: Board
                     ): Seq[(Int, Int)] = {
    var totalPath = Seq[(Int, Int)]()
    var currBoard = board
    while (parentTree.contains(currBoard)) {
      val (parentBoard, path) = parentTree(currBoard)
      totalPath :+= path
      currBoard = parentBoard
    }
    totalPath.reverse
  }


  def aStar(
           start: Board,
           heuristic: Heuristic,
           neighbouring: Neighbouring
           ): SearchResult.Success = {
    val distances = mutable.HashMap[Board, Int]()
    val parent_tree = mutable.HashMap[Board, (Board, (Int, Int))]()
    var visited = 0
    // Initialize the start node
    distances(start) = 0

    object MinOrder extends Ordering[(Int, Board)] {
      def compare(x: (Int, Board), y: (Int, Board)) = y._1 compare x._1
    }

    val pq = mutable.PriorityQueue.empty[(Int, Board)](MinOrder)
    pq.enqueue((heuristic.calculateFromPermutation(start), start))

    // Continue searching until the priority queue is empty
    while (pq.nonEmpty) {
      val (_, currBoard) = pq.dequeue()
      visited += 1


      // If we've found the goal, reconstruct the path and return Success
      if (currBoard.isSolved) {
        val path = reconstructPath(parent_tree, currBoard)
        return SearchResult.Success(distances(currBoard), path, visited)
      }

      // Loop through each neighbor and update the priority queue and distances
      val tentativeDistance: Int = distances.getOrElse(currBoard, Int.MaxValue-1) + 1
      for {
        (neighbor, move): (Board, (Int, Int)) <- neighbouring.getNeighbours(currBoard)
      } {

        // Update the distance if it's lower than the current distance
        if (tentativeDistance < distances.getOrElse(neighbor, Int.MaxValue)) {
          distances(neighbor) = tentativeDistance
          parent_tree(neighbor) = (currBoard, move)
          val fScore = tentativeDistance + heuristic.calculateFromPermutation(neighbor)
          pq.enqueue((fScore, neighbor))
        }
      }
    }

    throw Exception("Not found")
  }

}
