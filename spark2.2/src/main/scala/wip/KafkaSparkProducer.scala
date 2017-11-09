package wip

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructField, StructType}


object KafkaSparkProducer {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("Consumer")
      .getOrCreate()

    val schema = StructType(Array(
      StructField("id", StringType),
      StructField("name", StringType)
    ))
    import sparkSession.implicits._

    val df = sparkSession
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "apple")
      .load()

    val df1 = df.selectExpr("CAST(value AS STRING)").as[String]


    val query = df1.writeStream
      .format("console")
      .option("truncate", "false")
      .start()

    query.awaitTermination()

  }

}