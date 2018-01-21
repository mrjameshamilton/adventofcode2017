import org.scalatest.FlatSpec

class AppSpec extends FlatSpec {
  "test input" should "produce 6" in {
    assert(App.part1(App.fileToAdjacencyList("input.txt")) == 6)
  }

  "test input" should "produce adjacency list" in {
    assert(App.fileToAdjacencyList("input.txt") == List(List(2), List(1), List(0, 3, 4), List(2, 4), List(2, 3, 6), List(6), List(4, 5)))
  }

  "test input" should "have 2 groups" in {
    assert(App.part2(App.fileToAdjacencyList("input.txt")) == 2)
  }
}
