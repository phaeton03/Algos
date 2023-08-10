package util

import java.io.File

class FilesUtil(private val pathDirectory: String) {

    fun getFilesInput(): List<String> {
        val directory = File(pathDirectory)

        return  directory.walk()
                    .sortedBy { it.name }
                    .filter { it.isFile }
                    .filter { it.extension == "in" }
                    .map { it.readText().trim() }
                    .toList()
    }

    fun getFilesOutput(): List<String> {
        val directory = File(pathDirectory)

        return directory.walk()
            .sortedBy { it.name }
            .filter { it.isFile }
            .filter { it.extension == "out" }
            .map { it.readText().trim() }
            .toList()
    }
}