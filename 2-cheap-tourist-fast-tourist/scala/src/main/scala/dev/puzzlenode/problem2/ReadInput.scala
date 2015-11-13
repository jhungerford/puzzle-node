package dev.puzzlenode.problem2

import org.joda.time.LocalTime

import scala.io.Source
import scala.util.{Failure, Success, Try}

object ReadInput {
  def apply(source: Source): Try[List[FlightCase]] = {
    val state = source.getLines().foldLeft(new AllCasesCountReadState(): ReadState) { (state, line) =>
      state.handleLine(line)
    }

    state match {
      case done: DoneState => Success(done.cases)
      case error: ErrorState => Failure(new IllegalStateException(error.reason))
      case _ => Failure(new IllegalStateException("ReadInput didn't finish reading the input."))
    }
  }
}

sealed trait ReadState {
  def handleLine(line: String): ReadState
}

class AllCasesCountReadState extends ReadState {
  override def handleLine(line: String): ReadState = {
    try {
      val count = line.toInt
      NewlineState(FlightCountState(count, List.empty))

    } catch {
      case e: NumberFormatException => ErrorState(s"All cases count ($line) is not a number")
    }
  }
}

case class NewlineState(nextState: ReadState) extends ReadState {
  override def handleLine(line: String): ReadState = line.isEmpty match {
    case true => nextState
    case false => ErrorState(s"Expected empty line, got $line")
  }
}

case class FlightCountState(allCasesLeft: Int, allCases: List[FlightCase]) extends ReadState {
  override def handleLine(line: String): ReadState = {
    try {
      val count = line.toInt
      FlightState(allCasesLeft, count, List.empty, allCases)

    } catch {
      case e: NumberFormatException => ErrorState(s"Flight count ($line) is not a number")
    }
  }
}

case class FlightState(allCasesLeft: Int, flightsLeft: Int, flights: List[Flight], allCases: List[FlightCase]) extends ReadState {
  val flight = """([A-Z]) ([A-Z]) (\d{2}):(\d{2}) (\d{2}):(\d{2}) (\d+[.]\d+)""".r

  override def handleLine(line: String): ReadState = {
    line match {
      case flight(fromCity, toCity, fromHour, fromMinute, toHour, toMinute, amount) =>
        try {
          val flight = Flight(fromCity, toCity, new LocalTime(fromHour.toInt, fromMinute.toInt), new LocalTime(toHour.toInt, toMinute.toInt), amount.toDouble)

          if (flightsLeft > 1) {
            FlightState(allCasesLeft, flightsLeft - 1, flights :+ flight, allCases)

          } else if (allCasesLeft > 1) {
            val thisCase = new FlightCase(flights :+ flight)
            NewlineState(FlightCountState(allCasesLeft - 1, allCases :+ thisCase))

          } else {
            val thisCase = new FlightCase(flights :+ flight)
            DoneState(allCases :+ thisCase)
          }

        } catch {
          case e: NumberFormatException => ErrorState(s"Flight '$line' contained non-numeric values.")
        }

      case _ => new ErrorState(s"Line '$line' is not a valid flight")
    }
  }
}

case class ErrorState(reason: String) extends ReadState {
  override def handleLine(line: String): ReadState = this
}

case class DoneState(cases: List[FlightCase]) extends ReadState {
  override def handleLine(line: String): ReadState = this
}
