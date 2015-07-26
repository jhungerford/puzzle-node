package dev.puzzlenode.problem1

object Main {
  def main(args: Array[String]) {
    val transactions = ReadTransactions("TRANS.csv")
    val rates = ReadRates("RATES.xml")
    val exchange = RateExchange(rates)

    val total = SumTransactions(transactions, exchange, "DM1182", "USD")

    println(s"Grand total of sales for item DM1182 across all stores in USD currency: $total")
  }
}
