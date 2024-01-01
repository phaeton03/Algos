import demukron.demukron
import demukron.sumTotalInputEdges

fun main() {
    val graph = arrayOf(
        listOf(1),
        listOf(2,3),
        listOf(0),
        listOf(4),
        listOf(5),
        listOf(3)
    )

    val graph2 = arrayOf(
        listOf(1),
        listOf(2,5),
        listOf(3),
        listOf(4),
        listOf(),
        listOf(3,6),
        listOf(4)
    )

   // println(tariana(graph).toString())

    //println(sumTotalInputEdges(graph).contentDeepToString())

    println(demukron(graph2).toString())
}
