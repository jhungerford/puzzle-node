package dev.puzzlenode.problem2

import org.joda.time.LocalTime
import org.scalatest.{Matchers, FlatSpec}

class FormatTimeTest extends FlatSpec with Matchers {

  behavior of "FormatTime"

  it should "use 24 hour time" in {
    val time = new LocalTime(23, 45)

    FormatTime(time) shouldEqual "23:45"
  }

  it should "print a leading 0" in {
    val time = new LocalTime(9, 30)

    FormatTime(time) shouldEqual "09:30"
  }
}
