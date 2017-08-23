package com.jy;

public class ComplexListNode {
	// 节点值
	public int mValue;
	// 指向下一个节点
	public ComplexListNode mNext;
	// 指向任意节点
	public ComplexListNode mSibling;

	public ComplexListNode() {
		super();
	}

	public ComplexListNode(int mValue, ComplexListNode mNext, ComplexListNode mSibling) {
		super();
		this.mValue = mValue;
		this.mNext = mNext;
		this.mSibling = mSibling;
	}
}
