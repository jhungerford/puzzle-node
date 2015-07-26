package dev.puzzlenode.problem1

import com.google.common.io.Resources

import scala.xml.{Elem, XML}

object ReadRates {
  def apply(resourceName: String): List[Rate] = {
    val ratesElem: Elem = XML.load(Resources.getResource(resourceName))

    (ratesElem \ "rate").map { rateElem =>
      Rate(
        (rateElem \ "from").text,
        (rateElem \ "to").text,
        BigDecimal((rateElem \ "conversion").text)
      )
    }.toList
  }
}
