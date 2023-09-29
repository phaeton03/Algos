package tree

import kotlin.math.max

class AVLTree {
    var root: Node? = null

    fun remove(key: Int, node: Node?): Node? {
        TODO("Not yet implemented")
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

    fun insert(node: Node? = root, key: Int): Node? {
        if (root == null) {
            root = Node(key = key, height = 1)
        }

        if (node == null) {
            return Node(key, height = 1)
        }

        if (key < node.key) {
            node.L = insert(node.L, key)
        }

        if (key > node.key) {
            node.R = insert(node.R, key)
        }

        reheight(node)
        rebalance(node)

        return node
    }

    fun rebalance(node: Node? = root) {
        if (node == null) return

        val nodeLeftHeight = node.L?.height ?: 0
        val nodeRightHeight = node.R?.height ?: 0

        val nodeLeftLeftHeight = node.L?.L?.height ?: 0
        val nodeLeftRightHeight = node.L?.R?.height ?: 0

        val nodeRightLeftHeight = node.R?.L?.height ?: 0
        val nodeRightRightHeight = node.R?.R?.height ?: 0

        val balanceFactor = nodeLeftHeight - nodeRightHeight

        if (balanceFactor > 1) {
            if (nodeLeftLeftHeight >= nodeLeftRightHeight) {
                smallRightRotation(node)
            } else {
                bigRightRotationV2(node)
            }
        } else if (balanceFactor < -1) {
            if (nodeRightRightHeight >= nodeRightLeftHeight) {
                smallRightRotationV2(node)
            } else {
                bigRightRotationV2(node)
            }
        }
    }

    fun reheight(node: Node? = root): Int {
        if (node == null) return 0

        node.height = max(reheight(node.L), reheight(node.R)) + 1

        return node.height
    }

    fun smallRightRotation(node: Node) {
        val leftNode = node.L ?: return

        val tempKey: Int = node.key

        node.key = leftNode.key
        val tempNode = node.R
        node.R = Node(key = tempKey, height = node.height, L = leftNode.R)
        node.R!!.R = tempNode

        node.L = leftNode.L
    }

    fun smallRightRotationV2(node: Node) {
        val leftNode = node.L ?: return  // if left child is null, return

        // Swap keys
        val tempKey = node.key
        node.key = leftNode.key
        leftNode.key = tempKey

        // Re-arrange pointers
        node.L = leftNode.L
        leftNode.L = leftNode.R
        leftNode.R = node.R
        node.R = leftNode
    }

    fun bigRightRotationV2(node: Node) {
        node.R?.let { smallLeftRotation(it) }

        smallRightRotation(node)
    }

    fun bigLeftRotationV2(node: Node) {
        node.L?.let { smallRightRotation(it) }

        smallLeftRotation(node)
    }

    fun smallLeftRotation(node: Node) {
        val rightNode = node.R ?: return  // if left child is null, return

        // Swap keys
        val tempKey = node.key
        node.key = rightNode.key
        rightNode.key = tempKey

        // Re-arrange pointers
        node.R = rightNode.R
        rightNode.R = rightNode.L
        rightNode.L = node.L
        node.R = rightNode
    }

    private fun maxHeight(keyLeft: Node?, keyRight: Node?): Int {
        val keyLeftHeight = keyLeft?.height ?: 0
        val keyRightHeight = keyRight?.height ?: 0

        return max(keyLeftHeight, keyRightHeight)
    }


}