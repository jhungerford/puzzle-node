package dev.puzzlenode

import org.joda.time.LocalTime

package object problem2 {
  case class Flight(startCity: String, endCity: String, startTime: LocalTime, endTime: LocalTime, cost: Double)

  case class FlightCase(flights: List[Flight])
}
