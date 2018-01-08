import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {

  "{}" should "have a score of 1" in {
    assert(App.part1("{}") == 1)
  }

  "{{{}}}" should "have a score of 1 + 2 + 3 = 6" in {
    assert(App.part1("{{{}}}") == 6)
  }

  "{{},{}}" should "have a score of 1 + 2 + 2 = 5" in {
    assert(App.part1("{{},{}}") == 5)
  }

  "{{{},{},{{}}}}" should "have a score of 1 + 2 + 3 + 3 + 3 + 4 = 16" in {
    assert(App.part1("{{{},{},{{}}}}") == 16)
  }

  "{<a>,<a>,<a>,<a>}" should "have a score of 1" in {
    assert(App.part1("{<a>,<a>,<a>,<a>}") == 1)
  }

  "{{<ab>},{<ab>},{<ab>},{<ab>}}" should "have a score of 1 + 2 + 2 + 2 + 2 = 9" in {
    assert(App.part1("{{<ab>},{<ab>},{<ab>},{<ab>}}") == 9)
  }


  "{{<!!>},{<!!>},{<!!>},{<!!>}}" should "have a score of 1 + 2 + 2 + 2 + 2 = 9" in {
    assert(App.part1("{{<!!>},{<!!>},{<!!>},{<!!>}}") == 9)
  }

  "{{<a!>},{<a!>},{<a!>},{<ab>}}" should "have a score of 1 + 2 = 3" in {
    assert(App.part1("{{<a!>},{<a!>},{<a!>},{<ab>}}") == 3)
  }

  "<>" should "have 0 characters garbage" in {
    assert(App.part2("<>") == 0)
  }

  "<random characters>" should "have 17 characters garbage" in {
    assert(App.part2("<random characters>") == 17)
  }

  "<<<<>" should "have 3 characters garbage" in {
    assert(App.part2("<<<<>") == 3)
  }

  "<{!>}>" should "have 2 characters garbage" in {
    assert(App.part2("<{!>}>") == 2)
  }

  "<!!>" should "have 0 characters garbage" in {
    assert(App.part2("<!!>") == 0)
  }

  "<!!!>>" should "have 0 characters garbage" in {
    assert(App.part2("<!!!>>") == 0)
  }

  "<{o\"i!a,<{i<a>" should "have 10 characters garbage" in {
    assert(App.part2("<{o\"i!a,<{i<a>") == 10)
  }
}
