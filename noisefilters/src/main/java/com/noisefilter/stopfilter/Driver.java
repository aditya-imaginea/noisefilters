package com.noisefilter.stopfilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Driver {
	private static StopFilterFarm filterfarm = new StopFilterFarm();

	public static void main(String args[]) throws Exception {
		File file = new File(CommonParameters.dataPath + "stopwords.txt");
		// FileWriter fw = new FileWriter(file.getAbsoluteFile());
		// BufferedWriter bw = new BufferedWriter(fw);
		// Scanner sc = new Scanner(
		// Driver.class.getResourceAsStream("routes.txt"));
		// int count = 0;
		// while (sc.hasNext()) {
		// String s = sc.next();
		// count++;
		// bw.write(s.substring(0,8)+"\n") ;
		// //System.out.println(s.substring(0,8));
		// //filterfarm.addTree(s);
		// }
		// //System.out.println(count);
		// bw.close();
		// sc.close();
		Scanner sc_stop = new Scanner(file);
		while (sc_stop.hasNext()) {
			String s = sc_stop.next();
			filterfarm.addTree(s);
		}
		sc_stop.close();

		// Timing the algo
		File algoResult = new File(CommonParameters.dataPath + "algo.txt");
		FileWriter fw = new FileWriter(algoResult.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		long startTime = System.currentTimeMillis();
		Scanner sc_data = new Scanner(
				Driver.class.getResourceAsStream("routes.txt"));
		while (sc_data.hasNext()) {
			String s = sc_data.next();
			if (filterfarm.searchTree(s.substring(0, 8))) {
				bw.write(s.substring(8) + "\n");
			} else
				bw.write(s + "\n");
		}
		bw.close();
		sc_data.close();
		long endTime = System.currentTimeMillis();
		long algoDuration = (endTime - startTime);
		System.out.println("algo execution takes " + algoDuration
				+ "  milli sec");

		// timing the brute force
		File bruteResult = new File(CommonParameters.dataPath + "brute.txt");
		fw = new FileWriter(bruteResult.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		startTime = System.currentTimeMillis();
		sc_data = new Scanner(Driver.class.getResourceAsStream("routes.txt"));
		while (sc_data.hasNext()) {
			String data = sc_data.next();
			Scanner sc_stopword = new Scanner(file);
			boolean found = false;
			while (sc_stopword.hasNext()) {
				String next = sc_stopword.next();
				if (data.startsWith(next) && !found) {
					bw.write(data.substring(8) + "\n");
					found = true;
				}
			}
			if (!found)
				bw.write(data);
			sc_stopword.close();
		}
		bw.close();
		sc_data.close();
		endTime = System.currentTimeMillis();
		long bruteDuration = (endTime - startTime);
		System.out.println("brute force execution takes " + bruteDuration
				+ " milli sec");

	}
}
