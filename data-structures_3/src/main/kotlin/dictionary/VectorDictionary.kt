package dictionary

class VectorDictionary<K, V>() : IDictionary<K, V> {
    private var entries = arrayOfNulls<Pair<K, V>>(10)
    private val vector = 10
    private var pointer = 0
    override fun put(key: K, value: V): V? {
        if (pointer == size() - 1) {
            resize()
        }
        val entry = Pair(key, value)
        entries[pointer] = entry
        pointer++

        return entry.second
    }

    override fun get(key: K): V? {
        return entries.first { it?.first == key }?.second
    }

    override fun remove(key: K): V? {
        val keyIndex = entries.indexOfFirst { it?.first == key }
        val removedElement = get(key)
        entries = entries.sliceArray(0 until keyIndex) +
                entries.sliceArray(keyIndex until entries.size)
        pointer--

        return removedElement
    }

    override fun add(key: K, value: V, index: Int): V? {
        if (index < 0) return null

        val size = if (index > size()) index + vector else size() + vector

        val newEntries = entries.sliceArray(0 until index) +
                Pair(key, value) +
                entries.sliceArray(index + 1 until size)

        entries = newEntries

        return value
    }

    override fun size(): Int = entries.size

    override fun isEmpty(): Boolean = entries.isEmpty()

    private fun resize() {
        entries = entries.copyOf(entries.size + vector)
    }
}