package dev.puzzlenode

import org.joda.time.LocalTime

package object problem2 {
  case class Flight(startCity: Char, endCity: Char, startTime: LocalTime, endTime: LocalTime, cost: BigDecimal)

  case class FlightCase(flights: Set[Flight])
}
