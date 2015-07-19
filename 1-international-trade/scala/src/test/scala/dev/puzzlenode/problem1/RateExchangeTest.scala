package dev.puzzlenode.problem1

import org.scalatest.FlatSpec

class RateExchangeTest extends FlatSpec {
  val exchange = new RateExchange(List(
    Rate("USD", "CAD", BigDecimal(1.002)),
    Rate("SFK", "CAD", BigDecimal(3.50))
  ))

  behavior of "RateExchange"

  it should "Return 1 for the same rate" in {
    assert(exchange.getRate("USD", "USD") === Some(BigDecimal(1.0)))
  }

  it should "Return a rate it knows about" in {
    assert(exchange.getRate("USD", "CAD") === Some(BigDecimal(1.002)))
  }

  it should "Invert a rate if it knows the inverse rate" in {
    assert(exchange.getRate("CAD", "USD") === Some(BigDecimal(1.0) / BigDecimal(1.002)))
  }

  it should "Return none if it doesn't know about a rate" in {
    assert(exchange.getRate("Not a real rate", "Another fake rate") === None)
  }

  it should "Figure out transitive rates" in {
    assert(exchange.getRate("USD", "SFK") === BigDecimal(1.002) * BigDecimal(1.0) / BigDecimal(3.50))
  }
}
