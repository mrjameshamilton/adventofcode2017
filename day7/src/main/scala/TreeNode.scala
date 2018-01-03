
case class TreeNode(name:String, weight:Integer) {
  var parent:Option[TreeNode] = None
  var children:List[TreeNode] = List[TreeNode]()

  override def toString: String = name + " (" + weight + ")"
}
