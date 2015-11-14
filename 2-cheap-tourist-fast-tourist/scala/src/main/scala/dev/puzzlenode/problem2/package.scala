package dev.puzzlenode

import org.joda.time.LocalTime
import org.joda.time.format.{DateTimeFormatter, DateTimeFormat}

package object problem2 {
  case class Flight(startCity: String, endCity: String, startTime: LocalTime, endTime: LocalTime, cost: Double)

  case class FlightCase(flights: List[Flight])

  case class Path(flights: List[Flight]) {
    lazy val startCity = flights.head.startCity
    lazy val endCity = flights.last.endCity

    lazy val startTime = flights.head.startTime
    lazy val endTime = flights.last.endTime

    lazy val cost = flights.foldLeft(0.0) { (cost, flight) => cost + flight.cost }

    def ++(after: Path): Path = Path(flights ++ after.flights)
  }

  case class Solution(startTime: LocalTime, endTime: LocalTime, cost: Double) {
    def format: String = "%s %s %1.2f" format(FormatTime(startTime), FormatTime(endTime), cost)
  }

  object PathToSolution {
    def apply(path: Path): Solution = {
      Solution(path.startTime, path.endTime, path.cost)
    }
  }

  case class CaseSolution(steve: Solution, jennifer: Solution)
}
