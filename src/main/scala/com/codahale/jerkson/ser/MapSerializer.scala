package com.codahale.jerkson.ser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{SerializerProvider, JsonSerializer}

class MapSerializer extends JsonSerializer[collection.Map[Any, Any]] {
  def serialize(map: collection.Map[Any, Any], json: JsonGenerator, provider: SerializerProvider) {
    json.writeStartObject()
    for ((key, value) <- map) {
      provider.defaultSerializeField(key.toString, value, json)
    }
    json.writeEndObject()
  }
}
