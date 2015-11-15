package dev.puzzlenode.problem3

import scala.io.Source
import scala.util.Try

object ReadInput {
  def apply(in: Source): Try[List[WordCase]] = Try {
    val lines = in.getLines()

    val numLines = lines.next().toInt
    lines.next()

    (1 to numLines).map { caseNum =>
      val wordCase = WordCase(lines.next(), lines.next(), lines.next())

      if (caseNum < numLines) {
        lines.next()
      }

      wordCase
    }.toList
  }
}
