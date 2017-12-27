import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {

  "aa bb cc dd ee" should "be valid" in {
    assert(1 == App.part1(List("aa bb cc dd ee")))
  }

  "aa bb cc dd aa" should "not be valid" in {
    assert(0 == App.part1(List("aa bb cc dd aa")))
  }

  "aa bb cc dd aaa" should "be valid" in {
    assert(1 == App.part1(List("aa bb cc dd aaa")))
  }

  "abcde fghij" should "be valid" in {
    assert(1 == App.part2(List("abcde fghij")))
  }

  "abcde xyz ecdab" should "not be valid" in {
    assert(0 == App.part2(List("abcde xyz ecdab")))
  }

  "a ab abc abd abf abj" should "be valid" in {
    assert(1 == App.part2(List("a ab abc abd abf abj")))
  }

  "iiii oiii ooii oooi oooo" should "be valid" in {
    assert(1 == App.part2(List("iiii oiii ooii oooi oooo")))
  }

  "oiii ioii iioi iiio" should "not be valid" in {
    assert(0 == App.part2(List("oiii ioii iioi iiio")))
  }
}
