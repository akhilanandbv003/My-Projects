package wip

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{StructType, _}

object KafkaProducer {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder
      .appName("SparkToKafka1")
      .master("local")
      .getOrCreate()

    val schema = StructType(Array(
      //Date,Open,High,Low,Close,Volume,Adj
      StructField("Date", StringType),
      StructField("Open", StringType),
      StructField("High", StringType),
      StructField("Low", StringType),
      StructField("Close", StringType),
      StructField("Volume", StringType),
      StructField("AdjClose", StringType)
    ))

    val stockData = sparkSession
      .readStream
      .schema(schema)
      .option("header", "true")
      .csv("/Users/avenk3/spark/CCA175/spark2/src/main/resources/csv")

    /*val query = stockData.writeStream
      .format("console")
      .outputMode(OutputMode.Append()).start()*/

    val df1 = stockData.selectExpr("CAST(Date AS STRING) AS key", "to_json(struct(*)) AS value")

    val query = df1.writeStream
      .format("kafka")
      .option("topic", "apple")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("checkpointLocation", "/tmp/checkpoint")
      .start()

    query.awaitTermination()


    import sparkSession.implicits._
    val con = sparkSession
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "apple")
      .load()

    val query2 = con.selectExpr("CAST(value AS STRING)").as[String]
      .select(from_json($"value", schema).as("data"))
      .select("data.*")

    query2.writeStream
      .format("console")
      .option("truncate","false")
      .start()
      .awaitTermination()
  }
}



