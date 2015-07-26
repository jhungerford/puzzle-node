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

    val zero: Option[BigDecimal] = Some(BigDecimal("0.00"))
    val total: Option[BigDecimal] = transactions
      .filter(_.sku == "DM1182")
      .map(_.amount.toCurrency("USD", exchange))
      .foldLeft(zero) {
        case (Some(sum), Some(amount)) => Some(sum + amount)
        case _ => None
      }

    total shouldBe defined
    total.get.doubleValue() should be (134.22 +- 0.001)
  }
}
