package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._

class ASTTypeSupportSpec extends Specification {
  "An AST.JInt" should {
    "generate a JSON int" in {
      generate(JInt(15)).must_==("15")
    }

    "is parsable from a JSON int" in {
      parse[JInt]("15").must_==(JInt(15))
    }

    "is parsable from a JSON int as a JValue" in {
      parse[JValue]("15").must_==(JInt(15))
    }
  }

  "An AST.JFloat" should {
    "generates a JSON int" in {
      generate(JFloat(15.1)).must_==("15.1")
    }

    "is parsable from a JSON float" in {
      parse[JFloat]("15.1").must_==(JFloat(15.1))
    }

    "is parsable from a JSON float as a JValue" in {
      parse[JValue]("15.1").must_==(JFloat(15.1))
    }
  }


  "An AST.JString" should {
    "generates a JSON string" in {
      generate(JString("woo")).must_==("\"woo\"")
    }

    "is parsable from a JSON string" in {
      parse[JString]("\"woo\"").must_==(JString("woo"))
    }

    "is parsable from a JSON string as a JValue" in {
      parse[JValue]("\"woo\"").must_==(JString("woo"))
    }
  }

  "An AST.JNull" should {
    "generates a JSON null" in {
      generate(JNull).must_==("null")
    }

    "is parsable from a JSON null" in {
      parse[JNull.type]("null").must_==(JNull)
    }

    "is parsable from a JSON null as a JValue" in {
      parse[JValue]("null").must_==(JNull)
    }

    "is parsable from an embedded JSON null as a JValue" in {
      parse[JValue]("[null]").must_==(JArray(List(JNull)))
    }
  }

  "An AST.JBoolean" should {
    "generates a JSON true" in {
      generate(JBoolean(true)).must_==("true")
    }

    "generates a JSON false" in {
      generate(JBoolean(false)).must_==("false")
    }

    "is parsable from a JSON true" in {
      parse[JBoolean]("true").must_==(JBoolean(true))
    }

    "is parsable from a JSON false" in {
      parse[JBoolean]("false").must_==(JBoolean(false))
    }

    "is parsable from a JSON true as a JValue" in {
      parse[JValue]("true").must_==(JBoolean(true))
    }

    "is parsable from a JSON false as a JValue" in {
      parse[JValue]("false").must_==(JBoolean(false))
    }
  }

  "An AST.JArray of JInts" should {
    "generates a JSON array of ints" in {
      generate(JArray(List(JInt(1), JInt(2), JInt(3)))).must_==("[1,2,3]")
    }

    "is parsable from a JSON array of ints" in {
      parse[JArray]("[1,2,3]").must_==(JArray(List(JInt(1), JInt(2), JInt(3))))
    }

    "is parsable from a JSON array of ints as a JValue" in {
      parse[JValue]("[1,2,3]").must_==(JArray(List(JInt(1), JInt(2), JInt(3))))
    }
  }

  "An AST.JObject" should {
    val obj = JObject(List(JField("id", JInt(1)), JField("name", JString("Coda"))))

    "generates a JSON object with matching field values" in {
      generate(obj).must_==("""{"id":1,"name":"Coda"}""")
    }

    "is parsable from a JSON object" in {
      parse[JObject]("""{"id":1,"name":"Coda"}""").must_==(obj)
    }

    "is parsable from a JSON object as a JValue" in {
      parse[JValue]("""{"id":1,"name":"Coda"}""").must_==(obj)
    }

    "is parsable from an empty JSON object" in {
      parse[JObject]("""{}""").must_==(JObject(Nil))
    }
  }
}
