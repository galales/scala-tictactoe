package com.galales.tictactoe

import com.galales.tictactoe.models.Board
import com.galales.tictactoe.services.ai.{AIService, MonkeyAI}
import com.galales.tictactoe.services.interaction.{ConsoleInteraction, InteractionService}
import com.galales.tictactoe.services.ui.{ConsoleUI, UserInterfaceService}

object Main extends App {

  implicit val ai: AIService = MonkeyAI
  implicit val userInterface: UserInterfaceService = ConsoleUI
  implicit val interaction: InteractionService = ConsoleInteraction

  val board: Board = Board()

  userInterface.showResult(Game.play(board, isUserTurn = true))
}
