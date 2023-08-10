import org.junit.jupiter.api.Test
import util.FilesUtil
import kotlin.test.assertContentEquals

internal class LuckyTicketsTest {
    private val directoryPath = "./src/test/resources/tickets"
    private val filesUtil: FilesUtil = FilesUtil(directoryPath)

    @Test
    fun testLuckyTickets() {
        val actualResult = filesUtil.getFilesInput().map { luckyTickets(it.toInt()) }

        println(filesUtil.getFilesInput())

        val expectedResult = filesUtil.getFilesOutput().map { it.toLong() }

        println(expectedResult)

        println("actualResult: $actualResult\nexpectedResult: $expectedResult")

        assertContentEquals(actualResult, expectedResult)
    }
}