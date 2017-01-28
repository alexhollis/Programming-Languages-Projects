import scala.collection.mutable.Map
/*
 * PageRank in Scala
 * Authors: Brian Mauldin, Ronnie Brewer, Supriya Thapa, Alex Hollis
 */

object PageRankMainApp {
  
  var initialRank : Double = (1/11)
  var dampingFactor : Double = 0.85
  var pages : scala.collection.mutable.Map[Char, (Double, List[Char], Double)] = Map()
  var currentPage : Char = 'Z'
  var ranks : List[Double] = List()
  
  /**
   * MAIN ENTRY POINT
   */
  def main (args: Array[String]) : Unit = {
    println("PageRank Scala")
    pages = pages + ('A' -> Tuple3(initialRank, List('D','B','C','E','F','g','h','i','j','k'), 0.0))
    pages = pages + ('B' -> Tuple3(initialRank, List('C'), 0.0))
    pages = pages + ('C' -> Tuple3(initialRank, List('B'), 0.0))
    pages = pages + ('D' -> Tuple3(initialRank, List('A', 'B'), 0.0))
    pages = pages + ('E' -> Tuple3(initialRank, List('D','B', 'F'), 0.0))
    pages = pages + ('F' -> Tuple3(initialRank, List('B','E'), 0.0))
    pages = pages + ('g' -> Tuple3(initialRank, List('B','E'), 0.0))
    pages = pages + ('h' -> Tuple3(initialRank, List('B','E'), 0.0))
    pages = pages + ('i' -> Tuple3(initialRank, List('B','E'), 0.0))
    pages = pages + ('j' -> Tuple3(initialRank, List('E'), 0.0))
    pages = pages + ('k' -> Tuple3(initialRank, List('E'), 0.0))
    var i = 0
    while ( i < 100)
    {
      pages.foreach(sendRank)
      pages.foreach(multAndAddDampingFactor)
      i = i + 1
    }
    pages.foreach(printPageRanks)
  }
  
  /**
   * At each node n, we will send n/numoutlinks(n) to each and every node that n links to
   */
  def sendRank (page: (Char, (Double, List[Char], Double))) : Unit = {
    currentPage = page._1
    page._2._2.foreach(addRankToOutLinks) // Send value to everyone my current page is linked to
  }
  
  
  /**
   * Function to do the actual pagerank giveway and page n to each outlink page
   */
  def addRankToOutLinks (outLink: Char) : Unit = {
     var rankAndOutLinks = pages.get(outLink).get
     var newRank = rankAndOutLinks._3 + (pages.get(currentPage).get._1 / pages.get(currentPage).get._2.size)
     pages = pages + (outLink -> (rankAndOutLinks._1, rankAndOutLinks._2, newRank)) //
  }
  
  /**
   * Multiply the contributions field (number 3 in Tuple3) by damping factor then add 
   * ((1 - dampingFactor)/NumberOfPagesTotal) and store the result as the new page rank
   * for the given page in the iteration.
   */
  def multAndAddDampingFactor (page: (Char, (Double, List[Char], Double))) : Unit = {
    var newRank : Double = dampingFactor * page._2._3
    var equalizer : Double = (1 - dampingFactor) / 11
    newRank = newRank + equalizer
    pages = pages + (page._1 -> (newRank, page._2._2, 0.0)) //
  }
  
  /**
   * Prints page rank values to the screen
   */
  def printPageRanks (page: (Char, (Double, List[Char], Double))) : Unit = {
    println(page._1 + " -> " + (page._2._1 * 100) + "%")
  }
}