package kruskal

class Edge(val vStart: Int, val vEnd: Int, val weight: Int) {
    override fun toString(): String {
        return "V$vStart ------> V$vEnd W = $weight"
    }
}

/**
 * Реализовать алгоритм Краскала
 */
fun kruskal(graph: Array<List<Pair<Int, Int>>>): Array<Edge?> {
    val edges = graph.mapIndexed { idx, edgeList ->
        edgeList.map {
            Edge(idx, it.first, it.second)
        }
    }.flatten().sortedBy { it.weight }

    val parent = Array(graph.size) { it }

    val result = Array<Edge?>(graph.size - 1) { null }

    var edgeIdx = 0
    for (i in result.indices) {
        var minWeightEdge = edges[edgeIdx]

        while (findMain(minWeightEdge.vStart, parent) == findMain(minWeightEdge.vEnd, parent)) {
            minWeightEdge = edges[++edgeIdx]
        }

        result[i] = minWeightEdge

        val start = findMain(minWeightEdge.vStart, parent)
        val end = findMain(minWeightEdge.vEnd, parent)

        parent[start] = end
    }

    return result
}

fun findMain(vert: Int, parent: Array<Int>): Int {
    val mainVert = parent[vert]

    if (vert == mainVert) {
        return vert
    }
    parent[vert] = findMain(mainVert, parent)

    return parent[vert]
}

