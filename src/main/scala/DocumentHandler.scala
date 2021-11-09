import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.io.Source

class DocumentHandler(val documentList: List[(String, String)]) {

  val STOP_WORDS_FILENAME = "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/stop_words.txt"
  //  val documents: ListBuffer[document] = loadWords()

  def loadDocuments = {
    loadWords().toList
  }

  private def loadWords(): ListBuffer[Document] = {
    val documents = ListBuffer[Document]()
    documentList.foreach(documentInfo => {
      var wordsList = ListBuffer[String]()
      val source = Source.fromFile(documentInfo._2)
      val documentLines = try source.getLines.toList finally source.close
      for (line <- documentLines) {
        val split = line.split(' ').toList
        for (words <- split) {
          val cleaned = removeRegex(words)
          if (cleaned.nonEmpty) wordsList += words
        }
      }
      wordsList = removeStopWords(wordsList)
      val wordsCount = countWords(wordsList)
//      val numberOfWords = wordsCount.foldLeft(0)(_+_._2)
      documents += new Document(wordsList.toList, wordsCount, documentInfo._1)
    }
    )
    documents
  }

  private def removeRegex(words: String): String = {
//    words.replaceAll("[^A-Za-z0-9']", "")
    words.replaceAll("""[\p{Punct}&&[^.]]""", "")
  }

  private def removeStopWords(wordsList: ListBuffer[String]): ListBuffer[String] ={
    val source = Source.fromFile(STOP_WORDS_FILENAME)
    val stopWordsList = try source.getLines.toList finally source.close
    wordsList.filter(!stopWordsList.contains(_))
  }

  private def countWords(wordsList: ListBuffer[String]) = {
    Map(wordsList.groupBy(w => w)
      .map(t => (t._1, t._2.length))
      .toSeq.sortWith(_._2 > _._2):_*)
//    val listMapSave = ListMap(wordMap.toSeq.sortWith(_._2 > _._2):_*).toMap
  }

}
