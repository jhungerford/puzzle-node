package dev.puzzlenode.problem2

import org.joda.time.LocalTime
import org.scalatest.{FlatSpec, Matchers}

class FlightCaseToPathsTest extends FlatSpec with Matchers {

  it should "convert the simple test case to paths" in {
    val flightCase = FlightCase(List(
      Flight("A", "B", new LocalTime(9, 0), new LocalTime(10, 0), 100.0),
      Flight("B", "Z", new LocalTime(11, 30), new LocalTime(13, 30), 100.0),
      Flight("A", "Z", new LocalTime(10, 0), new LocalTime(12, 0), 300.0)
    ))

    val expectedPaths = List(
      List(
        Flight("A", "Z", new LocalTime(10, 0), new LocalTime(12, 0), 300.0)
      ),
      List(
        Flight("A", "B", new LocalTime(9, 0), new LocalTime(10, 0), 100.0),
        Flight("B", "Z", new LocalTime(11, 30), new LocalTime(13, 30), 100.0)
      )
    )

    val actualPaths = FlightCaseToPaths(flightCase)

    actualPaths shouldEqual expectedPaths
  }

  it should "convert the complicated test case to paths" in {
    val flightCase = FlightCase(List(
      Flight("A", "B", new LocalTime(8, 0), new LocalTime(9, 0), 50.0),
      Flight("A", "B", new LocalTime(12, 0), new LocalTime(13, 0), 300.0),
      Flight("A", "C", new LocalTime(14, 0), new LocalTime(15, 30), 175.0),
      Flight("B", "C", new LocalTime(10, 0), new LocalTime(11, 0), 75.0),
      Flight("B", "Z", new LocalTime(15, 0), new LocalTime(16, 30), 250.0),
      Flight("C", "B", new LocalTime(15, 45), new LocalTime(16, 45), 50.0),
      Flight("C", "Z", new LocalTime(16, 0), new LocalTime(19, 0), 100.0)
    ))

    val expectedPaths = List(
      List(
        Flight("A", "B", new LocalTime(8, 0), new LocalTime(9, 0), 50.0),
        Flight("B", "Z", new LocalTime(15, 0), new LocalTime(16, 30), 250.0)
      ),
      List(
        Flight("A", "B", new LocalTime(12, 0), new LocalTime(13, 0), 300.0),
        Flight("B", "Z", new LocalTime(15, 0), new LocalTime(16, 30), 250.0)
      ),
      List(
        Flight("A", "C", new LocalTime(14, 0), new LocalTime(15, 30), 175.0),
        Flight("C", "Z", new LocalTime(16, 0), new LocalTime(19, 0), 100.0)
      )
    )

    val actualPaths = FlightCaseToPaths(flightCase)
    actualPaths shouldEqual expectedPaths
  }

}
