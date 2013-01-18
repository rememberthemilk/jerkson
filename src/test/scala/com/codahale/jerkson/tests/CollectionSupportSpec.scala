package com.codahale.jerkson.tests

import scala.collection._
import org.specs2.mutable._
import com.codahale.jerkson.Json._

class CollectionSupportSpec extends Specification {
  "A collection.BitSet" should {
    "generates a JSON array of ints" in {
      generate(BitSet(1)).must_==("[1]")
    }

    "is parsable from a JSON array of ints" in {
      parse[BitSet]("[1,2,3]").must_==(BitSet(1, 2, 3))
    }
  }

  "A collection.Iterator[Int]" should {
    "generates a JSON array of ints" in {
      generate(Seq(1, 2, 3).iterator).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Iterator[Int]]("[1,2,3]").toList.must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Iterator[Int]]("[]").toList.must_==(List.empty[Int])
    }
  }

  "A collection.Traversable[Int]" should {
    "generates a JSON array of ints" in {
      generate(Seq(1, 2, 3).toTraversable).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Traversable[Int]]("[1,2,3]").toList.must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Traversable[Int]]("[]").toList.must_==(List.empty[Int])
    }
  }

  "A collection.BufferedIterator[Int]" should {
    "generates a JSON array of ints" in {
      generate(Seq(1, 2, 3).iterator.buffered).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[BufferedIterator[Int]]("[1,2,3]").toList.must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[BufferedIterator[Int]]("[]").toList.must_==(List.empty[Int])
    }
  }

  "A collection.Iterable[Int]" should {
    "generates a JSON array of ints" in {
      generate(Seq(1, 2, 3).toIterable).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Iterable[Int]]("[1,2,3]").toList.must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Iterable[Int]]("[]").toList.must_==(List.empty[Int])
    }
  }

  "A collection.Set[Int]" should {
    "generates a JSON array of ints" in {
      generate(Set(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Set[Int]]("[1,2,3]").must_==(Set(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Set[Int]]("[]").must_==(Set.empty[Int])
    }
  }

  "A collection.Map[String, Int]" should {
    "generates a JSON object with int field values" in {
      generate(Map("one" -> 1, "two" -> 2)).must_==("""{"one":1,"two":2}""")
    }

    "is parsable from a JSON object with int field values" in {
      parse[Map[String, Int]]("""{"one":1,"two":2}""").must_==(Map("one" -> 1, "two" -> 2))
    }

    "is parsable from an empty JSON object" in {
      parse[Map[String, Int]]("{}").must_==(Map.empty[String, Int])
    }
  }

  "A collection.IndexedSeq[Int]" should {
    "generates a JSON array of ints" in {
      generate(IndexedSeq(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[IndexedSeq[Int]]("[1,2,3]").must_==(IndexedSeq(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[IndexedSeq[Int]]("[]").must_==(IndexedSeq.empty)
    }
  }

  "A collection.Seq[Int]" should {
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

  "A collection.SortedMap[String, Int]" should {
    "generates a JSON object with int field values" in {
      generate(SortedMap("one" -> 1, "two" -> 2)).must_==("""{"one":1,"two":2}""")
    }

    // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

    /**
     * I think all this would take is a mapping from Class[_] to Ordering, which
     * would need to have hard-coded the various primitive types, and then add
     * support for Ordered and Comparable classes. Once we have the Ordering,
     * we can pass it in manually to a builder.
     */
    
    "is parsable from a JSON object with int field values" in {
      parse[SortedMap[String, Int]]("""{"one":1,"two":2}""").must_==(SortedMap("one" -> 1, "two" -> 2))
    }.pendingUntilFixed("FIXME")

    "is parsable from an empty JSON object" in {
      parse[SortedMap[String, Int]]("{}").must_==(SortedMap.empty[String, Int])
    }.pendingUntilFixed("FIXME")
  }

  "A collection.SortedSet[Int]" should {
    "generates a JSON array of ints" in {
      generate(SortedSet(1, 2, 3)).must_==("[1,2,3]")
    }

    // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

    /**
     * I think all this would take is a mapping from Class[_] to Ordering, which
     * would need to have hard-coded the various primitive types, and then add
     * support for Ordered and Comparable classes. Once we have the Ordering,
     * we can pass it in manually to a builder.
     */

    "is parsable from a JSON array of ints" in {
      parse[SortedSet[Int]]("[1,2,3]").must_==(SortedSet(1, 2, 3))
    }.pendingUntilFixed("FIXME")

    "is parsable from an empty JSON array" in {
      parse[SortedSet[Int]]("[]").must_==(SortedSet.empty[Int])
    }.pendingUntilFixed("FIXME")
  }
}

