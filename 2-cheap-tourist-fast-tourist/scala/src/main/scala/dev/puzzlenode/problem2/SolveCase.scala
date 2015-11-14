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

    val lowestCostPath = paths.sorted(costOrdering).head
    val shortestTimePath = paths.sorted(timeOrdering).head

    CaseSolution(
      PathToSolution(lowestCostPath),
      PathToSolution(shortestTimePath)
    )
  }

  val costOrdering = new Ordering[Path] {
    override def compare(x: Path, y: Path): Int = {
      x.cost - y.cost match {
        case 0 => x.minutes - y.minutes
        case diff => diff.signum
      }
    }
  }

  val timeOrdering = new Ordering[Path] {
    override def compare(x: Path, y: Path): Int = {
      x.minutes - y.minutes match {
        case 0 => (x.cost - y.cost).signum
        case diff => diff
      }
    }
  }
}
