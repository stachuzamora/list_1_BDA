import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

object Main {

  def main(args: Array[String]): Unit = {
    val documentsList = List[(String, String)](
//      ("Eragon", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/eragon.txt"),
//      ("Harry Potter and the Philosopher's stone", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/harry_potter_1.txt"),
//        ("Sample1", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/sample1.txt"),
//        ("Sample2", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/sample2.txt"),
//        ("Sample3", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/sample3.txt"),
        ("example1", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/example1.txt"),
        ("example2", "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/example2.txt"),
    )
    val documentHandler = new DocumentHandler(documentsList)
    val documents = documentHandler.loadDocuments
//    getMostFrequentInDocument(100, 0, documents)
    getMostFrequentInAllDocuments(50, documents)

    val tfidf = new TFIDF(documents)
//    println("TF score")
//    tfidf.computeTF.foreach(println(_))
//    println("IDF score")
//    tfidf.computeIDF.take(50).foreach(println(_))
    tfidf.getTFIDFMap.foreach(println(_))



  }

  private def getMostFrequentInDocument(n: Int, documentInd: Int, documents: List[Document]): Unit = {
    val document = documents(documentInd)
    val listMapCount = ListMap(document.wordsCountMap
      .toSeq.sortWith(_._2 > _._2):_*)
    listMapCount.take(n).foreach(p => println(p._1 + " " + p._2))
  }
  private def getMostFrequentInAllDocuments(n: Int, documents: List[Document]): Unit = {
    println(Console.RED + Console.BOLD + n + " most frequent words in all documents" + Console.RESET)
    documents.foreach(document => {
      println(Console.BOLD + f"Document: ${document.documentName}" + Console.RESET)
      ListMap(document.wordsCountMap
        .toSeq.sortWith(_._2 > _._2):_*)
        .take(n).foreach(p => println(f"${p._1}: ${p._2}"))
    })
  }

  private def getHighestTFIDFValuesInAllDocuments(n: Int, tfidfMap: Map[String, Map[String, Double]]) = {
    
  }
}
