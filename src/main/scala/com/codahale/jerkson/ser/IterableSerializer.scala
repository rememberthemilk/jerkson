package com.codahale.jerkson.ser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{SerializerProvider, JsonSerializer}

class IterableSerializer extends JsonSerializer[Iterable[Any]] {
  def serialize(value: Iterable[Any], json: JsonGenerator, provider: SerializerProvider): Unit = {
    json.writeStartArray()
    for (element <- value) {
      provider.defaultSerializeValue(element, json)
    }
    json.writeEndArray()
  }
}
