package dev.puzzlenode.problem2

import java.io.Serializable

// Transforms a flight case into a set of all possible paths from A to Z
object FlightCaseToPaths {
  def apply(flightCase: FlightCase): List[List[Flight]] = {
    // Start with the set of flights -> List(flight)
    val initialPaths = flightCase.flights.map{ flight => List(flight) }

    // Iterate until the set stops changing.
    // Loops are impossible because all flights happen on the same day.
    // For each flight, see if it fits on the head of tail of a path in the set
    val allPaths = iteratePaths(initialPaths)

    // After converging, filter any paths that don't run from A -> Z
    allPaths.filter( path => pathFromAToZ(path) )
  }

  private def iteratePaths(initialPaths: List[List[Flight]]): List[List[Flight]] = {
    // Paths from A -> Z automatically make it in to the set
    val (aToZPaths, incompletePaths) = initialPaths.partition( path => pathFromAToZ(path) )

    val combinedPaths = aToZPaths ++ combineIncompletePaths(incompletePaths)

    combinedPaths == initialPaths match {
      case true => combinedPaths
      case false => iteratePaths(combinedPaths)
    }
  }

  private def combineIncompletePaths(paths: List[List[Flight]]): List[List[Flight]] = {
    val emptyPaths = List.empty[List[Flight]]

    if (paths.size <= 1) {
      emptyPaths

    } else {
      val path = paths.head
      val toMatch = paths.tail

      val matchedPaths: List[List[Flight]] = toMatch.foldLeft(emptyPaths) { (acc, matchPath) =>
        path match {
          case before if fitsBefore(before, matchPath) => acc :+ before ++ matchPath
          case after  if fitsAfter(after, matchPath) => acc :+ matchPath ++ after
          case _ => acc
        }
      }

      matchedPaths ++ combineIncompletePaths(toMatch)
    }
  }

  private def fitsBefore(before: List[Flight], path: List[Flight]): Boolean = {
    before.last.endCity == path.head.startCity && before.last.endTime.isBefore(path.head.startTime)
  }

  private def fitsAfter(after: List[Flight], path: List[Flight]): Boolean = {
    after.head.startCity == path.last.endCity && after.head.startTime.isAfter(path.last.endTime)
  }

  private def pathFromAToZ(path: List[Flight]): Boolean = {
    path.head.startCity == "A" && path.last.endCity == "Z"
  }
}
