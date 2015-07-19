package dev.puzzlenode.problem1

import org.scalatest.FlatSpec

class AmountTest extends FlatSpec {

  behavior of "Amount.toCurrency"

  it should "not change if it's already in the right currency" in {
    val exchange = new RateExchange(List(Rate("USD", "CAD", BigDecimal(1.002))))
    val amount = Amount(BigDecimal(2.19), "USD")

    assert(amount.toCurrency("USD", exchange) === amount.value)
  }

  it should "apply the currency conversion correctly" in {
    val exchange = new RateExchange(List(Rate("USD", "CAD", BigDecimal(1.002))))
    val amount = Amount(BigDecimal(2.19), "USD")

    assert(amount.toCurrency("CAD", exchange) === amount.value * BigDecimal(1.002))
  }

}
