rootProject.name = "algos"

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

include("common")
include("lucky-tickets_1")
include("algebraic-algorithms_2")
include("data-structures_3")
include("simple-sorts-6")
include("heap-sort-7")
include("quick-sort_8")
include("linear-sort-9")
include("binary-tree-10")
include("cartesian-tree-and-others_12")
include("hash-function-13")
include("hash-function-part2-14")
include("trie_15")
include("kosaraju_18")
include("demukron_19")
include("minimal_skeleton_20")
include("shortest_path_21")
include("boyer–moore_24")
include("aho-corasick_25")
include("knuth-morris-pratt_26")

