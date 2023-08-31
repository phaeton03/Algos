package dictionary

class FactorDictionary<K, V> : IDictionary<K, V> {
    private val factor = 2
    private var pointer = 0
    private var entries = arrayOfNulls<Pair<K, V>>(10)
    override fun put(key: K, value: V): V? {
        if (pointer == entries.size) {
            resize()
        }

        entries[pointer] = Pair(key, value)

        pointer++

        return value
    }

    override fun add(key: K, value: V, index: Int): V? {
        if (index < 0) return null

        val size = if (index > size()) index * factor else size() * factor

        val newEntries = entries.sliceArray(0 until index) +
                Pair(key, value) +
                entries.sliceArray(index + 1 until size)

        entries = newEntries

        return value
    }

    override fun get(key: K): V? {
        return entries.first { it == key }?.second
    }

    override fun remove(key: K): V? {
        val removeElement = get(key)
        val indexToRemove = entries.indexOfFirst { it?.first == key }

        if (indexToRemove != -1) {
            entries[indexToRemove] = null
        }

        return removeElement
    }

    override fun size(): Int {
        return entries.size
    }

    override fun isEmpty(): Boolean {
        return pointer == 0
    }

    private fun resize() {
        entries = entries.copyOf(entries.size * factor + 1)
    }
}