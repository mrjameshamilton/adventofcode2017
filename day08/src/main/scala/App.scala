import scala.io.Source

object App {

  def main(args: Array[String]): Unit = {
    val s = Source.fromResource("input.txt").getLines().mkString("\n")
    val program = RegisterLanguageParser.parse(s)
    if (program.successful) {
      println(part1(program.get))
      println(part2(program.get))
    } else {
      println(program)
    }
  }

  def part1(program: Program): Int = {
    val env = new Environment
    RegisterLanguageInterpreter.exec(program, env)
    env.registers.maxBy(_._2)._2
  }

  def part2(program: Program): Int = {
    //this is a special Environment which keeps track of the maximum value
    val part2Env = new Environment() {
      var max = 0

      private def checkMax(r: String) {
        val n = get(r)
        if (n > max) max = n
      }

      override def inc(r: String, n: Int) = {
        super.inc(r, n); checkMax(r)
      }

      override def dec(r: String, n: Int) = {
        super.dec(r, n); checkMax(r)
      }
    }
    RegisterLanguageInterpreter.exec(program, part2Env)
    part2Env.max
  }
}
