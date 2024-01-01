package tree

val A: Int = 128

class Trie(
    var root: Node = Node()
) {
    fun insert(word: String) {
        var currNode = root

        for (c in word) {
            if (!currNode.isExist(c)) {
                currNode.children[c.code] = Node()
            }
            currNode = currNode.children[c.code]!!

        }

        currNode.isEnd = true
    }

    fun search(word: String): Boolean {
        var currNode = root

        for (c in word) {
            if (currNode.children[c.code] == null) {
                return false
            } else {
                currNode = currNode.children[c.code]!!

            }
        }

        return currNode.isEnd
    }

    fun startsWith(prefix: String): Boolean {
        var currNode = root

        for (c in prefix) {
            if (currNode.children[c.code] == null) {
                return false
            } else {
                currNode = currNode.children[c.code]!!
            }
        }

        return !!currNode.isEnd
    }
}
    class Node(
        val children: Array<Node?> = arrayOfNulls(A),
        var isEnd: Boolean = false
    ) {
        fun isExist(c: Char): Boolean {
            return children[c.code] != null
        }
    }


