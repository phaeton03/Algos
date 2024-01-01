package demukron

fun sumTotalInputEdges(graph: Array<List<Int>>): Array<Int> {
    val result: Array<Int> = Array(graph.size) { 0 }

    graph.forEach {
        it.forEach { v ->
            result[v]++
        }
    }

    return result
}

fun demukron(graph: Array<List<Int>>): MutableList<Int> {
    val degree = sumTotalInputEdges(graph)
    val outputVertices = mutableListOf<Int>()

    while (!degree.all { it == -1 }) {
        for (i in degree.indices) {
            if (degree[i] == 0) {
                outputVertices.add(i)
                graph[i].filter { it >= 0 }.forEach {
                    degree[it]--
                }
                degree[i] = -1
            }
        }
    }

    return outputVertices
}