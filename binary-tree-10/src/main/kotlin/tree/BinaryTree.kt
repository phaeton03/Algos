package tree

/**
 * 1 часть, обязательная. 5 байтов.
 * Создать простейшее двоичное дерево поиска. +3 байта.
 * Методы к реализации:
 * void insert(int x) - вставка элемента
 * bool search(int x) - поиск элемента
 * void remove(int x) - удаление элемента
 */
class BinaryTree {
    var top: Node? = null
    var root: Node? = null
    fun insert(node: Node? = root, key: Int): Node {
        if (root == null) {
            root = Node(key)
        }

        if (node == null) {
            return Node(key)
        }

        if (key < node.key) {
            val insertNode = insert(node.L, key)
            insertNode.P = node
            node.L = insertNode
        }

        if (key > node.key) {
            val insertNode = insert(node.R, key)
            insertNode.P = node
            node.R = insertNode
        }

        if (key == node.key) {
            node.key = key
        }

        return node
    }

    fun search(node: Node? = root, key: Int): Node? {
        var result: Node? = null

        if (node == null) return null

        if (node.key == key) return node

        if (key < node.key) {
            result = search(node.L, key)
        }
        if (key > node.key) {
            result = search(node.R, key)
        }

        return result
    }

    fun remove(key: Int): Node? {
        val node = search(key = key)
        /**
         * удаление без наследников
         * */
        if (node?.L == null && node?.R == null) {
            if (node!!.P == null) {
                root = null

                return node
            }

            if (node.P!!.L != null && node.P!!.L?.key == key) {
                node.P!!.L = null

                return node
            }

            if (node.P!!.R != null && node.P!!.R?.key == key) {
                node.P!!.R = null

                return node
            }
        }
        /**
         * удаление одного наследника
         * */
        if (node.R == null) {
            if (node.P!!.L != null && node.P!!.L!!.key == key) {
                node.P!!.L = node.L
                node.L!!.P = node.P!!

                return node
            }
        }
        if (node.L == null) {
            if (node.P!!.R != null && node.P!!.R?.key == key) {
                node.P!!.R = node.R
                node.R!!.P = node.P!!

                return node
            }
        }

        /**
         * удаление с двумя наследниками
         **/
        var replaceNode = node.R

        while (replaceNode!!.L != null) {
            replaceNode = replaceNode.L
        }

        node.key = replaceNode.key

        if (replaceNode.P == node) {
            node.R = replaceNode.R

            return node
        }
        TODO("доделать нужно ")
        val parent = replaceNode.P
        replaceNode.P = replaceNode.R
        replaceNode.R!!.P = parent

        return node
    }

    fun removeRecurs(key: Int, node: Node? = root): Node? {
        if (node == null) return null

        if (key < node.key) {
            node.L = removeRecurs(key, node.L)
        }
        if (key > node.key) {
            node.R = removeRecurs(key, node.R)
        }
        if (key == node.key) {
            if (node.R == null && node.L == null) {
                return null
            }
            if (node.L == null) return node.R
            if (node.R == null) return node.L

            var replaceNode = node.R

            while (replaceNode!!.L != null) {
                replaceNode = replaceNode.L
            }

            node.key = replaceNode.key

            node.R = removeRecurs(replaceNode.key, node.R)
        }

        return node
    }

    private fun removeOneSibling(node: Node?, key: Int): Node? {
        if (node!!.L == null) {
            if (node.P!!.L != null && node.P!!.L!!.key == key) {
                node.P!!.L = node.R
                node.R!!.P = node.P!!.L

                return node
            }
        }
        if (node.R == null) {
            if (node.P!!.R != null && node.P!!.R?.key == key) {
                node.P!!.R = node.L
                node.L!!.P = node.P!!.R

                return node
            }
        }

        return node
    }
}


class Node(
    var key: Int,
    var L: Node? = null,
    var R: Node? = null,
    var P: Node? = null,
    var height: Int = 0
) {
    override fun toString(): String {
        return "Node(key=$key, L=${L?.key}, R=${R?.key}, height=${height})"
    }
}