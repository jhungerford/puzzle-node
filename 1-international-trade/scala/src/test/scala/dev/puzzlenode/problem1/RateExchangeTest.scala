package dev.puzzlenode.problem1

import org.scalatest.FlatSpec

class RateExchangeTest extends FlatSpec {
  behavior of "RateExchange"

  it should "Return 1 for the same rate" in {
    val exchange = new RateExchange(List(
      Rate("USD", "CAD", BigDecimal(1.002))
    ))

    assert(exchange.getRate("USD", "USD") === BigDecimal(1.0))
  }

  it should "Return a rate it knows about" in {
    val exchange = new RateExchange(List(
      Rate("USD", "CAD", BigDecimal(1.002))
    ))

    assert(exchange.getRate("USD", "CAD") === BigDecimal(1.002))
  }

  it should "Invert a rate if it knows the inverse rate" in {
    val exchange = new RateExchange(List(
      Rate("USD", "CAD", BigDecimal(1.002))
    ))

    assert(exchange.getRate("CAD", "USD") === BigDecimal(1.0) / BigDecimal(1.002))
  }
}
