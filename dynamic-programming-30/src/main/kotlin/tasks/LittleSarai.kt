package tasks

import kotlin.math.min

/**
 * Фермер хочет построить на своей земле как можно больший по площади сарай.
 * Но на его участке есть деревья и хозяйственные постройки, которые он не хочет никуда переносить.
 * Для удобства представим ферму сеткой размера N × M.
 * Каждое из деревьев и построек размещается в одном или нескольких узлах сетки.
 * Найти максимально возможную площадь сарая и где он может размещаться.
 * Начальные данные: Вводится матрица размера N × M из 0 и 1.
 * 1 соответствует постройке, 0 - пустой клетке.
 * Начальные данные:
 * На первой строке вводится размер матрицы N M (через пробел) от 1 до 1000.
 * На второй строке вводится количество построек T (от 0 до 10000).
 * Далее на T строчках вводятся координаты построек по два числа X Y, где 0 <= X < N; 0 <= Y < M.
 * Вывод результата:
 * Число, соответствующее максимальной площади сарая (количество ячеек).
 */
var total = 0

fun maxSarajAreaBruteForce(matrix: Array<IntArray>): Int {
    var maxArea = 0

    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            if (matrix[i][j] == 0) {
                for (w in 1..matrix.size - i) {
                    for (l in 1..matrix[0].size - j) {
                        if (isValidRectangle(matrix, w, l, i, j)) {
                            val area = w * l
                            if (area > maxArea) maxArea = area
                        }
                    }
                }
            }
        }
    }

    return maxArea
}

fun isValidRectangle(matrix: Array<IntArray>, w: Int, l: Int, x: Int, y: Int): Boolean {
    var sum = 0
    for (i in x until x + w) {
        for (j in y until y + l) {
            sum += matrix[i][j]
        }
    }

    return sum == 0
}

fun calc(matrix: Array<IntArray>, x: Int, y: Int): Int {
    var total = 0
    fun sarajDFS(matrix: Array<IntArray>, x: Int, y: Int): Int {
        if (x >= matrix.size || x < 0 || y < 0 || y >= matrix[0].size || matrix[x][y] == 0) return 0
        if (matrix[x][y] == 1) total++
        sarajDFS(matrix, x + 1, y)
        sarajDFS(matrix, x, y + 1)

        return total
    }

    return sarajDFS(matrix, x, y)
}

/**
 * Длина сарая. Вычисление максимальной длины сарая для каждой клетки, +2 байта
 * https://www.robotsharp.info/index.php?page=TaskInfo&taskId=1739
 * Укажите в отчёте, сколько времени ушло на решение этой задачи.
 */
fun calcMaxLength(matrix: Array<IntArray>): Array<IntArray> {
    val lengthMatrix = matrix.copyOf()

    for (row in lengthMatrix) {
        var total = 0
        for (idx in row.indices) {
            if (row[idx] == 1) {
                total++
                row[idx] = total
            } else {
                total = 0
            }
        }
    }

    return lengthMatrix
}

/**
 * Ширина сарая. Вычисление максимально доступной ширины для выбранной длины. +2 байта.
 * https://www.robotsharp.info/index.php?page=TaskInfo&taskId=1741
 * Укажите в отчёте, сколько времени ушло на решение этой задачи.
 */
fun calcMaxSarajWidthForCertainLength(matrix: Array<IntArray>): Array<IntArray> {
    val widthMatrix = Array(matrix.size) { IntArray(matrix[0].size) { 0 } }

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            var totalWidth = 0
            var k = i
            var currEl = matrix[i][j]
            while (k <= matrix.size - 1) {
                val nextEl = matrix[k][j]
                if (currEl <= nextEl && nextEl != 0 && currEl != 0) {
                    totalWidth++
                    currEl = nextEl
                }
                k++
            }
            widthMatrix[i][j] = totalWidth
        }
    }

    return widthMatrix
}

fun searchMaxArea(matrixLength: Array<IntArray>, matrixWidth: Array<IntArray>): Int {
    var maxArea = 0

    for (i in matrixLength.indices) {
        for (j in matrixWidth.indices) {
            val newMaxArea = matrixLength[i][j] * matrixWidth[i][j]
            if (newMaxArea > maxArea) {
                maxArea = newMaxArea
            }
        }
    }

    return maxArea
}