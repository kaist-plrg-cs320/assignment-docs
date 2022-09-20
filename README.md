# KAIST CS320 Assignment Instructions

The course has 5 coding exercises (no submission required) and 3 projects
(submission required).

* [Exercise #1: Scala Tutorial](/ex1)
* Exercise #2: Identifiers
* Exercise #3: MVAE
* Exercise #5: MRFVAE
* Exercise #6: SRBFAE
* Project #1: FIBER
* Project #2: X-FIBER
* Project #3: FABRIC

## Prerequisites

* JDK >= 8 (<https://www.oracle.com/java/technologies/downloads/>)
* sbt (<https://www.scala-sbt.org/download.html>)

## Download

Run `sbt new kaist-plrg-cs320/[assignment name].g8` in your terminal.
The project directory whose name is the same as the assignment will be created
under the current directory.

Below is an example.

```
$ sbt new kaist-plrg-cs320/scala-tutorial.g8
...
name [Scala Tutorial]:
Template applied in ./scala-tutorial
```

See the instructions for each assignment to find the exact assignment name.

## Directory Structure

You can find the following directories and files in the project directory:

```
core/src
- main/scala
  - cs320
    - Implementation.scala
    - Template.scala
  - package.scala
- test/scala
  - cs320
    - Spec.scala
```

**DO NOT** edit files other than
`core/src/main/scala/cs320/Implementation.scala` and
`core/src/test/scala/cs320/Spec.scala`.

* `core/src/main/scala/cs320/Implementation.scala`:
You must fill this file to implement the required function(s).
It is enough to edit only this file to finish the assignment.

* `core/src/main/scala/cs320/Template.scala`:
This file contains the definitions of functions that you must implement to
complete the exercise.

* `core/src/main/scala/package.scala`:
This file defines `error` functions. You can throw an exception with an error
message `s` by calling `error(s)`. To omit the message, you can use `error()`,
instead of `error("")`.

* `core/src/test/scala/cs320/Spec.scala`
This file contains test cases.
Passing all the provided tests does not guarantee that your implementation is correct.
We highly recommend adding your own tests.

## Restrictions

You must not use any of the following features in your implementation:

* `asInstanceOf`
* `isInstanceOf`
* `null`
* `return`
* `throw`
* `var`
* `while`
* `try-catch`
* mutable data structures

You can use any other features that are not explicitly forbidden. For example,

* You can define helper functions and additional types.
* You can use immutable data structures.
* You can use `for`.
* You can mutate mutable variables/fields already defined in the provided code.

The use of the prohibited features will make your code not compile. If your code
compiles successfully, you are not using any prohibited features.

## Testing Your Implementation

Under the project directory, execute `sbt` to start an sbt console.

```
$ sbt
[info] welcome to sbt
...
sbt:...>
```

To run the test cases in `Spec.scala`, execute the `test` command.
Every test case will fail at the beginning.

```
sbt:...> test
...
[error] Failed tests:
[error]         cs320.Spec
```

After implementing every function correctly, every test will succeed.

```
sbt:...> test
...
[info] All tests passed.
```

(If you are working on Exercise #1, stop reading here. The remaining parts are
for the other assignments.)

### REPL

Any assignments other than Exercise #1 provide REPL.
REPL allows you to input an arbitrary expression into your implementation and see the result.
Using REPL, you can quickly test your implementation without changing `Spec.scala`.

Execute the `run` command to start REPL.
Below is REPL of Exercise #3.

```
sbt:mvae > run
[info] running cs320.Main
Welcome to the MVAE REPL.
Type in :q, :quit, or the EOF character to terminate the REPL.

MVAE>
```

REPL will not work properly at the beginning.

```
MVAE> { val x = (1, 2); (x + x) }
  Parsed: Val(x,Num(List(1, 2)),Add(Id(x),Id(x)))
scala.NotImplementedError: an implementation is missing
        at scala.Predef$.$qmark$qmark$qmark(Predef.scala:347)
        at cs320.Implementation$.interp(Implementation.scala:18)
```

After finishing the implementation, REPL will behave correctly.

```
MVAE> { val x = (1, 2); (x + x) }
  Parsed: Val(x,Num(List(1, 2)),Add(Id(x),Id(x)))
  Result: (2, 3, 3, 4)
```

(If you are working on an exercise, stop reading here. The remaining parts are
for the projects.)

### Fuzzing

Each project (but no exercise) provides a fuzzer. A fuzzer randomly generates
expressions to test your interpreter.

Execute the `run fuzzer` command to use the fuzzer.

```
sbt:... > run fuzzer
[info] running cs320.Main fuzzer
10000
```

The number shown on the screen is the number of expressions your interpreter
correctly interpreted so far. The fuzzer runs forever until it finds a wrong
behavior. Press `ctrl+c` to stop fuzzing.

When the fuzzer finds a wrong behavior, it prints detailed information and
terminates.

```
Incorrect behavior detected
Expr: (1 + 2)
Correct result: 3
Your result: Unexpected Error
an implementation is missing
        scala.Predef$.$qmark$qmark$qmark(Predef.scala:344)
        cs320.Implementation$.interp(Implementation.scala:7)
        cs320.Template.run(Template.scala:5)
        cs320.Template.run$(Template.scala:5)
        cs320.Implementation$.run(Implementation.scala:5)
```

If the fuzzer reports a failed case after you press `ctrl+c`, it is due to the
abrupt termination, not a defect in your implementation, so you can ignore it.

If your implementation correctly interprets a huge number of expressions, your
implementation is highly likely to be correct.

## Grading

You must submit your `Implementation.scala` of each project.
See the instructions for each project to find the submission link.

You must work on each project by yourself without getting any help. Violation of
the honor code will give you F.

We disallow late submissions. If you submit multiple times, only the last
submission will be graded.

You can get maximum 100 points.

* Test cases: 80 points
* Fuzzing: 20 points

The test cases for the grading are similar to, but not the same as, those
provided in `Spec.scala`. You get more points as you pass more test cases. The
execution of each test case does not affect the others, e.g., the other test
cases will be graded properly even when your implementation runs forever for a
certain test case.

If your implementation passes all the test cases,
we test the implementation with the same fuzzer as the one you have.
We use 10,000 expressions for each student.
The expressions are the same for every student.
If your implementation behaves correctly for every expression, you will get 20
points for the Fuzzing part. Otherwise, you will get 0 points.
