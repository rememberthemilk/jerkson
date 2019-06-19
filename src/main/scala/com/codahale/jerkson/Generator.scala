package com.codahale.jerkson

import java.io.{File, OutputStream, Writer, StringWriter}
import com.fasterxml.jackson.core.{JsonGenerator, JsonEncoding}

trait Generator extends Factory {
  /**
   * Generate JSON from the given object and return it as a string.
   */
  def generate[A](obj: A): String = {
    val writer = new StringWriter
    generate(obj, writer)
    writer.toString
  }

  /**
   * Generate JSON from the given object and write to the given Writer.
   */
  def generate[A](obj: A, output: Writer): Unit =
    generate(obj, factory.createGenerator(output))

  /**
   * Generate JSON from the given object and write it to the given OutputStream.
   */
  def generate[A](obj: A, output: OutputStream): Unit =
    generate(obj, factory.createGenerator(output, JsonEncoding.UTF8))

  /**
   * Generate JSON from the given object and write it to the given File.
   */
  def generate[A](obj: A, output: File): Unit =
    generate(obj, factory.createGenerator(output, JsonEncoding.UTF8))

  /**
   * Returns true if the given class is serializable.
   */
  def canSerialize[A](implicit mf: Manifest[A]) = mapper.canSerialize(mf.runtimeClass)

  private def generate[A](obj: A, generator: JsonGenerator): Unit = {
    generator.writeObject(obj)
    generator.close()
  }
}
