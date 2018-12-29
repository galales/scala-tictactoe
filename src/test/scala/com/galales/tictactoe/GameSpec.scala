package com.galales.tictactoe

import com.galales.tictactoe.enums.GameResult
import com.galales.tictactoe.models.{Board, Move, Player}
import com.galales.tictactoe.services.ai.AIService
import com.galales.tictactoe.services.interaction.InteractionService
import com.galales.tictactoe.services.ui.UserInterfaceService
import org.scalamock.scalatest.MockFactory

import language.postfixOps

class GameSpec extends UnitSpec with MockFactory {
  implicit val aiServiceMock: AIService = stub[AIService]
  implicit val userInterfaceMock: UserInterfaceService = stub[UserInterfaceService]
  implicit val interactionMock: InteractionService = stub[InteractionService]

  "The Game (you lost The Game)" should "allow the user to play in his/her/it turn" in {
    val board = generateBoardWithLastTileEmpty()

    aiServiceMock.makeMove _ when * never

    userInterfaceMock.updateBoard _ when * twice()

    (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(2,2) once

    Game.play(board, isUserTurn = true)

  }

  it should "allow Skynet to take over in AI turn" in {
    val board = generateBoardWithLastTileEmpty()

    aiServiceMock.makeMove _ when board returns Move(2,2) once

    userInterfaceMock.updateBoard _ when * once

    (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) never

    Game.play(board, isUserTurn = false)
  }

  it should "allow each side to respond to fire once per turn" in {
    val board = Board()

    inSequence {
      (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(0,0) once

      aiServiceMock.makeMove _ when board returns Move(2,0) once

      (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(1,0) once

      aiServiceMock.makeMove _ when board returns Move(0,1) once

      (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(0,2) once

      aiServiceMock.makeMove _ when board returns Move(1,1) once

      (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(1,2) once

      aiServiceMock.makeMove _ when board returns Move(2,2) once

      (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(2,1) once

    }

    Game.play(board, isUserTurn = true)

  }

  "The match" should "end in a User Win" in {
    val board = generateBoardWithLastTileEmpty(Player.user)

    (interactionMock.makeMove(_ : Int)(_ : UserInterfaceService)) when(*,*) returning Move(2,2) once

    Game.play(board, isUserTurn = true) should be (GameResult.userWin)

  }

  it should "end in a AI Win" in {
    val board = generateBoardWithLastTileEmpty(Player.ai)

    aiServiceMock.makeMove _ when board returns Move(2,2) once

    Game.play(board, isUserTurn = false) should be (GameResult.aiWin)
  }

  it should "end in a Draw" in {
    val board = generateBoardWithLastTileEmpty(Player.user)

    aiServiceMock.makeMove _ when board returns Move(2,2) once

    Game.play(board, isUserTurn = false) should be (GameResult.draw)
  }

  "User Input" should "be repeated in case of bad move" in {
    val board = generateBoardWithLastTileEmpty()

    inSequence {

      // Non empty tile
      (interactionMock.makeMove(_: Int)(_: UserInterfaceService)) when(*, *) returning Move(0, 0) once

      userInterfaceMock.outputErrorMessage _ when * once

      // Empty tile
      (interactionMock.makeMove(_: Int)(_: UserInterfaceService)) when(*, *) returning Move(2, 2) once

    }

    Game.play(board, isUserTurn = true)
  }

  private def generateBoardWithLastTileEmpty(winningPlayer: Player.Value = Player.user) : Board = {
    val board = Board()
    val opponent = winningPlayer match {
      case Player.user => Player.ai
      case Player.ai => Player.user
    }

    for(
      row <- 0 until 3;
      column <- 0 until 3 if row != 2 || column != 2
    ) yield board.execMove(Move(row, column), if (row == 2 || column == 2 || (row == column && column == 0)) winningPlayer else opponent)

    board
  }
}
