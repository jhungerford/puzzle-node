package dev.puzzlenode

package object problem4 {
  case class FactoryFloor(northLasers: Lasers, southLasers: Lasers, initialIndex: Int)

  case class Lasers(str: String)
}
