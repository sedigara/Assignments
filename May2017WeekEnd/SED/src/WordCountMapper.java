import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line=value.toString().toLowerCase();
		
		if(line.contains("hadoop".toLowerCase())){
			String line1=line.replaceAll("[Hh]adoop", "SPARK");
			System.out.println("+++++"+line1);
			context.write(new Text(line1), NullWritable.get());
		}
		else 
			context.write(value, NullWritable.get());
	
	}	
	
}
