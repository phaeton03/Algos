package kosaraju

import dfs.DFS

/**
 * Реализовать алгоритм Косарайю
 * Входные данные:
 * Граф задан вектором смежности int A[N][Smax]. Это п.5 в структурах данных в лекции. Отличие только в том, что вершины нумеруются от 0, а не от 1, и номера самой вершины первым столбцом в матрице не будет, будут только номера смежных вершин.
 * Задание:
 * Реализовать алгоритм Косарайю, рекурсивный вариант, как он был дан в лекции
 * Если понадобится использование стека/очереди обязательно применение собственных структур данных из предыдущих занятий
 */
class Graph(
    var vertices: IntArray,
    var edges: IntArray
) {
    fun getEdges(vert: Int): IntArray {
        val vertStart = vertices[vert]
        val vertEnd = vertices[vert + 1]

        return edges.sliceArray(vertStart until vertEnd)
    }
}

class AdjacencyGraph(
    val arr: Array<Array<Int>>
)

fun inverseGraph(graph: Array<List<Int>>): Array<List<Int>> {
    val reverseGraph = Array<MutableList<Int>>(graph.size) { mutableListOf() }

    graph.forEachIndexed { vIdx, vList ->
        vList.forEach { v ->
            reverseGraph[v].add(vIdx)
        }
    }

    return Array(graph.size) { i ->
        reverseGraph[i].toList()
    }
}

fun kosaraju(graph: Array<List<Int>>): MutableList<MutableList<Int>> {
    val dfsOrigin = DFS(graph = graph)
    val dfsReverse = DFS(graph = inverseGraph(graph))
    val visitedVertices = dfsOrigin.visited
    val scc = mutableListOf<MutableList<Int>>()

    for (v in graph.indices) {
        if (!visitedVertices.contains(v)) {
            dfsOrigin.dfsRecursive(v)
        }
    }

    val finishedComponents = dfsOrigin.stack

    while (!finishedComponents.isEmpty()) {
        val dequeuVert = finishedComponents.dequeu()!!

        if (!dfsReverse.visited.contains(dequeuVert)) {
            dfsReverse.dfsIterative(dequeuVert)
            val sccList = mutableListOf<Int>()
            while (!dfsReverse.stack.isEmpty()) {
                sccList.add(dfsReverse.stack.dequeu()!!)
            }
            scc.add(sccList)
            dfsReverse.stack.clear()
        }
    }

    return scc
}






