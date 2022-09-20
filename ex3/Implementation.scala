package cs320

object Implementation extends Template {

  def binOp(
    op: (Int, Int) => Int,
    ls: List[Int],
    rs: List[Int]
  ): List[Int] = ls match {
    case Nil => Nil
    case l :: rest =>
      def f(r: Int): Int = op(l, r)
      rs.map(f) ++ binOp(op, rest, rs)
  }

  type Env = Map[String, List[Int]]

  def interp(expr: Expr, env: Env): List[Int] = expr match {
    case Num(ns) => ns
    case Add(l, r) => binOp(_ + _, interp(l, env), interp(r, env))
    case Sub(l, r) => binOp(_ - _, interp(l, env), interp(r, env))
    case Val(x, e, b) => interp(b, env + (x -> interp(e, env)))
    case Id(x) => env.getOrElse(x, error(s"free identifier: $x"))
    case Min(l, m, r) =>
      val v = binOp(_ min _, interp(l, env), interp(m, env))
      binOp(_ min _, v, interp(r, env))
    case Max(l, m, r) =>
      val v = binOp(_ max _, interp(l, env), interp(m, env))
      binOp(_ max _, v, interp(r, env))
  }

  def interp(expr: Expr): List[Int] = interp(expr, Map())
}
