def parseNames(line: String) : Option[(Int, String)] = {
    var fields = line.split('\"')
    if (fields.length > 1) {
      return Some(fields(0).trim().toInt, fields(1))
    } else {
      return None // flatmap will just discard None results, and extract data from Some results.
    }
  }


val names = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/Marvel-names.txt")
names.collect.foreach(println)
\\Filter name and ID
val namesRdd = names.flatMap(parseNames)
namesRdd.collect.foreach(println)


val lines = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/Marvel-graph.txt")

// Function to extract the hero ID and number of connections from each line
  def countCoOccurences(line: String) = {
    var elements = line.split("\\s+")
    ( elements(0).toInt, elements.length - 1 )
  }

  // Convert to (heroID, number of connections) RDD
    val pairings = lines.map(countCoOccurences)
    pairings.collect.foreach(println)

    val totalFriendsByCharacter = pairings.reduceByKey( (x,y) => x + y )
    totalFriendsByCharacter.collect.foreach(println)

    val flipped = totalFriendsByCharacter.map( x => (x._2, x._1) )

    flipped.collect.foreach(println)

    val mostPopular = flipped.max()

    val mostPopularName = namesRdd.lookup(mostPopular._2)(0)


    

