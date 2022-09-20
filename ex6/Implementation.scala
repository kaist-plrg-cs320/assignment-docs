package cs320

import Value._

object Implementation extends Template {

  type Sto = Map[Addr, Value]

  def interp(expr: Expr): Value = interp(expr, Map(), Map())._1

  private def interp(e: Expr, env: Env, sto: Sto): (Value, Sto) = e match {
    case Num(n) => (NumV(n), sto)
    case Add(l, r) =>
      val (lv, ls) = interp(l, env, sto)
      val (rv, rs) = interp(r, env, ls)
      (numVAdd(lv, rv), rs)
    case Sub(l, r) =>
      val (lv, ls) = interp(l, env, sto)
      val (rv, rs) = interp(r, env, ls)
      (numVSub(lv, rv), rs)
    case Id(x) =>
      val v = env.getOrElse(x, error(s"free identifier: $x"))
      (v, sto)
    case Fun(p, b) =>
      val cloV = CloV(p, b, env)
      (cloV, sto)
    case App(f, arg) =>
      val (fv, fs) = interp(f, env, sto)
      fv match {
        case CloV(param, b, fenv) =>
          val (av, as) = interp(arg, env, fs)
          interp(b, fenv + (param -> av), as)
        case v => error(s"not a closure: $v")
      }
    case NewBox(e) =>
      val (v, s) = interp(e, env, sto)
      val addr = malloc(s)
      (BoxV(addr), s + (addr -> v))
    case SetBox(b, e) =>
      val (bv, bs) = interp(b, env, sto)
      bv match {
        case BoxV(addr) =>
          val (v, s) = interp(e, env, bs)
          (v, s + (addr -> v))
        case v => error(s"not a box: $v")
      }
    case OpenBox(b) =>
      val (bv, bs) = interp(b, env, sto)
      bv match {
        case BoxV(addr) =>
          val v = bs.getOrElse(addr, error(s"unallocated address: $addr"))
          (v, bs)
        case v => error(s"not a box: $v")
      }
    case Seqn(l, rs) =>
      val initial = interp(l, env, sto)
      rs.foldLeft(initial) {
        case ((v, s), r) => interp(r, env, s)
      }
    case Rec(fs) =>
      val (fields, s) = fs.foldLeft(Map[String, Addr](), sto) {
        case ((m0, s0), (f, e)) =>
          val (v, s1) = interp(e, env, s0)
          val addr = malloc(s1)
          val s2 = s1 + (addr -> v)
          val m1 = m0 + (f -> addr)
          (m1, s2)
      }
      (RecV(fields), s)
    case Get(r, f) =>
      val (rv, rs) = interp(r, env, sto)
      rv match {
        case RecV(fields) =>
          val addr = fields.getOrElse(f, error(s"no such field: $f"))
          val v = rs.getOrElse(addr, error(s"unallocated address: $addr"))
          (v, rs)
        case v => error(s"not a record: $v")
      }
    case Set(r, f, e) =>
      val (rv, rs) = interp(r, env, sto)
      rv match {
        case RecV(fields) =>
          val addr = fields.getOrElse(f, error(s"no such field: $f"))
          val (v, s) = interp(e, env, rs)
          (v, s + (addr -> v))
        case v => error(s"not a record: $v")
      }
  }

  private def numVop(op: (Int, Int) => Int): (Value, Value) => NumV = {
    case (NumV(x), NumV(y)) => NumV(op(x, y))
    case (x, y) => error(s"not both numbers: $x, $y")
  }
  private val numVAdd = numVop(_ + _)
  private val numVSub = numVop(_ - _)

  private def malloc(sto: Sto): Addr = sto.keys.maxOption.map(_ + 1).getOrElse(0)
}
