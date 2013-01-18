package com.codahale.jerkson.tests

import java.net.URI
import org.specs2.mutable._
import com.codahale.jerkson.Json._
import java.util.UUID

class FancyTypeSupportSpec extends Specification {
  "A URI" should {
    "generates a JSON string" in {
      generate(new URI("http://example.com/resource?query=yes")).must_==("\"http://example.com/resource?query=yes\"")
    }

    "is parsable from a JSON string" in {
      parse[URI]("\"http://example.com/resource?query=yes\"").must_==(new URI("http://example.com/resource?query=yes"))
    }
  }

  "A UUID" should {
    val uuid = UUID.fromString("a62047e4-bfb5-4d71-aad7-1a6b338eee63")

    "generates a JSON string" in {
      generate(uuid).must_==("\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\"")
    }

    "is parsable from a JSON string" in {
      parse[UUID]("\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\"").must_==(uuid)
    }
  }
}
