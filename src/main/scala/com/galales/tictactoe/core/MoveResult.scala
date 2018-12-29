package com.galales.tictactoe.core

sealed trait MoveResult

case class BadMove() extends MoveResult
case class GoodMove() extends MoveResult
