package dev.puzzlenode.problem4

import java.io.File

import com.google.common.io.Resources
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ProblemTest extends FlatSpec with Matchers {

  behavior of "Problem 4"

  it should "Get the right answer for the sample input" in {
    val inputFile = new File(Resources.getResource("sample-input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val outputFile = new File(Resources.getResource("sample-output.txt").toURI)
    val outputLines = Source.fromFile(outputFile).getLines().filterNot(_.isEmpty).toList

    val factoryFloors = ReadInput(inputSource).get

    val solutions = factoryFloors.map(SolveFactory(_))

    solutions should contain theSameElementsInOrderAs outputLines
  }

}
