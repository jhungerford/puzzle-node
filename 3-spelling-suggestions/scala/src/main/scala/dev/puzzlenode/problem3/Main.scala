package dev.puzzlenode.problem3

import java.io.{PrintWriter, File}

import com.google.common.io.Resources

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val inputFile = new File(Resources.getResource("input.txt").toURI)
    val inputSource = Source.fromFile(inputFile)

    val wordCases = ReadInput(inputSource).get

    val out = new PrintWriter(new File(inputFile.getParentFile, "output.txt"))
    wordCases.foreach { wordCase =>
      System.out.println(wordCase)
      out.println(SolveWordCase(wordCase))
    }

    inputSource.close()
    out.close()
  }
}
