/*
Chef wants to buy tickets to a sports game for K people (including himself).

The stadium has N seats, numbered 1 to N.
The i-th seat costs Ai rupees.

Some seats have already been bought.
You are given a binary string S:

Si = 1 → seat i is already taken
Si = 0 → seat i is available

Among the available seats, determine the minimum cost to buy K seats.

Seats do not need to be consecutive.

If it is impossible to buy K seats, print -1.

--------------------------------------------------

Constraints:

1 ≤ T ≤ 100
1 ≤ K ≤ N ≤ 100
1 ≤ Ai ≤ 100
Si ∈ {0, 1}

--------------------------------------------------


Result:
-1

input:
3
4 3
1 1 1 1
0000
4 3
4 2 6 8
1100
4 3
4 2 6 8
0100

output:
3
-1
18

Explanation:

Test Case 1:
Chef buys seats 1, 2, and 3.

Total cost:
1 + 1 + 1 = 3

--------------------------------------------------

Test Case 2:
Only 2 seats are available, but Chef needs 3.
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
		    int k = sc.nextInt();
		    
		    int arr[] = new int[n];
		    for(int i=0; i<n; i++){
		        arr[i] = sc.nextInt();
		    }
		    
		    String s = sc.next();
		    
		    PriorityQueue<Integer> pq = new PriorityQueue<>();
		    
		    for(int i=0; i<n; i++){
		        if(s.charAt(i) == '0'){
		            pq.add(arr[i]);
		        }
		    }
		    
		    if(k > pq.size()){
		        System.out.println(-1);
		    }
		    else{
		        int sum = 0;
		        for(int i=0; i<k; i++){
		            sum += pq.poll();
		        }
		        
		        System.out.println(sum);
		    }
		    
		}

	}
}
