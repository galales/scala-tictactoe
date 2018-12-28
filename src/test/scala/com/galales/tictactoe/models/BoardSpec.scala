package com.galales.tictactoe.models

import com.galales.tictactoe.UnitSpec

class BoardSpec extends UnitSpec {

  "A Board" should "be created with the default size (3) and filled with None value" in {
    val board = Board()
    val expectedBoardSize = 3

    board.size should equal (expectedBoardSize)

    for(
      row <- 0 until expectedBoardSize;
      column <- 0 until expectedBoardSize
    ) yield board(row)(column) shouldBe empty
  }

  it should "be created with custom size 5 and filled with None value" in {
    val expectedBoardSize = 5
    val board = Board(expectedBoardSize)

    board.size should equal (expectedBoardSize)

    for(
      row <- 0 until expectedBoardSize;
      column <- 0 until expectedBoardSize
    ) yield board(row)(column) shouldBe empty
  }

  "Direct access" should "throw an IndexOutOfBoundsException when trying to get elements outside size" in {
    val board = Board()

    an [IndexOutOfBoundsException] should be thrownBy board(0)(3)
    an [IndexOutOfBoundsException] should be thrownBy board(3)(0)
    an [IndexOutOfBoundsException] should be thrownBy board(3)(3)
  }

  "Indirect access" should "succeed when getting an existing row" in {
    val board = Board()
    board.execMove(Move(0,0), Player.user)
    board.execMove(Move(0,1), Player.ai)

    board.getRow(0) should be (List(Some(Player.user), Some(Player.ai), None))
  }

  it should "succeed when getting an existing column" in {
    val board = Board()
    board.execMove(Move(0,0), Player.user)
    board.execMove(Move(1,0), Player.ai)

    board.getColumn(0) should be (List(Some(Player.user), Some(Player.ai), None))
  }

  it should "succeed when getting diagonals" in {
    val board = Board()
    board.execMove(Move(0,0), Player.user)
    board.execMove(Move(1,1), Player.ai)
    board.execMove(Move(2,0), Player.user)

    board.getDiagonal(true) should be (List(Some(Player.user), Some(Player.ai), None))
    board.getDiagonal(false) should be (List(None, Some(Player.ai), Some(Player.user)))
  }

  it should "fail when getting a non existing row" in {
    val board = Board()

    an [IndexOutOfBoundsException] should be thrownBy board.getRow(4)
  }

  it should "fail when getting a non existing column" in {
    val board = Board()

    an [IndexOutOfBoundsException] should be thrownBy board.getColumn(4)
  }

  "A Move" should "be applied returning GoodMove if correct, not applied and return BadMove otherwise" in {
    val board = Board()

    board.execMove(Move(0, 0), Player.user).right.value shouldBe a [GoodMove]
    board(0)(0).value shouldBe Player.user
    board.execMove(Move(0, 0), Player.ai).left.value shouldBe a [BadMove]
    board(0)(0).value shouldBe Player.user
  }

  "Board Completion" should "be negative when the board is not completed" in {
    val board = Board()

    board.isComplete should be (false)

    for(
      row <- 0 until 3;
      column <- 0 until 3 if row != 2 && column != 2
    ) yield board.execMove(Move(row, column), Player.user)

    board.isComplete should be (false)
  }

  it should "be positive when the board is completed" in {
    val board = Board()

    for(
      row <- 0 until 3;
      column <- 0 until 3
    ) yield board.execMove(Move(row, column), Player.user)

    board.isComplete should be (true)
  }
}
