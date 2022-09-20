# Exercise #1: Scala Tutorial

**Read the [common instructions](..) first if you have not read them.**

## Download 

```bash
sbt new kaist-plrg-cs320/scala-tutorial.g8
```

## Descriptions

### Primitives

* `volumeOfCuboid` takes three non-negative integers `a`, `b`, and `c`
  denoting the lengths of three sides and returns the volume of the cuboid.
```scala
test(volumeOfCuboid(1, 3, 5), 15)
test(volumeOfCuboid(2, 3, 4), 24)
```

* `concat` takes two strings `x` and `y`
  and returns their concatenation.
```scala
test(concat("x", "y"), "xy")
test(concat("abc", "def"), "abcdef")
```

### Functions

* `addN` takes an integer `n`
  and returns a function that adds `n` to a given integer.
```scala
test(addN(5)(3), 8)
test(addN(5)(42), 47)
```

* `twice` takes a function `f` whose type is `Int => Int`
  and returns a function that applies `f` twice.
```scala
test(twice(addN(3))(2), 8)
test(twice(addN(3))(7), 13)
```

* `compose` takes two `Int => Int` functions `f` and `g`
  and returns their composition `f ∘ g`.
```scala
test(compose(addN(3), addN(4))(5), 12)
test(compose(addN(3), addN(4))(11), 18)
```

### Lists

* `double` takes an interger list `l`
  and returns a list whose elements are the doubles of the elements of `l`.
```scala
test(double(List(1, 2, 3)), List(2, 4, 6))
test(double(double(List(1, 2, 3, 4, 5))), List(4, 8, 12, 16, 20))
```

* `sum` takes an integer list `l`
  and returns the sum of the elements of `l`.
```scala
test(sum(List(1, 2, 3)), 6)
test(sum(List(4, 2, 3, 7, 5)), 21)
```

### Maps

* `getKey` takes a map `m` from strings to integers and a string `s`
  and returns the integer corresponding to `s` if `m` has such mapping
  and throws an exception with a message containing `s` otherwise.
```scala
val m: Map[String, Int] = Map("Ryu" -> 42, "PL" -> 37)
test(getKey(m, "Ryu"), 42)
testExc(getKey(m, "CS320"), "CS320")
```

### Algebraic Data Types

The `Tree` type represents binary trees.
A `Tree` is either `Branch` (a non-leaf node) or `Leaf` (a leaf node).
A `Branch` consists of three fields: `left` and `right` denoting
the left and right subtrees and `value` denoting its value.
A `Leaf` has one field: `value` denoting its value.

```scala
sealed trait Tree
case class Branch(left: Tree, value: Int, right: Tree) extends Tree
case class Leaf(value: Int) extends Tree
```

The `Tree` type is already defined in `Template.scala`.
**DO NOT** define it again in `Implementation.scala`.

* `countLeaves` takes a tree `t` and
  and returns the number of its leaf nodes.
```scala
val t1: Tree = Branch(Leaf(1), 2, Leaf(3))
val t2: Tree = Branch(Leaf(1), 2, Branch(Leaf(3), 4, Leaf(5)))
test(countLeaves(t1), 2)
test(countLeaves(t2), 3)
```

* `flatten` takes a tree `t`
  and returns a list containing the value of each node in `t` with in-order traversal.
```scala
val t1: Tree = Branch(Leaf(1), 2, Leaf(3))
val t2: Tree = Branch(Leaf(1), 2, Branch(Leaf(3), 4, Leaf(5)))
test(flatten(t1), List(1, 2, 3))
test(flatten(t2), List(1, 2, 3, 4, 5))
```
