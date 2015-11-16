package dev.puzzlenode.problem3

import scala.collection.mutable.ArrayBuffer

object SolveWordCase {
  def apply(wordCase: WordCase): String = {
    val option1Length: Int = longestSubsequenceLength(wordCase.input, wordCase.option1)
    val option2Length: Int = longestSubsequenceLength(wordCase.input, wordCase.option2)

    if (option1Length >= option2Length) {
      wordCase.option1
    } else {
      wordCase.option2
    }
  }

  def longestSubsequenceLength(wordX: String, wordY: String): Int = {
    // Use the dynamic programming algorithm described here:
    // https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

    val size = (wordX.length + 1) * (wordY.length + 1)
    val arr = new Array[Int](size)

    val idx = index(wordX.length) _

    (0 to wordX.length).foreach( x => arr(idx(x, 0)) = 0 )
    (0 to wordY.length).foreach( y => arr(idx(0, y)) = 0 )

    (1 to wordX.length).foreach { x =>
      (1 to wordY.length).foreach { y =>
        val value = if (wordX.charAt(x-1) == wordY.charAt(y-1)) {
          arr(idx(x-1, y-1)) + 1
        } else {
          Math.max(arr(idx(x-1, y)), arr(idx(x, y-1)))
        }

        arr(idx(x, y)) = value
      }
    }

    arr(idx(wordX.length, wordY.length))
  }

  private def index(wordXLength: Int)(wordXIndex: Int, wordYIndex: Int): Int = (wordXLength + 1) * wordXIndex + (1 + wordYIndex)
}
