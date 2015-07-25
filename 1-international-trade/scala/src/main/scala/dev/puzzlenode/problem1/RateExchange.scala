package dev.puzzlenode.problem1

class RateExchange private(rateMap: Map[String, List[RateEdge]]) {
  def getConversion(fromCurrency: String, toCurrency: String): Option[BigDecimal] = {
    if (fromCurrency.equals(toCurrency)) {
      Some(BigDecimal(1.0))
    } else {
      for {
        path <- findPath(fromCurrency, toCurrency, List.empty)
        conversions <- Some( conversions(path) )
      } yield product(conversions)
    }
  }

  private def findPath(fromCurrency: String, toCurrency: String, stack: List[String]): Option[List[RateEdge]] = ???

  private def conversions(path: List[RateEdge]): List[BigDecimal] = path.map( _.conversion )
  private def product(conversions: List[BigDecimal]): BigDecimal = conversions.foldLeft(BigDecimal(1.0))(_ * _)
}

object RateExchange {
  def apply(rates: List[Rate]): RateExchange = {
    val rateMap: Map[String, List[RateEdge]] = ???

    new RateExchange(rateMap)
  }
}

private case class RateEdge(toCurrency: String, conversion: BigDecimal)
