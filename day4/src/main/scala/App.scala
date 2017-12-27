import scala.io.Source

object App {

  /**
    * How many passphrases are valid? A valid passphrase must contain no duplicate anagram words.
    *
    * @param passphrases
    * @return number of valid passphrases
    */
  def part2(passphrases: List[String]): Int =
    part1(passphrases.map(p => p.split(" ").toList.map(p => p.sorted).mkString(" ")))

  /**
    * How many passphrases are valid? A valid passphrase must contain no duplicate words.
    *
    * @param passphrases
    * @return number of valid passphrases
    */
  def part1(passphrases : List[String]): Int = {
    def isValid(passphrase :String) = {
      val words = passphrase.split(" ")
      words.toSet.size == words.length
    }
    passphrases.count(passphrase => isValid(passphrase))
  }

  def main(args: Array[String]): Unit = {
    val passphrases = Source.fromResource("input.txt").getLines().toList
    println(part1(passphrases))
    println(part2(passphrases))
  }
}
