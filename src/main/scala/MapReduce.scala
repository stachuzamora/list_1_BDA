import scala.collection.mutable.ListBuffer

object MapReduce {

  def main(args: Array[String]): Unit = {
    val nodesAndEdges = List[(Int, List[Int])](
      (1, List(2, 3)),
      (3, List(1, 5)),
      (2, List(5)),
      (5, List()),
    )

    val mapper1 = new Mapper
    val mapper2 = new Mapper
    val mapper3 = new Mapper

    val reducer = new Reducer

    reducer.reduce(
      mapper1.map(nodesAndEdges.head)
    )

    reducer.reduce(
      mapper2.map(nodesAndEdges(1))
    )

    reducer.reduce(
      mapper3.map(nodesAndEdges.slice(2,4))
    )

    reducer.prettyPrintInvertedGraph
  }
}
