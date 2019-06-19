package com.codahale.jerkson.deser

import scala.collection.{BitSetOps, BitSet, SpecificIterableFactory}
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}
import com.fasterxml.jackson.core.{JsonToken, JsonParser}

class BitSetDeserializer[Coll <: BitSet](factory: SpecificIterableFactory[Int, Coll])
  extends JsonDeserializer[Coll] {

  def deserialize(jp: JsonParser, ctxt: DeserializationContext) = {
    val builder = factory.newBuilder

    if (jp.getCurrentToken != JsonToken.START_ARRAY) {
      throw ctxt.mappingException(classOf[BitSet])
    }

    while (jp.nextToken() != JsonToken.END_ARRAY) {
      builder += jp.getIntValue
    }

    builder.result()
  }

  override def isCachable = true
}
