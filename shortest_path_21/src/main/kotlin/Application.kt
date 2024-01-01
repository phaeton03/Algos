import bellman_ford.bellman_ford
import boruvka.boruvka
import boruvka.boruvka2
import dijkstra.dijkstra
import floyd_warshall.floyd_warshall
import kruskal.Edge
import kruskal.kruskal
import prima.prima

fun main() {
    val graph: Array<List<Pair<Int, Int>>> = arrayOf(
        listOf(Pair(1, 4), Pair(5, 3)), // 0
        listOf(Pair(0, 4), Pair(2, 2), Pair(4, 1), Pair(7, 3)), //1
        listOf(Pair(1, 2), Pair(3, 3), Pair(6, 2)),//2
        listOf(Pair(2, 3), Pair(4, 4)),//3
        listOf(Pair(3, 4), Pair(6, 2), Pair(5, 4), Pair(1, 1), Pair(7, 2)),//4
        listOf(Pair(0, 3), Pair(4, 4), Pair(6, 3)),//5
        listOf(Pair(2, 2), Pair(4, 2), Pair(5, 3)), //6
        listOf(Pair(4, 2), Pair(1, 3)) //7
    )

    val floydWarshall = floyd_warshall(graph.mapIndexed { index, edges ->
        edges.map { Edge(index, it.first, it.second) }
    }.toTypedArray())

    floydWarshall.forEach {
        println(it.contentDeepToString())
    }

    println("---------BELMAN FORD ------------")
    val bellmanFord = bellman_ford(7, graph.mapIndexed { index, edges ->
        edges.map { Edge(index, it.first, it.second) }
    }.toTypedArray())

    println(bellmanFord.contentDeepToString())

    println("---------Dijkstra------------")
    val dijkstra = dijkstra(graph.mapIndexed { index, edges ->
        edges.map { Edge(index, it.first, it.second) }
    }.toTypedArray())

    println(dijkstra.contentDeepToString())
}