package com.bplustree.database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	public static void main(String args[]){
		BplusTree a=new BplusTree();
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter a name into the employee table using sql example below");
		System.out.println("INSERT INTO EMPLOYEE VALUES(1,IT,Abdi,100)");
		System.out.println("SELECT * FROM EMPLOYEE");
		System.out.println("SELECT * FROM EMPLOYEE WHERE ID=1");
		System.out.println("SELECT * FROM EMPLOYEE WHERE ID>1");
		System.out.println("DELETE * FROM EMPLOYEE WHERE ID=1");
		System.out.println("Enter exit to exit program");

		while(true){
			String table=input.nextLine();
			if(table.equals("exit")){
				System.out.println("Thank you for using!!");
				break;
			}
			if(table.length()<4){
				System.out.println("Please enter valid query");
				continue;
			}
			String which=table.substring(0,6);
			if(which.equals("SELECT")){
				if(table.length()>28){
					if(table.substring(31,32).equals("=")){
						String checkquery=table.substring(0,32);
						if(checkquery.equals("SELECT * FROM EMPLOYEE WHERE ID=")){
							String values=table.substring(32);
							int id=Integer.parseInt(values);
							a.LookUp(id);
							
						}else{
							System.out.println("Please enter valid query");
							continue;
						}
					}else{
						String checkquery=table.substring(0,32);
						if(checkquery.equals("SELECT * FROM EMPLOYEE WHERE ID>")){
							String values=table.substring(32);
							int id=Integer.parseInt(values);
							a.Range(id);
							
						}else{
							System.out.println("Please enter valid query");
							continue;
						}
					}
					
					
				}else if(table.length()>20){
					String checkquery=table.substring(0,22);
					if(checkquery.equals("SELECT * FROM EMPLOYEE")){
						String tablename=table.substring(14);
						if(tablename.equals("EMPLOYEE")){
							a.display();
						}
					}else{
						System.out.println("Please enter valid query");
						continue;
					}
				}else{
					System.out.println("Please enter valid query");
					continue;
				}
			}else if(which.equals("INSERT")){
				String checkquery=table.substring(0,27);
				String values=table.substring(27);
				String nobrace= values.replaceAll("\\p{P}"," ");
				String split[]=nobrace.split(" ");
				String id = null,depname = null,mgrname = null,deppop = null;
				
				for(int i=0;i<split.length;i++){
					if(i==1){
						id=split[1];
					}else if(i==2){
						depname=split[2];
					}else if(i==3){
						mgrname=split[3];
					}else if(i==4){
						deppop=split[4];
					}
				}
				int ID=Integer.parseInt(id);
				int DEPP=Integer.parseInt(deppop);
				
				if("INSERT INTO EMPLOYEE VALUES".equals(checkquery)){
					//System.out.println(checkquery+""+name);
					if(isAlpha(depname) &&  isAlpha(mgrname)){
						a.insert(ID,depname,mgrname,DEPP);
						System.out.println("Succesfully Entered to database");
					}else{
						System.out.println("DNAME AND DMGR HAVE CONSTRAINT OF STRING");
					}
				}else{
					System.out.println("Please enter a valid query");
				}
			}else if(which.equals("DELETE")){
				String checkquery=table.substring(0,32);
				if(checkquery.equals("DELETE * FROM EMPLOYEE WHERE ID=")){
					String values=table.substring(32);
					int id=Integer.parseInt(values);
					a.Delete(id);
					
				}else{
					System.out.println("Please enter valid query");
					continue;
				}
			}
		}

}
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
}
