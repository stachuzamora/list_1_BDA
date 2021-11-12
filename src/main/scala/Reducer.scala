import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Reducer {
  private val invertedGraph = collection.mutable.Map[Int, ListBuffer[Int]]()

  def getInvertedGraph: mutable.Map[Int, ListBuffer[Int]] = {
    invertedGraph
  }

  def prettyPrintInvertedGraph = {
    println()
    invertedGraph.foreach(tuple => {
      var edgesString = ""
      tuple._2.foreach(edge => {
        edgesString = edgesString + edge + ", "
      })
      println(f"Node: ${tuple._1}, edges: [${edgesString
        .reverse
        .dropWhile(_ == ' ')
        .dropWhile(_ == ',')
        .reverse}]")
    }
    )
  }

  def reduce(mapperOutput: List[(Int, Int)]) = {
    mapperOutput.foreach(tuple => {
      invertedGraph.updateWith(tuple._1) {
        case Some(edgesList) => Some(edgesList.append(tuple._2))
        case None => Some(ListBuffer[Int](tuple._2))
      }
    })
  }
}
