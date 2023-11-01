package tariana

import queue.IQueue
import queue.Queue
import kotlin.math.min

fun tariana(graph: Array<List<Int>>): MutableList<MutableList<Int>> {
    val dfs = DFS(graph = graph)
    val sccList = mutableListOf<MutableList<Int>>()

    for (v in graph.indices) {
        if (!dfs.visited.contains(v)) {
            sccList.addAll(dfs.dfsRecursive(v))
        }
    }

    return sccList
}

class DFS(
    val visited: MutableSet<Int> = mutableSetOf(),
    val graph: Array<List<Int>>,
    val stack: Queue<Int> = IQueue(),
    val discovery: Array<Int> = Array(graph.size) { 0 },
    val lowLink: Array<Int> = Array(graph.size) { 0 },
    var counter: Int = 0,
    val sccList: MutableList<MutableList<Int>> = mutableListOf(),
    val processed: BooleanArray = BooleanArray(graph.size)
) {
    fun dfsRecursive(vert: Int): MutableList<MutableList<Int>> {

        visited.add(vert)
        discovery[vert] = counter++
        lowLink[vert] = discovery[vert]
        stack.enqueue(vert)


        for (neighbour in graph[vert]) {
            if (!visited.contains(neighbour)) {
                dfsRecursive(neighbour)
                lowLink[vert] = min(lowLink[vert], lowLink[neighbour])
            } else {
                if (!processed[vert]) {
                    lowLink[vert] = min(lowLink[vert], discovery[neighbour])
                }
            }
        }
        processed[vert] = true
        if (lowLink[vert] == discovery[vert]) {
            var v = stack.dequeu()
            val scc = mutableListOf<Int>()
            scc.add(vert)
            while (v != vert) {
                scc.add(v!!)
                v = stack.dequeu()  
            }
            sccList.add(scc)
        }
        return sccList
    }
}