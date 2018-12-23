package com.galales.tictactoe

import com.galales.tictactoe.enums.{AIType, UserInteractionType, UserInterfaceType}

final case class InputParser(userInterface: Option[UserInterfaceType.Value] = Some(UserInterfaceType.console), userInteraction: Option[UserInteractionType.Value] = Some(UserInteractionType.console), ai: Option[AIType.Value] = Some(AIType.monkey))

object InputParser {

  implicit val userInterfaceTypeRead: scopt.Read[UserInterfaceType.Value] =
    scopt.Read.reads(UserInterfaceType.withName)

  implicit val userInteractionRead: scopt.Read[UserInteractionType.Value] =
    scopt.Read.reads(UserInteractionType.withName)

  implicit val aiTypeRead: scopt.Read[AIType.Value] =
    scopt.Read.reads(AIType.withName)


  def apply(args: Array[String]): Option[InputParser] = {
    val parser = new scopt.OptionParser[InputParser]("Tic Tac Toe") {

      opt[UserInterfaceType.Value]('i', "userInterface").
        optional().
        action((i, p) => p.copy(userInterface = Some(i)))

      opt[UserInteractionType.Value]('t', "userInteraction").
        optional().
        action((t, p) => p.copy(userInteraction = Some(t)))

      opt[AIType.Value]('a', "ai").
        optional().
        action((a, p) => p.copy(ai = Some(a)))

    }

    parser.parse(args, InputParser())
  }

}
