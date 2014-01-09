package com.bah.externship;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

// ##############
// ##############
//
// NOTE: There is new code in the run() method
//
// ##############
// ##############

public class CustomWritableJob extends Configured implements Tool {

	public static class CustomMap extends
			Mapper<LongWritable, Text, Text, CustomWritable> {
		final String XOM = "XOM";
		final String WMT = "WMT";
		final String DIS = "DIS";
		final String CSX = "CSX";

		private Text outKey = new Text();
		private CustomWritable outValue = new CustomWritable();

		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] line = value.toString().split("\t");
			String symbol = line[1];
			String date = line[2];
			String close = line[6];

			if (XOM.equals(symbol) || WMT.equals(symbol) || DIS.equals(symbol)
					|| CSX.equals(symbol)) {
				outKey.set(symbol);
				outValue.setText(date);
				outValue.setNumber(Double.parseDouble(close));
				context.write(outKey, outValue);
			}
		}
	}

	public static class CustomReduce extends
			Reducer<Text, CustomWritable, Text, Text> {
		private DecimalFormat df = new DecimalFormat("##.####");
		private Text outputKey = new Text();
		private Text outputValue = new Text();
		double[][] stocks = new double[4][];
		double[] standardDev = new double[4];
		int count = 0;

		@Override
		public void reduce(Text key, Iterable<CustomWritable> values,
				Context context) throws IOException, InterruptedException {

			String symbol = key.toString();

			ArrayList<CustomWritable> sorted = new ArrayList<CustomWritable>();
			for (CustomWritable value : values) {
				sorted.add(new CustomWritable(value.getText(),value.getNumber()));
			}
			
			Collections.sort(sorted);
			double[] lnReturn = new double[sorted.size()-1];
	
			DescriptiveStatistics data = new DescriptiveStatistics();
			
			for (int i = 1 ; i < sorted.size(); i++) {
				lnReturn[i-1] = Math.log(sorted.get(i).getNumber()/sorted.get(i-1).getNumber());
				data.addValue(lnReturn[i-1]);
			}
			double sd = data.getStandardDeviation();
			
			stocks[count] = lnReturn;
			standardDev[count++] = sd;
			
			outputKey.set(symbol);
			outputValue.set(Double.toString(sd));
			context.write(outputKey, outputValue);
			
//			for (CustomWritable value : sorted) {
//				String date = value.getText();
//				double close = value.getNumber();
//				outputKey.set(symbol);
//				outputValue.set(date + "_" + close);
//				context.write(outputKey, outputValue);
//			}
		}
		
		@Override
		public void cleanup(Context context) throws IOException, InterruptedException{
			PearsonsCorrelation c = new PearsonsCorrelation();
			
			// correlation[0] = 0-1 CSXtoDIS
			// correlation[1] = 0-2 CSXtoWMT
			// correlation[2] = 0-3 CSXtoXOM
			// correlation[3] = 1-2 DIStoWMT
			// correlation[4] = 1-3 DIStoXOM
			// correlation[5] = 2-3 WMTtoXOM
			double[] correlation = new double[6];
			count = 0;
			for(int i = 0; i < stocks.length; i++){
				for(int j = i + 1; j < stocks.length; j++){
					correlation[count++] = c.correlation(stocks[i], stocks[j]);
				}
			}
			
			context.write(new Text("\nCorrelation\t\tCSX\t\t\tDIS\t\t\tWMT\t\t\tXOM"),new Text());
			context.write(new Text("CSX\t\t\t1\t\t\t" + df.format(correlation[0]) + "\t\t\t" + df.format(correlation[1]) 
					+ "\t\t\t" + df.format(correlation[2])),new Text());
			context.write(new Text("DIS\t\t\t" + df.format(correlation[0]) + "\t\t\t1\t\t\t" + df.format(correlation[3]) 
					+ "\t\t\t" + df.format(correlation[4])),new Text());
			context.write(new Text("WMT\t\t\t" + df.format(correlation[1]) + "\t\t\t" + df.format(correlation[3]) 
					+ "\t\t\t1\t\t\t" + df.format(correlation[5])),new Text());
			context.write(new Text("XOM\t\t\t" + df.format(correlation[2]) + "\t\t\t" + df.format(correlation[4]) 
					+ "\t\t\t" + df.format(correlation[5])+"\t\t\t1"),new Text());
			
			double ALPHA = 1.644853627;
			double[] VaRs = new double[4];
			final int POSITION = 1250000;
			for(int i = 0; i<VaRs.length;i++){
				VaRs[i] = standardDev[i]*POSITION*ALPHA;
			}
			
			// correlation[0] = 0-1 CSXtoDIS
			// correlation[1] = 0-2 CSXtoWMT
			// correlation[2] = 0-3 CSXtoXOM
			// correlation[3] = 1-2 DIStoWMT
			// correlation[4] = 1-3 DIStoXOM
			// correlation[5] = 2-3 WMTtoXOM
			double VaRsCSX = VaRs[0] + correlation[0]*VaRs[1] + correlation[1]*VaRs[2]
					+ correlation[2]*VaRs[3];
			double VaRsDIS = VaRs[1] + correlation[0]*VaRs[0] + correlation[3]*VaRs[2]
					+ correlation[4]*VaRs[3];
			double VaRsWMT = VaRs[2] + correlation[1]*VaRs[0] + correlation[3]*VaRs[1]
					+ correlation[5]*VaRs[3];
			double VaRsXOM = VaRs[3] + correlation[2]*VaRs[0] + correlation[4]*VaRs[1]
					+ correlation[5]*VaRs[1];
			
//			context.write(new Text("VaRs\t\t\t" + VaRs[0] + "\t\t" + VaRs[1] 
//					+ "\t\t" + VaRs[2] +"\t\t" + VaRs[3]),new Text());
			
			
			context.write(new Text("\nVaRs step-1\t\t" + (int)(VaRsCSX) + "\t\t\t" + (int)(VaRsDIS) 
					+ "\t\t\t" + (int)(VaRsWMT) +"\t\t\t" + (int)(VaRsXOM)),new Text());
			
			double VaRsSUM = VaRs[0]*VaRsCSX + VaRs[1]*VaRsDIS + VaRs[2]*VaRsWMT + VaRs[3]*VaRsXOM;
			
			context.write(new Text("\nVaRs step-2\t   " + (int)(VaRsSUM)),new Text());
			
			double portfolioVaR = Math.sqrt(VaRsSUM);
			double sumIndvVaRs = 0;
			for(double v : VaRs){
				sumIndvVaRs += v;
			}
			
			context.write(new Text("\n\t\t   Portfolio VaRs\t\t"
					+ "Sum Individual VaRs"),new Text());
			context.write(new Text("VaR\t\t\t"+ (int)(portfolioVaR) + "\t\t\t\t" + (int)(sumIndvVaRs)),new Text());
			// GROUP B
			context.write(new Text("\n\n\t     BBBBBBB     BBBBBBBBB       BBBBBBBBB     BB       BB  BBBBBBBBB       BBBBBBBBB       BBB"
					           + "\n\t    BB           BB      BB     BB       BB    BB       BB  BB      BB      BB      BB      BBB"
					           + "\n\t   BB            BB       BB   BB         BB   BB       BB  BB       BB     BB       BB     BBB"
					           + "\n\t  BB             BB       BB  BB           BB  BB       BB  BB       BB     BB      BB      BBB"
					           + "\n\t BB              BB      BB   BB           BB  BB       BB  BB      BB      BBBBBBBBB       BBB"
					           + "\n\t B        BBBB   BBBBBBBBB    BB           BB  BB       BB  BBBBBBBBB       BB      BB      BBB"
					           + "\n\t BB          BB  BB    BB     BB           BB  BB       BB  BB              BB       BB     BBB"
					           + "\n\t  BB         BB  BB     BB     BB         BB   BB       BB  BB              BB       BB        "
					           + "\n\t   BB        BB  BB      BB     BB       BB    BBB     BBB  BB              BB      BB      BBB"
					           + "\n\t    BBBBBBBBBB   BB       BB     BBBBBBBBB      BBBBBBBBB   BB              BBBBBBBBB       BBB\n\n"),new Text());
		}
		
		
	}

	@Override
	public int run(String[] strings) throws Exception {

		Job job = new Job(getConf(), "CustomWritableJob");
		job.setJarByClass(CustomWritable.class);

		// THIS PART IS NEW!!!!!
		// ==============
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CustomWritable.class);
		// ==============

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(CustomMap.class);
		job.setReducerClass(CustomReduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.setInputPaths(job, new Path(
				"/user/hue/NYSE-2000-2001.tsv"));
		TextOutputFormat.setOutputPath(job,
				new Path("/user/hue/jobs/" + System.currentTimeMillis()));

		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new CustomWritableJob(),
				args);
		System.exit(res);
	}
}
