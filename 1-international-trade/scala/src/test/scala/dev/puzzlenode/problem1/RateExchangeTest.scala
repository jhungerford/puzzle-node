package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}

class RateExchangeTest extends FlatSpec with Matchers {

  /*
   *     1.0079       1.0090
   * +--------> +--------> +---+
   * |AUD|      |CAD|      |USD|
   * +---+ <- - +---+ <--------+
   *     0.9921 ^   | 0.9911
   *            |   |
   *      0.5235    |0.9100
   *            |   v         0.0700
   *            +---+    +----------> +---+
   *            |RUB|    |LYR|        |YEN|
   *            +---+    +---+ <- - ------+
   *                          14.2765
   *
   * http://asciiflow.com/
   */
  val exchange = RateExchange(List(
    Rate("AUD", "CAD", BigDecimal(1.0079)),
    Rate("CAD", "USD", BigDecimal(1.0090)),
    Rate("USD", "CAD", BigDecimal(0.9911)),
    Rate("CAD", "RUB", BigDecimal(0.9100)),
    Rate("LYR", "YEN", BigDecimal(0.0700))
  ))

  behavior of "RateExchange"

  it should "return 1 for the same rate" in {
    assert(exchange.getConversion("USD", "USD") === Some(BigDecimal(1.0)))
  }

  it should "return a rate it knows about" in {
    assert(exchange.getConversion("USD", "CAD") === Some(BigDecimal(0.9911)))
  }

  it should "invert a rate if it knows the inverse rate" in {
    assert(exchange.getConversion("CAD", "AUD") === Some(BigDecimal(1.0) / BigDecimal(1.0079)))
  }

  it should "return none if it doesn't know about a rate" in {
    assert(exchange.getConversion("Not a real rate", "Another fake rate") === None)
  }

  it should "return none if two rates aren't connected" in {
    assert(exchange.getConversion("LYR", "RUB") === None)
  }

  it should "figure out transitive rates" in {
    assert(exchange.getConversion("USD", "AUD") === Some(BigDecimal(0.9911) * (BigDecimal(1.0) / BigDecimal(1.0079))))
  }

  it should "add missing edges" in {
    val edges = List(
      Rate("AUD", "CAD", BigDecimal(1.0079)),
      Rate("CAD", "USD", BigDecimal(1.0090)),
      Rate("USD", "CAD", BigDecimal(0.9911)),
      Rate("CAD", "RUB", BigDecimal(0.9100)),
      Rate("LYR", "YEN", BigDecimal(0.0700))
    )

    val allRates: List[Rate] = RateExchange.addMissingRates(edges)

    allRates should contain allOf(
      Rate("AUD", "CAD", BigDecimal(1.0079)),
      Rate("CAD", "AUD", BigDecimal(1.0) / BigDecimal(1.0079)),
      Rate("CAD", "USD", BigDecimal(1.0090)),
      Rate("USD", "CAD", BigDecimal(0.9911)),
      Rate("CAD", "RUB", BigDecimal(0.9100)),
      Rate("RUB", "CAD", BigDecimal(1.0) / BigDecimal(0.9100)),
      Rate("LYR", "YEN", BigDecimal(0.0700)),
      Rate("YEN", "LYR", BigDecimal(1.0) / BigDecimal(0.0700))
    )
  }
}
