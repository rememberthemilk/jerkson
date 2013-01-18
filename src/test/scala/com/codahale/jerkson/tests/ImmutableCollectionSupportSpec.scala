package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import scala.collection.immutable._
import com.codahale.jerkson.ParsingException

class ImmutableCollectionSupportSpec extends Specification {
  "An immutable.Seq[Int]" should {
    "generates a JSON array of ints" in {
      generate(Seq(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Seq[Int]]("[1,2,3]").must_==(Seq(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Seq[Int]]("[]").must_==(Seq.empty[Int])
    }
  }

  "An immutable.List[Int]" should {
    "generates a JSON array of ints" in {
      generate(List(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[List[Int]]("[1,2,3]").must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[List[Int]]("[]").must_==(List.empty[Int])
    }
  }

  "An immutable.IndexedSeq[Int]" should {
    "generates a JSON array of ints" in {
      generate(IndexedSeq(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[IndexedSeq[Int]]("[1,2,3]").must_==(IndexedSeq(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[IndexedSeq[Int]]("[]").must_==(IndexedSeq.empty[Int])
    }
  }

  "An immutable.TreeSet[Int]" should {
    "generates a JSON array" in {
      generate(TreeSet(1)).must_==("[1]")
    }

    // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeSet instances

    /**
     * I think all this would take is a mapping from Class[_] to Ordering, which
     * would need to have hard-coded the various primitive types, and then add
     * support for Ordered and Comparable classes. Once we have the Ordering,
     * we can pass it in manually to a builder.
     */
    
    "is parsable from a JSON array of ints" in {
      parse[TreeSet[Int]]("[1,2,3]").must_==(TreeSet(1, 2, 3))
    }.pendingUntilFixed("See above comment")

    "is parsable from an empty JSON array" in {
      parse[TreeSet[Int]]("[]").must_==(TreeSet.empty[Int])
    }.pendingUntilFixed("See above comment")
  }

  "An immutable.HashSet[Int]" should {
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

  "An immutable.BitSet" should {
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

  "An immutable.TreeMap[String, Int]" should {
    "generates a JSON object" in {
      generate(TreeMap("one" -> 1)).must_==("""{"one":1}""")
    }

    // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeMap instances

    /**
     * I think all this would take is a mapping from Class[_] to Ordering, which
     * would need to have hard-coded the various primitive types, and then add
     * support for Ordered and Comparable classes. Once we have the Ordering,
     * we can pass it in manually to a builder.
     */
    
    "is parsable from a JSON object with int field values" in {
      parse[TreeMap[String, Int]]("""{"one":1}""").must_==(TreeMap("one" -> 1))
    }.pendingUntilFixed("See above comment")

    "is parsable from an empty JSON object" in {
      parse[TreeMap[String, Int]]("{}").must_==(TreeMap.empty[String, Int])
    }.pendingUntilFixed("See above comment")
  }

  "An immutable.HashMap[String, Int]" should {
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

  "An immutable.HashMap[String, Any]" should {
    "generates a JSON object" in {
      generate(HashMap[String, Any]("one" -> 1)).must_==("""{"one":1}""")
    }

    "is parsable from a JSON object with int field values" in {
      parse[HashMap[String, Any]]("""{"one":1}""").must_==(HashMap("one" -> 1))
    }

    "is parsable from an empty JSON object" in {
      parse[HashMap[String, Any]]("{}").must_==(HashMap.empty[String, Any])
    }

    "is not parsable from an empty JSON object in a JSON array" in {
      parse[HashMap[String, Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "An immutable.Map[Int, String]" should {
    "generates a JSON object" in {
      generate(Map(1 -> "one")).must_==("""{"1":"one"}""")
    }

    "is parsable from a JSON object with decimal field names and string field values" in {
      parse[Map[Int, String]]("""{"1":"one"}""").must_==(Map(1 -> "one"))
    }

    "is not parsable from a JSON object with non-decimal field names" in {
      parse[Map[Int, String]]("""{"one":"one"}""").must(throwA[ParsingException])
    }

    "is parsable from an empty JSON object" in {
      parse[Map[Int, String]]("{}").must_==(Map.empty[Int, String])
    }
  }

  "An immutable.Map[Int, Any]" should {
    "is not parsable from an empty JSON object in a JSON array" in {
      parse[Map[Int, Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "An immutable.IntMap[Any]" should {
    "is not parsable from an empty JSON object in a JSON array" in {
      parse[IntMap[Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "An immutable.LongMap[Any]" should {
    "is not parsable from an empty JSON object in a JSON array" in {
      parse[LongMap[Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "An immutable.Map[Long, Any]" should {
    "is not parsable from an empty JSON object in a JSON array" in {
      parse[Map[Long, Any]]("[{}]").must(throwA[ParsingException])
    }
  }

  "An immutable.Map[Long, String]" should {
    "generates a JSON object" in {
      generate(Map(1L -> "one")).must_==("""{"1":"one"}""")
    }

    "is parsable from a JSON object with decimal field names and string field values" in {
      parse[Map[Long, String]]("""{"1":"one"}""").must_==(Map(1L -> "one"))
    }

    "is not parsable from a JSON object with non-decimal field names" in {
      parse[Map[Long, String]]("""{"one":"one"}""").must(throwA[ParsingException])
    }

    "is parsable from an empty JSON object" in {
      parse[Map[Long, String]]("{}").must_==(Map.empty[Long, String])
    }
  }

  "An immutable.IntMap[String]" should {
    "generates a JSON object" in {
      generate(IntMap(1 -> "one")).must_==("""{"1":"one"}""")
    }

    "is parsable from a JSON object with decimal field names and string field values" in {
      parse[IntMap[String]]("""{"1":"one"}""").must_==(IntMap(1 -> "one"))
    }

    "is not parsable from a JSON object with non-decimal field names" in {
      parse[IntMap[String]]("""{"one":"one"}""").must(throwA[ParsingException])
    }

    "is parsable from an empty JSON object" in {
      parse[IntMap[String]]("{}").must_==(IntMap.empty[String])
    }
  }

  "An immutable.LongMap[String]" should {
    "generates a JSON object" in {
      generate(LongMap(1L -> "one")).must_==("""{"1":"one"}""")
    }

    "is parsable from a JSON object with int field names and string field values" in {
      parse[LongMap[String]]("""{"1":"one"}""").must_==(LongMap(1L -> "one"))
    }

    "is not parsable from a JSON object with non-decimal field names" in {
      parse[LongMap[String]]("""{"one":"one"}""").must(throwA[ParsingException])
    }

    "is parsable from an empty JSON object" in {
      parse[LongMap[String]]("{}").must_==(LongMap.empty)
    }
  }

  "An immutable.Queue[Int]" should {
    "generates a JSON array" in {
      generate(Queue(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Queue[Int]]("[1,2,3]").must_==(Queue(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Queue[Int]]("[]").must_==(Queue.empty)
    }
  }
}
