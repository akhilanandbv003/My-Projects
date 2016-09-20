package com.sjb;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class wc {

	public static void main(String[] args) throws Exception {
		Configuration c = new Configuration();
		Job j = Job.getInstance(c, "wc");
		j.setJarByClass(wc.class);
		j.setMapperClass(Map.class);
		j.setCombinerClass(Red.class);
		j.setReducerClass(Red.class);
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		j.setJarByClass(wc.class);
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		System.exit(j.waitForCompletion(true) ? 0 : 1);
	}
}
