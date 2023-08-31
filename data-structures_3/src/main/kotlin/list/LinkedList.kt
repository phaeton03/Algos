package list

class LinkedList<T> : List<T> {
    private var chain: Chain<T> = Chain()
    private val firstChain: Chain<T> = chain
    private var size = 0L
    override fun add(element: T) {
        with(chain) {
            if (currElement == null) {
                currElement = element
            } else {
                nextElement = Chain(element)
                chain = nextElement!!
            }
        }

        size++
    }

    override fun size(): Long = size

    override fun get(): T? {
        return firstChain.currElement
    }

    override fun get(index: Int): T? {
        if (index >= size || index < 0) return null

        var counter = 0
        var result = firstChain

        while (counter != index) {
            result = result.nextElement!!
            counter++
        }

        return result.currElement
    }
}

class Chain<T>(
    var currElement: T? = null,
    var nextElement: Chain<T>? = null
)