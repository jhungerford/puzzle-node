package dev.puzzlenode.problem4

import java.io.File

import com.google.common.io.Resources
import org.scalatest.{TryValues, FlatSpec, Matchers}

import scala.io.Source

class ReadInputTest extends FlatSpec with Matchers with TryValues {
  behavior of "ReadInput"

  it should "read the sample input correctly" in {
    val inputFile = new File(Resources.getResource("sample-input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val expectedInput = List(
      FactoryFloor("#|#|#|##", "###||###", 3),
      FactoryFloor("##|#|#|#", "###||###", 4),
      FactoryFloor("##|#|#|#", "###|||##", 4)
    )

    ReadInput(inputSource).success.value shouldEqual expectedInput

  }
}
