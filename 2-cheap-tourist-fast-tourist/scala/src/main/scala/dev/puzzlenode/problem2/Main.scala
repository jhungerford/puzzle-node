package dev.puzzlenode.problem2

import java.io.{PrintWriter, File}

import com.google.common.io.Resources

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val inputFile = new File(Resources.getResource("input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val flightCases = ReadInput(inputSource).get

    val out = new PrintWriter(new File(inputFile.getParentFile, "output.txt"))
    flightCases.foreach { flightCase =>
      val solution = SolveCase(flightCase)

      out.println(solution.lowestCost.format)
      out.println(solution.shortestTime.format)
      out.println()
    }

    out.close()
  }
}
