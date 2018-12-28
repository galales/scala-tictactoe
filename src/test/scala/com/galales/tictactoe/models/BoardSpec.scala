package com.galales.tictactoe.models

import com.galales.tictactoe.UnitSpec

class BoardSpec extends UnitSpec {

  "A Board with the default size (3)" should "be created and filled with None value" in {
    val board = Board()
    val expectedBoardSize = 3

    board.size should equal (expectedBoardSize)

    for(
      row <- 0 until expectedBoardSize;
      column <- 0 until expectedBoardSize
    ) yield board(row)(column) shouldBe empty
  }

  "A Board" should "be created with custom size 5 and filled with None value" in {
    val expectedBoardSize = 5
    val board = Board(expectedBoardSize)

    board.size should equal (expectedBoardSize)

    for(
      row <- 0 until expectedBoardSize;
      column <- 0 until expectedBoardSize
    ) yield board(row)(column) shouldBe empty
  }

  "An IndexOutOfBoundsException" should "thrown when trying to access elements outside size" in {
    val board = Board()

    an [IndexOutOfBoundsException] should be thrownBy board(0)(3)
    an [IndexOutOfBoundsException] should be thrownBy board(3)(0)
    an [IndexOutOfBoundsException] should be thrownBy board(3)(3)
  }

  "A Move" should "be applied returning GoodMove if correct, not applied and return BadMove otherwise" in {
    val board = Board()

    board.execMove(Move(0, 0), Player.user).right.value shouldBe a [GoodMove]
    board(0)(0).value shouldBe Player.user
    board.execMove(Move(0, 0), Player.ai).left.value shouldBe a [BadMove]
    board(0)(0).value shouldBe Player.user
  }
}
