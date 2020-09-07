package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import java.io.ByteArrayInputStream

class EdgeCaseSpec extends Specification {
  "Deserializing lists" should {
    "doesn't cache Seq builders" in {
      parse[List[Int]]("[1,2,3,4]").must_==(List(1, 2, 3, 4))
      parse[List[Int]]("[1,2,3,4]").must_==(List(1, 2, 3, 4))
    }
  }

  "Parsing a JSON array of ints with nulls" should {
    "should be readable as a List[Option[Int]]" in {
      parse[List[Option[Int]]]("[1,2,null,4]").must_==(List(Some(1), Some(2), None, Some(4)))
    }
  }

  "Deserializing maps" should {
    "doesn't cache Map builders" in {
      parse[Map[String, Int]](""" {"one":1, "two": 2} """).must_==(Map("one" -> 1, "two" -> 2))
      parse[Map[String, Int]](""" {"one":1, "two": 2} """).must_==(Map("one" -> 1, "two" -> 2))
    }
  }

  "Parsing malformed JSON" should {
    "should throw a ParsingException with an informative message" in {
      parse[Boolean]("jjf8;09").must(throwA[ParsingException](
            "Malformed JSON. Unrecognized token 'jjf8': was expecting \\(JSON String, Number, Array, Object or token 'null', 'true' or 'false'\\) at character offset 4."
      ))

      parse[CaseClass]("{\"ye\":1").must(throwA[ParsingException](
            """Malformed JSON\. Unexpected end-of-input: expected close marker for """ + 
                    """Object \(start marker at \[Source: \(String\)"\{"ye":1"; line: 1, column: 1\]\) at character offset 7\."""))
    }
  }

  "Parsing invalid JSON" should {
    "should throw a ParsingException with an informative message" in {
      parse[CaseClass]("900").must(throwA[ParsingException](
        ("""Cannot deserialize instance of `com.codahale.jerkson.tests.CaseClass` out of VALUE_NUMBER_INT token\n at \[Source: \(String\)"900"; line: 1, column: 1\]""")))

      parse[CaseClass]("{\"woo\": 1}").must(throwA[ParsingException]("""Invalid JSON. Needed \[id, name\], but found \[woo\]."""))
    }
  }

  "Parsing an empty document" should {
    "should throw a ParsingException with an informative message" in {
      val input = new ByteArrayInputStream(Array.empty)
      parse[CaseClass](input).must(throwA[ParsingException]("No content to map due to end\\-of\\-input\n"))
    }
  }
}
