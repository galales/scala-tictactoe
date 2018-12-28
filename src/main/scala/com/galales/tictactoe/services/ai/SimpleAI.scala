package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move, Player}

object SimpleAI extends AIService {

  // Just tries to put marks in the best place to win
  // It looks for the spot where it could win with fewer moves
  // If there's no hope to win, just make some random move

  override def makeMove(board: Board) : Move = {
    val moves = (board.size - 1 to 0 by -1).flatMap(getPotentiallyWinningCoordinates(board, _, Player.ai)).map(c => Move(c._1, c._2))

    if(moves.isEmpty) {
      MonkeyAI.makeMove(board)
    } else {
      moves.head
    }

  }

  private[ai] def getPotentiallyWinningCoordinates(board: Board, playerMarksCount: Int, player: Player.Value): List[(Int, Int)] = {

    val rowsCombinations = searchWinningCoordinates(List.range(0, board.size), board.getRow, playerMarksCount, player, board.size)
    val columnsCombinations = searchWinningCoordinates(List.range(0, board.size), board.getColumn, playerMarksCount, player, board.size).map(t => (t._2, t._1))
    val diagonalsCombinations = searchWinningCoordinates(List(true, false), board.getDiagonal, playerMarksCount, player, board.size).map(t => (t._2, if(t._1) t._2 else board.size - 1 - t._2))

    rowsCombinations ::: columnsCombinations ::: diagonalsCombinations
  }

  /**
    * Search available spots that could lead to a win.
    * The row/columns/diagonal must already have the number of Player marks requested
    * @param range Range required to extract coordinates
    * @param extractor Function to extract row/column/diagonal coordinates
    * @param playerMarksCount Number of Player marks that the row/column/diagonal must already have
    * @param player Player to search
    * @tparam T Type of range elements
    * @return The list of possible moves
    */
  private def searchWinningCoordinates[T](range: List[T], extractor: T => List[Option[Player.Value]], playerMarksCount: Int, player: Player.Value, boardSize: Int): List[(T, Int)] = {
    val winningCoordinates = for(
      r <- range
      if extractor(r).count(_.contains(player)) == playerMarksCount && extractor(r).count(_.isEmpty) == boardSize - playerMarksCount
    ) yield for(r2 <- List.range(0, boardSize) if extractor(r)(r2).isEmpty) yield(r, r2)

    winningCoordinates.flatten
  }
}
