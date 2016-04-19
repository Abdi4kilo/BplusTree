package com.bplustree.abdi;

public class Node {
	int key1;
	int key2;
	int key3;
	Node first;
	Node second;
	Node third;
	Node fourth;
	int counter;
	public Node() {
		counter=0;
	}
	public int getKey1() {
		return key1;
	}
	public void incrementCounter(){
		counter++;
	}
	public int getCounter(){
		return counter;
	}
	boolean checkIfFull(){
		
		if(counter==3)
			return true;
		return false;
	}
	public void setKey1(int key1) {
		this.key1 = key1;
	}
	public int getKey2() {
		return key2;
	}
	public void setKey2(int key2) {
		this.key2 = key2;
	}
	public int getKey3() {
		return key3;
	}
	public void setKey3(int key3) {
		this.key3 = key3;
	}
	public Node getFirst() {
		return first;
	}
	public void setFirst(Node first) {
		this.first = first;
	}
	public Node getSecond() {
		return second;
	}
	public void setSecond(Node second) {
		this.second = second;
	}
	public Node getThird() {
		return third;
	}
	public void setThird(Node third) {
		this.third = third;
	}
	public Node getFourth() {
		return fourth;
	}
	public void setFourth(Node fourth) {
		this.fourth = fourth;
	}
	
}
