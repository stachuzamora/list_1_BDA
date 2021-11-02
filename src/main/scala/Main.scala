import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

object Main {

  def main(args: Array[String]): Unit = {
    val booksList = new ListBuffer[String]
    booksList.addOne("/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/eragon.txt")
    booksList.addOne("/home/stanislaw/PWR/Big_Data_Anal/2 semester/BDA_lab/list_1/src/main/scala/resources/harry_potter_1.txt")
    val bookHandler = new BookHandler(booksList)
    val books = bookHandler.loadBooks
//    getMostFrequentInBook(100, 0, books)
    getMostFrequentInAllBooks(50, books)


  }

  private def getMostFrequentInBook(n: Int, bookInd: Int, books: List[Book]) = {
    val book = books(bookInd)
    val listMapCount = ListMap(book.wordsCount
      .toSeq.sortWith(_._2 > _._2):_*)
    listMapCount.take(n).foreach(p => println(p._1 + " " + p._2))
  }
  private def getMostFrequentInAllBooks(n: Int, books: List[Book]) = {
    val nMostListForPrinting = new ListBuffer[String]
    var i = 0
    books.foreach(book => {
      val helperStringList = new ListBuffer[String]
      book.wordsCount.take(n).foreach(p => {
        helperStringList += p._1 + " " + p._2 + " (" + book.bookName + ")"
      })
      i = 0
    })
    nMostListForPrinting.foreach(println(_))
  }

}
