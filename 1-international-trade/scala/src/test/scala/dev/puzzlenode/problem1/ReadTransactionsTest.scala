package dev.puzzlenode.problem1

import org.scalatest.{Matchers, FlatSpec}

class ReadTransactionsTest extends FlatSpec with Matchers {
  behavior of "ReadTransactions"

  it should "read sample transactions" in {
    ReadTransactions("SAMPLE_TRANS.csv") should contain allOf(
      Transaction("Yonkers", "DM1210", Amount(BigDecimal("70.00"), "USD")),
      Transaction("Yonkers", "DM1182", Amount(BigDecimal("19.68"), "AUD")),
      Transaction("Nashua", "DM1182", Amount(BigDecimal("58.58"), "AUD")),
      Transaction("Scranton", "DM1210", Amount(BigDecimal("68.76"), "USD")),
      Transaction("Camden", "DM1182", Amount(BigDecimal("54.64"), "USD"))
    )
  }
}
