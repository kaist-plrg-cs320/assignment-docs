package cs320

import Value._

object Implementation extends Template {

  def numVop(op: (Int, Int) => Int): (Value, Value) => NumV = (_, _) match {
    case (NumV(x), NumV(y)) => NumV(op(x, y))
    case (x, y) => error(s"not both numbers: $x, $y")
  }

  val numVAdd = numVop(_ + _)
  val numVSub = numVop(_ - _)

  def interp(expr: Expr, env: Env): Value = expr match {
    case Num(n) => NumV(n)
    case Add(l, r) => numVAdd(interp(l, env), interp(r, env))
    case Sub(l, r) => numVSub(interp(l, env), interp(r, env))
    case Val(x, e, b) => interp(b, env + (x -> interp(e, env)))
    case Id(x) => env.getOrElse(x, error(s"free identifier: $x"))
    case Fun(ps, b) => CloV(ps, b, env)
    case App(f, args) => interp(f, env) match {
      case CloV(params, b, fenv) => {
        val avals = args.map(interp(_, env))
        if (args.length != params.length) error(s"wrong arity: $params, $args")
        interp(b, fenv ++ (params zip avals))
      }
      case v => error(s"not a closure: $v")
    }
    case Rec(m) => RecV(m.map { case (f, e) => (f, interp(e, env)) })
    case Acc(r, x) => interp(r, env) match {
      case RecV(m) => m.getOrElse(x, error(s"no such field: $x"))
      case v => error(s"not a record: $v")
    }
  }

  def interp(expr: Expr): Value = interp(expr, Map())
}
