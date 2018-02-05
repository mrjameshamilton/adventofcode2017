import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {

  "s3" should "produce cdeab" in {
    assert(App.part1("s3", 5) == "cdeab")
  }

  "x3/4" should "swap last two programs" in {
    assert(App.part1("x3/4", 5) == "abced")
  }

  "pe/b" should "swap programs e and b" in {
    assert(App.part1("pe/b", 5) == "aecdb")
  }

  "s3,x3/4" should "spin 3 times and then swap last two programs" in {
    assert(App.part1("s3,x3/4", 5) == "cdeba")
  }

  "test input" should "produce baedc" in {
    assert(App.part1("s1,x3/4,pe/b", 5) == "baedc")
  }

  "repeat 2 times" should "produce ceadb" in {
    assert(App.part2("s1,x3/4,pe/b", 5, 2) == "ceadb")
  }
}
