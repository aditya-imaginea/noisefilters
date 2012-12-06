package com.noisefilter.stopfilter;

import java.util.HashMap;
import java.util.Map;

public class StopFilterFarm {
	private Map<Character, StopFarmNode> farm = new HashMap<Character, StopFarmNode>();

	public void addTree(String stopWord) {
		StopFarmNode farmNode = farm.get(stopWord.charAt(0));
		if (farmNode != null) {
			if (stopWord.length() > farmNode.getMaxLength()) {
				farmNode.setMaxLength(stopWord.length());
			}
			if (stopWord.length() > 1)
				farm.get(stopWord.charAt(0)).getFarmNode()
						.addChildNode(stopWord.substring(1));
		} else {
			if (stopWord.length() > 1)
				farm.put(stopWord.charAt(0),
						new StopFarmNode(stopWord.length(), new StopTreeNode(
								stopWord.substring(1))));
			else
				farm.put(stopWord.charAt(0),
						new StopFarmNode(stopWord.length(), new StopTreeNode(
								stopWord.charAt(0))));
		}
	}

	public boolean searchTree(String stopWord) {
		if (farm.containsKey(stopWord.charAt(0))) {
			StopTreeNode node = farm.get(stopWord.charAt(0)).getFarmNode();
			return node.search(stopWord.substring(1));
		}
		return false;
	}

	public void dump() {
		for (Character c : farm.keySet()) {
			System.out.println("parent : " + c +" max depth: "+farm.get(c).getMaxLength() );
			//farm.get(c).getFarmNode().dumpValue();
		}
	}
}
