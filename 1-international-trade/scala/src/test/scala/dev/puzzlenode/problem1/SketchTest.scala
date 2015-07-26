package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}

class SketchTest extends FlatSpec with Matchers {

  behavior of "Problem 1 solution"

  it should "calculate the grand total of sales for item 'DM1182' across all stores in 'USD' currency" in {
    val transactions = ReadTransactions("SAMPLE_TRANS.csv")

    val rates: List[Rate] = List(
      Rate("AUD", "CAD", BigDecimal(1.0079)),
      Rate("CAD", "USD", BigDecimal(1.0090)),
      Rate("USD", "CAD", BigDecimal(0.9911))
    )

    val exchange = RateExchange(rates)

    val total = SumTransactions(transactions, exchange, "DM1182", "USD")

    assert(total === Some(BigDecimal(134.22)))
  }
}
