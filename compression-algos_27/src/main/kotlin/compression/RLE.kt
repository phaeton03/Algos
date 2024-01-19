package compression

import huffman_tree.Node
import priority_queue.PriorityQueue
import java.io.File
import java.io.FileOutputStream
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Run length encoding
 */

fun compressFile(inputFile: String, outputFile: String) {
    try {
        val file = File(inputFile)
        val readBytes = file.inputStream().readBytes()
        val arch = compressBytes(readBytes)

        FileOutputStream(outputFile).use { outputStream ->
            outputStream.write(arch)
        }
    } catch (ex: Exception) {
        println("Error: ${ex.message}")
    }
}

fun decompresFile(archFileName: String, outputFile: String) {
    try {
        val file = File(archFileName)
        val arch = file.inputStream().readBytes()
        val data = decompressBytes(arch)

        FileOutputStream(outputFile).use { outputStream ->
            outputStream.write(data)
        }
    } catch (ex: Exception) {
        println("Error: ${ex.message}")
    }
}

fun decompressBytes(arch: ByteArray): ByteArray {
    val (dataLength, startIndex, freqs) = parseHeader(arch)

    val root = createHuffmanTree(freqs)
    val data = decompressBytes(arch, startIndex, dataLength, root)

    return data
}

fun decompressBytes(arch: ByteArray, startIndex: Int, dataLength: Int, root: Node): ByteArray {
    var size = 0
    var curr = root
    var bit = 1
    val data = ByteArray(dataLength)
    for (i in startIndex until arch.size) {
        while (bit <= 128) {
            if ((arch[i] and bit.toByte()).equals(0)) {
                curr = curr.bit0!!
            } else {
                curr = curr.bit1!!
            }

            if (curr.bit0 == null) {
                if (size < dataLength) {
                    data[size++] = curr.symbol!!
                }
                curr = root
            }

            bit = bit shl 1
        }
    }
    return data
}

fun parseHeader(arch: ByteArray): Triple<Int, Int, IntArray> {
    val dataLength: Int = (
            arch[0] or
                    (arch[1].toInt() shl 8).toByte() or
                    (arch[2].toInt() shl 16).toByte() or
                    (arch[3].toInt() shl 24).toByte()
            )
        .toInt()

    val freqs = IntArray(256)
    var count = arch[4]
    if (count.equals(0)) count = 256.toByte()
    for (i in 0 until count) {
        val symbol = arch[5 + i * 2]
        freqs[symbol.toInt()] = arch[5 + i * 2 + 1].toInt()
    }

    val startIndex = 4 + 1 + 2 * (count-1)

    return Triple(dataLength, startIndex, freqs)
}

private fun compressBytes(data: ByteArray): ByteArray {
    val freqs = calculateFreqs(data)
    val head = createHeader(data.size, freqs)
    val root = createHuffmanTree(freqs)
    val codes = createHuffmanCodes(root)
    val bits = compress(data, codes)

    return head + bits
}

private fun compress(data: ByteArray, codes: Array<String>): ByteArray {
    val bits = mutableListOf<Byte>()
    var sum: Byte = 0
    var bit: Byte = 1

    for (symbol in data) {
        for (c in codes[symbol.toInt()]) {
            if (c == '1') {
                sum = sum or bit
            }
            if (bit and 128.toByte() == bit) {
                bits.add(sum)
                sum = 0
                bit = 1
            } else {
                bit = (bit.toInt() shl 1).toByte()
            }
        }
        if (bit > 1) {
            bits.add(sum)
        }
    }
    return bits.toByteArray()
}

private fun createHuffmanCodes(root: Node): Array<String> {
    val codes = Array(256) { "" }

    fun next(node: Node, code: String) {
        if (node.bit0 == null || node.bit1 == null) {
            codes[node.symbol!!.toInt()] = code
        } else {
            next(node.bit0, code + "0")
            next(node.bit1, code + "1")
        }
    }
    next(root, "")

    return codes
}

private fun createHuffmanTree(freqs: IntArray): Node {
    val pq = PriorityQueue()

    freqs.withIndex()
        .filter { it.value > 0 }
        .forEach {
            pq.enqueue(
                Node(
                    priority = freqs[it.index],
                    symbol = it.value.toByte()
                )
            )
        }

    while (pq.size() > 1) {
        val bit0: Node = pq.dequeue()
        val bit1: Node = pq.dequeue()
        val parent = Node(bit0, bit1)
        parent.priority = bit0.priority + bit1.priority

        pq.enqueue(parent)
    }


    return pq.dequeue()
}

private fun createHeader(size: Int, freqs: IntArray): ByteArray {
    val head = mutableListOf<Byte>()
    head.apply {
        addBytesFromInt(size, 0)
        addBytesFromInt(size, 8)
        addBytesFromInt(size, 16)
        addBytesFromInt(size, 24)

        val count = freqs.count { it > 0 }
        add(count.toByte())
        freqs.forEachIndexed { index, value ->
            if (value > 0) {
                add(index.toByte())
                add(value.toByte())
            }
        }
    }

    return head.toByteArray()
}

private fun calculateFreqs(data: ByteArray): IntArray {
    val result = IntArray(256)
    for (b in data) {
        result[b.toInt()]++
    }

    return result
}


private fun MutableList<Byte>.addBytesFromInt(value: Int, shift: Int) {
    add(((value shr shift) and 255).toByte())
}

