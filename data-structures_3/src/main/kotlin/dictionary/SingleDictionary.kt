package dictionary

class SingleDictionary<K, V> : IDictionary<K, V> {
    private var entries: Array<Pair<K, V>?> = emptyArray()
    override fun put(key: K, value: V): V {
        resize()
        val entry = Pair(key, value)
        entries[size() - 1] = entry
        return entry.second
    }

    override fun remove(key: K): V? {
        val removeElement = get(key)
        val indexToRemove = entries.indexOfFirst { it?.first == key }

        if (indexToRemove != -1) {
            entries[indexToRemove] = null
        }

        return removeElement
    }

    private fun resize() {
        val newEntries = arrayOfNulls<Pair<K, V>>(entries.size + 1)
        entries.forEachIndexed { index, value -> newEntries[index] = value }
        entries = newEntries
    }

    override fun get(key: K): V? = entries.first { it?.first == key }?.second

    override fun add(key: K, value: V, index: Int): V? {
        if (index < 0) return null
        val size = if (index > size()) index + 1 else size() + 1

        val newEntries = entries.sliceArray(0 until index) +
                Pair(key, value) +
                entries.sliceArray(index + 1 until size)

        entries = newEntries


        return value
    }

    override fun size(): Int {
        return entries.size
    }

    override fun isEmpty(): Boolean = entries.isEmpty()
}