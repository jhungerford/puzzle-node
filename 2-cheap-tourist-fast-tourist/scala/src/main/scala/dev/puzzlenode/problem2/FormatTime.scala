package dev.puzzlenode.problem2

import org.joda.time.LocalTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object FormatTime {
  val fmt: DateTimeFormatter = DateTimeFormat.forPattern("HH:mm")

  def apply(time: LocalTime): String = fmt.print(time)
}
