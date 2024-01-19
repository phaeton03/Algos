package huffman_tree

class Node(
    val bit0: Node? = null,
    val bit1: Node? = null,
    var sequenceNumber: Int = 0,
    var priority: Int = 0,
    val symbol: Byte? = null
)