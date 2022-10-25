# Project #2: X-FIBER

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the
[REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl),
[Fuzzing](https://github.com/kaist-plrg-cs320/assignment-docs#fuzzing), and
[Grading](https://github.com/kaist-plrg-cs320/assignment-docs#grading)
sections.**

## Download 

```bash
sbt new kaist-plrg-cs320/x-fiber.g8
```

## Descriptions

Read the X-FIBER language specification (`x-fiber-spec.pdf`) in the project directory,
and then implement an interpreter for X-FIBER.
Expressions are defined in `core/src/main/scala/cs320/Expr.scala`,
and values are defined in `core/src/main/scala/cs320/Value.scala`.

### Reference Interpreter

The reference interpreter of X-FIBER is available at <https://plrg.kaist.ac.kr/x-fiber>.
Use the reference interpreter to find the correct result of a certain expression.

## Submission

Submit your `Implementation.scala` at
<https://kaist-cs320.appspot.com/assignment/Project%202>.
