package banker;

import java.util.Scanner;

public class banker {
	private int []available;
	private int [][]demand;
	private int [][]allocate;	// source has been alloted
	private int	[][]need;	// need[i, j]=demand[i, j]-allocate[i, j]
	private int []order;
	private int n,m;
	
	banker(int p, int s, int[]a, int [][]d, int [][]l) {
		n = p;
		m = s;
//		available = new int[m];
//		demand = new int[n][m];
//		allocate = new int[n][m];
		need = new int[n][m];
		available = a;
		demand = d;
		allocate = l;
		for(int i=0;i<n;i++) 
			for(int j=0;j<m;j++)
				need[i][j]=demand[i][j]-allocate[i][j];
	}
	
	public boolean isSafe() {
		int[] work = new int [m];
		order = new int[n];
		for(int i=0;i<m;i++) work[i]=available[i];
		boolean[] finish = new boolean [n];
		int count = 0;
		long t1 = System.currentTimeMillis(),t2;
		for(int i=0;i<n;i++) finish[i]=false;
		for(int i=0;i<n;i++) {
			if(satisfy(need[i],work)==true&&finish[i]==false) {
				for(int j=0;j<m;j++) {
					work[j]+=allocate[i][j];	// release
				}
				order[count]=i;
				finish[i]=true;
				count++;
			}
			if(count == n)return true;
			if(i == n-1)i=-1;
			t2 = System.currentTimeMillis();
			if(t2-t1>100)break;
		}
		
		for (int i=0;i<n;i++) {
			if(finish[i]==false)return false;
		}
		return true;
	}
	
	private boolean satisfy(int[]need, int []work) {
		for(int i=0;i<need.length;i++) {
			if(need[i]>work[i])return false;
		}
		return true;
	}
	
	public int[] getOrder() {
		return order;
	}
	
//	public static void main(String[] args) {
//		new banker().isSafe();
//	}
}
