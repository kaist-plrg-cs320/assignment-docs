# Project #3: FABRIC

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the
[REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl),
[Fuzzing](https://github.com/kaist-plrg-cs320/assignment-docs#fuzzing), and
[Grading](https://github.com/kaist-plrg-cs320/assignment-docs#grading)
sections.**

## Download 

```bash
sbt new kaist-plrg-cs320/fabric.g8
```

## Descriptions

Read the FABRIC language specification (`fabric.pdf`) in the project directory,
and then implement a type checker and an interpreter for FABRIC.

### Important Files

* `core/src/main/scala/cs320/Implementation.scala`

You must fill this file to implement the type checker and the interpreter.
More precisely, you must complete the `typeCheck` and `interp` functions.
You will see the following code in the file:
```scala
def typeCheck(e: Typed.Expr): Typed.Type = T.typeCheck(e)

def interp(e: Untyped.Expr): Untyped.Value = U.interp(e)

object T {
  import Typed._

  def typeCheck(expr: Expr): Type = ???
}

object U {
  import Untyped._

  def interp(expr: Expr): Value = ???
}
```
We recommend you to implement everything related to type checking inside the
object `T` and to implement everything related to interpretation inside the
object `U`.

* `core/src/main/scala/cs320/Template.scala`

This file contains the
definition of two functions that you must implement to complete the project.
```scala
def typeCheck(expr: Typed.Expr): Typed.Type
def interp(expr: Untyped.Expr): Untyped.Value
```
In addition, it defines two other functions:
```scala
def run(str: String): String = {
  val expr = Typed.Expr(str)
  typeCheck(expr)
  val erased = Typed.erase(expr)
  Untyped.showValue(interp(erased))
}
def runWithStdLib(str: String): String =
  run(StdLib.code + str)
```
The `run` function parses a given string to get a typed expression. It
type-checks the expression with the `typeCheck` function.
If the type checking succeeds, it erases types from
the expression with the `erase` function to obtain an untyped expression.
It evaluates the untyped expression with the `interp` function and returns
the result after converting it to a string.

The `runWithStdLib` function does a similar thing with `run`, but it
prepends the source code of the standard library in front of a given string
before running it.

* `core/src/main/scala/cs320/Typed.scala`

This file defines typed expressions, the parser, the desugarer, and the type
erasure of the language.

* `core/src/main/scala/cs320/Untyped.scala`

This file defines untyped expression and values of the language.

### REPL

If you give the `--lib` flag to the REPL, it will run a given expression with
the standard library. In this case, it does not show the results of
parsing and erasing.

```
sbt:fabric> run --lib
[info] running cs320.Main
Welcome to the FABRIC REPL.
Type in :q, :quit, or the EOF character to terminate the REPL.

FABRIC> listLength[Int](List5[Int](1, 2, 3, 4, 5))
  Type: Int
  Result: 5
```

### Fuzzing

Note that fuzzing may take some time. It may not print anything for a minute
after start-up.

### Reference Interpreter

The reference interpreter of FABRIC is available at <https://plrg.kaist.ac.kr/fabric>.
Use the reference interpreter to find the correct result of a certain expression.

## Submission

Submit your `Implementation.scala` at
<https://kaist-cs320.appspot.com/assignment/Project%203>.
