object Domain {
  type App = String
  type RAM = Double
  type Amount = Int

  val AppCatalog: Map[App, RAM] = Map(
    "frontend" -> 2.5,
    "backend" -> 4.0,
    "hapi" -> 1.5
  )

  case class Node(apps: Map[App, Amount] = Map()) {
    def add(item: (App, Amount)): Node = ???

    def total: RAM = ???
  }

  case class Server(name: String)

  case class Cluster(clusterNodes: Map[Server, Node] = Map()) {
    def addServer(server: Server): Cluster = ???

    def node(serverNode: (Server, Node)): Cluster = ???

    def total: RAM = ???

    def combine(other: Cluster): Cluster = ???
  }
}
