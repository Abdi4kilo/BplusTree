package com.bplustree.database;

import java.util.Arrays;

public class BplusTree {
	Node root=null;
	int nodeKeys[]=new int[3];
	public void insert(int key){
		if(root==null){
			Node newNode=new Node();
			//System.out.println(newNode.getCounter()+"counter");
			newNode.getKeys()[0]=key;
			newNode.increament();
			root=newNode;
		}else{
			Node traverse=root;
			int numberOfComparisions=traverse.getCounter();
			//System.out.println(numberOfComparisions+"counter inside");
			nodeKeys=Arrays.copyOf(traverse.getKeys(),3);
			boolean looped=false;
			for(int i=0;i<numberOfComparisions;i++){
				if(key<nodeKeys[i]){
					if(traverse.getFirst()==null){
						if(numberOfComparisions!=3){
							insertToPosition(numberOfComparisions,key,i);
							traverse.setKeys(nodeKeys);
							traverse.increament();   
						}else{
							int fourKeys[]=middleValue(key);
							//System.out.println(fourKeys[1]);
							if(traverse.parentNode==null){
								Node nodeparent=new Node();
								nodeparent.getKeys()[0]=key;
								nodeparent.increament();
								traverse.setKeys(Arrays.copyOfRange(fourKeys,1,4));
								Node nodeleft=new Node();
								nodeleft.getKeys()[0]=fourKeys[0];
								nodeleft.increament();
								nodeleft.setFourth(traverse);
								nodeleft.parentNode=nodeparent;
								traverse.parentNode=nodeparent;
								nodeparent.first=nodeleft;
								nodeparent.second=traverse;
								root=nodeparent;
							}else{
								// go up
							}
						
							
							
						}
						looped=true;
						break;
					}else{
						moveToRightLevel(traverse, i,key);
					}
				}
			}
			if(traverse.getFirst()==null && looped==false){
				if(numberOfComparisions!=3){
					nodeKeys[numberOfComparisions]=key;
					traverse.increament();
					traverse.setKeys(nodeKeys);
				}else{
					int fourKeys[]=middleValue(key);
					if(traverse.parentNode==null){
						Node nodeparent=new Node();
						nodeparent.getKeys()[0]=fourKeys[1];
						Node nodeleft=new Node();
						nodeleft.getKeys()[0]=fourKeys[0];
						traverse.setKeys(Arrays.copyOfRange(fourKeys,1,4));
						nodeleft.setFourth(traverse);
						nodeleft.parentNode=nodeparent;
						traverse.parentNode=nodeparent;
						nodeparent.first=nodeleft;
						nodeparent.second=traverse;
						root=nodeparent;
						
					}else{
						// go up
					}
				}
			}
			
		}
	}
	private void insertToPosition(int numberOfComparisions, int key,int i) {
			int num=numberOfComparisions;
			for(int j=i;j<numberOfComparisions;j++){
				nodeKeys[num]=nodeKeys[num-1];
				num--;
			}
			nodeKeys[i]=key;
	}
	public int[] middleValue(int key){
		int four[]=new int[4];
		four=Arrays.copyOf(nodeKeys,4);
		four[3]=key;
		Arrays.sort(four);
		return four;
	}
	public void moveToRightLevel(Node traverse,int i,int key){
		if(i==0){
			traverse=traverse.getFirst();
		}
		while(true){
			int counter=traverse.getCounter();
			nodeKeys=Arrays.copyOf(traverse.getKeys(),3);
			for(int j=0;j<counter;j++){
				if(key<nodeKeys[j]){
					if(traverse.getFirst()==null){
						if(counter!=3){
							insertToPosition(counter,key,i);
							traverse.setKeys(nodeKeys);
							traverse.increament();   
							break;
						}
					}else{
						moveToRightLevel(traverse, j, key);
						break;
					}
				}
			}
		}
	}
	public void print(){
		Node node=root;
		while(node.first!=null){
			node=node.first;
		}
		System.out.println(node.getKeys()[0]+"keys");
		while(node.fourth!=null){
			node=node.fourth;
			for(int i=0;i<node.getCounter();i++){
				System.out.println(node.getKeys()[i]+"keys");
			}
		}
	}
}
