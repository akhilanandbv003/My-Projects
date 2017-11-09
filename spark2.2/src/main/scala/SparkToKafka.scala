import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
object SparkToKafka {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder
      .appName("SparkToKafka1")
      .master("local")
      .getOrCreate()

    val schema = StructType(Array(
      StructField("id", IntegerType),
      StructField("name", StringType)
    ))

    val stockData = sparkSession
      .readStream
      .schema(schema)
      .option("header", "true")
      .csv("/Users/avenk3/spark/CCA175/spark2/src/main/resources/csv")

      val query = stockData.selectExpr(
        "CAST(id AS STRING) AS key",
        "to_json(struct(*)) AS value"
      )
        .writeStream
        .format("kafka")
        .option("topic", "apple")
        .option("kafka.bootstrap.servers", "localhost:9092")
        .option("checkpointLocation", "/tmp/checkpoint")
        .start()

    query.awaitTermination()

    /*import sparkSession.implicits._

    val df = sparkSession
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "apple")
      .load()

    val df1 = df.selectExpr("CAST(value AS STRING)").as[String]
      .select(from_json($"value", schema).as("data"))
      .select("data.*")

    df1.writeStream
      .format("console")
      .option("truncate", "false")
      .start()
      .awaitTermination()*/
  }
}



