import scala.annotation.tailrec

object App {

  def main(args: Array[String]): Unit = {
    val a = 703; val b = 516
    println(App.part1(a, b))
    println(App.part2(a, b))
  }

  def part1(a: Int, b: Int): Int = solution(40000000, a, b)

  def part2(a: Int, b: Int): Int = solution(5000000, a, b, 4, 8)

  def solution(n: Int, a: Int, b: Int, aMultipleOf: Int = 1, bMultipleOf: Int = 1): Int = {
    @tailrec def _solution(n: Int, prevA: Long, prevB: Long, count: Int = 0): Int = {
      @tailrec def next(n: Long, factor: Int, multipleOf: Int): Long = {
        val ans = (n * factor) % 2147483647
        if (ans % multipleOf == 0)
          ans
        else
          next(ans, factor, multipleOf)
      }

      if (n == 0) count else {
        val (nA, nB) = (next(prevA, 16807, aMultipleOf), next(prevB, 48271, bMultipleOf))
        _solution(n - 1, nA, nB, if ((nA & 65535) == (nB & 65535)) count + 1 else count)
      }
    }

    _solution(n, a, b)
  }
}
