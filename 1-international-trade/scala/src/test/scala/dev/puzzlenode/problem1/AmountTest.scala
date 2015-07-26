package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

class AmountTest extends FlatSpec with TableDrivenPropertyChecks {

  behavior of "Amount.toCurrency"

  it should "not change if it's already in the right currency" in {
    val exchange = RateExchange(List(Rate("USD", "CAD", BigDecimal(1.002))))
    val amount = Amount(BigDecimal(2.19), "USD")

    assert(amount.toCurrency("USD", exchange) === Some(amount.value))
  }

  it should "apply the currency conversion correctly" in {
    val exchange = RateExchange(List(Rate("USD", "CAD", BigDecimal(1.0213))))
    val amount = Amount(BigDecimal(2.19), "USD")

    assert(amount.toCurrency("CAD", exchange) === Some(BigDecimal(2.24)))
  }

  behavior of "Amount.roundHalfToEven"

  val roundings = Table(
    ("input", "expected"),
    (BigDecimal( 0.016), BigDecimal( 0.02)),
    (BigDecimal( 0.015), BigDecimal( 0.02)),
    (BigDecimal( 0.014), BigDecimal( 0.01)),
    (BigDecimal( 0.006), BigDecimal( 0.01)),
    (BigDecimal( 0.005), BigDecimal( 0.00)),
    (BigDecimal( 0.004), BigDecimal( 0.00)),
    (BigDecimal(-0.004), BigDecimal(-0.00)),
    (BigDecimal(-0.005), BigDecimal(-0.00)),
    (BigDecimal(-0.006), BigDecimal(-0.01)),
    (BigDecimal(-0.014), BigDecimal(-0.01)),
    (BigDecimal(-0.015), BigDecimal(-0.02)),
    (BigDecimal(-0.016), BigDecimal(-0.02))
  )

  forAll (roundings) { (input: BigDecimal, expected: BigDecimal) =>
      assert(RoundHalfToEven(input) === expected)
  }
}
