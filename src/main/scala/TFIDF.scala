import scala.collection.mutable.ListBuffer

class TFIDF(val documents: List[Document]) {

  def getTFIDFMap: Map[String, Map[String, Double]] = {
    val tfValues = computeTF
    val idfValues = computeIDF
    val tfidf = collection.mutable.Map[String, Map[String, Double]]()
    documents.foreach(document => {
      val documentTF = tfValues(document.documentName)
//      val documentWords = document.wordsCountMap.keys.toList
      tfidf += (document.documentName -> document.wordsCountMap.map(p => (p._1, documentTF(p._1) * idfValues(p._1))))
//      val documentTFIDF = documentWords.map(word => word -> documentTF(word) * idfValues(word))
//      println(documentTFIDF)
//      tfidf += Map(document.documentName -> documentTFIDF)
    })
    tfidf.toMap
  }

  private def computeTF: collection.mutable.Map[String, Map[String, Double]] = {
    val tfValues = collection.mutable.Map[String, Map[String, Double]]()
    documents.foreach(document => {
      val wordsCount = document.wordsCountMap
      //      Map(wordsCount.toSeq.sortWith(_._2 > _._2):_*).take(20).foreach(p => println(p._2.asInstanceOf[Double]/document.allWordsList.length))
      tfValues += (document.documentName -> wordsCount.map(p => (p._1, p._2.asInstanceOf[Double] / document.allWordsList.length)))
      //        .toSeq.sortWith(_._2 > _._2): _*)
    })
    tfValues
  }

  def computeIDF: Map[String, Double] = {
    val setsOfdocumentsWords = ListBuffer[Set[String]]()
    documents.foreach(document => {
      setsOfdocumentsWords.addOne(document.allWordsList.toSet)
    })
    val allWordsSet = collection.mutable.Set[String]()
    documents.foreach(document => {
      document.allWordsList.toSet.foreach(allWordsSet.add)
    })
    allWordsSet.map(word => (word, Math.log(documents.length / getNumberOfOccurrences(word, setsOfdocumentsWords)))).toMap
  }

  private def getNumberOfOccurrences(p: String, wordSets: ListBuffer[Set[String]]): Int = {
    var occurrences = 0
    wordSets.foreach(set => {
      if (set.contains(p))
        occurrences += 1
    })
    //    println(f"word: $p, count: $occurrences")
    occurrences
  }


}
