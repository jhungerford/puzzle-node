package dev.puzzlenode.problem2

// Transforms a flight case into a set of all possible paths from A to Z
object FlightCaseToPaths {
  def apply(flightCase: FlightCase): List[Path] = {
    // Start with the set of flights -> List(flight)
    val initialPaths = flightCase.flights.map{ flight => Path(List(flight)) }

    // Iterate until the set stops changing.
    // Loops are impossible because all flights happen on the same day.
    // For each flight, see if it fits on the head of tail of a path in the set
    val allPaths = iteratePaths(initialPaths)

    // After converging, filter any paths that don't run from A -> Z
    allPaths.filter( path => pathFromAToZ(path) )
  }

  private def iteratePaths(initialPaths: List[Path]): List[Path] = {
    // Paths from A -> Z automatically make it in to the set
    val (aToZPaths, incompletePaths) = initialPaths.partition( path => pathFromAToZ(path) )

    val combinedPaths = aToZPaths ++ combineIncompletePaths(incompletePaths)

    combinedPaths == initialPaths match {
      case true => combinedPaths
      case false => iteratePaths(combinedPaths)
    }
  }

  private def combineIncompletePaths(paths: List[Path]): List[Path] = {
    val emptyPaths = List.empty[Path]

    if (paths.size <= 1) {
      emptyPaths

    } else {
      val path = paths.head
      val toMatch = paths.tail

      val matchedPaths: List[Path] = toMatch.foldLeft(emptyPaths) { (acc, matchPath) =>
        path match {
          case before if fitsBefore(before, matchPath) => acc :+ before ++ matchPath
          case after  if fitsAfter(after, matchPath) => acc :+ matchPath ++ after
          case _ => acc
        }
      }

      matchedPaths ++ combineIncompletePaths(toMatch)
    }
  }

  private def fitsBefore(before: Path, path: Path): Boolean = {
    before.endCity == path.startCity && before.endTime.isBefore(path.startTime)
  }

  private def fitsAfter(after: Path, path: Path): Boolean = {
    path.endCity == after.startCity && path.endTime.isBefore(after.startTime)
  }

  private def pathFromAToZ(path: Path): Boolean = {
    path.startCity == "A" && path.endCity == "Z"
  }
}
