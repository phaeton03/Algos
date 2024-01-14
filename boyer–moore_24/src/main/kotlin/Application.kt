import algorithms.fullScan
import algorithms.fullScanPrefixOptimization
import algorithms.fullScanReverse
import algorithms.searchBoyerMoore

fun main() {
   val word = "ABRACACDACBREA"
   val pattern1 = "CAC"
   val pattern2 = "ABRA"
   val pattern3 = "AC"
   val pattern4 = "ACCDAC"

   println("---------------------FULL SCAN-------------------")
   var startTime = System.currentTimeMillis()
   println(fullScan(word, pattern1))
   println(fullScan(word, pattern2))
   println(fullScan(word, pattern3))
   println(fullScan(word, pattern4))
   var endTime = System.currentTimeMillis()
   println("duration = " + (endTime - startTime))

   println("---------------------FULL SCAN REVERSE-------------------")
   startTime = System.currentTimeMillis()
   println(fullScanReverse(word, pattern1))
   println(fullScanReverse(word, pattern2))
   println(fullScanReverse(word, pattern3))
   println(fullScanReverse(word, pattern4))
   endTime = System.currentTimeMillis()
   println("duration = " + (endTime - startTime))

   println("---------------------FULL SCAN PREFIX OPTIMIZATION-------------------")
   startTime = System.currentTimeMillis()
   println(fullScanPrefixOptimization(word, pattern1))
   println(fullScanPrefixOptimization(word, pattern2))
   println(fullScanPrefixOptimization(word, pattern3))
   println(fullScanPrefixOptimization(word, pattern4))
   endTime = System.currentTimeMillis()
   println("duration = " + (endTime - startTime))

   println("---------------------SEARCH BOYER-MOORE-------------------")
   startTime = System.currentTimeMillis()
   println(searchBoyerMoore(word, pattern1))
   println(searchBoyerMoore(word, pattern2))
   println(searchBoyerMoore(word, pattern3))
   println(searchBoyerMoore(word, pattern4))
   endTime = System.currentTimeMillis()
   println("duration = " + (endTime - startTime))
}
