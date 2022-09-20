# Exercise #5: MRFVAE

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the [REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl) section.**

## Download 

```bash
sbt new kaist-plrg-cs320/mrfvae.g8
```

## Descriptions

MRFVAE extends FVAE with multi-parameter functions and records.
Implement an interpreter for MRFVAE.

### Overview

#### Multi-Parameter Functions

Functions have any number (including zero) of parameters.

```scala
test(run("{ (x, y) => (x + y) }(1, 2)"), "3")
test(run("{ () => (3 + 4) }()"), "7")
```

Function applications can cause two kinds of error: applying a non-function
value to arguments and applying a function to a wrong number of arguments. In
the first case, the interpreter must throw an exception whose message includes
`"not a closure"`. In the second case, the interpreter must throw an exception
whose message includes `"wrong arity"`.

```scala
testExc(run("2((3 + 4))"), "not a closure")
testExc(run("{ (x, y) => (x + y) }(1)"), "wrong arity")
```

#### Records

A record is a value mapping fields to values. One can access a field of a record
to obtain the value of the field.

```scala
test(run("{ a = 10, b = (1 + 2) }"), "{ a = 10, b = 3 }")
test(run("{ a = 10, b = (1 + 2) }.b"), "3")
```

Accessing the fields of records can cause two kinds of error: accessing a field
of a non-record value and accessing a field not in a given record value.  In the
first case, the interpreter must throw an exception whose message includes `"not
a record"`. In the second case, the interpreter must throw an exception whose
message includes `"no such field"`.

```scala
testExc(run("(1 + 2).a"), "not a record")
testExc(run("{ z = { z = 0 }.y }"), "no such field")
```

### Concrete Syntax 

We use the [extended Backus-Naur form](https://en.wikipedia.org/wiki/Extended_Backus\%E2\%80\%93Naur_form).
Note that `{ }` denotes a repetition of zero or more times.

```
expr ::= ...
       | "{" "(" ")" "=>" expr "}" 
       | "{" id "=>" expr "}"
       | "{" "(" id {"," id} ")" "=>" expr "}"
       | expr "(" ")"
       | expr "(" expr {"," expr} ")"
       | "{" "}"
       | "{" field "=" expr {"," field "=" expr} "}"
       | expr "." field
```

### Abstract Syntax

$e::=\cdots\ |\ \lambda x\cdots x.e\ |\ e(e,\cdots,e)\ |\ \lbrace f=e,\cdots,f=e\rbrace\ |\ e.f$

$v::=\cdots\ |\ \langle\lambda x\cdots x.e,\sigma\rangle\ |\ \lbrace f=v,\cdots,f=v\rbrace$

Expressions are defined in `core/src/main/scala/cs320/Expr.scala`, and values
are defined in `core/src/main/scala/cs320/Value.scala`.

### Operational Semantics

$\sigma\vdash\lambda x_1\cdots x_n.e\Rightarrow\langle\lambda x_1\cdots x_n.e,\sigma\rangle$

$\Large\frac{\sigma\vdash e\Rightarrow\langle\lambda x_1\cdots x_n.e',\sigma'\rangle\quad\sigma\vdash e_1\Rightarrow v_1\quad\cdots\quad\sigma\vdash e_n\Rightarrow v_n\quad\sigma'[x_1\mapsto v_1,\cdots,x_n\mapsto v_n]\vdash e'\Rightarrow v'}{\sigma\vdash e(e_1,\cdots,e_n)\Rightarrow v'}$

$\Large\frac{\sigma\vdash e_1\Rightarrow v_1\quad\cdots\quad\sigma\vdash e_n\Rightarrow v_n}{\sigma\vdash\lbrace f_1=e_1,\cdots,f_n=e_n\rbrace\Rightarrow\lbrace f_1=v_1,\cdots,f_n=v_n\rbrace}$

$\Large\frac{\sigma\vdash e\Rightarrow\lbrace\cdots,f=v,\cdots\rbrace}{\sigma\vdash e.f\Rightarrow v}$

## Sample Solution

[`Implementation.scala`](./Implementation.scala)
