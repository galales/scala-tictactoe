package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService

trait InteractionService {
  def makeMove(boardSize: Int)(implicit userInterface: UserInterfaceService): Move
}
