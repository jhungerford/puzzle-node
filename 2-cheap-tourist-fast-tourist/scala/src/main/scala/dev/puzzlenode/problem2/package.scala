package dev.puzzlenode

import org.joda.time.LocalTime
import org.joda.time.format.{DateTimeFormatter, DateTimeFormat}

package object problem2 {
  case class Flight(startCity: String, endCity: String, startTime: LocalTime, endTime: LocalTime, cost: Double)

  case class FlightCase(flights: List[Flight])

  case class Solution(startTime: LocalTime, endTime: LocalTime, cost: Double) {
    def format: String = "%s %s %1.2f" format(FormatTime(startTime), FormatTime(endTime), cost)
  }

  case class CaseSolution(steve: Solution, jennifer: Solution)
}
