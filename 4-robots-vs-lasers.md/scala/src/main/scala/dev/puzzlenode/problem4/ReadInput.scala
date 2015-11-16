package dev.puzzlenode.problem4

import scala.io.Source
import scala.util.Try

object ReadInput {
  def apply(source: Source): Try[List[FactoryFloor]] = Try {
    source.getLines().foldLeft( (0, List.empty[FactoryFloor]) ) { case ((i, floors), line) => i match {
      case 0 => (1, floors :+ FactoryFloor(line, null, -1))
      case 1 => (2, floors.init :+ floors.last.copy(initialIndex = line.indexOf('X')))
      case 2 => (3, floors.init :+ floors.last.copy(southLasers = line))
      case 3 => (0, floors)
    }}._2
  }
}
