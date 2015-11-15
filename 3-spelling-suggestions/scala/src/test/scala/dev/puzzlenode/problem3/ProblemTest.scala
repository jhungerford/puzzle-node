package dev.puzzlenode.problem3

import java.io.File

import com.google.common.io.Resources
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ProblemTest extends FlatSpec with Matchers {

  behavior of "Problem 3"

  it should "get the right answer for the sample input" in {
    val inputFile = new File(Resources.getResource("SAMPLE_INPUT.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val outputFile = new File(Resources.getResource("SAMPLE_OUTPUT.txt").toURI)
    val outputLines = Source.fromFile(outputFile).getLines().filterNot(_.isEmpty).toList

    val wordCases = ReadInput(inputSource).get

    val solutions = wordCases.map(SolveWordCase(_))

    solutions should contain theSameElementsInOrderAs outputLines
  }

}
