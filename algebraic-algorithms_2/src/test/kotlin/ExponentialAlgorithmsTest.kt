import exponential_algorithm.powerBinaryIterative
import exponential_algorithm.powerBinaryRecursive
import exponential_algorithm.powerIterativeJunior
import exponential_algorithm.powerMultipleAlgo
import org.junit.jupiter.api.Test
import util.FilesUtil
import kotlin.math.abs

class ExponentialAlgorithmsTest {
    private val directoryPath = "./src/test/resources/3.Power"
    private val filesUtil: FilesUtil = FilesUtil(directoryPath)

    private val eps = 0.0005
    @Test
    fun testPowerIterativeJunior() {
        val actualResult = filesUtil.getFilesInput()
            .map {
                println(it)
                it.split("\n") }
            .map {
                println("${it[0]}, it[1] = ${it[1]}")
                powerIterativeJunior(it[0].toDouble(), it[1].toLong()) }

          println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toDouble() }

        actualResult.mapIndexed { index, result -> abs(result - expectedResult[index]) }
            .forEach { assert(it < eps) }

        println("actual result $actualResult")
        println("expected result $expectedResult")
    }

    @Test
    fun testPowerMultipleAlgo() {
        val actualResult = filesUtil.getFilesInput()
            .map {
                it.split("\n") }
            .map {
                powerMultipleAlgo(it[0].toDouble(), it[1].toLong()) }

        println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toDouble() }

        actualResult.mapIndexed { index, result -> abs(result - expectedResult[index]) }
            .forEach { assert(it < eps) }

        println("actual result $actualResult")
        println("expected result $expectedResult")
    }

    @Test
    fun testPowerBinaryRecursive() {
        val actualResult = filesUtil.getFilesInput()
            .map {
                it.split("\n") }
            .map {
                powerBinaryRecursive(it[0].toDouble(), it[1].toLong()) }

        println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toDouble() }

        actualResult.mapIndexed { index, result -> abs(result - expectedResult[index]) }
            .forEach { assert(it < eps) }

        println("actual result $actualResult")
        println("expected result $expectedResult")
    }

    @Test
    fun testPowerBinaryIterative() {
        val actualResult = filesUtil.getFilesInput()
            .map {
                it.split("\n") }
            .map {
                powerBinaryIterative(it[0].toDouble(), it[1].toLong()) }

        println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toDouble() }

        actualResult.mapIndexed { index, result -> abs(result - expectedResult[index]) }
            .forEach { assert(it < eps) }

        println("actual result $actualResult")
        println("expected result $expectedResult")
    }
}