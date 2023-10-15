package tree

import kotlin.random.Random

class Node(
    var L: Node? = null,
    var R: Node? = null,
    var key: Int,
    var size: Int = 1,
    var priority: Int = Random.nextInt(Int.MAX_VALUE)
)