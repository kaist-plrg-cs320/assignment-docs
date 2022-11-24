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
Typed expressions are defined in `core/src/main/scala/cs320/Typed.scala`,
and untyped expressions are defined in `core/src/main/scala/cs320/Untyped.scala`.

Note that fuzzing may take some time. It may not print anything for a minute
after start-up.

### Reference Interpreter

The reference interpreter of FABRIC is available at <https://plrg.kaist.ac.kr/fabric>.
Use the reference interpreter to find the correct result of a certain expression.

## Submission

Submit your `Implementation.scala` at
<https://kaist-cs320.appspot.com/assignment/Project%203>.
