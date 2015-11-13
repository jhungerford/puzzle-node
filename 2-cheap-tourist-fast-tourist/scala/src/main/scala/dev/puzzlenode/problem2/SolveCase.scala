package dev.puzzlenode.problem2

object SolveCase {

  // build a DAG forest (or sequence of paths) from source -> destination.
  // find the shortest path through the graph with cost function:
  //   steve (cost)
  //   jennifer (time)27612

  // Graph: city -> outgoing flight
  // outgoing flight: cost, time, destination

  // loops are impossible - same day condition
  // multiple flights arriving at a city at different times

  def apply(flightCase: FlightCase): CaseSolution = ???
}
