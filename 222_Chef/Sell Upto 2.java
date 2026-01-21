/*
There are N days, and the sell price of a watch on the ith day is Ai, Initially you have 0 watches.

For each day from 1 to N, the follwing events happen:

First, you acqire one watch for free on each day.
Next, you may choose to sell uto 2 watches (that you own) for Ai coins each.

What is the maximum porfit you can ontain by optimally selling items.

input:
3
3
1 2 3
3
3 2 1
4
2 7 3 5

output:
8
6
24

Explanation:
Test case1: The optmial approach is as follow:
- sell nothing on day 1
- sell one watch on day 2 fro 2 coins
- sell 2 watch on day 3 for 5 coins.

Test case 2:
we should sell 1 watch on each day for 3+ 2+ 1 = 6 coins
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
            int[] arr = new int[n];
                
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            long ans = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            
            for(int i=n-1; i>=0; i--){
                pq.add(arr[i]);
                pq.add(arr[i]);
                
                if(!pq.isEmpty()){
                    ans += pq.poll();
                }
            }
		    
		    System.out.println(ans);
		}
   
	}
}
