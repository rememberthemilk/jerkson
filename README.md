Jerkson
-------

Forked from https://github.com/codahale/jerkson to apply some fixes and compile on Scala 2.13.

Requirements
------------

* Scala 2.13
* Jackson 2.9.x


Parsing JSON
------------

```scala
import com.codahale.jerkson.Json._

// Parse JSON arrays
parse[List[Int]]("[1,2,3]") //=> List(1,2,3)

// Parse JSON objects
parse[Map[String, Int]]("""{"one":1,"two":2}""") //=> Map("one"->1,"two"->2)

// Parse JSON objects as case classes
// (Parsing case classes isn't supported in the REPL.)
case class Person(id: Long, name: String)
parse[Person]("""{"id":1,"name":"Coda"}""") //=> Person(1,"Coda")

// Parse streaming arrays of things
for (person <- stream[Person](inputStream)) {
  println("New person: " + person)
}
```

For more examples, check out the [specs](https://github.com/codahale/jerkson/blob/master/src/test/scala/com/codahale/jerkson/tests/).


Generating JSON
---------------

```scala
// Generate JSON arrays
generate(List(1, 2, 3)) //=> [1,2,3]

// Generate JSON objects
generate(Map("one"->1, "two"->"dos")) //=> {"one":1,"two":"dos"}
```

For more examples, check out the [specs](https://github.com/codahale/jerkson/blob/master/src/test/scala/com/codahale/jerkson/tests/).


Handling `snake_case` Field Names
=================================

```scala
case class Person(firstName: String, lastName: String)

@JsonSnakeCase
case class Snake(firstName: String, lastName: String)

generate(Person("Coda", "Hale"))   //=> {"firstName": "Coda","lastName":"Hale"}
generate(Snake("Windey", "Mover")) //=> {"first_name": "Windey","last_name":"Mover"}
```


License
-------

Copyright (c) 2010-2011 Coda Hale

Maintenance by Remember The Milk Inc.

Published under The MIT License, see LICENSE
