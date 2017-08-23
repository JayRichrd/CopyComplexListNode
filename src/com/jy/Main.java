package com.jy;

public class Main {

	public static void main(String[] args) {

	}

	/**
	 * 第一步 复制出新的节点，并让新节点接在原始节点后
	 * 
	 * @param head
	 *            待复制链表的头结点
	 */
	public static void cloneNodes(ComplexListNode head) {
		// 当前节点
		ComplexListNode complexListNode = head;
		// 循环复制当前节点，并将复制出来的节点跟在当前节点后面
		while (complexListNode != null) {
			// 新节点的mSibling为null
			ComplexListNode newNode = new ComplexListNode(complexListNode.mValue, complexListNode.mNext, null);
			complexListNode.mNext = newNode;
			// 这里是用原始节点的下一个节点继续往下循环，因此使用的是newNode.mNext
			complexListNode = newNode.mNext;
		}
	}

	/**
	 * 第二步 填充复制出的节点的mSibling
	 * 
	 * @param head
	 *            待连接的头节点
	 */
	public static void connectedSiblingNodes(ComplexListNode head) {
		// 当前节点
		ComplexListNode complexListNode = head;
		// 循环填充复制出的节点的mSibling
		while (complexListNode != null) {
			if (complexListNode.mSibling != null)
				complexListNode.mNext.mSibling = complexListNode.mSibling.mNext;
			// 遍历原始节点，找到复制的节点和mSibling
			complexListNode = complexListNode.mNext.mNext;
		}
	}

	/**
	 * 第三步 重新整理前两步得到的链表，得到复制出的链表，并返回它的头结点
	 * 
	 * @param head
	 *            原始节点和复制出链表混合的链表的头结点
	 * @return 返回复制出的链表的头结点
	 */
	public static ComplexListNode reconnectedNodes(ComplexListNode head) {
		// 当前节点
		ComplexListNode complexListNode = head;
		// 复制出的链表的头结点
		ComplexListNode newNodeHead = null;

		ComplexListNode newNode = null;
		// 先找到复制出链表的头节点
		if (complexListNode != null) {
			newNodeHead = newNode = complexListNode.mNext;

			complexListNode.mNext = newNode.mNext;
			complexListNode = complexListNode.mNext;
		}
		// 从第三个节点开始
		while (complexListNode != null) {
			newNode.mNext = complexListNode.mNext;
			newNode = newNode.mNext;
			complexListNode.mNext = newNode.mNext;
			complexListNode = complexListNode.mNext;
		}
		return newNodeHead;
	}

}
