/**
 * Реализовать хеш-таблицу, использующую метод цепочек - 5 байт
 */
class HashTable {
    var buckets: Array<Entry<String, String>?> = Array(10) { null }
    var size = 0
    var capacity = buckets.size
    val loadFactor = 0.75
    var threshold = capacity * loadFactor

    fun insert(key: String, value: String) {
        var idx = hash(key, 0)
        var i = 0
        var freezeCount = 0
        var freezeI = -1

        while (buckets[idx] != null) {
            if (buckets[idx]!!.isFreeze) {
                freezeCount++
                freezeI = i
            }
            if (buckets[idx]!!.key == key && !buckets[idx]!!.isFreeze) {
                buckets[idx]!!.value = value

                return
            }
            idx = hash(key, ++i)
        }


        if (freezeI > 0) {
            val freezeIdx = hash(key, freezeI - freezeCount)
            buckets[freezeIdx] = Entry(key, value)
        } else {
            buckets[idx] = Entry(key, value)
            size++
        }

        if (size > threshold) {
            rehash()
        }
    }

    fun search(key: String): String? {
        var i = 0
        var freezeCount = 0
        var freezeI = -1
        var idx = hash(key, i)

        while (buckets[idx] != null) {
            if (buckets[idx]!!.isFreeze) {
                freezeCount++
                freezeI = i
            }
           if (buckets[idx]!!.key == key && !buckets[idx]!!.isFreeze) {
               val result = buckets[idx]!!.value
               if (freezeI > 0) {
                   val temp = buckets[idx]
                   val freezeIdx = hash(key, freezeI - freezeCount)
                   buckets[idx] = buckets[freezeIdx]
                   buckets[freezeIdx] = temp
               }
               return result
           }
           idx = hash(key, ++i)
        }

        return null
    }

    fun remove(key: String): String? {
        var i = 0
        var idx = hash(key, i)

        while (buckets[idx] != null) {
            if (buckets[idx]!!.key == key && !buckets[idx]!!.isFreeze) {
                buckets[idx]!!.isFreeze = true

                return buckets[idx]!!.value
            }
            idx = hash(key, ++i)
        }

        return null
    }

    fun rehash() {
        val oldBuckets = buckets
        buckets = Array(capacity * 2 + 1) { null }
        capacity *= 2
        threshold = capacity * loadFactor
        size = 0

        for (entry in oldBuckets) {
            if (entry != null && !entry.isFreeze) {
                insert(entry.key, entry.value)
            }
        }

    }

    fun hash(key: String, i: Int): Int {
        val hash = key.hashCode() % capacity
        return (hash % capacity + i) % capacity
    }
}

class Entry<K, V>(
    var key: K,
    var value: V,
    var isFreeze: Boolean = false,
)