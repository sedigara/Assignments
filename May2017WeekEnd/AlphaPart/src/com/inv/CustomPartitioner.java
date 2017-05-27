package com.inv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<Text, IntWritable> {

	@Override
	public int getPartition(Text key, IntWritable value, int noOfReducers) {
		
		char ch=key.toString().toLowerCase().charAt(0);
		if(ch>=97 && ch<=109){
			return 0%noOfReducers;
		}else if(ch>=110 && ch<=122){
			return 1%noOfReducers;
		}else return 2%noOfReducers;
		
		
	}

}
