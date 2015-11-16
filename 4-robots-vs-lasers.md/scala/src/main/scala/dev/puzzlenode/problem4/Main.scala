package dev.puzzlenode.problem4

import java.io.{PrintWriter, File}

import com.google.common.io.Resources

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val inputFile = new File(Resources.getResource("input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val factoryFloors = ReadInput(inputSource).get

    val out = new PrintWriter(new File(inputFile.getParentFile, "output.txt"))
    factoryFloors.foreach { factoryFloor =>
      out.println(SolveFactory(factoryFloor))
    }

    inputSource.close()
    out.close()
  }
}
