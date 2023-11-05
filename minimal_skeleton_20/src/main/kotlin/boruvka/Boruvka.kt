package boruvka

import kruskal.Edge

fun boruvka(graph: Array<List<Edge>>): Array<Edge?> {
    var resultIdx = 0
    val result = Array<Edge?>(graph.size - 1) { null }
    val parent = Array(graph.size) { it }
    val graphSort = graph.map { it.sortedBy { edge -> edge.weight } }.toTypedArray()
    val cheapest = Array<Edge?>(graph.size) { null }

    var numComponents = graph.size

    while (numComponents > 1) {
        for (i in graphSort.indices) {
            var minWeighEdgeIdx = 0
            val adjEdges = graphSort[i]
            while (minWeighEdgeIdx < adjEdges.size &&
                findMain(i, parent) == findMain(adjEdges[minWeighEdgeIdx].vEnd, parent))
            {
                minWeighEdgeIdx++
            }
            if (minWeighEdgeIdx < adjEdges.size) {
                val minWeightEdge = adjEdges[minWeighEdgeIdx]
                cheapest[parent[i]] = minWeightEdge
            }
        }

        for (i in graphSort.indices) {
            val edge = cheapest[parent[i]]
            if (edge != null && findMain(i, parent) != findMain(edge.vEnd, parent)) {
                result[resultIdx++] = edge
                union(i, edge.vEnd, parent)
                numComponents--
            }
        }
        parent.forEachIndexed {idx, comp -> print("idx = $idx and comp = $comp ") }
        println()
    }

    return result
}

fun boruvka2(graph: Array<List<Edge>>): Array<Edge?> {
    val result = mutableListOf<Edge>()
    val parent = Array(graph.size) { it }
    val cheapest = Array<Edge?>(graph.size) { null }

    var numComponents = graph.size

    while (numComponents > 1) {
        // Find the cheapest outgoing edge for each component
        for (i in graph.indices) {
            var minEdge: Edge? = null
            for (edge in graph[i]) {
                if (findMain(i, parent) != findMain(edge.vEnd, parent)) {
                    if (minEdge == null || edge.weight < minEdge.weight) {
                        minEdge = edge
                    }
                }
            }
            cheapest[i] = minEdge
        }

        // For each component, add its cheapest outgoing edge to the MST
        for (i in graph.indices) {
            val edge = cheapest[i]
            if (edge != null && findMain(i, parent) != findMain(edge.vEnd, parent)) {
                result.add(edge)
                union(i, edge.vEnd, parent)
                numComponents--
            }
        }
    }

    return result.toTypedArray()
}

fun findMain(vert: Int, parent: Array<Int>): Int {
    if (vert >= parent.size) return -1

    val mainVert = parent[vert]

    if (vert == mainVert) {
        return vert
    }
    parent[vert] = findMain(mainVert, parent)

    return parent[vert]
}

fun union(firstSet: Int, secondSet: Int, parent: Array<Int>) {
    val firstSetRoot = findMain(firstSet, parent)
    val secondSetRoot = findMain(secondSet, parent)
    parent[firstSetRoot] = secondSetRoot
}