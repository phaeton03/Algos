import compression.compressFile
import compression.decompresFile

fun main() {
    compressFile("/Users/niko/IdeaProjects/Algos/compression-algos_27/src/main/resources/text.txt",
        "/Users/niko/IdeaProjects/Algos/compression-algos_27/src/main/resources/output.txt.huffman")

    decompresFile("/Users/niko/IdeaProjects/Algos/compression-algos_27/src/main/resources/output.txt.huffman",
        "/Users/niko/IdeaProjects/Algos/compression-algos_27/src/main/resources/decompress2.txt.huffman")
}
