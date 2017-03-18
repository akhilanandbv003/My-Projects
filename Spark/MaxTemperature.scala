def parseTemperature(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (stationID, entryType, temperature)
  }

  // Read each line of input data
    val lines = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/1800.csv")

  // Convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseTemperature)

  // Filter out all but TMAX entries
    val maxTemps = parsedLines.filter(x => x._2 == "TMAX")

  // Convert to (stationID, temperature)
    val stationTemps = maxTemps.map(x => (x._1, x._3.toFloat))

// Reduce by stationID retaining the maximum temperature found
    val maxTempsByStation = stationTemps.reduceByKey( (x,y) => max(x,y))

  // Collect, format, and print the results
    val results = maxTempsByStation.collect()

//Print the results
for (result <- results.sorted) {
       val station = result._1
       val temp = result._2
       val formattedTemp = f"$temp%.2f F"
       println(s"$station maximum temperature: $formattedTemp") 