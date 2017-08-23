package com.jy;

public class Main {

	public static void main(String[] args) {
		// 构建链表
		ComplexListNode node5 = new ComplexListNode(5, null, null);
		ComplexListNode node4 = new ComplexListNode(4, node5, null);
		ComplexListNode node3 = new ComplexListNode(3, node4, null);
		ComplexListNode node2 = new ComplexListNode(2, node3, null);
		ComplexListNode node1 = new ComplexListNode(1, node2, null);
		node1.mSibling = node3;
		node2.mSibling = node5;
		node4.mSibling = node2;
		System.out.println("====================原始链表结构====================");
		printNodes(node1);
		System.out.println("====================克隆链表结构====================");
		cloneNodes(node1);
		connectedSiblingNodes(node1);
		reconnectedNodes(node1);
		printNodes(node1);
	}

	/**
	 * 打印链表
	 * 
	 * @param head
	 *            待打印链表的头结点
	 */
	public static void printNodes(ComplexListNode head) {
		ComplexListNode complexListNode = head;
		System.out.print("按顺序打印链表：");
		while (complexListNode.mNext != null) {
			System.out.print(complexListNode.mValue + "->");
			complexListNode = complexListNode.mNext;
		}
		System.out.print(complexListNode.mValue);

		System.out.println();

		complexListNode = head;
		System.out.println("打印mSibling信息：");
		while (complexListNode != null) {
			if (complexListNode.mSibling != null)
				System.out.println(complexListNode.mValue + "->" + complexListNode.mSibling.mValue);
			complexListNode = complexListNode.mNext;
		}
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
