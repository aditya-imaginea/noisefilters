package com.noisefilter.stopfilter;

import java.util.HashMap;
import java.util.Map;

public class StopTreeNode {
	private Character value;
	private Map<Character, StopTreeNode> childMap = new HashMap<Character, StopTreeNode>();

	// for building leaf node
	public StopTreeNode(Character value) {
		super();
		this.value = value;
	}

	// for building the tree top down from a stop word
	public StopTreeNode(String inputSequence) {
		this(inputSequence.charAt(0));
		if (inputSequence.length() > 1)
			this.addChildNode(inputSequence.substring(1));
	}

	public Character getValue() {
		return value;
	}

	public StopTreeNode getChildNode(Character childRoot) {
		return childMap.get(childRoot);
	}

	// recursive search through the stop word tree.
	// Search for the child node in BFS way and then get into its childnodes
	// using recursion (DFS)
	public boolean search(String inputQuery) {
		StopTreeNode childNode = getChildNode(inputQuery.charAt(0));
		if (childNode == null)
			return false;
		else {
			//if inputQuery length is 1 and it overcame the above hurdle
			//it means the whole query has been found in a stop word tree.
			//so return true
			if ((inputQuery.length() == 1))
				return true;
			else
				return childNode.search(inputQuery.substring(1));
		}
	}

	public void addChildNode(String inputSequence) {
		StopTreeNode node = getChildNode(inputSequence.charAt(0));
		if (node != null) {
			// if it just has a length >1 check to what depth the tree
			// is similar to the one already built
			if (inputSequence.length() > 1)
				node.addChildNode(inputSequence.substring(1));
		} else {
			// if Child node is null create one and add it to the map
			// if the input sequence has just one character just add the
			// node dont bother with subnodes
			node = new StopTreeNode(inputSequence.charAt(0));
			childMap.put(inputSequence.charAt(0), node);
			if (inputSequence.length() > 1)
				node.addChildNode(inputSequence.substring(1));

		}
	}

	public void dumpValue() {
		for (Character child : childMap.keySet()) {

			getChildNode(child).dumpValue();
			System.out.println("\t" + child);
		}
	}
}
