package dictionary

interface IDictionary<K, V> {
    fun put(key: K, value: V): V?

    fun add(key: K, value: V, index: Int): V?
    fun get(key: K): V?

    fun remove(key: K): V?

    fun size(): Int

    fun isEmpty(): Boolean
}