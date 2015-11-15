package dev.puzzlenode.problem3

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

  def longestSubsequenceLength(word1: String, word2: String): Int = ???
}
