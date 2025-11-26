package rw.loops.java;
//import java.util.Scanner;

public class MyLoops {
	public static void main(String[] args){
		/*
		 0 1
		 1 2
		 2 4
		 3 8
		 4 16
		 5 32
		 */
		
		
		// 1 way
		
		for(int i = 0,j = 1 ; i <= 5 ; i++ , j= j*2) {
			System.out.println(i + " " + j);	
		}
		
		//2 way
		
		for(int i = 0; i <= 5; i++) {
			int first = i;
			int second = (int) Math.pow(2, i );
			System.out.println(first + " " + second);
		}
		
		// while loop
		int num = 0;
		while(num <= 5) {
			System.out.println("Iteration " + num);
			num++;
		}
	}
}
