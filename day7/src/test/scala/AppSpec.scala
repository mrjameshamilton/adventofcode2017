import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {

  val tree = TreeUtils.fromFile("input.txt")

  "test tree" should "have tknk (41) as root" in {
    assert(TreeNode("tknk", 41) == App.part1(tree))
  }

  "weight" should "be 60" in {
    assert(60 == App.part2(tree))
  }
}
