package cs320

object Implementation extends Template {

  def freeIds(expr: Expr): Set[String] = {
    def frees(e: Expr, ids: Set[String]): Set[String] = e match {
      case Num(n) => Set()
      case Add(left, right) => frees(left, ids) ++ frees(right, ids)
      case Sub(left, right) => frees(left, ids) ++ frees(right, ids)
      case Val(name, expr, body) => frees(expr, ids) ++ frees(body, ids + name)
      case Id(id) => if (ids contains id) Set() else Set(id)
    }
    frees(expr, Set())
  }

  def bindingIds(expr: Expr): Set[String] = expr match {
    case Num(n) => Set()
    case Add(left, right) => bindingIds(left) ++ bindingIds(right)
    case Sub(left, right) => bindingIds(left) ++ bindingIds(right)
    case Val(name, expr, body) => bindingIds(expr) ++ bindingIds(body) + name
    case Id(id) => Set()
  }

  def boundIds(expr: Expr): Set[String] = {
    def bounds(e: Expr, ids: Set[String]): Set[String] = e match {
      case Num(n) => Set()
      case Add(left, right) => bounds(left, ids) ++ bounds(right, ids)
      case Sub(left, right) => bounds(left, ids) ++ bounds(right, ids)
      case Val(name, expr, body) => bounds(expr, ids) ++ bounds(body, ids + name)
      case Id(id) => if (ids contains id) Set(id) else Set()
    }
    bounds(expr, Set())
  }
}
