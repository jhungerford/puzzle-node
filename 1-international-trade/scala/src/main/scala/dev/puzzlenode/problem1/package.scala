package dev.puzzlenode

package object problem1 {
  case class Amount(value: BigDecimal, currency: String) {
    def toCurrency(currency: String, rates: RateExchange): BigDecimal = ???
  }

  case class Rate(fromCurrency: String, toCurrency: String, rate: BigDecimal)

  case class Transaction(store: String, sku: String, amount: Amount)
}
