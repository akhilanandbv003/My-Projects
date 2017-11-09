package wip

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql._
import org.apache.spark.sql._
import org.apache.spark.sql.types._


object KafkaSparkProducer {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("Consumer")
      .getOrCreate()

    val schema = StructType(Array(
      StructField("Date", StringType),
      StructField("Open", StringType),
      StructField("High", StringType),
      StructField("Low", StringType),
      StructField("Close", StringType),
      StructField("Volume", StringType),
      StructField("AdjClose", StringType)
    ))

    import sparkSession.implicits._

    //create stream from folder
    val fileStreamDf = sparkSession.readStream
      .option("header", "true")
      .schema(schema)
      .csv("/Users/avenk3/spark/CCA175/spark2/src/main/resources/csv")



   /* Consolw Sink
   val query = fileStreamDf.writeStream
      .format("console")
      .outputMode(OutputMode.Append()).start()
    query.awaitTermination()
    */
  //val ds = fileStreamDf.selectExpr( "Volume as value").as[String]

    //val ds = fileStreamDf.select(struct("Description", "InvoiceNo").alias("complex")) "Date as key "
    fileStreamDf.withColumn("value","struct(fileStreamDf.columns.head, fileStreamDf.columns.tail: _*)").show(false)

    val q= fileStreamDf.writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "topic1")
     .option("checkpointLocation","/tmp/")
      .start()

    q.awaitTermination()
  }

}