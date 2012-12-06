package com.noisefilter.test;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.noisefilter.stopfilter.Driver;
import com.noisefilter.stopfilter.StopFilterFarm;

public class TestStopFilter {
	private static StopFilterFarm filterfarm = new StopFilterFarm();

	@Before
	public void setupFarm() {
		Scanner sc = new Scanner(
				Driver.class.getResourceAsStream("stopwords.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			filterfarm.addTree(s);
		}
		sc.close();
	}

	@Test
	public void test() {
		Scanner sc = new Scanner(
				Driver.class.getResourceAsStream("inputstream.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			
			Assert.assertTrue(filterfarm.searchTree(s));
			System.out.println(s);
		}
		sc.close();		
	}

}
