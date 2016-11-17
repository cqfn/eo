<img src="http://cf.jare.io/?u=http%3A%2F%2Fwww.yegor256.com%2Fimages%2Fbooks%2Felegant-objects%2Fcactus.svg" height="100px" />

**EO** (stands for [Elegant Objects](http://www.yegor256.com/elegant-objects.html))
is an object-oriented programming language. It's still a prototype.
It's the future of OOP. Please contribute!

If you want to contribute, please join our
[Gitter chat](https://gitter.im/yegor256/elegantobjects) first.

Our Twitter tag is [#eolang](https://twitter.com/search?q=%23eolang).

These things we **don't tolerate**:

  * static/class methods or attributes
  * classes (only types and objects)
  * implementation inheritance
  * mutability
  * NULL
  * global variables/procedures
  * reflection
  * constants
  * type casting
  * scalar types
  * garbage collection ([huh?](https://github.com/yegor256/eo/issues/4))
  * annotations
  * unchecked exceptions
  * operators (`for`, `while`, `if`, etc)

These things we **want** to build in:

  * static analysis
  * continuous integration
  * build automation
  * aspects (AOP)
  * logging
  * unit testing
  * versioning
  * concurrency
  * object metadata
  * persistence
  * transactions
  * licensing
  * artifact repositories

These things we are **not sure** about (please, help us):

  * we don't need generics ([not sure](https://github.com/yegor256/eo/issues/1))
  * we don't need private and protected methods
  * we don't need public and protected attributes

We want EO to be compilable to Java. We want to stay as close to Java and JVM
as possible, mostly in order to re-use the eco-system and libraries
already available.

## Quick Start

Here is a classic "hello, world" example:

```
import org.eolang.printed;
import org.eolang.string;

cli:
  printed:
    string:
      "Hello, world!"
```

This code will compile into a `.java` class that will compile into
a `.class` byte code that will run and print "Hello, world!".

What exactly happens here? [a detailed explanation wanted]

## Overview

That's all we have in the language:

  * objects
  * types
  * methods
  * attributes
  * method arguments

Pay attention, we don't have: classes, statements, variables.

This is how we define a type (similar to Java `interface`):

```
type Book:
    String asText()
```

This is how we create a new object:

```
create abc("978-1-51916-691-3", "The Alphabet") as Book:
  String @isbn
  String @title

  constructor(ISBN i, String t):
    @isbn = i
    @title = t

  String asText():
    copy sprintf:
      "ISBN is %s, title is '%s'",
      @isbn,
      @title
```

This is how we create another object, copying an existing one:

```
copy abc("978-0-73561-965-4", "Object Thinking")
```

## Examples

Fibonacci number:

```
create fibonacci(1) as Number:
  Number n
  bytes asBytes():
    if:
      lessThan: @n, 2
      1,
      plus:
        @n
        fibonacci:
          minus: @n, 1
```
