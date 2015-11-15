package dev.puzzlenode.problem3

import org.scalatest.{FlatSpec, Matchers}

class SolveWordCaseTest extends FlatSpec with Matchers {

  behavior of "SolveWordCase"

  it should "get the right answer for the first sample input" in {
    val wordCase = WordCase("remimance", "remembrance", "reminiscence")
    SolveWordCase(wordCase) shouldEqual wordCase.option1
  }

  it should "get the right anser for the second sample input" in {
    val wordCase = WordCase("inndietlly", "immediately", "incidentally")
    SolveWordCase(wordCase) shouldEqual wordCase.option2
  }


  behavior of "LongestSubsequenceLength"

  it should "compute the length of remimance / remembrance correctly" in {
    val length = SolveWordCase.longestSubsequenceLength("remimance", "remembrance")
    length shouldEqual 8
  }

  it should "compute the length of remimance / reminiscence correctly" in {
    val length = SolveWordCase.longestSubsequenceLength("remimance", "reminiscence")
    length shouldEqual 7
  }
}
