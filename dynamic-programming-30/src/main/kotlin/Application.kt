import tasks.*
import java.util.Arrays


fun main() {
//    println(goroh(2, 10, 3 , 10))
//    println(elka(mutableListOf(
//        mutableListOf(1),
//        mutableListOf(2,3),
//        mutableListOf(4,5,6),
//        mutableListOf(9,8,0,3)
//    )))
//    (0..7).forEach { println(fiveAndEight(it)) }
    val matrix = arrayOf(
        intArrayOf(0, 0, 1, 1),
        intArrayOf(0, 1, 1, 0),
        intArrayOf(0, 1, 0, 1),
        intArrayOf(1, 0, 0, 1),
    )

    val matrix2 = arrayOf(
        intArrayOf(1, 1, 1, 1),
        intArrayOf(1, 1, 0, 1),
        intArrayOf(1, 0, 0, 1),
    )

    val matrix3 = arrayOf(
        intArrayOf(1, 1, 1, 1),
        intArrayOf(0, 0, 1, 1),
        intArrayOf(0, 1, 1, 1),
    )

    val matrix4 = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(1, 0, 0, 0),
    )

   // println("Max Sarai's square ${maxSarajAreaBruteForce(matrix)}")
   // println("Max Sarai's square ${maxSarajAreaBruteForce(matrix2)}")

//    val matrix4Area = maxSarajAreaByLengthAndWidthOptionThree(matrix4)
    val matrix3Area = maxSarajAreaByLengthAndWidthOptionThree(matrix3)
    val matrix2Area = maxSarajAreaByLengthAndWidthOptionThree(matrix2)
    val matrix1Area = maxSarajAreaByLengthAndWidthOptionThree(matrix)
//    println("matrix4: $matrix4Area")
    println("matrix3: $matrix3Area")
    println("matrix2: $matrix2Area")
    println("matrix1: $matrix1Area")
}


