package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move, Player}

object SimpleAI extends AIService {

  // Just tries to put marks in the best place to win
  // It looks for the spot where it could win with less moves
  // If there's no hope to win, just make some random move

  override def makeMove(board: Board) : Move = {
    val moves = (2 to 0 by -1).flatMap(getAvailableCombinations(board, _)).map(c => Move(c._1, c._2))

    if(moves.isEmpty) {
      MonkeyAI.makeMove(board)
    } else {
      moves(0)
    }

  }

  private def getAvailableCombinations(board: Board, aiMarks: Int): List[(Int, Int)] = {
    val rowsCombinations = for(
      row <- List.range(0, 3)
      if board.getRow(row).count(_ == Some(Player.ai)) == aiMarks && board.getRow(row).count(_ == None) == 3 - aiMarks
    ) yield for(column <- List.range(0, 3) if board(row)(column).isEmpty) yield(row, column)

    val columnsCombinations = for(
      column <- List.range(0, 3)
      if board.getColumn(column).count(_ == Some(Player.ai)) == aiMarks && board.getColumn(column).count(_ == None) == 3 - aiMarks
    ) yield for(row <- List.range(0, 3) if board(row)(column).isEmpty) yield(row, column)

    val diagonalsCombinations = for(
      isMainDiagonal <- List(true, false)
      if board.getDiagonal(isMainDiagonal).count(_ == Some(Player.ai)) == aiMarks && board.getDiagonal(isMainDiagonal).count(_ == None) == 3 - aiMarks
    ) yield for(row <- List.range(0, 3) if (isMainDiagonal && board(row)(row).isEmpty) || (!isMainDiagonal && board(row)(2 - row).isEmpty)) yield(row, if(isMainDiagonal) row else 2 - row)

    (rowsCombinations ::: columnsCombinations ::: diagonalsCombinations).flatten
  }

}
