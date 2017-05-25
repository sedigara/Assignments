import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{

	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		
	}
	
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int sum=0;
		
		for(IntWritable val:values){
			sum=sum+val.get();
					
		}
		context.write(key,new IntWritable(sum));
	
	
	}
	
	
	
}
