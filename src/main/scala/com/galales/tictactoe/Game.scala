package com.galales.tictactoe

import com.galales.tictactoe.models.{Board, Player}
import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.services.interaction.InteractionService
import com.galales.tictactoe.services.ai.AI
import com.galales.tictactoe.services.ui.UserInterfaceService

object Game {

  def getGameResult(board: Board) : GameResult.Value = {

    val singleValueInCombinations = List(
      (0 to 2).map(board.getRow).filter(_.distinct.length == 1),
      (0 to 2).map(board.getColumn).filter(_.distinct.length == 1),
      List(true, false).map(board.getDiagonal).filter(_.distinct.length == 1),
    ).flatten.flatMap(_.head)

    val winner = if(singleValueInCombinations.nonEmpty) singleValueInCombinations.head else None

    winner match {
      case Player.user => GameResult.userWin
      case Player.ai => GameResult.aiWin
      case _ => if(board.isComplete) GameResult.draw else GameResult.stillGoing
    }
  }

  def play(board: Board, ai: AI, userInterface: UserInterfaceService, userInteract: InteractionService, isUserTurn: Boolean) : GameResult.Value = {

    if(isUserTurn) {
      // User Turn
      userInterface.printBoard(board)
      board.execMove(userInteract.makeMove, Player.user)
    } else {
      // AI Turn
      board.execMove(ai.makeMove(board), Player.ai)
    }

    val result = getGameResult(board)

    if(result != GameResult.stillGoing) {
      result
    } else {
      play(board, ai, userInterface, userInteract, !isUserTurn)
    }

  }
}
