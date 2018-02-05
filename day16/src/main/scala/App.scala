import scala.io.Source

object App {

  def main(args: Array[String]): Unit = {
    val s = Source.fromResource("input.txt").getLines().mkString("")
    println(part1(s))
    println(part2(s))
  }

  /**
    * What order are the programs in after the dance?
    *
    * @param danceInstructions The dance moves
    * @param numberOfPrograms The number of programs participating in the dance (default is 16)
    * @return The programs order as a string
    */
  def part1(danceInstructions: String, numberOfPrograms: Int = 16): String = {
    val env = new Environment(numberOfPrograms)
    DanceParser.parse(danceInstructions) match {
      case DanceParser.Success(result, _) => DanceInterperter.exec(result, env)
      case DanceParser.Failure(error, _) => throw new RuntimeException(error)
    }
    env.toString
  }

  /**
    * What is the order of the programs if they repeat their dance 1 billion times?
    *
    * It's too slow to compute all 1 billion moves but there is a pattern - the repeated
    * dance ends in the same moves every so often; so find the length of this cycle
    * to find the billionth position.
    *
    * @param danceInstructions The dance moves
    * @param numberOfPrograms The number of programs participating (default is 16)
    * @param repeat default is 1 billion (for part2)
    * @return The programs order as a string
    */
  def part2(danceInstructions: String, numberOfPrograms: Int = 16, repeat: Int = 1000000000): String = {
    DanceParser.parse(danceInstructions) match {
      case DanceParser.Failure(error, _) => throw new RuntimeException(error)
      case DanceParser.Success(result, _) =>
        val env = new Environment(numberOfPrograms)
        val startEnv = env.toString
        var cycleLength = 0
        var i = 1
        while (cycleLength == 0 && i < repeat) {
          DanceInterperter.exec(result, env)
          if (env.toString == startEnv) {
            cycleLength = i
          }
          i = i + 1
        }
        (1 to (if (cycleLength > 0) repeat % cycleLength else 1)).foreach(_ => {
          DanceInterperter.exec(result, env)
        })
        env.toString
    }
  }
}
