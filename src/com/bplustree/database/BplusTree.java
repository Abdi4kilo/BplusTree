package com.bplustree.database;

import java.util.Arrays;
import java.util.HashMap;

public class BplusTree {
	Node root=null;
	int nodeKeys[]=new int[3];
	HashMap<Integer,Department> map=new HashMap<Integer,Department>();
	public void insert(int key,String dname,String mgrname,int empnum){
		Department dep=new Department();
		dep.setId(key);
		dep.setDepartment_name(dname);
		dep.setMgr_name(mgrname);
		dep.setNumber_of_employees(empnum);
		map.put(key, dep);
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
					//System.out.println("move down if less");
					// move down
					moveDown(traverse, i,key);
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
			moveDown(traverse,3,key);
			//moveToRightLevel(traverse, i, key);
			//System.out.println("move down if greater");
		}
	}
	public void split(Node traverse,int key){
		int fourKeys[]=middleValue(key);
	//System.out.println(fourKeys[0]+" "+fourKeys[1]+" "+fourKeys[2]+" "+fourKeys[3]+" ");
	//	System.out.println(traverse.getKeys()[0]+" "+traverse.getKeys()[1]+" ");
		if(traverse.parentNode==null){
			System.out.println("here");
			Node nodeparent=new Node();
			nodeparent.getKeys()[0]=fourKeys[1];
			nodeparent.increament();
			Node nodeleft=new Node();
			nodeleft.getKeys()[0]=fourKeys[0];
			nodeleft.increament();
			Node noderight=new Node();
			noderight.setKeys(Arrays.copyOfRange(fourKeys,1,4));
			noderight.setCounter(3);
			nodeleft.setFourth(noderight);
			nodeleft.parentNode=nodeparent;
			noderight.parentNode=nodeparent;
			nodeparent.first=nodeleft;
			nodeparent.second=noderight;
			root=nodeparent;
		}else{
			//go up
			if(!traverse.parentNode.isFull()){
			
				key=fourKeys[1];
			//	System.out.println(fourKeys[0]+"  "+fourKeys[1]+"  "+fourKeys[2]+"  "+fourKeys[3]);
				//traverse.setKeys(Arrays.copyOfRange(fourKeys,1,4));
				traverse=traverse.parentNode;
				boolean looped=false;
				nodeKeys=Arrays.copyOf(traverse.getKeys(),3);
				for(int i=0;i<traverse.getCounter();i++){
					if(key<nodeKeys[i]){
					
						insertToPosition(traverse.getCounter(), key, i);
						traverse.setKeys(nodeKeys);
						traverse.increament(); 
						Node less=new Node();
						less.parentNode=traverse;
						less.increament();
						less.getKeys()[0]=fourKeys[0];
						Node more=new Node();
						more.setKeys(Arrays.copyOfRange(fourKeys,1,4));
						more.setCounter(3);
						more.parentNode=traverse;
						int index=-1;
						
						for(int j=0;j<traverse.getCounter();j++){
							if(key==traverse.getKeys()[j]){
								index=j;
								break;
							}
						}
						if(index==0){
							less.fourth=more;
							traverse.first=less;
							more.fourth=traverse.second;
							traverse.second=more;
						}else if(index==1){
							traverse.first.fourth=less;
							less.fourth=more;
							more.fourth=traverse.second;
							traverse.second=less;
							traverse.third=more;
						}
						looped=true;
						break;
					}
				}
				if(looped==false){
					//System.out.println(nodeKeys[0]+" "+nodeKeys[1]+" "+nodeKeys[2]);
					//System.out.println(traverse.getCounter());
					//System.out.println(key);
					//System.out.println("keza");
					nodeKeys[traverse.getCounter()]=key;
					//System.out.println(key+"kj");
					traverse.setKeys(nodeKeys);
					traverse.increament();
//					for(int i=0;i<traverse.counter;i++){
//						System.out.println(traverse.getKeys()[i]+"di");
//					}
					if(traverse.getCounter()==2){
						Node less=new Node();
						less.getKeys()[0]=fourKeys[0];
						less.increament();
						Node more=new Node();
						more.setKeys(Arrays.copyOfRange(fourKeys,1,4));
						more.setCounter(3);
						more.parentNode=traverse;
						less.parentNode=traverse;
						traverse.first.fourth=less;
						traverse.second=less;
						less.fourth=more;
						traverse.third=more;
					}else if(traverse.getCounter()==3){
						Node less=new Node();
						less.getKeys()[0]=fourKeys[0];
						less.increament();
						Node more=new Node();
						more.setKeys(Arrays.copyOfRange(fourKeys,1,4));
						more.setCounter(3);
						traverse.second.fourth=less;
						less.setFourth(more);
						traverse.setThird(less);
						traverse.setFourth(more);
					}
				}
			}else if(traverse.parentNode.isFull()){
				//if it is full
//				Node left=new Node();
//				left.getKeys()[0]=fourKeys[0];
//				left.increament();
//				Node right=new Node();
//				right.setKeys(Arrays.copyOfRange(fourKeys,1,4));
//				right.setCounter(3);
//				Node parent=new Node();
//				parent.getKeys()[0]=fourKeys[1];
//				parent.increament();
//				left.parentNode=parent;
//				right.parentNode=parent;
//				left.fourth=right;
//				right=traverse.fourth;
//				parent.first=left;
//				parent.second=right;
				// YOu should continue here
				
				
			}
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
	public void moveDown(Node traverse,int i,int key){
		if(i==0){
			traverse=traverse.getFirst();
		}else if(i==1){
			traverse=traverse.getSecond();
		}else if(i==2){
			traverse=traverse.getThird();
		}else if(i==3){
			int count=traverse.getCounter();
			if(count==1){
				traverse=traverse.getSecond();
			}else if(count==2){
				traverse=traverse.getThird();
//				System.out.println(traverse.getKeys()[0]+" "+traverse.getKeys()[1]+" "+traverse.getKeys()[2]);
			}else if(count==3){
				traverse=traverse.getFourth();
			}
		}
		moveToLevel(traverse, key);
	}
	public void LookUp(int value){
		Node node=root;
		boolean finding=false;
		for(int i=0;i<node.getCounter();i++){
			if(value<node.getKeys()[i]){
				if(i==0 && node.first!=null){
					node=node.first;
				}else if(i==1 && node.second!=null){
					node=node.second;
				}else if(i==2 && node.third!=null){
					node=node.third;
				}
				finding=true;
				break;
			}else if(value==node.getKeys()[i]){
				if(i==0 && node.second!=null)
					node=node.second;
				if(i==1 && node.third!=null)
					node=node.third;
				if(i==2 && node.fourth!=null)
					node=node.fourth;
				finding=true;
				break;
			}
		}
		if(finding==false){
			if(node.getCounter()==1 && node.second!=null){
				node=node.second;
			}
			else if(node.getCounter()==2 && node.third!=null){
				node=node.third;
			}
			else if(node.getCounter()==3 && node.fourth!=null){
				node=node.fourth;
			}
		}
		boolean found=false;
		for(int i=0;i<node.getCounter();i++){
			if(value==node.getKeys()[i] && node.getKeys()[i]!=-1){
				System.out.println("---------------------------------");
				System.out.println("ID  |  DNAME   |   DMGR  | EMPNO");
				System.out.println("---------------------------------");
				Department console=map.get(value);
				System.out.println(console.getId()+"  |  "+console.getDepartment_name()+"      |    "+console.getMgr_name()+" |  "+console.getNumber_of_employees());
				System.out.println("---------------------------------");
				found=true;
			}
		}
		if(found==false){
			System.out.println("the number you entered doesn't exist");
		}
	}
	public void print(){
		
		Node node=root;
		while(node.first!=null){
			node=node.first;
		}
		for(int i=0;i<node.getCounter();i++){
			System.out.print(node.getKeys()[i]+" ");
		}
		System.out.print("->");
		while(node.fourth!=null){
			node=node.fourth;
			for(int i=0;i<node.getCounter();i++){
				System.out.print(node.getKeys()[i]+" ");
			}
			System.out.print("->");
		}
		System.out.println();
	}
	public void display(){
		System.out.println("---------------------------------");
		System.out.println("ID  |  DNAME   |   DMGR  | EMPNO");
		System.out.println("---------------------------------");
		Node node=root;
		while(node.first!=null){
			node=node.first;
		}
		for(int i=0;i<node.getCounter();i++){
			if(node.getKeys()[i]!=-1){
				Department console=map.get(node.getKeys()[i]);
				System.out.println(console.getId()+"  |  "+console.getDepartment_name()+"      |    "+console.getMgr_name()+" |  "+console.getNumber_of_employees());
				System.out.println("---------------------------------");
			}
		}
		
		while(node.fourth!=null){
			node=node.fourth;
			for(int i=0;i<node.getCounter();i++){
				if(node.getKeys()[i]!=-1){
					Department console=map.get(node.getKeys()[i]);
					System.out.println(console.getId()+"  |  "+console.getDepartment_name()+"      |    "+console.getMgr_name()+" |  "+console.getNumber_of_employees());
					System.out.println("---------------------------------");
				}
				
			}
		}
	}
	
	public void Range(int id){
		System.out.println("---------------------------------");
		System.out.println("ID  |  DNAME   |   DMGR  | EMPNO");
		System.out.println("---------------------------------");
		Node node=root;
		while(node.first!=null){
			node=node.first;
		}
		for(int i=0;i<node.getCounter();i++){
			if(id<=node.getKeys()[i] && node.getKeys()[i]!=-1){
				Department console=map.get(node.getKeys()[i]);
				System.out.println(console.getId()+"  |  "+console.getDepartment_name()+"      |    "+console.getMgr_name()+" |  "+console.getNumber_of_employees());
				System.out.println("---------------------------------");
			}
		}
		while(node.fourth!=null){
			node=node.fourth;
			for(int i=0;i<node.getCounter();i++){
				if(id<=node.getKeys()[i] && node.getKeys()[i]!=-1){
					Department console=map.get(node.getKeys()[i]);
					System.out.println(console.getId()+"  |  "+console.getDepartment_name()+"      |    "+console.getMgr_name()+" |  "+console.getNumber_of_employees());
					System.out.println("---------------------------------");
				}
			}
			
		}
		
	}
	public void Delete(int value){
		Node node=root;
		boolean finding=false;
		for(int i=0;i<node.getCounter();i++){
			if(value<node.getKeys()[i]){
				if(i==0 && node.first!=null){
					node=node.first;
				}else if(i==1 && node.second!=null){
					node=node.second;
				}else if(i==2 && node.third!=null){
					node=node.third;
				}
				finding=true;
				break;
			}else if(value==node.getKeys()[i]){
				if(i==0 && node.second!=null)
					node=node.second;
				if(i==1 && node.third!=null)
					node=node.third;
				if(i==2 && node.fourth!=null)
					node=node.fourth;
				finding=true;
				break;
			}
		}
		if(finding==false){
			if(node.getCounter()==1 && node.second!=null){
				node=node.second;
			}
			else if(node.getCounter()==2 && node.third!=null){
				node=node.third;
			}
			else if(node.getCounter()==3 && node.fourth!=null){
				node=node.fourth;
			}
		}
		boolean found=false;
		for(int i=0;i<node.getCounter();i++){
			if(value==node.getKeys()[i]){
				node.getKeys()[i]=-1;
				System.out.println("the colum has been deleted");
				found=true;
			}
		}
		if(found==false){
			System.out.println("the number you entered doesn't exist");
		}
	}
}
