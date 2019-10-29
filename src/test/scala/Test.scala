import org.scalatest.{FlatSpec, Matchers}

class Test extends FlatSpec with Matchers {
  import Domain._

  it should "sum the total RAM of an empty node" in {
    Node().total shouldBe 0.0
  }

  it should "sum the total RAM of a node" in {
    Node()
      .add("backend" -> 1)
      .add("frontend" -> 2)
      .add("hapi" -> 2)
      .total shouldBe
    AppCatalog("backend") +
    AppCatalog("frontend") * 2 +
    AppCatalog("hapi") * 2
  }

  it should "be able to represent the RAM for the combination of clusters" in {
    val cluster1 = Cluster()
      .node(
        Server("Alpha") -> Node(Map("backend" -> 1, "frontend" -> 1))
      )
      .node(
        Server("Beta") -> Node(Map("hapi" -> 2, "frontend" -> 2))
      )

    val cluster2 = Cluster()
      .node(
        Server("Charlie") -> Node(Map("backend" -> 1))
      )
      .node(
        Server("Delta") -> Node(Map("frontend" -> 1))
      )

    cluster1.combine(cluster2) shouldBe
      Cluster(
        Map(
          Server("Alpha")   -> Node(Map("backend" -> 1, "frontend" -> 1)),
          Server("Beta")    -> Node(Map("hapi" -> 2, "frontend" -> 2)),
          Server("Charlie") -> Node(Map("backend" -> 1)),
          Server("Delta")   -> Node(Map("frontend" -> 1))
        )
      )

    cluster1.combine(cluster2).total shouldBe 21.0
  }
}
