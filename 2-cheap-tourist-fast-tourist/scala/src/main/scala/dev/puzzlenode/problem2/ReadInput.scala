package dev.puzzlenode.problem2

import scala.io.Source
import scala.util.{Failure, Success, Try}

object ReadInput {
  def apply(source: Source): Try[Set[FlightCase]] = {
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
      NewlineState(FlightCountState(count, Set.empty))

    } catch {
      case e: NumberFormatException => ErrorState(s"All cases count ($line) was not a number")
    }
  }
}

case class NewlineState(nextState: ReadState) extends ReadState {
  override def handleLine(line: String): ReadState = line.isEmpty match {
    case true => nextState
    case false => ErrorState(s"Expected empty line, got $line")
  }
}

case class FlightCountState(allCasesLeft: Int, allCases: Set[FlightCase]) extends ReadState {
  override def handleLine(line: String): ReadState = ???
}

case class FlightState(allCasesLeft: Int, flightsLeft: Int, flights: Set[Flight], allCases: Set[FlightCase]) extends ReadState {
  override def handleLine(line: String): ReadState = ???
}

case class ErrorState(reason: String) extends ReadState {
  override def handleLine(line: String): ReadState = this
}

case class DoneState(cases: Set[FlightCase]) extends ReadState {
  override def handleLine(line: String): ReadState = ???
}
