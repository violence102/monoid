import simulacrum.{op, typeclass}

@typeclass
trait Semigroup[A] {
  @op("|+|") def combine(x: A, y: A): A
}

object SemigroupOps {
  implicit val intSemi: Semigroup[Int] = (x, y) => x + y
  implicit def listSemi[A]: Semigroup[List[A]] = (x, y) => x ::: y
  implicit def mapSemi[K, V: Semigroup]: Semigroup[Map[K, V]] = (x, y) => {
    import Semigroup.ops._

    def optionCombine(a: V, opt: Option[V]): V =
      opt.map(a |+| _).getOrElse(a)

    x.foldLeft(y) {
      case (acc, (k, v)) => acc + (k -> optionCombine(v, acc.get(k)))
    }
  }
}