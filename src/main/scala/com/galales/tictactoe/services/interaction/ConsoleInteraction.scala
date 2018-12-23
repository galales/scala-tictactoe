package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.models.Move

object ConsoleInteraction extends InteractionService {
  override def makeMove: Move = {
    println("Make your move (row column): ")
    Move(scala.io.StdIn.readInt, scala.io.StdIn.readInt)
  }
}
