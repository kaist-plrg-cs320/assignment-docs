# Exercise #2: Identifiers

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the [REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl) section.**

## Download 

```bash
sbt new kaist-plrg-cs320/identifiers.g8
```

## Descriptions

Expressions are defined in `core/src/main/scala/cs320/Expr.scala`:
```
e ::= n
    | e + e
    | e - e
    | val x = e; e
    | x
```

* `freeIds` takes an expression
  and returns the set of every free identifier in the expression.
```scala
test(freeIds(Expr("{ val x = 1; (x + y) }")), Set("y"))
test(freeIds(Expr("{ val z = 2; 1 }")), Set())
```

* `bindingIds` takes an expression 
  and returns the set of every binding identifier in the expression
  (whether or not the binding identifier is ever referenced by a bound identifier).
```scala
test(bindingIds(Expr("{ val x = 1; (x + y) }")), Set("x"))
test(bindingIds(Expr("{ val z = 2; 1 }")), Set("z"))
```

* `boundIds` takes an expression 
  and returns the set of every bound identifier in the expression.
```scala
test(boundIds(Expr("{ val x = 1; (x + y) }")), Set("x"))
test(boundIds(Expr("{ val z = 2; 1 }")), Set())
```

## Sample Solution

[`Implementation.scala`](./Implementation.scala)
