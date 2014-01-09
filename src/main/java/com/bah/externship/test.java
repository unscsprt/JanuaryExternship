package com.bah.externship;

import java.util.Scanner;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] lnMSFT = {0.007, 
				0.019,
				-0.008,
				-0.002,
				-0.006,
				-0.004,
				0.020,
				0.011,
				-0.011,
				-0.004,
				-0.028,
				-0.018,
				0.007,
				0.026,
				0.007,
				0.002,
				0.026,
				-0.023,
				-0.015,
				-0.009,
				-0.006,
				-0.017,
				-0.002,
				0.007,
				-0.001,
				0.022,
				-0.009,
				0.005,
				0.005,
				-0.027,
				0.018,
				-0.026,
				0.007,
				0.028,
				-0.006,
				0.023,
				0.007,
				-0.005,
				0.013,
				-0.026,
				0.014,
				-0.004,
				0.029,
				-0.001,
				0.004,
				-0.026,
				-0.005,
				-0.007,
				0.017,
				0.000,
				-0.001,
				-0.022,
				0.000,
				0.016,
				-0.015,
				-0.006,
				-0.010,
				-0.015,
				-0.016,
				0.014,
				-0.001,
				0.008,
				-0.005,
				-0.011,
				-0.025,
				-0.001,
				-0.007,
				0.000,
				0.001,
				-0.004,
				-0.003,
				0.009,
				-0.006,
				-0.009,
				0.044,
				-0.004,
				-0.010,
				0.012,
				0.009,
				-0.006,
				0.021,
				-0.004,
				-0.020,
				-0.013,
				0.010,
				-0.023,
				-0.011,
				0.001,
				0.004,
				-0.002,
				-0.010,
				-0.002,
				0.018,
				0.000,
				0.003,
				-0.003,
				-0.007,
				-0.012,
				-0.008,
				0.002,
				0.003,
				0.019,
				0.002,
				-0.001,
				0.005,
				0.009,
				-0.008,
				-0.009,
				-0.007,
				0.017,
				-0.004,
				0.016,
				-0.004,
				0.004,
				0.003,
				-0.005,
				0.006,
				-0.001,
				0.040,
				-0.007,
				-0.011,
				0.003,
				-0.009,
				0.004,
				0.010,
				0.005,
				-0.001,
				0.010,
				0.002,
				0.012,
				-0.003,
				0.013,
				-0.009,
				-0.002,
				0.007,
				-0.013,
				0.001,
				0.055,
				-0.004,
				-0.001,
				0.000,
				0.009,
				0.010,
				-0.004,
				0.004,
				-0.013,
				0.015,
				0.010,
				0.023,
				0.031,
				-0.002,
				0.008,
				-0.008,
				0.000,
				0.008,
				0.002,
				-0.010,
				0.019,
				-0.018,
				0.017,
				-0.001,
				-0.007,
				0.010,
				-0.007,
				0.012,
				-0.008,
				-0.002,
				-0.002,
				0.019,
				-0.002,
				-0.012,
				0.029,
				-0.001,
				0.023,
				-0.007,
				-0.013,
				-0.008,
				-0.012,
				-0.009,
				-0.021,
				-0.025,
				-0.001,
				-0.006,
				0.024,
				0.003,
				-0.036,
				0.013,
				0.021,
				-0.011,
				0.020,
				0.001,
				-0.024,
				-0.013,
				-0.010,
				0.025,
				-0.008,
				-0.014,
				0.001,
				0.004,
				-0.003,
				-0.007,
				0.012,
				-0.011,
				0.003,
				0.008,
				-0.001,
				0.002,
				0.026,
				-0.003,
				0.017,
				0.021,
				0.032,
				-0.015,
				-0.022,
				-0.005,
				-0.004,
				0.009,
				0.015,
				0.000,
				-0.036,
				-0.037,
				-0.008,
				0.003,
				0.005,
				0.018,
				0.018,
				0.006,
				0.006,
				-0.018,
				0.008,
				0.019,
				-0.011,
				-0.016,
				-0.015,
				0.014,
				0.015,
				0.023,
				0.027,
				-0.013,
				0.007,
				0.030,
				-0.003,
				-0.025,
				-0.023,
				-0.004,
				-0.006,
				0.016,
				-0.004,
				0.040,
				-0.055,
				0.044};

				Scanner input = new Scanner("0.002"
						+ "\n\n0.020"
						+ "\n-0.006"
						+ "\n0.000"
						+ "\n0.001"
						+ "\n-0.004"
						+ "\n0.035"
						+ "\n0.009"
						+ "\n0.001"
						+ "\n-0.013"
						+ "\n0.008"
						+ "\n0.030"
						+ "\n0.021"
						+ "\n0.007"
						+ "\n0.003"
						+ "\n-0.003"
						+ "\n0.011"
						+ "\n-0.001"
						+ "\n-0.018"
						+ "\n-0.007"
						+ "\n0.000"
						+ "\n-0.017"
						+ "\n0.014"
						+ "\n0.013"
						+ "\n0.001"
						+ "\n0.028"
						+ "\n-0.009"
						+ "\n0.008"
						+ "\n0.007"
						+ "\n-0.019"
						+ "\n0.011"
						+ "\n-0.022"
						+ "\n-0.007"
						+ "\n0.019"
						+ "\n0.011"
						+ "\n0.010"
						+ "\n-0.004"
						+ "\n-0.007"
						+ "\n-0.006"
						+ "\n-0.021"
						+ "\n0.004"
						+ "\n-0.004"
						+ "\n0.018"
						+ "\n-0.014"
						+ "\n0.013"
						+ "\n-0.017"
						+ "\n-0.013"
						+ "\n-0.010"
						+ "\n0.005"
						+ "\n-0.020"
						+ "\n-0.010"
						+ "\n0.014"
						+ "\n-0.022"
						+ "\n0.023"
						+ "\n-0.037"
						+ "\n-0.009"
						+ "\n0.029"
						+ "\n0.012"
						+ "\n-0.002"
						+ "\n-0.014"
						+ "\n0.007"
						+ "\n-0.006"
						+ "\n0.009"
						+ "\n0.018"
						+ "\n-0.023"
						+ "\n0.006"
						+ "\n0.005"
						+ "\n-0.001"
						+ "\n-0.017"
						+ "\n-0.001"
						+ "\n0.009"
						+ "\n0.014"
						+ "\n0.006"
						+ "\n0.003"
						+ "\n-0.005"
						+ "\n-0.014"
						+ "\n-0.003"
						+ "\n0.006"
						+ "\n-0.030"
						+ "\n-0.041"
						+ "\n0.023"
						+ "\n0.014"
						+ "\n-0.006"
						+ "\n-0.002"
						+ "\n-0.004"
						+ "\n-0.012"
						+ "\n-0.007"
						+ "\n0.009"
						+ "\n-0.011"
						+ "\n-0.011"
						+ "\n0.013"
						+ "\n-0.004"
						+ "\n0.010"
						+ "\n-0.005"
						+ "\n0.009"
						+ "\n0.010"
						+ "\n-0.001"
						+ "\n0.014"
						+ "\n0.006"
						+ "\n0.008"
						+ "\n-0.003"
						+ "\n0.021"
						+ "\n0.008"
						+ "\n-0.011"
						+ "\n0.001"
						+ "\n0.003"
						+ "\n-0.015"
						+ "\n-0.011"
						+ "\n-0.002"
						+ "\n0.007"
						+ "\n0.000"
						+ "\n0.015"
						+ "\n-0.001"
						+ "\n0.006"
						+ "\n-0.003"
						+ "\n-0.010"
						+ "\n0.015"
						+ "\n-0.003"
						+ "\n0.002"
						+ "\n-0.007"
						+ "\n-0.004"
						+ "\n0.010"
						+ "\n-0.009"
						+ "\n0.003"
						+ "\n0.005"
						+ "\n-0.004"
						+ "\n0.021"
						+ "\n0.019"
						+ "\n0.007"
						+ "\n0.001"
						+ "\n0.004"
						+ "\n-0.004"
						+ "\n0.021"
						+ "\n-0.002"
						+ "\n-0.020"
						+ "\n-0.008"
						+ "\n-0.001"
						+ "\n-0.087"
						+ "\n0.010"
						+ "\n0.007"
						+ "\n0.006"
						+ "\n-0.007"
						+ "\n0.006"
						+ "\n0.005"
						+ "\n0.001"
						+ "\n-0.043"
						+ "\n-0.014"
						+ "\n-0.014"
						+ "\n0.004"
						+ "\n0.030"
						+ "\n0.005"
						+ "\n0.004"
						+ "\n-0.001"
						+ "\n0.011"
						+ "\n0.005"
						+ "\n0.006"
						+ "\n-0.007"
						+ "\n0.014"
						+ "\n-0.007"
						+ "\n0.010"
						+ "\n0.002"
						+ "\n-0.012"
						+ "\n0.000"
						+ "\n-0.003"
						+ "\n0.018"
						+ "\n-0.012"
						+ "\n-0.001"
						+ "\n-0.003"
						+ "\n0.008"
						+ "\n0.011"
						+ "\n0.024"
						+ "\n0.028"
						+ "\n-0.009"
						+ "\n0.044"
						+ "\n-0.013"
						+ "\n-0.017"
						+ "\n-0.002"
						+ "\n-0.024"
						+ "\n-0.010"
						+ "\n-0.017"
						+ "\n-0.008"
						+ "\n0.006"
						+ "\n0.008"
						+ "\n0.022"
						+ "\n-0.010"
						+ "\n-0.019"
						+ "\n0.007"
						+ "\n0.020"
						+ "\n-0.002"
						+ "\n0.021"
						+ "\n0.011"
						+ "\n-0.024"
						+ "\n-0.013"
						+ "\n0.002"
						+ "\n0.021"
						+ "\n0.005"
						+ "\n-0.022"
						+ "\n0.010"
						+ "\n0.012"
						+ "\n0.005"
						+ "\n-0.017"
						+ "\n0.014"
						+ "\n-0.016"
						+ "\n0.057"
						+ "\n0.019"
						+ "\n0.010"
						+ "\n0.011"
						+ "\n0.042"
						+ "\n0.001"
						+ "\n0.020"
						+ "\n0.006"
						+ "\n0.013"
						+ "\n-0.039"
						+ "\n-0.024"
						+ "\n-0.003"
						+ "\n-0.020"
						+ "\n0.014"
						+ "\n0.012"
						+ "\n0.009"
						+ "\n-0.035"
						+ "\n-0.014"
						+ "\n0.000"
						+ "\n0.000"
						+ "\n0.008"
						+ "\n0.020"
						+ "\n0.005"
						+ "\n-0.001"
						+ "\n0.010"
						+ "\n-0.019"
						+ "\n0.002"
						+ "\n0.022"
						+ "\n-0.005"
						+ "\n-0.014"
						+ "\n-0.016"
						+ "\n0.000"
						+ "\n0.003"
						+ "\n0.023"
						+ "\n0.013"
						+ "\n-0.006"
						+ "\n0.009"
						+ "\n0.041"
						+ "\n0.015"
						+ "\n-0.028"
						+ "\n-0.054"
						+ "\n-0.011"
						+ "\n-0.033"
						+ "\n-0.012"
						+ "\n0.003"
						+ "\n0.024"
						+ "\n-0.043"
						+ "\n0.049");
				double[] lnGOOG = new double[lnMSFT.length];
				for(int i = 0; i < lnGOOG.length; i++){
					lnGOOG[i] = input.nextDouble();
				}
				double[] lnAPPL = {.011
						,.013
						,.002
						,-.006
						,.026
						,.017
						,.018
						,.000
						,-.044
						,-.005
						,-.001
						,-.016
						,.013
						,-.001
						,.000
						,.003
						,.010
						,-.009
						,-.006
						,-.009
						,.013
						,-.007
						,.017
						,.012
						,.014
						,.026
						,-.010
						,.004
						,.002
						,-.020
						,.008
						,-.014
						,-.003
						,.003
						,.020
						,.005
						,-.001
						,-.007
						,.009
						,-.016
						,.015
						,.000
						,.015
						,-.003
						,.006
						,-.029
						,-.002
						,.012
						,.018
						,-.005
						,-.009
						,.024
						,-.008
						,.057
						,.000
						,-.030
						,-.013
						,-.009
						,-.015
						,-.007
						,.002
						,.002
						,-.002
						,.007
						,-.029
						,-.007
						,.007
						,-.003
						,-.032
						,-.008
						,-.004
						,.085
						,-.020
						,-.002
						,-.025
						,-.035
						,-.002
						,.050
						,-.042
						,-.029
						,-.005
						,-.004
						,-.012
						,.004
						,.015
						,-.008
						,.017
						,.031
						,-.017
						,-.013
						,.005
						,.012
						,.018
						,-.006
						,-.005
						,-.006
						,.008
						,.026
						,.000
						,-.007
						,.037
						,.029
						,.012
						,.006
						,.021
						,.001
						,-.005
						,-.022
						,.001
						,.004
						,.013
						,.018
						,.006
						,.012
						,.007
						,-.004
						,.025
						,.000
						,.009
						,-.023
						,.014
						,.018
						,.001
						,.034
						,.017
						,.010
						,.009
						,.010
						,-.002
						,-.001
						,.008
						,.013
						,.006
						,-.005
						,.061
						,-.017
						,.017
						,-.018
						,-.003
						,.010
						,.012
						,-.004
						,-.003
						,-.002
						,.004
						,-.002
						,.010
						,.011
						,.005
						,.015
						,.000
						,.006
						,-.010
						,.008
						,.012
						,.005
						,.001
						,.035
						,.003
						,.005
						,-.003
						,-.022
						,-.008
						,-.005
						,.008
						,.004
						,-.005
						,-.005
						,.008
						,.005
						,.015
						,.024
						,-.008
						,.034
						,-.009
						,-.026
						,.020
						,-.016
						,-.007
						,-.019
						,-.010
						,.025
						,-.014
						,-.002
						,-.026
						,-.027
						,.016
						,-.001
						,-.007
						,.014
						,.002
						,-.021
						,.000
						,.001
						,.010
						,.007
						,-.020
						,.032
						,-.006
						,-.008
						,-.058
						,.005
						,-.005
						,.033
						,.015
						,.005
						,.029
						,.050
						,-.020
						,-.002
						,.015
						,-.006
						,-.018
						,-.024
						,-.016
						,-.006
						,-.010
						,-.003
						,.006
						,-.025
						,-.003
						,.004
						,.027
						,.019
						,.009
						,.012
						,.012
						,.006
						,-.017
						,.001
						,.011
						,.015
						,-.018
						,-.010
						,-.013
						,.000
						,.017
						,.026
						,-.007
						,.007
						,.047
						,.001
						,-.028
						,-.039
						,.000
						,-.008
						,.017
						,.009
						,.027
						,-.028
						,.057};
				
				PearsonsCorrelation c = new PearsonsCorrelation();
				for(int i = 0; i < lnGOOG.length;i++){
					System.out.println(lnMSFT[i] + "\t\t" + lnGOOG[i] + "\t\t" + lnAPPL[i]);
				}
				
				System.out.println("\n1\t\t\t\t" + c.correlation(lnMSFT, lnGOOG)+ "\t\t" + c.correlation(lnMSFT, lnAPPL));
				System.out.println(c.correlation(lnMSFT, lnGOOG)+ "\t\t\t1\t\t\t" + c.correlation(lnGOOG, lnAPPL));
				System.out.println(c.correlation(lnAPPL, lnMSFT)+ "\t\t" + c.correlation(lnAPPL, lnGOOG)+ "\t\t\t1");
	}

}
