/*
Chef is making a layered cake consisting of 2 layers.
chef has N options for the first layer, represented by an array A = [a1, a2, a3, ..., An], where Ai represents the size of the i-th first layer of the cake.
similarlt, chef has M options for the second layer represented by and array B = [B!, ... , bn]

Chef must ensure that the size of the second layer is strictly smaller than the size of the first layer.

How many possible cakes can make by combining one first layer and one second layer while respecting that constraint.

input:
4
4 2
5 2 6 1
3 7
3 3
3 3 3
2 2 2
1 1
5
2
1 1
1
1

output:
2
9
1
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
		    int m = sc.nextInt();
		    
		    int first[] = new int[n];
		    int second[] = new int[m];
		    
		    for(int i=0; i<n; i++){
		        first[i] = sc.nextInt();
		    }
		    for(int i=0; i<m; i++){
		        second[i] = sc.nextInt();
		    }
		    
		    Arrays.sort(first);
		    Arrays.sort(second);
		    
		    int i = n-1;
		    int j = m-1;
		    
		    int ans = 0;
		    while(i>=0 && j>=0){
		        if(second[j] < first[i]){
		            ans += j+1;
		            i--;
		        }
		        else{
		            j--;
		        }
		    }
		    
		    System.out.println(ans);
		    // while ends
		}

	}
}
