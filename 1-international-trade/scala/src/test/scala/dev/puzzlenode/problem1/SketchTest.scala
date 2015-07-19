package dev.puzzlenode.problem1

import org.scalatest.FlatSpec

class SketchTest extends FlatSpec {

  behavior of "Problem 1 solution"

  it should "calculate the grand total of sales for item 'DM1182' across all stores in 'USD' currency" in {

    val transactions: List[Transaction] = List(
      Transaction("Yonkers", "DM1210", Amount(BigDecimal("70.00"), "USD")),
      Transaction("Yonkers", "DM1182", Amount(BigDecimal("19.68"), "AUD")),
      Transaction("Nashua", "DM1182", Amount(BigDecimal("58.58"), "AUD")),
      Transaction("Scranton", "DM1210", Amount(BigDecimal("68.76"), "USD")),
      Transaction("Camden", "DM1182", Amount(BigDecimal("54.64"), "USD"))
    )

    val rates: List[Rate] = List(
      Rate("AUD", "CAD", BigDecimal(1.0079)),
      Rate("CAD", "USD", BigDecimal(1.0090)),
      Rate("USD", "CAD", BigDecimal(0.9911))
    )

    val exchange = new RateExchange(rates)

    val total = transactions
      .filter(_.sku == "DM1182")
      .map(_.amount.toCurrency("USD", exchange))
      .foldLeft(BigDecimal("0.00")) { (sum, amount) => sum + amount }

    assert(total === BigDecimal("134.22"))
  }

}
