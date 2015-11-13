package dev.puzzlenode.problem2

import org.joda.time.LocalTime
import org.scalatest.{Matchers, FlatSpec}

class ProblemTest extends FlatSpec with Matchers {

  behavior of "Solution.format"

  it should "format a solution correctly" in {
    val solution = new Solution(new LocalTime(9, 0), new LocalTime(13, 30), 200.0)
    solution.format shouldEqual "09:00 13:30 200.00"
  }

}
