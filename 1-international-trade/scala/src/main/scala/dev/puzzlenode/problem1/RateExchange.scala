package dev.puzzlenode.problem1

class RateExchange (rateMap: Map[String, List[RateEdge]]) {
  def getConversion(fromCurrency: String, toCurrency: String): Option[BigDecimal] = {
    val startStack = List(RateEdge(fromCurrency, BigDecimal(1.0)))
    for {
      path <- findPath(fromCurrency, toCurrency, startStack)
    } yield product(conversions(path))
  }

  private def atNode(fromCurrency: String, toCurrency: String): Boolean = fromCurrency.equals(toCurrency)

  private def findPath(fromCurrency: String, toCurrency: String, stack: List[RateEdge]): Option[List[RateEdge]] = {
    if (atNode(fromCurrency, toCurrency)) Some(stack)
    else if (! rateMap.contains(fromCurrency)) None
    else {
      val visitedNodes = stack.map(_.toCurrency)

      val potentialNodes = rateMap(fromCurrency)
      val unvisitedNodes = potentialNodes.filterNot( node => visitedNodes.contains(node.toCurrency) )

      unvisitedNodes.map( node => findPath(node.toCurrency, toCurrency, stack :+ node) ).find( _.isDefined ).flatten
    }
  }


  private def conversions(path: List[RateEdge]): List[BigDecimal] = path.map( _.conversion )
  private def product(conversions: List[BigDecimal]): BigDecimal = conversions.foldLeft(BigDecimal(1.0))(_ * _)
}

object RateExchange {
  def apply(rates: List[Rate]): RateExchange = {
    val bidirectionalRates = addMissingRates(rates)
    val rateMap = ratesToMap(bidirectionalRates)
    new RateExchange(rateMap)
  }

  def addMissingRates(rates: List[Rate]): List[Rate] = {
    val presentRates = rates.map( rateEdgeName ).toSet

    rates.foldLeft( rates ) { (allRates, rate) =>
      if (presentRates.contains(reverseRateEdgeName(rate))) allRates
      else allRates :+ Rate(rate.toCurrency, rate.fromCurrency, BigDecimal(1.0) / rate.conversion)
    }
  }

  private def rateEdgeName(rate: Rate) = rate.fromCurrency + ':' + rate.toCurrency
  private def reverseRateEdgeName(rate: Rate) = rate.toCurrency + ':' + rate.fromCurrency

  private def ratesToMap(rates: List[Rate]): Map[String, List[RateEdge]] = {
    rates.foldLeft (Map.empty[String, List[RateEdge]]) { (map, rate) =>
      val edges = map.getOrElse(rate.fromCurrency, List.empty[RateEdge])
      map + (rate.fromCurrency -> (edges :+ RateEdge(rate.toCurrency, rate.conversion)))
    }
  }
}

private case class RateEdge(toCurrency: String, conversion: BigDecimal)
