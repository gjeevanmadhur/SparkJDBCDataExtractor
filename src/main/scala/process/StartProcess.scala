package process

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SaveMode
import org.apache.spark.{SparkConf, SparkContext}
import process.ExecuteSparkQuery._
import utils.FetchTableNames._
import utils.PartitionColums._

import scala.util.{Failure, Success, Try}

object StartProcess {

  def main(args: Array[String]) = {

    println("Printing the paramters "+args(0) + args(1))
    Logger.getRootLogger().setLevel(Level.ERROR)

    val conf = new SparkConf()
      //.setMaster("local[*]")
      //.setAppName("Generic Spark JDBC Extractor")

    val spark = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.hive.HiveContext(spark)

    val url: String = "jdbc:as400://xxx.xx.xxx.xxx:8471/"
    val user: String = "diatool"
    val password: String = "datalod9"
    val databasename:String="INC0408949"
    val numbofparititon:String="50"
    val tablelist = gettableNames(args(0))
    //val reruntable=args(2)
    println("Printing the table list "+ tablelist)
    //tablelist.foreach(println(_))
    val run_date=args(1)


    tablelist.foreach {
      eachtable =>

        val tablename=eachtable.toString
        val database_name=mapdatabase(tablename)
        val hadooptableName=maphadooptableName(tablename)
        val partColumn=partitionColums(tablename)
        println("Starting data extraction for the table .... "+tablename)

          val bounds = getBoundaries(sqlContext, tablename, partColumn, url, user, password)

        println("Executed bounds for the table "+tablename + " Boundaries are "+bounds(0)+" and "+bounds(1))
        println("output is "+bounds)
        val output = Try
        {
          println("extracting data for the table "+tablename)
        val result=extractData(sqlContext, url, databasename, tablename, user, password, numbofparititon, partColumn, bounds(0).toString, bounds(1).toString)

        val colrenam=  result.columns.foldLeft(result){(newdf,colname) => newdf.withColumnRenamed(colname,
            colname
              .replace(" ","_")
              .replace(".","_")
              .replace("#","")
              .replace("$","")
          )
          }
          colrenam.select("*")
            .write.mode(SaveMode.Overwrite)
            .format("orc")
            .save(s"hdfs://user/$database_name/$hadooptableName/run_date=$run_date")

          }
        output match {

          case Success(v) =>

            println("Successfully completed the table "+tablename)

          case Failure(v) =>

            println("Job failed .."+tablename+" ..... " + v)
        }
    }

  }

}
