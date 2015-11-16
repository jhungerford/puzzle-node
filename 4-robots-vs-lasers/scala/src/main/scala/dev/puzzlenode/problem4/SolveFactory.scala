package dev.puzzlenode.problem4

object SolveFactory {
  def apply(factoryFloor: FactoryFloor): String = {
    // determine if left (west) or right (east) is more dangerous
    // north lasers fire on even, south on odd, robot is off the conveyor when it's off the floor
    val northOnEven: Boolean = factoryFloor.initialIndex % 2 == 0

    val westDamage = factoryFloor.initialIndex.to(0, -1)
      .map(index => computeTickDamage(factoryFloor, index, northOnEven))
      .sum

    val eastDamage = factoryFloor.initialIndex.until(factoryFloor.northLasers.length)
      .map(index => computeTickDamage(factoryFloor, index, northOnEven))
      .sum

    westDamage <= eastDamage match {
      case true => "GO WEST"
      case false => "GO EAST"
    }
  }

  def computeTickDamage(factoryFloor: FactoryFloor, index: Int, northOnEven: Boolean): Int = {
    val lasers = (index % 2 == 0) == northOnEven match {
      case true => factoryFloor.northLasers
      case false => factoryFloor.southLasers
    }

    lasers.charAt(index) == '|' match {
      case true => 1
      case false => 0
    }
  }
}
