package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import scala.collection.mutable._
import com.codahale.jerkson.ParsingException

class MutableCollectionSupportSpec extends Specification {
  "A mutable.ArraySeq[Int]" should {
    "generates a JSON array of ints" in {
      generate(ArraySeq(1, 2, 3)).must_==("[1,2,3]")
    }


    "is parsable from a JSON array of ints" in {
      parse[ArraySeq[Int]]("[1,2,3]").must_==(ArraySeq(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[ArraySeq[Int]]("[]").must_==(ArraySeq.empty[Int])
    }
  }


  "A mutable.Queue[Int]" should {
    "generates a JSON array" in {
      generate(Queue(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Queue[Int]]("[1,2,3]").must_==(Queue(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Queue[Int]]("[]").must_==(new Queue[Int]())
    }
  }

  "A mutable.ListBuffer[Int]" should {
    "generates a JSON array" in {
      generate(ListBuffer(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[ListBuffer[Int]]("[1,2,3]").must_==(ListBuffer(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[ListBuffer[Int]]("[]").must_==(ListBuffer.empty[Int])
    }
  }

  "A mutable.ArrayBuffer[Int]" should {
    "generates a JSON array" in {
      generate(ArrayBuffer(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[ArrayBuffer[Int]]("[1,2,3]").must_==(ArrayBuffer(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[ArrayBuffer[Int]]("[]").must_==(ArrayBuffer.empty[Int])
    }
  }

  "A mutable.BitSet" should {
    "generates a JSON array" in {
      generate(BitSet(1)).must_==("[1]")
    }

    "is parsable from a JSON array of ints" in {
      parse[BitSet]("[1,2,3]").must_==(BitSet(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[BitSet]("[]").must_==(BitSet.empty)
    }
  }

  "A mutable.HashSet[Int]" should {
    "generates a JSON array" in {
      generate(HashSet(1)).must_==("[1]")
    }

    "is parsable from a JSON array of ints" in {
      parse[HashSet[Int]]("[1,2,3]").must_==(HashSet(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[HashSet[Int]]("[]").must_==(HashSet.empty[Int])
    }
  }

  "A mutable.LinkedHashSet[Int]" should {
    "generates a JSON array" in {
      generate(LinkedHashSet(1)).must_==("[1]")
    }

    "is parsable from a JSON array of ints" in {
      parse[LinkedHashSet[Int]]("[1,2,3]").must_==(LinkedHashSet(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[LinkedHashSet[Int]]("[]").must_==(LinkedHashSet.empty[Int])
    }
  }

  "A mutable.Map[String, Int]" should {
    "generates a JSON object" in {
      generate(Map("one" -> 1)).must_==("""{"one":1}""")
    }

    "is parsable from a JSON object with int field values" in {
      parse[Map[String, Int]]("""{"one":1}""").must_==(Map("one" -> 1))
    }

    "is parsable from an empty JSON object" in {
      parse[Map[String, Int]]("{}").must_==(Map.empty[String, Int])
    }
  }

  "A mutable.Map[String, Any]" should {
    "is not parsable from an empty JSON object in a JSON array" in {
      parse[Map[String, Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "A mutable.HashMap[String, Int]" should {
    "generates a JSON object" in {
      generate(HashMap("one" -> 1)).must_==("""{"one":1}""")
    }

    "is parsable from a JSON object with int field values" in {
      parse[HashMap[String, Int]]("""{"one":1}""").must_==(HashMap("one" -> 1))
    }

    "is parsable from an empty JSON object" in {
      parse[HashMap[String, Int]]("{}").must_==(HashMap.empty[String, Int])
    }
  }

  "A mutable.LinkedHashMap[String, Int]" should {
    "generates a JSON object" in {
      generate(LinkedHashMap("one" -> 1)).must_==("""{"one":1}""")
    }

    "is parsable from a JSON object with int field values" in {
      parse[LinkedHashMap[String, Int]]("""{"one":1}""").must_==(LinkedHashMap("one" -> 1))
    }

    "is parsable from an empty JSON object" in {
      parse[LinkedHashMap[String, Int]]("{}").must_== (LinkedHashMap.empty[String, Int])
    }
  }
}

