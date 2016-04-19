package com.bplustree.abdi;

public class BplusTree {
	Node root;
	public BplusTree() {
		root=null;
	}
	public void addElement(int key){
		if(root==null){
			Node newNode=new Node();
			newNode.setKey1(key);
			newNode.incrementCounter();
			root=newNode;
		}else{
			if(!root.checkIfFull()){
				int numberOfComparisons=root.getCounter();
				if(numberOfComparisons==1){
					if(key<root.getKey1()){
						int temp=root.getKey1();
						root.setKey1(key);
						root.setKey2(temp);
					}else{
						root.setKey2(key);
					}
					root.incrementCounter();
				}else if(numberOfComparisons==2){
					int temp1=root.getKey1();
					int temp2=root.getKey2();
					if(key<root.getKey1() && key<root.getKey2()){
						root.setKey1(key);
						root.setKey2(temp1);
						root.setKey3(temp2);
					}else if(key>root.getKey1() && key<root.getKey2()){
						root.setKey2(key);
						root.setKey3(temp2);
					}else if(key>root.getKey1() && key>root.getKey2()){
						root.setKey3(key);
					}
					root.incrementCounter();
					
				}
			}else{
				System.out.println("its full");
				Node newNode=new Node();
				int middle = 0;
				
				if(key<root.getKey1()){
					 middle=root.getKey1();
					newNode.setKey1(middle);
					
				}else if(key<root.getKey2()){
					 middle=key;
					
				}else if(key<root.getKey3()){
					middle=root.getKey2();
					
				}else if(key>root.getKey3()){
					middle=root.getKey2();
					
				}
				
				
			}
		}
	}
	public void print(){
		System.out.println(root.getKey1());
		System.out.println(root.getKey2());
		System.out.println(root.getKey3());
	}
}