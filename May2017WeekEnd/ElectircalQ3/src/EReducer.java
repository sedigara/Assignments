import java.io.IOException;

import org.apache.commons.math3.analysis.function.Floor;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EReducer extends Reducer<IntWritable, Text, Text,Text>{

	
	
	int minCYear=9999;
	int maxCYear=-9999;
	int minYear;
	int maxYear;
@Override
protected void cleanup(Reducer<IntWritable, Text, Text, Text>.Context context)
		throws IOException, InterruptedException {
	context.write(new Text("Min Consuption"), new Text(minCYear+"\t"+minYear));
	context.write(new Text("Max Consuption"), new Text(maxCYear+"\t"+maxYear));
}
	
	
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		//Q1 -> Year\tMinConsumption\tMaxConsumption
		
		
		for(Text val:values){
			 if(val.toString().contains("month")){	
				int mConsumption=Integer.parseInt(val.toString().split("-")[0]);
				
				if (minCYear > mConsumption){
					minCYear = mConsumption;
					minYear=key.get();
				}
				if (maxCYear < mConsumption){
					maxCYear = mConsumption;
					maxYear=key.get();
				}	
			 }
				
			}
				
		
		
	}
	
	
	
	
	
}
