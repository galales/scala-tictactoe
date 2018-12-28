package com.galales.tictactoe

import com.galales.tictactoe.models.{Board, Player}
import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.services.interaction.InteractionService
import com.galales.tictactoe.services.ai.AIService
import com.galales.tictactoe.services.ui.UserInterfaceService

object Game {

  def getGameResult(board: Board) : GameResult.Value = {

    val singleValueInCombinations = List(
      (0 until board.size).map(board.getRow).filter(_.distinct.length == 1),
      (0 until board.size).map(board.getColumn).filter(_.distinct.length == 1),
      List(true, false).map(board.getDiagonal).filter(_.distinct.length == 1),
    ).flatten.flatMap(_.head)

    val winner = singleValueInCombinations.headOption.getOrElse(None)

    winner match {
      case Player.user => GameResult.userWin
      case Player.ai => GameResult.aiWin
      case _ => if(board.isComplete) GameResult.draw else GameResult.stillGoing
    }
  }

  def play(board: Board, isUserTurn: Boolean)(implicit ai: AIService, userInterface: UserInterfaceService, userInteract: InteractionService) : GameResult.Value = {

    if(isUserTurn) {
      // User Turn
      userInterface.updateBoard(board)

      board.execMove(userInteract.makeMove(board.size), Player.user) match {
        case Left(_) =>
          userInterface.outputErrorMessage("Move not allowed, please retry\n")
          play(board, isUserTurn)
        case Right(_) =>
      }
    } else {
      // AI Turn
      board.execMove(ai.makeMove(board), Player.ai)
    }

    getGameResult(board) match {
      case GameResult.stillGoing => play(board, !isUserTurn)
      case r =>
        userInterface.updateBoard(board)
        r
    }

  }
}
