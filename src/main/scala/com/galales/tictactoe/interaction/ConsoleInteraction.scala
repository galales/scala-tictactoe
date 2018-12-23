package com.galales.tictactoe.interaction

import com.galales.tictactoe.models.Move

object ConsoleInteraction extends UserInteractionHandler {
  override def makeMove: Move = {
    println("Make your move (row column): ")
    Move(scala.io.StdIn.readInt, scala.io.StdIn.readInt)
  }
}
