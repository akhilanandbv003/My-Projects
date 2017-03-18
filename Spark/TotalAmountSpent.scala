def parseData(line:String)= {
    val fields = line.split(",")
    val cId = fields(0).toInt
    val amount = fields(2).toFloat
   
    (cId, amount)
  }



// Load each line of my book into an RDD
    val input = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/customer-orders.csv")

    val parsedLines = input.map(parseData)
    
    //Can be used instead of calling functions
    //val amountSpent = input.map(x => (x.(0), x.(2).toFloat))

    val totalAmountSpent = parsedLines.reduceByKey((x,y) => x+ y)

    

    val sortedTotalAmountSpent = totalAmountSpent.sortBy(_._2)


    totalAmountSpent.collect.foreach(println)