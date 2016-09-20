package com.sjb;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends
		Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable in = new IntWritable(1);
	private Text t = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		StringTokenizer i = new StringTokenizer(value.toString());
		while (i.hasMoreTokens()) {
			t.set(i.nextToken());
			context.write(t, in);
		}
	}
}