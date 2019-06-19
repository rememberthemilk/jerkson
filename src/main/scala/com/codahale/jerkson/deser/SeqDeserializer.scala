package com.codahale.jerkson.deser

import com.fasterxml.jackson.core.{JsonToken, JsonParser}
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.{JsonDeserializer, DeserializationContext}
import scala.collection.Factory
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer

class SeqDeserializer[+CC[_] <: AnyRef](companion: Factory[Object, CC[Object]], elementType: JavaType)
  extends JsonDeserializer[Object] with ResolvableDeserializer {

  var elementDeserializer: JsonDeserializer[Object] = _

  def deserialize(jp: JsonParser, ctxt: DeserializationContext): CC[Object] = {
    val builder = companion.newBuilder

    if (jp.getCurrentToken != JsonToken.START_ARRAY) {
      throw ctxt.mappingException(elementType.getRawClass)
    }

    while (jp.nextToken() != JsonToken.END_ARRAY) {
      builder += elementDeserializer.deserialize(jp, ctxt)
    }

    builder.result()
  }

  def resolve(ctxt: DeserializationContext): Unit = {
    elementDeserializer = ctxt.findRootValueDeserializer(elementType)
  }

  override def isCachable = true
}
