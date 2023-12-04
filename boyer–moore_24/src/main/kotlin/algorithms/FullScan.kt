package algorithms

/**
 * - Написать алгоритм поиска подстроки полным перебором, 2 байта.
 */
fun fullScan(word: String, pattern: String): Int {

    val end = word.length - pattern.length
    for (chIdx in 0..end) {
        var mIdx = 0
        while (mIdx < pattern.length && word[chIdx + mIdx] == pattern[mIdx]) {

            mIdx++
        }

        if (mIdx == pattern.length) {
            return chIdx
        }
    }

    return -1
}

fun fullScanReverse(word: String, pattern: String): Int {
    val end = word.length - pattern.length
    for (chIdx in 0..end) {
        var mIdx = pattern.length - 1
        while (mIdx > -1 && word[chIdx + mIdx] == pattern[mIdx]) {
            mIdx--
        }

        if (mIdx == -1) {
            return chIdx
        }
    }

    return -1
}

fun fullScanPrefixOptimization(word: String, pattern: String): Int {
    val shift = createShift(pattern)

    val end = word.length - pattern.length
    var chIdx = 0
    while (chIdx <= end) {
        var mIdx = pattern.length - 1
        while (mIdx > -1 && word[chIdx + mIdx] == pattern[mIdx]) {
            mIdx--
        }

        if (mIdx == -1) {
            return chIdx
        }

        chIdx += shift[word[chIdx + mIdx].code]
    }

    return -1
}

fun searchBoyerMoore(word: String, pattern: String): Int {
    val shiftSuffix = createSuffixShift(pattern)
    val shiftPrefix = createShift(pattern)

    val end = word.length - pattern.length
    var chIdx = 0
    while (chIdx <= end) {
        var mIdx = pattern.length - 1
        while (mIdx > -1 && word[chIdx + mIdx] == pattern[mIdx]) {
            mIdx--
        }

        if (mIdx == -1) {
            return chIdx
        }

        if (mIdx == pattern.length - 1) { 
            chIdx += shiftPrefix[word[chIdx + mIdx].code]
        } else {
            chIdx += shiftSuffix[mIdx]
        }
    }

    return -1
}

fun createShift(pattern: String): IntArray {
    val shift = IntArray(128) { pattern.length }

    for (p in 0 until pattern.length - 1) {
        shift[pattern[p].code] = pattern.length - p - 1
    }

    return shift
}

fun createSuffixShift(pattern: String): IntArray {
    val shift = IntArray(pattern.length) { pattern.length }.apply { this[0] = createShift(pattern)[pattern[0].code] }

    var match = ""
    val patternLastIdx = pattern.length - 1
    var firstZeroIdx = 0
    for (i in pattern.indices) {
        match += pattern[patternLastIdx - i]
        val matchIdx = fullScanPrefixOptimization(pattern, match)
        if (matchIdx == -1) {
            firstZeroIdx = i
            break
        } else {
            shift[i] = matchIdx
        }
    }

    var k = 0
    var l = patternLastIdx
    var shiftSuffix = pattern.length
    for (i in firstZeroIdx..patternLastIdx) {
        if (k < l) {
            if (pattern[k] != pattern[l]) {
                break
            } else {
                shiftSuffix--
            }
            k++
            l--
        } else {
            break
        }
    }

    for (i in firstZeroIdx..patternLastIdx) {
        shift[i] = shiftSuffix
    }

    return shift
}