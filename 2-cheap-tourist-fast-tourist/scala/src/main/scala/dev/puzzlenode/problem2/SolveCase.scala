package dev.puzzlenode.problem2

object SolveCase {

  // build a DAG forest (or sequence of paths) from source -> destination.
  // find the shortest path through the graph with cost function:
  //   steve (cost)
  //   jennifer (time)

  // Graph: city -> outgoing flight
  // outgoing flight: cost, time, destination

  // loops are impossible - same day condition
  // multiple flights arriving at a city at different times

  def apply(flightCase: FlightCase): CaseSolution = {
    val paths = FlightCaseToPaths(flightCase)

    val lowestCostPath = paths.sortWith(costSort).head
    val shortestTimePath = paths.sortWith(timeSort).head

    CaseSolution(
      PathToSolution(lowestCostPath),
      PathToSolution(shortestTimePath)
    )
  }

  def costSort(path1: Path, path2: Path): Boolean = ???

  def timeSort(path1: Path, path2: Path): Boolean = ???
}
