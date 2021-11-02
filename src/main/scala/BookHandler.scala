import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.io.Source

class BookHandler(val bookList: ListBuffer[String]) {

  val STOP_WORDS_FILENAME = "/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/stop_words.txt"
  //  val books: ListBuffer[Book] = loadWords()

  def loadBooks = {
    loadWords().toList
  }

  private def loadWords(): ListBuffer[Book] = {
    val books = ListBuffer[Book]()
    bookList.foreach(bookName => {
      var wordsList = ListBuffer[String]()
      val source = Source.fromFile(bookName)
      val bookLines = try source.getLines.toList finally source.close
      for (line <- bookLines) {
        val split = line.split(' ').toList
        for (words <- split) {
          val cleaned = removeRegex(words)
          if (cleaned.nonEmpty) wordsList += words
        }
      }
      wordsList = removeStopWords(wordsList)
      val wordsCount = countWords(wordsList)
      books += new Book(wordsList.toList, wordsCount, bookName)
    }
    )
    books
  }

  private def removeRegex(words: String): String = {
    words.replaceAll("[^A-Za-z0-9']", "")
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
