package dev.puzzlenode

package object problem1 {
  case class Amount(value: BigDecimal, currency: String)

  case class ConversionRate(fromCurrency: String, toCurrency: String, rate: BigDecimal)
}
