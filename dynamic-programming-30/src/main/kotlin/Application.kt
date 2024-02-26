import tasks.*


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
        intArrayOf(1, 1, 0, 0),
        intArrayOf(1, 0, 0, 1),
        intArrayOf(1, 0, 1, 0),
        intArrayOf(0, 1, 1, 0),
    )

    val matrix2 = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 1, 0),
        intArrayOf(0, 1, 1, 0),
    )

    val matrix3 = arrayOf(
        intArrayOf(1, 1, 1, 1),
        intArrayOf(0, 0, 1, 1),
        intArrayOf(0, 1, 1, 1),
    )

    val matrix4 = arrayOf(
        intArrayOf(1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(0, 1, 1, 1),
    )

   // println("Max Sarai's square ${maxSarajAreaBruteForce(matrix)}")
   // println("Max Sarai's square ${maxSarajAreaBruteForce(matrix2)}")
    calcMaxLength(matrix)
    println(matrix4.contentDeepToString())
    val calcMaxLength1 = calcMaxLength(matrix)
    val calcMaxLength2 = calcMaxLength(matrix4)
    val calcMaxLength = calcMaxLength(matrix3)
    val calcMaxSarajWidthForCertainLength = calcMaxSarajWidthForCertainLength(calcMaxLength)
    val calcMaxSarajWidthForCertainLength2 = calcMaxSarajWidthForCertainLength(calcMaxLength2)

 //   println(calcMaxSarajWidthForCertainLength.contentDeepToString())
    println(matrix4.contentDeepToString())
    println(calcMaxLength2.contentDeepToString())
    println(calcMaxSarajWidthForCertainLength2.contentDeepToString())
    println(searchMaxArea(calcMaxLength2, calcMaxSarajWidthForCertainLength2))
}


