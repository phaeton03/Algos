package dictionary

class MatrixDictionary<K,V>: IDictionary<K,V> {
    private var entries = Array<Array<Pair<K,V>?>>(10) { Array(10) { null } }
    override fun put(key: K, value: V): V? {
        TODO("Not yet implemented")
    }

    override fun add(key: K, value: V, index: Int): V? {
        TODO("Not yet implemented")
    }

    override fun get(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun remove(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}