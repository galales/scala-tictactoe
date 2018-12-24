package com.galales.tictactoe

import com.galales.tictactoe.enums.{AIType, InteractionType, UserInterfaceType}
import com.galales.tictactoe.models.Board
import com.galales.tictactoe.services.ai._
import com.galales.tictactoe.services.interaction._
import com.galales.tictactoe.services.ui._

object Main extends App {

  val board: Board = Board()

  val parameters = ParametersParser(args).getOrElse(ParametersParser())

  implicit val ai: AIService = parameters.ai match {
    case AIType.monkey => MonkeyAI
    case AIType.simple => SimpleAI
  }

  implicit val userInterface: UserInterfaceService = parameters.userInterface match {
    case UserInterfaceType.console => ConsoleUI
  }

  implicit val interaction: InteractionService = parameters.userInteraction match {
    case InteractionType.console => ConsoleInteraction
  }


  userInterface.showResult(Game.play(board, isUserTurn = true))
}
