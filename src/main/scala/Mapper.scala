import scala.collection.mutable.ListBuffer

class Mapper {

  def map(list: List[(Int, List[Int])]): List[(Int, Int)] = {
    var invertedEdgeList = ListBuffer[(Int, Int)]()
    list.foreach(tuple => {
      invertedEdgeList = invertedEdgeList ++ map(tuple)
    })
    invertedEdgeList.toList
  }

  def map(tuple: (Int, List[Int])): List[(Int, Int)] = {
    val invertedEdgeList = ListBuffer[(Int, Int)]()
    tuple._2.foreach(edge => {
      invertedEdgeList.append((edge, tuple._1))
    })
    invertedEdgeList.toList
  }

}
