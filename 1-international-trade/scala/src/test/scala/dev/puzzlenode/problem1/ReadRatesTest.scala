package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}

class ReadRatesTest extends FlatSpec with Matchers {
  behavior of "ReadRates"

  it should "read sample rates" in {
    ReadRates("SAMPLE_RATES.xml") should contain allOf(
      Rate("AUD", "CAD", BigDecimal(1.0079)),
      Rate("CAD", "USD", BigDecimal(1.0090)),
      Rate("USD", "CAD", BigDecimal(0.9911))
    )
  }
}
