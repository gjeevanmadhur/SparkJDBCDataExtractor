package utils

import scala.xml._

object FetchTableNames {

  def gettableNames(path: String): List[String] = {

    val inputables = XML.load(path)

    val tables = (inputables \ "table")

    tables.map(_.text).toList


  }

}
