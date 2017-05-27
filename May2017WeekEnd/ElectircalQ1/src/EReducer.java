import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EReducer extends Reducer<IntWritable, Text, Text,Text>{

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		context.write(new Text("YEAR"), new Text("MinTemp\tMaxtemp"));
	}
	
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		//Q1 -> Year\tMinConsumption\tMaxConsumption
		
		int minYear=9999;
		int maxYear=-9999;
		
		//1979,23,23,2,43,24,25,26,26,26,26,25,26,25
		//year, <23-month ... 25-avg>
		
		for(Text val:values){
		 if(val.toString().contains("month")){	
			int mConsumption=Integer.parseInt(val.toString().split("-")[0]);
			
			if (minYear > mConsumption){
				minYear = mConsumption;
			}
			if (maxYear < mConsumption){
				maxYear = mConsumption;
			}	
		 }
			
		}
		context.write(new Text(key.toString()), new Text(minYear+"\t"+maxYear));
		
		
		
		
	}
	
	
	
}
