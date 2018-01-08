import scala.annotation.tailrec
import scala.io.Source

/**
  * The characters represent groups - sequences that begin with { and end with }.
  * Within a group, there are zero or more other things, separated by commas: either another group or garbage.
  * Since groups can contain other groups, a } only closes the most-recently-opened unclosed group
  * - that is, they are nestable. Your puzzle input represents a single, large group which itself
  * contains many smaller ones.
  *
  * Sometimes, instead of a group, you will find garbage. Garbage begins with < and ends with >.
  * Between those angle brackets, almost any character can appear, including { and }.
  * Within garbage, < has no special meaning.
  *
  * In a futile attempt to clean up the garbage, some program has canceled some of the characters within it
  * using !: inside garbage, any character that comes after ! should be ignored, including <, >, and even another !.
  *
  * You don't see any characters that deviate from these rules. Outside garbage, you only find well-formed groups,
  * and garbage always terminates according to the rules above.
  */
object App {

  def main(args: Array[String]): Unit = {
    val s = Source.fromResource("input.txt").getLines().mkString
    println(solution(s))
  }

  private def solution(s: String): (Int, Int) = {
    @tailrec
    def _solution(chars: List[Char], groupCount: Int, score: Int, garbageCount: Int, isGarbage: Boolean): (Int, Int) =
    (chars, isGarbage) match {
      case (Nil, _) => (score, garbageCount)
      case ('{' :: tail, false) => _solution(tail, groupCount + 1, score, garbageCount, isGarbage)
      case ('<' :: tail, false) => _solution(tail, groupCount, score, garbageCount, isGarbage = true)
      case ('>' :: tail, true) => _solution(tail, groupCount, score, garbageCount, isGarbage = false)
      case ('!' :: _ :: tail, true) => _solution(tail, groupCount, score, garbageCount, isGarbage)
      case ('}' :: tail, false) => _solution(tail, groupCount - 1, score + groupCount, garbageCount, isGarbage)
      case (_ :: tail, true) => _solution(tail, groupCount, score, garbageCount + 1, isGarbage)
      case (_ :: tail, _) => _solution(tail, groupCount, score, garbageCount, isGarbage)
    }

    _solution(s.toList, 0, 0, 0, isGarbage = false)
  }

  /**
    * Find the total score for all groups in your input. Each group is assigned a score which is
    * one more than the score of the group that immediately contains it. (The outermost group gets a score of 1.)
    *
    * @param s the input string
    * @return the total score
    */
  def part1(s: String): Int = solution(s)._1

  /**
    * Count all of the characters within the garbage. The leading and trailing < and > don't count,
    * nor do any canceled characters or the ! doing the canceling.
    *
    * @param s the input string
    * @return the number of garbage characters
    */
  def part2(s: String): Int = solution(s)._2
}
