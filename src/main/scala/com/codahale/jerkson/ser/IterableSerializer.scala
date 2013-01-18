package com.codahale.jerkson.ser

import org.codehaus.jackson.JsonGenerator
import org.codehaus.jackson.map.{SerializerProvider, JsonSerializer}
import org.codehaus.jackson.map.annotate.JsonCachable

@JsonCachable
class IterableSerializer extends JsonSerializer[Iterable[Any]] {
  def serialize(value: Iterable[Any], json: JsonGenerator, provider: SerializerProvider) {
    json.writeStartArray()
    for (element <- value) {
      provider.defaultSerializeValue(element, json)
    }
    json.writeEndArray()
  }
}
