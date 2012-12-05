package com.noisefilter.stopfilter;

import java.util.HashMap;
import java.util.Map;

public class StopFilterFarm {
	private Map<Character, StopTreeNode> farm = new HashMap<Character, StopTreeNode>();

	public void addTree(String stopWord) {
		if (farm.containsKey(stopWord.charAt(0))) {
			if (stopWord.length() > 1)
				farm.get(stopWord.charAt(0))
						.addChildNode(stopWord.substring(1));
		} else {
			if (stopWord.length() > 1)
				farm.put(stopWord.charAt(0),
						new StopTreeNode(stopWord.substring(1)));
			else
				farm.put(stopWord.charAt(0),
						new StopTreeNode(stopWord.charAt(0)));
		}
	}

	public boolean searchTree(String stopWord) {
		if (farm.containsKey(stopWord.charAt(0))) {
			StopTreeNode node = farm.get(stopWord.charAt(0));
			return node.search(stopWord.substring(1));
		}
		return false;
	}

	public void dump() {
		for (Character c : farm.keySet()) {
			System.out.println("parent : " + c);
			farm.get(c).dumpValue();
		}
	}
}
