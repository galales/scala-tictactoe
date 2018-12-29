package com.galales.tictactoe.services.interaction

import com.galales.tictactoe.UnitSpec
import java.io.ByteArrayInputStream


class ConsoleInteractionSpec extends UnitSpec {

  "Console Interaction" should "read a value" in {
    val in = new ByteArrayInputStream("1".getBytes)

    Console.withIn(in)  {
      ConsoleInteraction.requestInput.right.value should be (1)
    }
  }

  it should "reject a non integer value" in {
    val in = new ByteArrayInputStream("a".getBytes)

    Console.withIn(in)  {
      ConsoleInteraction.requestInput.left.value shouldBe a [String]
    }
  }
}
