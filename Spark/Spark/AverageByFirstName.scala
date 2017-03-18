def parseLinebyName(line: String) = {
      // Split by commas
      val fields = line.split(",")
      // Extract the firstName and numFriends fields, and convert to integers
      val firstName = fields(1)
      val numFriends = fields(3).toInt
      // Create a tuple that is our result.
      (firstName, numFriends)
  }

  //Read file from HDFS
  //line.collect.foreach(println) =>>> 499,Leeta,62,12
  val line = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/fakefriends.csv")

  //Call parseLinebyName function to parse input based on firstName and numFriends fields
  //parsedRDD.collect.foreach(println) =>>>  (Leeta,12)
  val parsedRDD = line.map(parseLinebyName)


  //Maps just the values and createds a tuple of (Value, 1) by appending 1 for each value 
  //totalsByFirstName.collect.foreach(println) =>>> (Leeta,(12,1))
  val totalsByFirstName = parsedRDD.mapValues(x => (x, 1))

  //sum up the total numFriends and total instances for each firstName, by adding together all the numFriends values and 1's respectively.
  //totalByFirstName.collect.foreach(println) =>>> (Leeta,(3909,15))
  val totalByFirstName = totalsByFirstName.reduceByKey( (x,y) => (x._1 + y._1, x._2 + y._2))

  //To compute the average we divide totalFriends / totalInstances for each FirstName.
  val averagesByFirstName = totalByFirstName.mapValues(x => x._1 / x._2)

  //Collect the results from the RDD (This kicks off computing the DAG and actually executes the job)
  //results: Array[(String, Int)] = Array(.....,(Leeta,260),....)
  val results = averagesByFirstName.collect()
    
  // Sort and print the final results.
  //(Kasidy,225)(Keiko,270)(Leeta,260)(Lwaxana,245)(Martok,170)
  results.sorted.foreach(println)

