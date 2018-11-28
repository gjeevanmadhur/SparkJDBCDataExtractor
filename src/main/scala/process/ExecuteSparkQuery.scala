package process

import org.apache.spark.sql.SQLContext

object ExecuteSparkQuery {


  def extractData(sqlContext: SQLContext, url: String, databasename: String, tablename: String, user: String, password: String, numofPartitions: String, partitionColumnName: String, lowerbound: String, upperbound: String) = {

    sqlContext.read
      .format("jdbc")
      .option("url", url)
      .option("dbtable", s"$databasename.$tablename")
      .option("user", user)
      .option("password", password)
      .option("numPartitions", numofPartitions)
      //.option("partitionColumn", partitionColumnName)
      //.option("lowerBound", lowerbound)
      //.option("upperBound", upperbound)
      .option("driver", "com.ibm.as400.access.AS400JDBCDriver")
      .load()

  }

  def getBoundaries(sqlContext: SQLContext, tablename: String, partitionColumn: String, url: String, user: String, password: String) = {

    //val sql = s"select min($partitionColumn) as min, max($partitionColumn) as max from $tablename"
    val sql = s"select cast(min($partitionColumn) as VARCHAR(500)) as min , cast(max($partitionColumn) as VARCHAR(500)) as maz from $tablename"

    val df = runSQL(sqlContext, sql, url, user, password).collect()(0)

    Array(df.get(0).asInstanceOf[String], df.get(1).asInstanceOf[String])

  }

  def runSQL(sqlContext: SQLContext, sqlQuery: String, url: String, user: String, password: String) = {

    sqlContext.read
      .format("jdbc")
      .option("url", url)
      .option("user", user)
      .option("password", password)
      .option("driver", "com.ibm.as400.access.AS400JDBCDriver")
      .option("dbtable", s"($sqlQuery) as tmp")
      .load()

  }

}
