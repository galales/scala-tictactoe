package com.galales.tictactoe.services.ai

import com.galales.tictactoe.models.{Board, Move, Player}

object SimpleAI extends AIService {

  // Just tries to put marks in the best place to win
  // Steps:
  // 1. Look for a combinations where AI has 2 marks and the third is empty
  // 2. Look for a combinations where AI has 1 mark2 and the others are empty
  // 3. Look for a combinations where AI has all 3 spots are empty
  // 4. No hope to win, just make some random move

  override def makeMove(board: Board) : Move = {
    val moves = (2 to 0 by -1).flatMap(getAvailableCombinations(board, _)).map(c => Move(c.head._1, c.head._2))

    if(moves.isEmpty) {
      MonkeyAI.makeMove(board)
    } else {
      moves(0)
    }
//    getAvailableCombinations(board, 2) match {
//      case Some(v) => Move(v.head._1, v.head._2)
//      case None => getAvailableCombinations(board, 1) match {
//        case Some(v) => Move(v.head._1, v.head._2)
//        case None =>getAvailableCombinations(board, 0) match {
//          case Some(v) => Move(v.head._1, v.head._2)
//          case None => // No hope to win, just make some random move
//            MonkeyAI.makeMove(board)
//        }
//      }
//    }

  }

  private def getAvailableCombinations(board: Board, aiMarks: Int): Option[List[(Int, Int)]] = ???

}
