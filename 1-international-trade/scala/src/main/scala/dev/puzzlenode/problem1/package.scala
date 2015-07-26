package dev.puzzlenode

package object problem1 {
  case class Amount(value: BigDecimal, currency: String) {
    def toCurrency(toCurrency: String, rates: RateExchange): Option[BigDecimal] = rates
      .getConversion(currency, toCurrency)
      .map( conversion => RoundHalfToEven(conversion * value) )
  }

  case class Rate(fromCurrency: String, toCurrency: String, conversion: BigDecimal)

  case class Transaction(store: String, sku: String, amount: Amount)

  object RoundHalfToEven {
    def apply(value: BigDecimal): BigDecimal = value.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)
  }
}
