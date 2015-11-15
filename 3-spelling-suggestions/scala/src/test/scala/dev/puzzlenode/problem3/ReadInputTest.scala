package dev.puzzlenode.problem3

import java.io.File

import com.google.common.io.Resources
import org.scalatest.{TryValues, Matchers, FlatSpec}

import scala.io.Source

class ReadInputTest extends FlatSpec with Matchers with TryValues {
  behavior of "ReadInput"

  it should "read the sample input correctly" in {
    val inputFile = new File(Resources.getResource("SAMPLE_INPUT.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val expectedInput = List(
      WordCase("remimance", "remembrance", "reminiscence"),
      WordCase("inndietlly", "immediately", "incidentally")
    )

    ReadInput(inputSource).success.value shouldEqual expectedInput
  }
}
