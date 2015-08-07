package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException

class DefaultCollectionSupportSpec extends Specification {
  "A Range" should {
    "generates a JSON object" in {
      generate(Range.inclusive(1, 4, 3)).must_==("""{"start":1,"end":4,"step":3,"inclusive":true}""")
    }

    "generates a JSON object without the inclusive field if it's exclusive" in {
      generate(Range(1, 4, 3)).must_==("""{"start":1,"end":4,"step":3}""")
    }

    "generates a JSON object without the step field if it's 1" in {
      generate(Range(1, 4)).must_==("""{"start":1,"end":4}""")
    }

    "is parsable from a JSON object" in {
      parse[Range]("""{"start":1,"end":4,"step":3,"inclusive":true}""").must_==(Range.inclusive(1, 4, 3))
    }

    "is parsable from a JSON object without the inclusive field" in {
      parse[Range]("""{"start":1,"end":4,"step":3}""").must_==(Range(1, 4, 3))
    }

    "is parsable from a JSON object without the step field" in {
      parse[Range]("""{"start":1,"end":4}""").must_==(Range(1, 4))
    }

    "is not parsable from a JSON object without the required fields" in {
      parse[Range]("""{"start":1}""").must(throwA[ParsingException]("""Invalid JSON. Needed \[start, end, <step>, <inclusive>\], but found \[start\]."""))
    }

  }

  "A Tuple2[Int]" should {
    "generates a two-element JSON array of ints" in {
      // TODO: 5/31/11 <coda> -- fix Pair serialization
      generate((1, 2)).must_==("[1,2]")
    }.pendingUntilFixed("FIXME")

    "is parsable from a two-element JSON array of ints" in {
      // TODO: 5/31/11 <coda> -- fix Pair deserialization
      parse[(Int, Int)]("[1,2]").must_==((1, 2))
    }.pendingUntilFixed("FIXME")
  }

  "A Tuple3[Int]" should {
    "generates a three-element JSON array of ints" in {
      // TODO: 5/31/11 <coda> -- fix Triple serialization
      generate((1, 2, 3)).must_==("[1,2,3]")
    }.pendingUntilFixed("FIXME")

    "is parsable from a three-element JSON array of ints" in {
      // TODO: 5/31/11 <coda> -- fix Triple deserialization
      parse[(Int, Int, Int)]("[1,2,3]").must_==((1, 2, 3))
    }.pendingUntilFixed("FIXME")
  }

  "A four-tuple" should {
    "generates a four-element JSON array" in {
      // TODO: 5/31/11 <coda> -- fix Tuple4 serialization
      generate((1, "2", 3, "4")).must_==("[1,\"2\",3,\"4\"]")
    }.pendingUntilFixed("FIXME")

    "is parsable from a three-element JSON array of ints" in {
      // TODO: 5/31/11 <coda> -- fix Tuple4 deserialization
      parse[(Int, String, Int, String)]("[1,\"2\",3,\"4\"]").must_==((1, "2", 3, "4"))
    }.pendingUntilFixed("FIXME")
  }

  // TODO: 6/1/11 <coda> -- add support for all Tuple1->TupleBillionty types

  "A Seq[Int]" should {
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

  "A List[Int]" should {
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

  "An IndexedSeq[Int]" should {
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

  "A Vector[Int]" should {
    "generates a JSON array of ints" in {
      generate(Vector(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Vector[Int]]("[1,2,3]").must_==(Vector(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Vector[Int]]("[]").must_==(Vector.empty[Int])
    }
  }

  "A Set[Int]" should {
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

  "A Map[String, Int]" should {
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

  "A Map[String, Any]" should {
    "generates a JSON object with mixed field values" in {
      generate(Map("one" -> 1, "two" -> "2")).must_==("""{"one":1,"two":"2"}""")
    }

    "is parsable from a JSON object with mixed field values" in {
      parse[Map[String, Any]]("""{"one":1,"two":"2"}""").must_==(Map[String, Any]("one" -> 1, "two" -> "2"))
    }

    "is parsable from an empty JSON object" in {
      parse[Map[String, Any]]("{}").must_==(Map.empty[String, Any])
    }
  }

  "A Stream[Int]" should {
    "generates a JSON array" in {
      generate(Stream(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Stream[Int]]("[1,2,3]").must_==(Stream(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Stream[Int]]("[]").must_==(Stream.empty[Int])
    }
  }

  "An Iterator[Int]" should {
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

  "A Traversable[Int]" should {
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

  "A BufferedIterator[Int]" should {
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

  "An Iterable[Int]" should {
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
}

