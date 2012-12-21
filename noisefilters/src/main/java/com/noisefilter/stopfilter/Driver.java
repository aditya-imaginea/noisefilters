package com.noisefilter.stopfilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Driver {
	private static StopFilterFarm filterfarm = new StopFilterFarm();

	public static void main(String args[]) throws Exception {
		File file = new File(CommonParameters.dataPath+"stopwords.txt");
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		Scanner sc = new Scanner( Driver.class.getResourceAsStream("routes.txt"));
//		int count = 0;
//		while (sc.hasNext()) {
//			String s = sc.next();
//			count++;
//			bw.write(s.substring(0,8)+"\n") ;
//			//System.out.println(s.substring(0,8));
//			//filterfarm.addTree(s);
//		}
//		//System.out.println(count);
//		bw.close();
//		sc.close();
		Scanner sc_stop = new Scanner(file);
		while (sc_stop.hasNext()) {
			String s = sc_stop.next();
			filterfarm.addTree(s);		
		}
		sc_stop.close();
		Scanner sc_data = new Scanner(Driver.class.getResourceAsStream("routes.txt"));
		while (sc_data.hasNext()) {
			String s = sc_data.next();
			if(filterfarm.searchTree(s.substring(0,8))){
				System.out.println(s.substring(8));
			}	
			else
				System.out.println(s);
		}
		sc_data.close();
		//filterfarm.dump();
		//System.out.println(filterfarm.searchTree("ZL4178SY"));
		//System.out.println(filterfarm.searchTree("alwaresderewdfa"));
		
	}
}
