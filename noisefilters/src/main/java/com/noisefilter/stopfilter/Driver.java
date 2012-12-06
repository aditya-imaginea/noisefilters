package com.noisefilter.stopfilter;

import java.util.Scanner;

public class Driver {
	private static StopFilterFarm filterfarm = new StopFilterFarm();

	public static void main(String args[]) {
		Scanner sc = new Scanner( Driver.class.getResourceAsStream("stopwords.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			//System.out.println(s);
			filterfarm.addTree(s);
		}
		sc.close();
		filterfarm.dump();
		System.out.println(filterfarm.searchTree("the"));
		//System.out.println(filterfarm.searchTree("alwaresderewdfa"));
		
	}
}
