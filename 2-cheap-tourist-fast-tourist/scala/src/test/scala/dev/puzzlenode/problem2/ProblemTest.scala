package dev.puzzlenode.problem2

import java.io.File

import com.google.common.io.Resources
import org.joda.time.LocalTime
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ProblemTest extends FlatSpec with Matchers {

  behavior of "Problem 2"

  it should "produce the right answers for the sample input" in {
    val inputFile = new File(Resources.getResource("sample-input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val outputFile = new File(Resources.getResource("sample-output.txt").toURI)
    val outputLines = Source.fromFile(outputFile).getLines().filterNot(_.isEmpty).toList

    val flightCases = ReadInput(inputSource).get

    val solutionLines = flightCases
      .map(SolveCase(_))
      .flatMap(caseSolution => List(
        caseSolution.lowestCost.format,
        caseSolution.shortestTime.format
      ))

    solutionLines should contain theSameElementsInOrderAs outputLines
  }


  behavior of "Solution.format"

  it should "format a solution correctly" in {
    val solution = new Solution(new LocalTime(9, 0), new LocalTime(13, 30), 200.0)
    solution.format shouldEqual "09:00 13:30 200.00"
  }

}
