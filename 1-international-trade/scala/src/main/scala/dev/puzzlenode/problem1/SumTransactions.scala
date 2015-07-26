package dev.puzzlenode.problem1

object SumTransactions {
  def apply(transactions: List[Transaction], exchange: RateExchange, sku: String, currency: String): Option[BigDecimal] = {
    val zero: Option[BigDecimal] = Some(BigDecimal("0.00"))

    transactions
      .filter(_.sku == sku)
      .map(_.amount.toCurrency(currency, exchange))
      .foldLeft(zero) {
        case (Some(sum), Some(amount)) => Some(sum + amount)
        case _ => None
      }
  }
}
