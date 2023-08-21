package exponential_algorithm

class Matrix(private val array: Array<LongArray>) {
    operator fun times(b: Matrix): Matrix {
        val bArray = b.getArray()

        val resultArray = Array(array.size) { LongArray(bArray.size) }

        for (i in array[0].indices) {
            for (j in array[0].indices) {
                var result = 0L
                for (k in bArray.indices) {
                    result += array[i][k] * bArray[k][j]
                }
                resultArray[i][j] = result
            }
        }

        return Matrix(resultArray)
    }


    operator fun get(i: Int, j: Int): Long {
        return array[i][j]
    }

    fun toIdentity(): Matrix {
        val identityArray = Array(array.size) { LongArray(array.size) }

        for (i in array.indices) {
            identityArray[i][i] = 1
        }

        return Matrix(identityArray)
    }

    private fun getArray() = array

    override fun toString(): String {
        return array.contentDeepToString()
    }
}




