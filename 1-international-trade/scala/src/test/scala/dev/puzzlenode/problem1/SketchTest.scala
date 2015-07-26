package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}

class SketchTest extends FlatSpec with Matchers {

  behavior of "Problem 1 solution"

  it should "calculate the grand total of sales for item 'DM1182' across all stores in 'USD' currency" in {
    val transactions = ReadTransactions("SAMPLE_TRANS.csv")
    val rates = ReadRates("SAMPLE_RATES.xml")
    val exchange = RateExchange(rates)

    val total = SumTransactions(transactions, exchange, "DM1182", "USD")

    assert(total === Some(BigDecimal(134.22)))
  }
}
