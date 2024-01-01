import kmp.createPiFast
import kmp.createPiSLow


fun main() {
   val word = "AABA@AABAABAAABA"

   println("PATTERN: $word")
   println("--------------CREATE PI SLOW-----------------")
   println(createPiSLow(word).contentToString())

   println("--------------CREATE PI FAST-----------------")
   println(createPiFast(word).contentToString())

   createPiFast(word).withIndex()
      .filter { it.value == 4 }
      .forEach{ println("index: ${it.index - 8}, value = ${it.value}") }
}
