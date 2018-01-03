import scala.annotation.tailrec
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.io.Source

object TreeUtils {

  def fromFile(filename: String): TreeNode = {
    var nodes = ListBuffer[TreeNode]()
    var map: Map[TreeNode, Array[String]] = Map()
    val lines = Source.fromResource(filename).getLines()
    lines.foreach(line => {
      val nodeConnections = line.split("->").map(_.trim)
      val pattern = "([a-zA-Z]+) \\((\\d+)\\)".r
      val pattern(nodeName, nodeWeight) = nodeConnections.head
      val node = TreeNode(nodeName, nodeWeight.toInt)
      nodes += node
      if (nodeConnections.length > 1) {
        map += node -> nodeConnections.tail.head.split(", ")
      }
    })

    for ((parent, children) <- map) {
      children.foreach(childName => {
        val child = nodes.find(node => node.name == childName).get
        child.parent = Option(parent)
        parent.children = child :: parent.children
      })
    }

    getRoot(nodes.head)
  }

  /**
    * Get the root of a tree, given any node. (Answer to part1).
    *
    * @param tree
    * @return root of the tree
    */
  @tailrec def getRoot(tree: TreeNode): TreeNode = tree.parent match {
    case None => tree
    case _ => getRoot(tree.parent.get)
  }

  /**
    * Given a node calculate the weights of itself and all its descendants.
    *
    * @param tree
    * @return the weight
    */
  def getWeight(tree: TreeNode): Int = {
    @tailrec def _getWeight(nodes: List[TreeNode], acc: Int): Int = nodes match {
      case Nil => acc
      case head :: tail => _getWeight(head.children ::: tail, acc + head.weight)
    }

    _getWeight(tree.children, tree.weight)
  }
}
