package dijkstra

import kruskal.Edge

val INFINITY = Int.MAX_VALUE
fun dijkstra(graph: Array<List<Edge>>): Array<Int> {
    val graphEdges = graph.map {
        it.sortedBy { edge -> edge.weight }.toMutableList()
    }.toTypedArray()
    val distances = Array(graph.size) { INFINITY }.apply { this[0] = 0 }
    val visitedVertices = BooleanArray(graph.size).apply { this[0] = true }

    for (i in 0 until  graph.size - 1) {
        val minDistEdge = minDistanceEdge(graphEdges, visitedVertices)
        visitedVertices[minDistEdge.vEnd] = true

        val oldDistance = distances[minDistEdge.vEnd]
        val newDistance = minDistEdge.weight + distances[minDistEdge.vStart]
        if (newDistance < oldDistance) {
            distances[minDistEdge.vEnd] = newDistance
        }
    }

    return distances
}

fun minDistanceEdge(graph: Array<MutableList<Edge>>, visitedVertices: BooleanArray): Edge {
    var minVertIdx = 0
    var minDistVert = INFINITY

    visitedVertices.forEachIndexed { idx, isVisit ->
        if (isVisit && graph[idx].isNotEmpty()) {
            var possibleMinEdge = graph[idx].first()
            while (visitedVertices[possibleMinEdge.vEnd] && graph[idx].isNotEmpty()) {
                graph[idx].removeFirst()
                if (graph[idx].isNotEmpty()) {
                    possibleMinEdge = graph[idx].first()
                }
            }
            if (minDistVert > possibleMinEdge.weight && graph[idx].isNotEmpty()) {
                minVertIdx = idx
                minDistVert = possibleMinEdge.weight
            }
        }
    }

    return graph[minVertIdx].removeFirst()
}
