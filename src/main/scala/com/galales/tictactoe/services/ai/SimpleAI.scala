package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move, Player}

object SimpleAI extends AIService {

  // Just tries to put marks in the best place to win
  // It looks for the spot where it could win with less moves
  // If there's no hope to win, just make some random move

  override def makeMove(board: Board) : Move = {
    val moves = (2 to 0 by -1).flatMap(getPotentiallyWinningCoordinates(board, _)).map(c => Move(c._1, c._2))

    if(moves.isEmpty) {
      MonkeyAI.makeMove(board)
    } else {
      moves(0)
    }

  }

  private def getPotentiallyWinningCoordinates(board: Board, aiMarksCount: Int): List[(Int, Int)] = {

    val rowsCombinations = searchWinningCoordinates(List.range(0, 3), board.getRow, aiMarksCount)
    val columnsCombinations = searchWinningCoordinates(List.range(0, 3), board.getColumn, aiMarksCount).map(t => (t._2, t._1))
    val diagonalsCombinations = searchWinningCoordinates(List(true, false), board.getDiagonal, aiMarksCount).map(t => (t._2, if(t._1) t._2 else 2 - t._2))

    rowsCombinations ::: columnsCombinations ::: diagonalsCombinations
  }

  private def searchWinningCoordinates[T](range: List[T], extractor: T => List[Option[Player.Value]], aiMarksCount: Int): List[(T, Int)] = {
    val winningCoordinates = for(
      r <- range
      if extractor(r).count(_.contains(Player.ai)) == aiMarksCount && extractor(r).count(_.isEmpty) == 3 - aiMarksCount
    ) yield for(r2 <- List.range(0, 3) if extractor(r)(r2).isEmpty) yield(r, r2)

    winningCoordinates.flatten
  }

}
