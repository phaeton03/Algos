import exponential_algorithm.fiboIterative
import org.junit.jupiter.api.Test
import util.FilesUtil
import kotlin.test.assertContentEquals

class FiboTest {
    private val directoryPath = "./src/test/resources/4.Fibo"
    private val filesUtil: FilesUtil = FilesUtil(directoryPath)

    @Test
    fun testFiboIterative() {
        val actualResult = filesUtil.getFilesInput().map { fiboIterative(it.toInt()) }

        println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toLong() }

        println(expectedResult)

        println("actualResult: $actualResult\nexpectedResult: $expectedResult")

        assertContentEquals(actualResult, expectedResult)
    }
}