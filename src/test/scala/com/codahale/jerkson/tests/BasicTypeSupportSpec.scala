package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.JsonNode

class BasicTypeSupportSpec extends Specification {
  "A Byte" should {
    "generates a JSON int" in {
      generate(15.toByte).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[Byte]("15").must_==(15)
    }
  }

  "A Short" should {
    "generates a JSON int" in {
      generate(15.toShort).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[Short]("15").must_==(15)
    }
  }

  "An Int" should {
    "generates a JSON int" in {
      generate(15).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[Int]("15").must_==(15)
    }
  }

  "A Long" should {
    "generates a JSON int" in {
      generate(15L).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[Long]("15").must_==(15L)
    }
  }

  "A BigInt" should {
    "generates a JSON int" in {
      generate(BigInt(15)).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[BigInt]("15").must_==(BigInt(15))
    }

    "is parsable from a JSON string" in {
      parse[BigInt]("\"15\"").must_==(BigInt(15))
    }
  }

  "A Float" should {
    "generates a JSON float" in {
      generate(15.1F).must_==("15.1")
    }

    "is parsable from a JSON float" in {
      parse[Float]("15.1").must_==(15.1F)
    }
  }

  "A Double" should {
    "generates a JSON float" in {
      generate(15.1).must_==("15.1")
    }

    "is parsable from a JSON float" in {
      parse[Double]("15.1").must_==(15.1D)
    }
  }

  "A BigDecimal" should {
    "generates a JSON float" in {
      generate(BigDecimal(15.5)).must_==("15.5")
    }

    "is parsable from a JSON float" in {
      parse[BigDecimal]("15.5").must_==(BigDecimal(15.5))
    }

    "is parsable from a JSON int" in {
      parse[BigDecimal]("15").must_==(BigDecimal(15.0))
    }
  }

  "A String" should {
    "generates a JSON string" in {
      generate("woo").must_==("\"woo\"")
    }

    "is parsable from a JSON string" in {
      parse[String]("\"woo\"").must_==("woo")
    }
  }

  "A StringBuilder" should {
    "generates a JSON string" in {
      generate(new StringBuilder("foo")).must_==("\"foo\"")
    }

    "is parsable from a JSON string" in {
      parse[StringBuilder]("\"foo\"").toString().must_==("foo")
    }
  }

  "A null Object" should {
    "generates a JSON null" in {
      generate[Object](null).must_==("null")
    }

    "is parsable from a JSON null" in {
      parse[Object]("null").must(beNull)
    }
  }

  "A Boolean" should {
    "generates a JSON true" in {
      generate(true).must_==("true")
    }

    "generates a JSON false" in {
      generate(false).must_==("false")
    }

    "is parsable from a JSON true" in {
      parse[Boolean]("true").must_==(true)
    }

    "is parsable from a JSON false" in {
      parse[Boolean]("false").must_==(false)
    }
  }

  "A Some[Int]" should {
    "generates a JSON int" in {
      generate(Some(12)).must_==("12")
    }

    "is parsable from a JSON int as an Option[Int]" in {
      parse[Option[Int]]("12").must_==(Some(12))
    }
  }

  "A None" should {
    "generates a JSON null" in {
      generate(None).must_==("null")
    }

    "is parsable from a JSON null as an Option[Int]" in {
      parse[Option[Int]]("null").must_==(None)
    }
  }

  "A Left[String]" should {
    "generates a JSON string" in {
      generate(Left("woo")).must_==("\"woo\"")
    }

    "is parsable from a JSON string as an Either[String, Int]" in {
      parse[Either[String, Int]]("\"woo\"").must_==(Left("woo"))
    }
  }

  "A Right[String]" should {
    "generates a JSON string" in {
      generate(Right("woo")).must_==("\"woo\"")
    }

    "is parsable from a JSON string as an Either[Int, String]" in {
      parse[Either[Int, String]]("\"woo\"").must_==(Right("woo"))
    }
  }

  "A JsonNode" should {
    "generates whatever the JsonNode is" in {
      generate(new IntNode(2)).must_==("2")
    }

    "is parsable from a JSON AST node" in {
      parse[JsonNode]("2").must_==(new IntNode(2))
    }

    "is parsable from a JSON AST node as a specific type" in {
      parse[IntNode]("2").must_==(new IntNode(2))
    }

    "is itself parsable" in {
      parse[Int](new IntNode(2)).must_==(2)
    }
  }

  "An Array[Int]" should {
    "generates a JSON array of ints" in {
      generate(Array(1, 2, 3)).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[Array[Int]]("[1,2,3]").toList.must_==(List(1, 2, 3))
    }

    "is parsable from an empty JSON array" in {
      parse[Array[Int]]("[]").toList.must_==(List.empty)
    }
  }
}

