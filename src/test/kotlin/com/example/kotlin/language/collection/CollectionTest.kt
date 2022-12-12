package language.collection;

import org.junit.jupiter.api.Test;

class CollectionTest {
    @Test
    fun testCollection() {
        val numbers = mutableListOf("one", "two", "three", "four")
        numbers.add("five")   // this is OK
        println(numbers)
        //numbers = mutableListOf("six", "seven")      // compilation error
    }

    @Test
    fun testCollection1() {
        val stringList = listOf("one", "two", "one")
        printAll(stringList)

        val stringSet = setOf("one", "two", "three")
        printAll(stringSet)
    }

    @Test
    fun testCollection2() {
        val words = "A long time ago in a galaxy far far away".split(" ")
        val shortWords = mutableListOf<String>()
        words.getShortWordsTo(shortWords, 3)
        println(shortWords)
    }

    @Test
    fun testCollection3() {
        val numbers = listOf("one", "two", "three", "four")
        println("Number of elements: ${numbers.size}")
        println("Third element: ${numbers.get(2)}")
        println("Fourth element: ${numbers[3]}")
        println("Index of element \"two\" ${numbers.indexOf("two")}")
    }

    data class Person(var name: String, var age: Int)

    @Test
    fun testCollection4() {
        val bob = Person("Bob", 31)
        val people = listOf(Person("Adam", 20), bob, bob)
        val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
        println(people == people2)
        bob.age = 32
        println(people == people2)
    }

    @Test
    fun testCollection5() {
        val numbers = setOf(1, 2, 3, 4)
        println("Number of elements: ${numbers.size}")
        if (numbers.contains(1)) println("1 is in the set")

        val numbersBackwards = setOf(4, 3, 2, 1)
        println("The sets are equal: ${numbers == numbersBackwards}")
    }

    @Test
    fun testCollection6() {
        val numbers = setOf(1, 2, 3, 4)  // LinkedHashSet is the default implementation
        val numbersBackwards = setOf(4, 3, 2, 1)

        println(numbers.first() == numbersBackwards.first())
        println(numbers.first() == numbersBackwards.last())
    }

    @Test
    fun testCollection7() {
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

        println("All keys: ${numbersMap.keys}")
        println("All values: ${numbersMap.values}")
        if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
        if (1 in numbersMap.values) println("The value 1 is in the map")
        if (numbersMap.containsValue(1)) println("The value 1 is in the map") // same as previous
    }

    @Test
    fun testCollection8() {
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
        val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)

        println("The maps are equal: ${numbersMap == anotherMap}")
    }

    @Test
    fun testCollection9() {
        val numbersMap = mutableMapOf("one" to 1, "two" to 2)
        numbersMap.put("three", 3)
        numbersMap["one"] = 11
        println(numbersMap)
    }

    fun printAll(strings: Collection<String>) {
        for (t in strings) print("$t ")
        println()
    }

    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }
        // throwing away the articles
        val articles = setOf("a", "A", "an", "An", "the", "The")
        shortWords -= articles
    }
}
