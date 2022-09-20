# Exercise #6: SRBFAE

**Read the [common instructions](https://github.com/kaist-plrg-cs320/assignment-docs) first if you have not read them.**
**Don't forget to read the [REPL](https://github.com/kaist-plrg-cs320/assignment-docs#repl) section.**

## Download 

```bash
sbt new kaist-plrg-cs320/srbfae.g8
```

## Descriptions

SRBFAE extends BFAE with generalized sequencing and mutable records.
Implement an interpreter for SRBFAE.

### Overview

#### Generalized Sequencing

Generalized sequencing allows one or more subexpressions,
instead of exactly two subexpressions.

```scala
test(run("""{
              b => {
                b.set((2 + b.get));
                b.set((3 + b.get));
                b.set((4 + b.get));
                b.get
              }
            }(Box(1))"""), "10")
```

#### Mutable Records

Unlike records in MRFVAE, records in SRBFAE are mutable.  `{ e1.f = e2 }`
changes the value of a field `f` in the record produced by `e1`. The value of
`e2` determines the field's new value, and that value is also the result of the
expression.

```scala
test(run("""{
              r => {
                { r.x = 5 };
                r.x
              }
            }({ x = 1 })"""), "5")
```

As in MRFVAE, the interpreter must throw an exception whose message
includes `"not a record"` when updating a field of a non-record value and `"no
such field"` when updating a field not in a given record value.

### Concrete Syntax 

We use the [extended Backus-Naur form](https://en.wikipedia.org/wiki/Extended_Backus\%E2\%80\%93Naur_form).
Note that `{ }` denotes a repetition of zero or more times.

```
expr ::= ...
       | "{" expr {";" expr} "}"
       | "{" "}"
       | "{" field "=" expr {"," id "=" field} "}"
       | expr "." field
       | "{" expr "." field "=" expr "}"
```

### Abstract Syntax

$e::=\cdots\ |\ e;\cdots;e\ |\ \lbrace f=e,\cdots,f=e\rbrace\ |\ e.f\ |\ e.f:=e$

$v::=\cdots\ \| \lbrace f=a,\cdots,f=a\rbrace$

### Operational Semantics

$\Large\frac{\sigma,M_0\vdash e_1\Rightarrow v_1,M_1\quad\cdots\quad\sigma,M_{n-1}\vdash e_n\Rightarrow v_n,M_n}{ \sigma,M_0\vdash e_1;\cdots;e_n\Rightarrow v_n,M_n}$

$\Large\frac{\sigma,M_0\vdash e_1\Rightarrow v_1,M_1'\quad\cdots\quad \sigma,M_{n-1}\vdash e_n\Rightarrow v_n,M_n'\quad a_1\not\in\mathit{Domain}(M_1')\quad\cdots\quad a_n\not\in\mathit{Domain}(M_n')\quad M_1=M_1'[a_1\mapsto v_1]\quad\cdots\quad M_n=M_n'[a_n\mapsto v_n]}{\sigma,M_0\vdash\lbrace f_1=e_1,\cdots,f_n=e_n\rbrace\Rightarrow\lbrace f_1=a_1,\cdots,f_n=a_n\rbrace,M_n}$

$\Large\frac{\sigma,M\vdash e\Rightarrow\lbrace\cdots,f=a,\cdots\rbrace,M_1\quad a\in\mathit{Domain}(M_1)}{\sigma,M\vdash e.f\Rightarrow M_1(a),M_1}$

$\Large\frac{\sigma,M\vdash e_1\Rightarrow\lbrace\cdots,f=a,\cdots\rbrace,M_1\quad\sigma,M_1\vdash e_2\Rightarrow v,M_2}{\sigma,M\vdash e_1.f:=e_2\Rightarrow v,M_2[a\mapsto v]}$

## Sample Solution

[`Implementation.scala`](./Implementation.scala)
