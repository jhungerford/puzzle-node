package dev.puzzlenode.problem1

import scala.collection.JavaConversions._

import com.google.common.base.Charsets
import com.google.common.io.Resources

import scala.util.matching.Regex

object ReadTransactions {
  def apply(resourceName: String): List[Transaction] = {
    Resources.readLines(Resources.getResource(resourceName), Charsets.UTF_8)
      .tail // First line is a header
      .flatMap(toTransaction)
      .toList
  }

  def toTransaction(line: String): Option[Transaction] = {
    val lineRegex: Regex = """([^,]+),([^,]+),([0-9.]+) ([a-zA-Z]+)""".r
    line match {
      case lineRegex(store, sku, price, currency) => Some(Transaction(store, sku, Amount(BigDecimal(price), currency)))
      case _ => None
    }
  }
}
