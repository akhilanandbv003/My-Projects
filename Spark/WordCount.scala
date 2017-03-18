   // Load each line of my book into an RDD
    val input = sc.textFile("/user/akhilanand.bv_gmail/Sparkdata/book.txt")
    
    // Split using a regular expression that extracts words
    val words = input.flatMap(x => x.split("\\W+"))
    
     // Filter out all but TMIN entries

    
  
    // Normalize everything to lowercase
    val lowercaseWords = words.map(x => x.toLowerCase())



    // Count of the occurrences of each word
    val wordCounts = lowercaseWords.map(x => (x, 1)).reduceByKey( (x,y) => x + y )
    
    // Flip (word, count) tuples to (count, word) and then sort by key (the counts)
    val wordCountsSorted = wordCounts.map( x => (x._2, x._1) ).sortByKey()

    val verbs = List("you", "to", "your", "the", "a" ,"of","and",
     "that","it","in","is","for","on","are","if","is")


    val cleanedWords = wordCountsSorted.filter(x => !verbs.contains(x._1)).sortByKey()

    
    // Print the results, flipping the (count, word) results to word: count as we go.
    for (result <- cleanedWords) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }



    // filter words
