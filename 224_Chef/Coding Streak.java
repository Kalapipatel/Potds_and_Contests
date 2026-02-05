/*
you are given as array A of size N, where Ai represents the number of problems solved on the i-th day.
A streak is defined as a sequence of consecutive days where you solves at least 1 problem every day.
find the length of the longest streak present among these N days.

input:
4
1
1
2
5 0
4
0 5 2 0
2
0 0

output:
1
1
2
0
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		while(t-- > 0){
		    int n = sc.nextInt();
		    int arr[] = new int[n];
		    
		    for(int i=0; i<n; i++){
		        arr[i] = sc.nextInt();
		    }
		    
		    int max = 0;
		    int count = 0;
		    
		    for(int i=0; i<n; i++){
		        if(arr[i] != 0){
		            count++;
		            max = Math.max(max, count);
		        }
		        else{
		            count = 0;
		        }
		    }
		    
		    System.out.println(max);
		}

	}
}
