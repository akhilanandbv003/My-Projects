package com.sjb;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Red extends
		Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable res = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int total = 0;
		for (IntWritable val : values) {
			total += val.get();
		}
		res.set(total);
		context.write(key, res);
	}
}