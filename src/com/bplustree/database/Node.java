package com.bplustree.database;

import java.util.Arrays;

public class Node {
	int keys[]=new int[3];
	String tuple[]=new String[1];
	Node first,second,third,fourth;
	int counter;
	Node parentNode;
	
	public Node(){
		counter=0;
		first=null;
		second=null;
		third=null;
		fourth=null;
		parentNode=null;
		tuple[0]="";
	}
	public boolean isFull(){
		if(counter==3)
			return true;
		return false;
	}
	public int getCounter() {
		return counter;
	}
	public void increament() {
		 counter++;
	}
	public void setCounter(int counter){
		this.counter=counter;
	}
	public int[] getKeys() {
		return keys;
	}
	public void setKeys(int[] keys) {
		this.keys = Arrays.copyOf(keys,3);
	}
	public String[] getTuple() {
		return tuple;
	}
	public void setTuple(String[] tuple) {
		this.tuple = tuple;
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

