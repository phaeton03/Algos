package aho_corasick

import java.util.*


val root = TrieNode()

class TrieNode(
    var value: String = "",
    var pattern: String = "",
    var isEnd: Boolean = false,
    var finalLink: TrieNode? = null,
    var suffixLink: TrieNode? = null,
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
) {
    fun isExist(char: Char): Boolean {
        return children[char] != null
    }
}

fun searchPattern(pattern: String): List<String> {
    val matches = mutableListOf<String>()
    var node: TrieNode? = root
    for (ch in pattern) {
        while (node != null && !node.children.containsKey(ch)) {
            node = node.suffixLink
        }
        if (node == null) {
            node = root
            continue
        }
        node = node.children[ch]
        if (node!!.pattern.isNotEmpty()) {
            matches.add(node.pattern)
        }

        var finals = node.finalLink
        while (finals != null) {
            matches.add(finals.pattern)
            finals = finals.finalLink
        }
    }
    return matches
}

fun buildTree(word: String, pattern: String) {
    val splitWords = pattern.split("\\s+")
    var currNode = root

    for (w in splitWords) {
        currNode = root
        for (ch in w) {
            if (!root.isExist(ch)) {
                currNode.children[ch] = TrieNode(
                    value = ch + currNode.value,
                )
            }
            currNode = currNode.children[ch]!!
        }
        currNode.isEnd = true
        currNode.pattern = w
    }
}

fun createSuffixAndFinalLinks() {
    val queue: Queue<TrieNode> = LinkedList(root.children.values.onEach { it.suffixLink = root })

    while (queue.isNotEmpty()) {
        val nodeParent = queue.poll()
        for (c in nodeParent.children.keys) {
            val child = nodeParent.children[c]
            queue.offer(child)
            var suffixLink = nodeParent.suffixLink
            while (suffixLink != null && !suffixLink.children.containsKey(c)) {
                suffixLink = suffixLink.suffixLink
            }
            child!!.suffixLink = suffixLink?.children?.get(c) ?: root
            child.finalLink =
                if (child.suffixLink!!.pattern.isNotEmpty()) child.suffixLink else child.suffixLink!!.finalLink
        }
    }
}

fun search(node: TrieNode, pattern: String): TrieNode? {
    var currNode = node
    for (ch in pattern) {
        if (currNode.children[ch] == null) {
            return null
        }
        currNode = currNode.children[ch]!!
    }
    return currNode
}

fun getPrefix(word: String): String {
    var prefix = ""
    if (word.length == 1) return prefix
    for (i in 1 until word.length) {
        prefix += word[i]
    }
    return prefix
}