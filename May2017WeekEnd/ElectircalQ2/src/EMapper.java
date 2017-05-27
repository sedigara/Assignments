import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EMapper extends Mapper<LongWritable, Text, IntWritable, Text>{

	@Override
	protected void map(LongWritable key, Text value,Context context)
					throws IOException, InterruptedException {
		//1979,23,23,2,43,24,25,26,26,26,26,25,26,25
	    //year,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec,Avg
		
		//Year key, consumption+Month consumption+Avg 
		
		String[] vals=value.toString().split(",");
		
		int year=Integer.parseInt(vals[0]);
		
		String opvalue="";
		
		for (int i = 1; i < vals.length; i++) {
			if(i<=12){ // monthly usage
				 opvalue=vals[i]+"-month";
			}else {// average usage
				opvalue=vals[i]+"-avg";
			}
			
			context.write(new IntWritable(year), new Text(opvalue));
		}
		
		
		
		
		
	}
	
}
