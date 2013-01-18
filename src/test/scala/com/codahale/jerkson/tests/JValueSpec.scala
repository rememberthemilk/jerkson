package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._

class JValueSpec extends Specification {
  "Selecting single nodes" should {
    "returns None with primitives" in {
      (parse[JValue]("8") \ "blah").must_==(JNull)
    }
    
    "returns None on nonexistent fields" in {
      (parse[JValue]("{\"one\": \"1\"}") \ "two").must_==(JNull)
    }
    
    "returns a JValue with an existing field" in {
      (parse[JValue]("{\"one\": \"1\"}") \ "one").must_==(JString("1"))
    }
  }
  
  "Selecting array members" should {
    "returns None with primitives" in {
      (parse[JValue]("\"derp\"").apply(0)).must_==(JNull)
    }
    
    "returns None on out of bounds" in {
      (parse[JValue]("[0, 1, 2, 3]").apply(4)).must_==(JNull)
    }
    
    "returns a JValue" in {
      (parse[JValue]("[0, 1, 2, 3]").apply(2)).must_==(JInt(2))
    }
  }
  
  "Deep selecting" should {
    "returns Nil with primitives" in {
      (parse[JValue]("0.234") \\ "herp").must_==(Nil)
    }

    "returns Nil on nothing found" in {
      (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "four").must_==(Nil)
    }
    
    "returns single leaf nodes" in {
      (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "two").must_==(Seq(JString("three")))
    }
    
    "should return multiple leaf nodes" in {
      (parse[JValue]("{\"one\": {\"two\" : \"three\"}, \"four\": {\"two\" : \"five\"}}") \\ "two").must_==(Seq(JString("three"),JString("five")))
    }
  }
}

