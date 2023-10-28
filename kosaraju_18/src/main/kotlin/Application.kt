import kosaraju.kosaraju

fun main() {
    val graph = arrayOf(
        listOf(1),
        listOf(2,3),
        listOf(0),
        listOf(4),
        listOf(5),
        listOf(3)
    )
    val kosaraju = kosaraju(graph)

    println(kosaraju.toString())
}
