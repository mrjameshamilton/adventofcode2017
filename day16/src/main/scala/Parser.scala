import scala.collection.mutable
import scala.util.parsing.combinator.RegexParsers

sealed trait Node
case class Program(name:Char) extends Node
sealed abstract class Move() extends Node
case class Spin(n:Int) extends Move
case class Exchange(a:Int, b:Int) extends Move
case class Partner(a:Program, b:Program) extends Move
case class Dance(moves:List[Move]) extends Node

object DanceParser extends RegexParsers {
  def program:Parser[Program] = "[a-p]".r ^^ (s => Program(s.charAt(0)))
  def number:Parser[Int] = """\d+""".r ^^ { _.toInt }
  def spin:Parser[Spin] = "s" ~> number ^^ { Spin }
  def exchange:Parser[Exchange] = ("x" ~> number <~ "/") ~ number ^^ { case a ~ b => Exchange(a, b) }
  def partner:Parser[Partner] = ("p" ~> program <~ "/") ~ program ^^ { case a ~ b => Partner(a, b) }
  def move:Parser[Move] = spin | exchange | partner
  def dance:Parser[Dance] = repsep(move, ",") ^^ { Dance }

  def parse(s:String): ParseResult[Dance] = parseAll(dance, s)
}

class Environment(n:Int = 16) {
  require(n > 0 && n <=16, "The number of programs must be between 0 and 16")

  private var programs = mutable.ArraySeq.range('a', ('a' + n).toChar)

  def spin(n:Int) {
    val (left, right) = programs.splitAt(programs.length - n)
    programs = right ++ left
  }

  def exchange(a:Int, b:Int) {
    val temp = programs(a)
    programs(a) = programs(b)
    programs(b) = temp
  }

  def partner(a:Program, b:Program) {
    exchange(programs.indexOf(a.name), programs.indexOf(b.name))
  }

  override def toString: String = programs.mkString("")
}

object DanceInterperter {
  def exec(dance:Dance, env:Environment): Unit = {
    dance.moves.foreach {
      case Spin(n) => env.spin(n)
      case Exchange(a, b) => env.exchange(a, b)
      case Partner(a, b) => env.partner(a, b)
    }
  }
}