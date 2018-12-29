package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.UnitSpec
import com.galales.tictactoe.models.Move
import com.galales.tictactoe.services.ui.UserInterfaceService
import org.scalamock.scalatest.MockFactory

import language.postfixOps

class InteractionServiceSpec extends UnitSpec with MockFactory {

  implicit val userInterfaceStub: UserInterfaceService = stub[UserInterfaceService]

  "Interaction Service" should "read row and column values" in {
    val inputValue = 1
    val interactor = new InteractionService {
      override def requestInput: Either[String, Int] = Right(inputValue)
    }
    userInterfaceStub.outputMessage _ when * atLeastOnce

    userInterfaceStub.outputErrorMessage _ when * never

    interactor.makeMove(3) should be (Move(inputValue - 1,inputValue - 1))
  }

  it should "repeat the input if it returns an error" in {
    val inputValue = 1

    val reqInput = mockFunction[Either[String, Int]]
    reqInput.expects().returning(Left("Error message"))
    reqInput.expects().returning(Right(inputValue)).anyNumberOfTimes()

    val interactor = new InteractionService {
      override def requestInput: Either[String, Int] = reqInput()
    }
    userInterfaceStub.outputMessage _ when * atLeastOnce

    userInterfaceStub.outputErrorMessage _ when * atLeastOnce

    interactor.makeMove(3) should be (Move(inputValue - 1,inputValue - 1))
  }

  it should "repeat the input if it lower than 1 or it exceeds board size" in {
    val inputValue = 1
    val boardSize = 3

    val reqInput = mockFunction[Either[String, Int]]
    reqInput.expects().returning(Right(-1))
    reqInput.expects().returning(Right(0))
    reqInput.expects().returning(Right(5))
    reqInput.expects().returning(Right(inputValue)).anyNumberOfTimes()

    val interactor = new InteractionService {
      override def requestInput: Either[String, Int] = reqInput()
    }

    userInterfaceStub.outputMessage _ when * atLeastOnce

    userInterfaceStub.outputErrorMessage _ when * atLeastOnce

    interactor.makeMove(boardSize) should be (Move(inputValue - 1,inputValue - 1))
  }
}
