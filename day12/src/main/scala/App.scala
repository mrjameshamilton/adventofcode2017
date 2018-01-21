import scala.collection.mutable
import scala.io.Source

object App {

  def main(args: Array[String]): Unit = {
    println(App.part1(fileToAdjacencyList("input.txt")))
    println(App.part2(fileToAdjacencyList("input.txt")))
  }

  /**
    * Given an adjacency list how big is the group that contains node 0?
    *
    * @param adjacencyList the adjacency list
    * @return the size of the group
    */
  def part1(adjacencyList: List[List[Int]]): Int = getGroup(adjacencyList, 0).size

  /**
    * Given an adjacency list, how many groups are there?
    *
    * @param adjacencyList the adjacency list
    * @return the number of groups
    */
  def part2(adjacencyList: List[List[Int]]): Int = {
    val toVisit = mutable.Set() ++ adjacencyList.indices
    var numberOfGroups = 0
    while (toVisit.nonEmpty) {
      toVisit --= getGroup(adjacencyList, toVisit.head)
      numberOfGroups += 1
    }
    numberOfGroups
  }

  /**
    * Given a filename, open the file and convert to an adjacency list.
    *
    * Format a <-> b, c, d where a is connected to b, c and d.
    *
    * @param filename the name of the file in the resources folder
    * @return an adjacency list
    */
  def fileToAdjacencyList(filename: String): List[List[Int]] = {
    Source.fromResource(filename).getLines().map(line => {
      line.split(" <-> ").tail.head.split(',').map(_.trim.toInt).toList
    }).toList
  }

  /**
    * Given an adjacency list and a node n, find all the possible
    * nodes that can be reached from n.
    *
    * @param adjacencyList the graph represented as an adjacency list
    * @param n the starting node
    * @return a list of nodes in this group
    */
  def getGroup(adjacencyList: List[List[Int]], n: Int): List[Int] = {
    def visit(visited: Set[Int], n:Int): Set[Int] = {
      if (visited.contains(n)) {
        visited
      } else {
        adjacencyList(n).filterNot(visited.contains).foldLeft(visited + n)(visit)
      }
    }
    visit(Set(), n).toList
  }
}

