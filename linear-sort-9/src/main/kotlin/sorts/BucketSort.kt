package sorts


/**
 * BS1. +1 байт. Реализовать алгоритм блочной сортировки BucketSort.
 */
fun bucketSort(array: IntArray): IntArray {
    val N = array.size
    val max = array.max() + 1

    val buckets = Array<Node?>(N) { null }
    val resultArray = IntArray(N)

    for ((ind, el) in array.withIndex()) {
        val bucketInd = el * N / max

        val currBucket = buckets[bucketInd]
        buckets[bucketInd] = Node(value = el, nextNode = currBucket)
        var item = buckets[bucketInd]

        while (item?.nextNode != null) {
            if (item.value > item.nextNode!!.value) {
                val temp = item.value
                item.value = item.nextNode!!.value
                item.nextNode!!.value = temp

                item = item.nextNode
            } else break
        }

    }

    var resInd = 0

    for (i in buckets.indices) {
        var bucket = buckets[i]
        while (bucket != null) {
            resultArray[resInd] = bucket.value
            bucket = bucket.nextNode
            resInd++
        }
    }

    return resultArray
}

class Node(
    var value: Int,
    var nextNode: Node? = null
)