import java.io.IOException;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Word {
public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
	Configuration conf=new Configuration();
	String []files= new GenericOptionsParser(conf,args).getRemainingArgs();
	Path input=new Path(files[0]);
	Path output=new Path(files[1]);
	
	

	Job j=new Job(conf,"word count");
	
	j.setJarByClass(Word.class);
	j.setMapperClass(WordMapper.class);
	j.setReducerClass(WordReducer.class);
	j.setOutputKeyClass(Text.class);
	j.setOutputValueClass(IntWritable.class);
	FileInputFormat.addInputPath(j, input);
	FileOutputFormat.setOutputPath(j, output);
	
	System.exit(j.waitForCompletion(true)?0:1);
	
			//hadoop jar file.jar mainclass inputfile .outputdir
}


public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String words[]= value.toString().split(" ");
		for(String word:words)
		{
			Text outputKey=new Text(word);
			IntWritable one=new IntWritable(1);
			context.write(outputKey, one);
		}
	
	}
}

public  static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int sum=0;
		for(IntWritable value: values)
		{
			sum=sum+value.get();
		}
		IntWritable outputval=new IntWritable(sum);
		
		context.write(key, outputval);
	}
		
}
}
