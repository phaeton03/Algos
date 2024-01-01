package hash

/**
 * Реализовать хеш-таблицу, использующую метод цепочек - 5 байт
 */
class HashTable {
    var buckets: Array<Entry<String, String>?> = Array(10) { null }
    var size = 0
    var capacity = buckets.size
    val loadFactor = 0.75
    var threshold = capacity * loadFactor

    fun get(key: String): String? {
        val hash = key.hashCode()
        if (hash >= buckets.size || buckets[hash] == null) return null

        var currEntry = buckets[hash]
        while (currEntry!!.next != null) {
            if (key == currEntry.key) {
                return currEntry.value
            }
            currEntry = currEntry.next
        }

        return currEntry.value
    }

    fun put(key: String, value: String) {
        val idx = hash(key)
        var hasElement = false
        if (buckets[idx] == null) {
            buckets[idx] = Entry(key, value)
            size++
        } else {
            var curr = buckets[idx]
            while (curr != null) {
                if (key == curr.key) {
                    curr.value = value
                    hasElement = true
                    break
                }
                curr = curr.next
                size++
            }
            if (!hasElement) {
                val newEntry = Entry(key, value)
                newEntry.next = buckets[idx]
                buckets[idx] = newEntry
                size++
            }
        }


        if (buckets.size > threshold) {
            rehash()
        }
    }

    fun remove(key: String): String? {
        val hash = hash(key)

        if (hash >= buckets.size || buckets[hash] == null) return null

        var currEntry = buckets[hash]

        var prevEntry = currEntry
        while (currEntry != null) {
            if (key == currEntry.key) {
                if (prevEntry == currEntry) {
                    buckets[hash] = currEntry.next

                    size--
                    return currEntry.value
                }
                prevEntry!!.next = currEntry.next

                size--
                return currEntry.value
            }
            prevEntry = currEntry
            currEntry = currEntry.next
        }

        return null
    }

    fun rehash() {
        val oldBuckets = buckets
        buckets = Array(oldBuckets.size * 2 + 1) { null }

        for (entryList in oldBuckets) {
            if (entryList != null) {
                var curr = entryList
                while (curr != null) {
                    val idx = hash(curr.key)
                    val dest = buckets[idx]
                    if (dest == null) {
                        buckets[idx] = Entry(curr.key, curr.value)
                    } else {
                        val newEntry = Entry(curr.key, curr.value)
                        newEntry.next = dest
                        buckets[idx] = newEntry
                    }
                    curr = curr.next
                }
            }
        }

        capacity = buckets.size
        threshold = capacity * loadFactor
    }

    fun hash(key: String): Int {
        return key.hashCode() % capacity
    }
}

class Entry<K, V>(
    var key: K,
    var value: V,
    var next: Entry<K, V>? = null
)