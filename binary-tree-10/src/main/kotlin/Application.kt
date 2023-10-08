import tree.AVLTree
import tree.BinaryTree
import kotlin.random.Random

fun main() {
    val avlTree = AVLTree()

    avlTree.insert(key = 70)
    avlTree.insert(key = 50)
    avlTree.insert(key = 100)
    avlTree.insert(key = 30)
    avlTree.insert(key = 60)

    println(avlTree.search(key = 70))
    println(avlTree.search(key = 100))
    println(avlTree.search(key = 50))

    avlTree.search(key = 70)?.let { avlTree.smallRightRotationV2(it) }

    println(avlTree.search(key = 70))
    println(avlTree.search(key = 100))
    println(avlTree.search(key = 50))
}
