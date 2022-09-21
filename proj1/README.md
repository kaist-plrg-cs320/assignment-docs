# Project #1: FIBER

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the
[REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl),
[Fuzzing](https://github.com/kaist-plrg-cs320/assignment-docs#fuzzing), and
[Grading](https://github.com/kaist-plrg-cs320/assignment-docs#grading)
sections.**

## Download 

```bash
sbt new kaist-plrg-cs320/fiber.g8
```

## Descriptions

Read the FIBER language specification (`fiber-spec.pdf`) in the project directory,
and then implement an interpreter for FIBER.
Expressions are defined in `core/src/main/scala/cs320/Expr.scala`,
and values are defined in `core/src/main/scala/cs320/Value.scala`.

### Reference Interpreter

The reference interpreter of FIBER is available at <https://plrg.kaist.ac.kr/fiber>.
Use the reference interpreter to find the correct result of a certain expression.

## Submission

Submit your `Implementation.scala` at
<https://kaist-cs320.appspot.com/assignment/Project%201>.
