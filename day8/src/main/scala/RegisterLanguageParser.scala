import scala.collection.mutable
import scala.util.parsing.combinator.RegexParsers

sealed trait Node
case class Register(name:String) extends Node
sealed abstract class Instruction() extends Node
sealed abstract class Condition() extends Node
case class Increment(r:Register, n:Int) extends Instruction
case class Decrement(r:Register, n:Int) extends Instruction
case class Equal(r:Register, n:Int) extends Condition
case class NotEqual(r:Register, n:Int) extends Condition
case class GreaterThan(r:Register, n:Int) extends Condition
case class LessThan(r:Register, n:Int) extends Condition
case class GreaterThanEqual(r:Register, n:Int) extends Condition
case class LessThanEqual(r:Register, n:Int) extends Condition
case class Command(ins:Instruction, cond:Condition) extends Node
case class Program(commands:List[Command]) extends Node

object RegisterLanguageParser extends RegexParsers {
  override val whiteSpace = """[ \t]+""".r
  val CRLF = "\r\n" | "\n"
  def register: Parser[Register] = """[a-z]+""".r ^^ { Register }
  def integer: Parser[Int] = """-?\d+""".r ^^ { _.toInt }
  def condition: Parser[Condition] = "if"~>register~("==" | "!=" | "<=" | ">=" | "<" | ">")~integer ^^ {
    case r~"=="~n => Equal(r, n)
    case r~"!="~n => NotEqual(r, n)
    case r~"<="~n => LessThanEqual(r, n)
    case r~"<"~n => LessThan(r, n)
    case r~">="~n => GreaterThanEqual(r, n)
    case r~">"~n => GreaterThan(r, n)
  }
  def command: Parser[Command] = register~("inc"|"dec")~integer~condition ^^ {
    case r~"inc"~n~c => Command(Increment(r, n), c)
    case r~"dec"~n~c => Command(Decrement(r, n), c)
  }
  def lines: Parser[Program] = repsep(command, CRLF) ^^ { Program }

  def parse(s:String) = parseAll(lines, s)
}

class Environment {
  val registers = mutable.Map[String, Int]()
  def get(r:String) = registers.getOrElse(r, 0)
  def inc(r:String, n:Int) = registers(r) = get(r) + n
  def dec(r:String, n:Int) = registers(r) = get(r) - n
  override def toString: String = registers.toString
}

object RegisterLanguageInterpreter {
  def exec(program:Program, env:Environment) {
    program.commands.foreach(command => {
      if (eval(command.cond)) {
        command.ins match {
          case Increment(r, n) => env.inc(r.name, n)
          case Decrement(r, n) => env.dec(r.name, n)
        }
      }
    })

    def eval(condition: Condition):Boolean = condition match {
      case Equal(r, n) => env.get(r.name) == n
      case NotEqual(r, n) => env.get(r.name) != n
      case GreaterThan(r, n) => env.get(r.name) > n
      case GreaterThanEqual(r, n) => env.get(r.name) >= n
      case LessThan(r, n) => env.get(r.name) < n
      case LessThanEqual(r, n) => env.get(r.name) <= n
    }
  }
}