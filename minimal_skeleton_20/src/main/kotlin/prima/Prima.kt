package prima

class Edge(val v1: Int, val v2: Int) {
    override fun toString(): String {
        return "V${v1} ------> V${v2}"
    }
}

/**
 * Реализовать алгоритм Прима
 * Результат должен быть представлен в виде массива Edge[] edges
 * где Edge - класс, содержащий пару вершин, которые соединяет это ребро
 */
fun prima(graph: Array<List<Pair<Int, Int>>>): Array<Edge?> {
    val trackWeight = Array<Int?>(graph.size) { null }
    val processedVertices = Array(graph.size) { false }
    val parent = Array<Int?>(graph.size) { null }
    val result = Array<Edge?>(graph.size - 1) { null }

    var vertIdx = 0
    for(i in result.indices) {
        graph[vertIdx].forEach { vert ->
            val weight = trackWeight[vert.first]
            if ((weight == null || weight > vert.second) && !processedVertices[vert.first]) {
                trackWeight[vert.first] = vert.second
                parent[vert.first] = vertIdx
            }
        }

        processedVertices[vertIdx] = true
        vertIdx = minIdx(trackWeight, processedVertices)

        result[i] = Edge(parent[vertIdx]!!, vertIdx)
    }

    return result
}

fun minIdx(trackWeight: Array<Int?>, processedVertices: Array<Boolean>): Int {
    var weightIdx = processedVertices.indexOfFirst { !it }
    if (weightIdx == -1) return -1

    var weight: Int? = trackWeight[weightIdx]

    for (i in trackWeight.indices) {
        if (trackWeight[i] != null && !processedVertices[i]) {
            if (weight == null || trackWeight[i]!! < weight) {
                weight = trackWeight[i]
                weightIdx = i
            }
        }
    }

    return weightIdx
}