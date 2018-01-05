import org.scalatest.FlatSpec

import scala.io.Source

class AppSpec extends FlatSpec {

  val s = Source.fromResource("input.txt").getLines().mkString("\n")
  val program = RegisterLanguageParser.parse(s)

  "test input" should "have register with highest value of 1" in {
    if (program.successful) {
      assert(App.part1(program.get) == 1)
    } else {
      println(program)
      throw new Exception("""Invalid input""")
    }
  }

  it should "have highest value in any register during execution of 10" in {
    if (program.successful) {
      assert(App.part2(program.get) == 10)
    } else {
      println(program)
      throw new Exception("""Invalid input""")
    }
  }
}
