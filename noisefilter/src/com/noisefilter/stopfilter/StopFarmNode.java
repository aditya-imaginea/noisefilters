package com.noisefilter.stopfilter;

/**
 * This class holds the top level farm node along with the depth of the tree at
 * each level
 * 
 * @author adityap
 * 
 */
public class StopFarmNode {
	private int maxLength;
	private StopTreeNode farmNode;

	public StopFarmNode(int length, StopTreeNode stopTreeNode) {
		super();
		this.maxLength = length;
		this.farmNode = stopTreeNode;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public StopTreeNode getFarmNode() {
		return farmNode;
	}

	public void setFarmNode(StopTreeNode farmNode) {
		this.farmNode = farmNode;
	}

}
