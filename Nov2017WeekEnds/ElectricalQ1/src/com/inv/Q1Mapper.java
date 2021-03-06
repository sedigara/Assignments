package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q1Mapper extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(new Text("YEAR"), new Text("MAX,MIN"));
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String vals[]=value.toString().split(",");
		String year=vals[0];
		
		int init=Integer.parseInt(vals[1]);
		int max=init;
		int min=init;
		
		// get jan - dec consumption
		for (int i = 1; i < vals.length-1; i++) {
			//1979,23,23,2,43,24,25,26,26,26,26,25,26,25
			int consumption=Integer.parseInt(vals[i]);
			if(max<consumption){
				max=consumption;				
			}
			if(min>consumption){
				min=consumption;				
			}			
		}
	
		context.write(new Text(year), new Text(max+","+min));
	
	}
}
