import scala.annotation.tailrec

object App {

  def main(args: Array[String]): Unit = {
    val tree = TreeUtils.fromFile("input.txt")
    println(part1(tree).name)
    println(part2(tree))
  }

  /**
    * Find the root of the tree.
    *
    * @param tree
    * @return the root node
    */
  def part1(tree: TreeNode) = tree.parent match {
    case None => tree
    case _ => TreeUtils.getRoot(tree)
  }

  /**
    * Find the node which unbalances the tree and return its correct weight.
    *
    * @param tree
    * @return
    */
  def part2(tree: TreeNode): Int = {
    @tailrec def _part2(tree: TreeNode, requiredWeight: Int): Int = {
      val nodeWeightPairs = tree.children.map(child => (child, TreeUtils.getWeight(child))).sortBy(_._2)
      if (nodeWeightPairs.groupBy(_._2).size == 1) {
        requiredWeight
      } else {
        val heaviest = nodeWeightPairs.last
        _part2(heaviest._1, heaviest._1.weight - (heaviest._2 - nodeWeightPairs.head._2))
      }
    }

    _part2(tree, 0)
  }

}
