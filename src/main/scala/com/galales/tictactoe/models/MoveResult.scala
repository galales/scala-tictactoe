package com.galales.tictactoe.models

sealed trait MoveResult

case class BadMove() extends MoveResult
case class GoodMove() extends MoveResult
