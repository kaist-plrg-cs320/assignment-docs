# Exercise 8: TpolyFAE

Consider TpolyFAE.

$\Large\frac{\alpha\not\in{\it Domain}(\Gamma)\quad\Gamma[\alpha]\vdash e:\tau}{\Gamma\vdash\Lambda\alpha.e:\forall\alpha.\tau}$

$\Large\frac{\Gamma\vdash\tau_0\quad\Gamma\vdash e:\forall\alpha.\tau_1}{\Gamma\vdash e[\tau_0]:\tau_1[\alpha\leftarrow\tau_0]}$

$\Large\frac{\alpha\in{\it Domain}(\Gamma)}{\Gamma\vdash\alpha}$

$\Large\frac{\Gamma[\alpha]\vdash\tau}{\Gamma\vdash\forall\alpha.\tau}$

Rewrite the following expression using explicit annotations of polymorphic types
with $\Lambda\alpha.$ and $[\tau]$
to replace all the occurrences of $?$ with types
and to make function calls to take explicit type arguments.
For example, if a given expression is $(\lambda {\tt x}{:}?.{\tt x})\ 1$, then the answer is
$(\Lambda\alpha.\lambda {\tt x}{:}\alpha.{\tt x})[{\sf num}]\ 1$.

${\sf val}\ {\tt f}{:}?=(\lambda{\tt g}{:}?.\lambda{\tt v}{:}?.{\tt g}\ {\tt v});\ {\sf val}\ {\tt g}{:}?=(\lambda {\tt x}{:}?.{\tt x});\ {\tt f}\ {\tt g}\ 10$

## Sample Solution

[sample solution](./solution.md)
