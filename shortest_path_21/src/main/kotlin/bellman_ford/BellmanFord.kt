package bellman_ford

import kruskal.Edge

val INFINITY = Int.MAX_VALUE
fun bellman_ford(startedVert: Int, graph: Array<List<Edge>>): Array<Int> {
    val distances = Array(graph.size) { INFINITY }.apply { this[startedVert] = 0 }
    val allEdges = graph.flatMap { it }
    val shortestPathLength = graph.size - 1

    for (i in 0 until shortestPathLength) {
        for (edge in allEdges) {
            val oldDist = distances[edge.vEnd]
            val newDist = if (distances[edge.vStart] == INFINITY) INFINITY else distances[edge.vStart] + edge.weight
            if (newDist < oldDist) {
                distances[edge.vEnd] = newDist
            }
        }
    }

    return distances
}

