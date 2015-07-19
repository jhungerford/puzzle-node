package dev.puzzlenode.problem1

class RateExchange(rates: List[Rate]) {
  def getRate(fromCurrency: String, toCurrency: String): Option[BigDecimal] = {
    if (fromCurrency.equals(toCurrency)) {
      Some(BigDecimal(1.0))
    } else {
      rates.find( rate => rate.fromCurrency.equals(fromCurrency) && rate.toCurrency.equals(toCurrency) ) match {
        case Some(rate) => Some(rate.rate)
        case None =>
          rates.find( rate => rate.toCurrency.equals(fromCurrency) && rate.fromCurrency.equals(toCurrency) ) match {
            case Some(rate) => Some(BigDecimal(1.0) / rate.rate)
            case None => None
          }
      }
    }
  }
}
