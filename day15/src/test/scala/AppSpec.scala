import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {


  "part1" should "return 588 with inputs 65 and 8921" in {
    assert(App.part1(65, 8921) == 588)
  }

  "part2" should "return 309 with inputs 65 and 8921" in {
    assert(App.part2(65, 8921) == 309)
  }
}
