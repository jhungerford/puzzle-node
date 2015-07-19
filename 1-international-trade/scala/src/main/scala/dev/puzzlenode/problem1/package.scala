package dev.puzzlenode

package object problem1 {
  case class Amount(value: BigDecimal, currency: String) {
    def toCurrency(toCurrency: String, rates: RateExchange): Option[BigDecimal] = {
      rates.getRate(currency, toCurrency).map(_ * value)
    }
  }

  case class Rate(fromCurrency: String, toCurrency: String, rate: BigDecimal)

  case class Transaction(store: String, sku: String, amount: Amount)
}
