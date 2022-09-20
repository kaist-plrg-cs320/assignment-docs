# Exercise #3: MVAE

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the [REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl) section.**

## Download 

```bash
sbt new kaist-plrg-cs320/mvae.g8
```

## Descriptions

MVAE extends VAE with multi-integers. Implement an interpreter for MVAE.

### Overview

A multi-integer is a value consisting of zero or more integers. For example,
`()`, `(2)`, `(1, 42)`, and `(3, 4, 5)` are multi-integers. A multi-integer
expression is defined as follows:

```scala
case class Num(values: List[Int]) extends Expr
```

Addition and subtraction on multi-integers produce multi-integers by considering
every possible combination.

```scala
test(run("(3 + 7)"), List(10))
test(run("(10 - (3, 5))"), List(7, 5))
test(run("((1, 2) + (3, 4))"), List(4, 5, 5, 6))
```

`((3) + (7))` is `(10)` because `3 + 7` is the only possible combination.
`((10) - (3, 5))` is `(7, 5)` because both `10 - 3` and `10 - 5` are possible.
`((1, 2) + (3, 4))` is `(4, 5, 5, 6)` because `1 + 3`, `1 + 4`, `2 + 3`, and `2 + 4` are possible.

MVAE additionally supports two more arithmetic operators: `min` and `max`.
Each of them takes three expressions and returns the minimum/maximum.

```scala
test(run("min(3, 4, 5)"), List(3))
test(run("min((1, 4), (2, 9), 3)"), List(1, 1, 2, 3))
```

`min((3), (4), (5))` is `(3)` because `min(3, 4, 5)` is the only possible combination.
`min((1, 4), (2, 9), 3)` is `(1, 1, 2, 3)` because `min(1, 2, 3)`, `min(1, 9,
3)`, `min(4, 2, 3)`, and `min(4, 9 ,3)` are possible.

### Concrete Syntax 

We use the [extended Backus-Naur form](https://en.wikipedia.org/wiki/Extended_Backus\%E2\%80\%93Naur_form).
Note that `{ }` denotes a repetition of zero or more times.

```
expr ::= ...
       | "( ")"
       | "(" num {"," num} ")"
       | "min" "(" expr "," expr "," expr ")"
       | "max" "(" expr "," expr "," expr ")"
```

### Abstract Syntax

$e::=\cdots\ |\ (n,\cdots, n)\ |\ \min(e,e,e)\ |\ \max(e,e,e)$

### Operational Semantics

$\sigma\vdash (n_1,\cdots,n_i) \Rightarrow (n_1,\cdots,n_i)$

$\Large\frac{\sigma\vdash e_1\Rightarrow(n_1,\cdots,n_i)\quad\sigma\vdash e_2\Rightarrow(m_1,\cdots,m_j)}{\sigma\vdash e_1+e_2\Rightarrow(n_1+m_1,n_1+m_2,\cdots,n_i+m_{j-1},n_i+m_j)}$

$\Large\frac{\sigma\vdash e_1\Rightarrow(n_1,\cdots,n_i)\quad\sigma\vdash e_2\Rightarrow(m_1,\cdots,m_j)}{\sigma\vdash e_1-e_2\Rightarrow(n_1-m_1,n_1-m_2,\cdots,n_i-m_{j-1},n_i-m_j)}$

$\Large\frac{\sigma\vdash e_1\Rightarrow(n_1,\cdots,n_i)\quad\sigma\vdash e_2\Rightarrow(m_1,\cdots,m_j)\quad\sigma\vdash e_3\Rightarrow(l_1,\cdots,l_k)}{\sigma\vdash\min(e_1,e_2,e_3)\Rightarrow(\min(n_1,m_1,l_1),\min(n_1,m_1,l_2),\cdots,\min(n_i,m_j,l_k))}$

$\Large\frac{\sigma\vdash e_1\Rightarrow(n_1,\cdots,n_i)\quad\sigma\vdash e_2\Rightarrow(m_1,\cdots,m_j)\quad\sigma\vdash e_3\Rightarrow(l_1,\cdots,l_k)}{\sigma\vdash\max(e_1,e_2,e_3)\Rightarrow(\max(n_1,m_1,l_1),\max(n_1,m_1,l_2),\cdots,\max(n_i,m_j,l_k))}$

## Sample Solution

[`Implementation.scala`](./Implementation.scala)
