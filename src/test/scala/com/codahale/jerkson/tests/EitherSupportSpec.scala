package com.codahale.jerkson.tests

import org.specs2.mutable._
import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import org.codehaus.jackson.node.IntNode

class EitherSupportSpec extends Specification {
  "An Either of two case classes" should {
    "is parseable when the Left is used" in {
      val e = parse[Either[CaseClassWithArrays, CaseClass]]("""{"one": "a", "two": [ "b", "c" ], "three": [ 0, 1 ]}""")

      e.isLeft.must_==(true)

      val c = e.left.get
      c.one.must_==("a")
      c.two.must_==(Array[String]("b", "c"))
      c.three.must_==(Array[Int](0, 1))
    }

    "is parseable when the Right is used" in {
      val e = parse[Either[CaseClass, CaseClassWithArrays]]("""{"one": "a", "two": [ "b", "c" ], "three": [ 0, 1 ]}""")

      e.isRight.must_==(true)

      val c = e.right.get
      c.one.must_==("a")
      c.two.must_==(Array[String]("b", "c"))
      c.three.must_==(Array[Int](0, 1))
    }
  }
}
