package dev.puzzlenode.problem2

import java.io.File

import com.google.common.io.Resources
import org.joda.time.LocalTime
import org.scalatest.{FlatSpec, Matchers, TryValues}

import scala.io.Source

class ReadInputTest extends FlatSpec with Matchers with TryValues {

  behavior of "ReadInput"

  it should "read the sample input" in {
    val file = new File(Resources.getResource("sample-input.txt").toURI)
    val source = Source.fromFile(file)

    val actualCases = ReadInput(source)

    val caseOne = Set(
      Flight('A', 'B', new LocalTime(9, 0), new LocalTime(10, 0), 100.0),
      Flight('B', 'Z', new LocalTime(11, 30), new LocalTime(13, 30), 100.0),
      Flight('A', 'Z', new LocalTime(10, 0), new LocalTime(12, 0), 300.0)
    )
    
    val caseTwo = Set(
      Flight('A', 'B', new LocalTime(8, 0), new LocalTime(9, 0), 50.0),
      Flight('A', 'B', new LocalTime(12, 0), new LocalTime(13, 0), 300.0),
      Flight('A', 'C', new LocalTime(14, 0), new LocalTime(15, 30), 175.0),
      Flight('B', 'C', new LocalTime(10, 0), new LocalTime(11, 0), 75.0),
      Flight('B', 'Z', new LocalTime(15, 0), new LocalTime(16, 30), 250.0),
      Flight('C', 'B', new LocalTime(15, 45), new LocalTime(16, 45), 50.0),
      Flight('C', 'Z', new LocalTime(16, 0), new LocalTime(19, 0), 100.0)
    )

    val expectedCases = Set(caseOne, caseTwo)

    actualCases.success.value should contain theSameElementsAs expectedCases
  }

  behavior of "AllCasesCountReadState"

  it should "parse a number" in {
    val initialState = new AllCasesCountReadState()
    val newState = initialState.handleLine("2")

    val expectedState = new NewlineState(new FlightCountState(2, Set.empty))

    newState shouldEqual expectedState
  }

  it should "fail on a blank line" in {
    val initialState = new AllCasesCountReadState()
    val newState = initialState.handleLine("two")

    newState.getClass shouldEqual classOf[ErrorState]
  }

  it should "fail on a non-numeric line" in {
    val initialState = new AllCasesCountReadState()
    val newState = initialState.handleLine("two")

    newState.getClass shouldEqual classOf[ErrorState]
  }

  behavior of "CaseCountState"

  it should "parse a number" in {
    val initialState = FlightCountState(2, Set.empty)
    val newState = initialState.handleLine("3")

    val expectedState = FlightState(2, 3, Set.empty, Set.empty)

    newState shouldEqual expectedState
  }

  it should "fail on a blank line" in {
    val initialState = FlightCountState(2, Set.empty)
    val newState = initialState.handleLine("two")

    newState.getClass shouldEqual classOf[ErrorState]
  }

  it should "fail on a non-numeric line" in {
    val initialState = FlightCountState(2, Set.empty)
    val newState = initialState.handleLine("two")

    newState.getClass shouldEqual classOf[ErrorState]
  }


  behavior of "FlightState"

  it should "Parse a flight" in {
    val initialState = FlightState(2, 2, Set.empty, Set.empty)
    val newState = initialState.handleLine("A B 09:00 10:00 100.00")

    newState.getClass shouldEqual classOf[FlightState]
    val newFlightState = newState.asInstanceOf[FlightState]

    newFlightState.allCasesLeft shouldEqual initialState.allCasesLeft
    newFlightState.allCases shouldEqual initialState.allCases
    newFlightState.flightsLeft shouldEqual initialState.flightsLeft - 1
    newFlightState.flights shouldEqual Set(Flight('A', 'B', new LocalTime(9, 0), new LocalTime(10, 0), 100.0))
  }

  it should "Transition when all flights are parsed" in {
    val initialState = FlightState(2, 1, Set.empty, Set.empty)
    val newState = initialState.handleLine("A Z 10:00 12:00 300.00")

    val expectedFlight = Flight('A', 'Z', new LocalTime(10, 0), new LocalTime(12, 0), 300.0)
    val expectedState: NewlineState = NewlineState(FlightCountState(1, Set(new FlightCase(Set(expectedFlight)))))

    newState.getClass shouldEqual expectedState
  }

  it should "Transition when all flights are parsed and all cases are complete" in {
    val initialState = FlightState(1, 1, Set.empty, Set.empty)
    val newState = initialState.handleLine("C Z 16:00 19:00 100.00")

    val expectedFlight = Flight('C', 'Z', new LocalTime(16, 0), new LocalTime(19, 0), 100.0)
    val expectedState: DoneState = DoneState(Set(new FlightCase(Set(expectedFlight))))
  }

  it should "fail on a non-flight line" in {
    val initialState = FlightState(2, 2, Set.empty, Set.empty)
    val newState = initialState.handleLine("not a flight")

    newState.getClass shouldEqual classOf[ErrorState]
  }


  behavior of "ErrorState"

  it should "always stay the same" in {
    val initialState = ErrorState("something went wrong")
    val newState = initialState.handleLine("")

    newState shouldEqual initialState
  }


  behavior of "DoneState"

  it should "always stay the same" in {
    val initialState = DoneState(Set.empty)
    val newState = initialState.handleLine("")

    newState shouldEqual initialState
  }
}
