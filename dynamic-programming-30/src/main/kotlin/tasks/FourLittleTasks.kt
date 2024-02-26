package tasks

import java.util.LinkedList
import kotlin.math.max

/**
 * Раз/два горох +1 байт
 */

fun goroh(a: Int, b: Int, c: Int, d: Int): String {
    val nok = (b * d) / nod(b, d)
    val newA = a * (nok / b)
    val newC = c * (nok / d)
    val z = newA + newC
    val nod = nod(z, nok)
    val newZ = z / nod
    val newNok = nok / nod

    return "$newZ / $newNok"
}

fun nod(a: Int, b: Int): Int {
    if (a == b) return a

    if (a % 2 == 0 && b % 2 == 0) {
        return 2 * nod(a / 2, b / 2)
    }

    if (a % 2 == 0 && b % 2 == 1) {
        return nod(a / 2, b)
    }

    if (a % 2 == 1 && b % 2 == 0) {
        return nod(a, b / 2)
    }

    if (a > b) {
        return nod((a - b) * 2, b)
    }

    return nod(a, (b - a) * 2)
}

/**
 * "Цифровая ёлочка" +1 байт
 */

fun elka(elka: MutableList<MutableList<Int>>): Int {
    var curr = mutableListOf<Int>()
    for (i in elka.size - 1 downTo 0) {
        if (curr.isNotEmpty()) {
            for (j in elka[i].indices) {
                val currValue = elka[i][j]
                elka[i][j] = max(currValue + curr[j], currValue + curr[j + 1])
            }
        }
        curr = elka[i]
    }

    return elka[0][0]
}

/**
 * Пятью-восемь
 */
fun fiveAndEight(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 2
    if (n == 2) return 4
    var firstCombo = 2
    var secondCombo = 4
    var resultCombo = 0
    for (i in 3..n) {
        resultCombo = firstCombo + secondCombo
        firstCombo = secondCombo
        secondCombo = resultCombo
    }
    return resultCombo
}

/**
 * Острова
 */
fun islandsBFSOrZero(matrix: Array<IntArray>): Int {
    val queue = LinkedList<Pair<Int, Int>>()

    var islands = 0

    for (iIdx in matrix.indices) {
        for (jIdx in matrix[0].indices) {
            if (matrix[iIdx][jIdx] == 1) {
                queue.offer(Pair(iIdx, jIdx))
            }
            while (queue.isNotEmpty()) {
                val (coordX, coordY) = queue.poll()
                matrix[coordX][coordY] = 0
                val nextX = coordX + 1
                val prevY = coordY - 1
                val nextY = coordY + 1

                if (nextX < matrix.size && matrix[nextX][coordY] == 1) {
                    queue.offer(Pair(nextX, coordY))
                }
                if (nextY < matrix.size && matrix[coordX][nextY] == 1) {
                    queue.offer(Pair(coordX, nextY))
                }
                if (prevY >= 0 && matrix[coordX][prevY] == 1) {
                    queue.offer(Pair(coordX, prevY))
                }
                if (queue.isEmpty()) {
                    islands++
                }
            }
        }
    }
    return islands
}

fun islandsBFS(matrix: Array<IntArray>): Int {
    if (matrix.isEmpty()) return 0

    val queue = LinkedList<Pair<Int, Int>>()
    val rows = matrix.size
    val cols = matrix[0].size
    var islands = 0

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (matrix[i][j] == 1) {
                islands++
                queue.offer(Pair(i, j))
                matrix[i][j] = 0 // Mark as visited

                while (queue.isNotEmpty()) {
                    val (x, y) = queue.poll()

                    // Check all 4 directions around the cell (up, down, left, right)
                    val directions = arrayOf(
                        Pair(x - 1, y), // up
                        Pair(x + 1, y), // down
                        Pair(x, y - 1), // left
                        Pair(x, y + 1)  // right
                    )

                    for ((newX, newY) in directions) {
                        if (newX in 0 until rows && newY in 0 until cols && matrix[newX][newY] == 1) {
                            queue.offer(Pair(newX, newY))
                            matrix[newX][newY] = 0 // Mark as visited
                        }
                    }
                }
            }
        }
    }

    return islands
}

fun islandsDFS(matrix: Array<IntArray>): Int {
    var islands = 0
    for (x in matrix.indices) {
        for (y in matrix[0].indices) {
            if (matrix[x][y] == 1) {
                DFS(matrix, x, y)
                islands++
            }
        }
    }

    return islands
}

fun DFS(matrix: Array<IntArray>, x: Int, y: Int) {
    if (x < 0 || y < 0 || x >= matrix.size || y >= matrix[0].size || matrix[x][y] == 0) {
        return
    }

    matrix[x][y] = 0
    DFS(matrix, x - 1, y)
    DFS(matrix, x + 1, y)
    DFS(matrix, x, y - 1)
    DFS(matrix, x, y + 1)
}
