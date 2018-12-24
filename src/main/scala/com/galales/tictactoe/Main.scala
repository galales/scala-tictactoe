package com.galales.tictactoe

import com.galales.tictactoe.models.Board
import com.galales.tictactoe.services.ai.{AIService, MonkeyAI}
import com.galales.tictactoe.services.interaction.{ConsoleInteraction, InteractionService}
import com.galales.tictactoe.services.ui.{ConsoleUI, UserInterfaceService}

object Main extends App {

  val ai: AIService = MonkeyAI
  val interaction: InteractionService = ConsoleInteraction
  val userInterface: UserInterfaceService = ConsoleUI
  val board: Board = Board()

  userInterface.showResult(Game.play(board, ai, userInterface, interaction, isUserTurn = true))
}
