package floyd_warshall

import kruskal.Edge
import kotlin.math.min

fun floyd_warshall(graph: Array<List<Edge>>): Array<Array<Int?>> {
    val distances = initDistanceArray(graph)

    for (interVert in graph.indices) {
        for (startVert in graph.indices) {
            for (endVert in graph.indices) {
                if (startVert != endVert) {
                    val directCost = distances[startVert][endVert]
                    val indirectCostFirstHalf = distances[startVert][interVert]
                    val indirectCostSecondHalf = distances[interVert][endVert]
                    distances[startVert][endVert] =
                        calculateDistance(directCost, indirectCostFirstHalf, indirectCostSecondHalf)
                }
            }
        }
    }
    return distances
}

fun initDistanceArray(graph: Array<List<Edge>>): Array<Array<Int?>> {
    val distances = Array<Array<Int?>>(graph.size) { Array(graph.size) { null } }

    graph.forEach {
        it.forEach { edge ->
            distances[edge.vStart][edge.vEnd] = edge.weight
            distances[edge.vStart][edge.vStart] = 0
        }
    }

    return distances
}

inline fun calculateDistance(directCost: Int?, indirectCostFirstHalf: Int?, indirectCostSecondHalf: Int?): Int? {
    var newCost: Int? = null
    if (directCost == null && (indirectCostFirstHalf != null && indirectCostSecondHalf != null)) {
        newCost = indirectCostFirstHalf + indirectCostSecondHalf
    }
    if (directCost != null && (indirectCostFirstHalf != null && indirectCostSecondHalf != null)) {
        val indirectCost = indirectCostFirstHalf + indirectCostSecondHalf
        newCost = min(directCost, indirectCost)
    }
    if (directCost != null && (indirectCostFirstHalf == null || indirectCostSecondHalf == null)) {
        newCost = directCost
    }

    return newCost
}