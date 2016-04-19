package com.bplustree.database;

import java.util.Arrays;

public class BplusTree {
	Node root=null;
	int nodeKeys[]=new int[3];
	public void insert(int key){
		if(root==null){
			Node newNode=new Node();
			newNode.getKeys()[0]=key;
			newNode.increament();
			root=newNode;
		}else{
			Node traverse=root;
			moveToLevel(traverse, key);
		}
	}
	
	public void moveToLevel(Node traverse,int key){
		boolean looped=false;
		nodeKeys=Arrays.copyOf(traverse.getKeys(),3);
		int numberOfComparisions=traverse.getCounter();
		for(int i=0;i<numberOfComparisions;i++){
			if(key<nodeKeys[i]){
				if(traverse.getFirst()==null){
					if(numberOfComparisions!=3){
						insertToPosition(numberOfComparisions,key,i);
						traverse.setKeys(nodeKeys);
						traverse.increament(); 
					}else{
						split(traverse,key);
					}
				}else{
					System.out.println("move down if less");
					// move down
					moveDown(traverse, i);
				}
				looped=true;
				break;
			}
		}
		if(traverse.getFirst()==null && looped==false){
			if(numberOfComparisions!=3){
				nodeKeys[numberOfComparisions]=key;
				traverse.increament();
				traverse.setKeys(nodeKeys);
			}else{
				split(traverse,key);
			}
		}else if(traverse.getFirst()!=null && looped==false){
			moveDown(traverse,3);
			//moveToRightLevel(traverse, i, key);
			System.out.println("move down if greater");
		}
	}
	public void split(Node traverse,int key){
		int fourKeys[]=middleValue(key);
		if(traverse.parentNode==null){
			Node nodeparent=new Node();
			nodeparent.getKeys()[0]=fourKeys[1];
			nodeparent.increament();
			Node nodeleft=new Node();
			nodeleft.getKeys()[0]=fourKeys[0];
			nodeleft.increament();
			traverse.setKeys(Arrays.copyOfRange(fourKeys,1,4));
			nodeleft.setFourth(traverse);
			nodeleft.parentNode=nodeparent;
			traverse.parentNode=nodeparent;
			nodeparent.first=nodeleft;
			nodeparent.second=traverse;
			root=nodeparent;
		}else{
			//go up
		}
	}
	public int[] middleValue(int key){
		int four[]=new int[4];
		four=Arrays.copyOf(nodeKeys,4);
		four[3]=key;
		Arrays.sort(four);
		return four;
	}
	private void insertToPosition(int numberOfComparisions, int key,int i) {
		int num=numberOfComparisions;
		for(int j=i;j<numberOfComparisions;j++){
			nodeKeys[num]=nodeKeys[num-1];
			num--;
		}
		nodeKeys[i]=key;
	}
	public void moveDown(Node traverse,int i){
		System.out.println(i+" node");
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
