package dev.puzzlenode

import org.joda.time.{LocalTime, Minutes}

package object problem2 {
  case class Flight(startCity: String, endCity: String, startTime: LocalTime, endTime: LocalTime, cost: Double)

  case class FlightCase(flights: List[Flight])

  case class Path(flights: List[Flight]) {
    lazy val startCity: String = flights.head.startCity
    lazy val endCity: String = flights.last.endCity

    lazy val startTime: LocalTime = flights.head.startTime
    lazy val endTime: LocalTime = flights.last.endTime

    lazy val cost: Double = flights.foldLeft(0.0) { (cost, flight) => cost + flight.cost }

    lazy val minutes: Int = Minutes.minutesBetween(startTime, endTime).getMinutes

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

  case class CaseSolution(lowestCost: Solution, shortestTime: Solution)
}
