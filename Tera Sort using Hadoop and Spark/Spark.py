from pyspark import SparkContext

import sys

if __name__ == "__main__":
	sc        = SparkContext(appName = "PythonSort")
	rdd       = sc.textFile("\100gbinput.txt")
	mapper    = rdd.map(lambda x : (x[0:10],x[10:]))
	preResult = mapper.sortByKey()
	result    = preResult.map(lambda (x,y) : x + y)		
	result.saveAsTextFile("\output")
