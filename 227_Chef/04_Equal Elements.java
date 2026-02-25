/*
Equal Elements

An array B of size 2·M is called good if:

For each i = 1, 2, …, M:
B[2·i − 1] = B[2·i]

Examples of good arrays:
[1, 1, 3, 3]
[4, 4]

Not good:
[1, 1, 2]

--------------------------------------------------

You are given an array A of length N.

Find the length of the longest subsequence of A that is good.

A subsequence is obtained by deleting elements without changing
the order of the remaining elements.

The subsequence does NOT need to be contiguous.

--------------------------------------------------

Constraints:

1 ≤ T ≤ 10^4
2 ≤ N ≤ 2·10^5
1 ≤ Ai ≤ N

The sum of N over all test cases does not exceed 2·10^5.

input:
3
4
1 1 3 3
3
2 2 2
9
1 2 1 2 3 2 3 4 4

output:
4
2
6

Explanation:

Test Case 1:
The entire array is good.

--------------------------------------------------

Test Case 2:
A subsequence [2, 2] of length 2 is good.

Test case 3:
1 1 3 3 4 4 (len = 6)
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
		    
		    int lastSceen[] = new int[n+1];
		    Arrays.fill(lastSceen, -1);
		    int ans = 0;
		    int segmentStart = 0;
		    
		    for(int i=0; i<n; i++){
		        int x = arr[i];
		        
		        if(lastSceen[x] >= segmentStart){
		            ans += 2;
		            segmentStart = i+1;
		        }
		        else{
		            lastSceen[x] = i;
		        }
		    }
		    
		    System.out.println(ans);
		}

	}
}

/*
Complex Example & Dry Run

Array:
[1, 2, 3, 1, 4, 5, 2, 4, 5, 3]

Initial State:
ans = 0
segmentStart = 0
lastSeen array initialized to -1

--------------------------------------------------

i = 0, x = 1:
lastSeen[1] = -1 → update lastSeen[1] = 0

i = 1, x = 2:
lastSeen[2] = -1 → update lastSeen[2] = 1

i = 2, x = 3:
lastSeen[3] = -1 → update lastSeen[3] = 2

i = 3, x = 1:
lastSeen[1] = 0
0 ≥ segmentStart (0) → pair found

ans = 2
segmentStart = 4

--------------------------------------------------

i = 4, x = 4:
lastSeen[4] = -1 → update lastSeen[4] = 4

i = 5, x = 5:
lastSeen[5] = -1 → update lastSeen[5] = 5

i = 6, x = 2:
lastSeen[2] = 1
1 < segmentStart (4) → invalid → update lastSeen[2] = 6

i = 7, x = 4:
lastSeen[4] = 4
4 ≥ segmentStart (4) → pair found

ans = 4
segmentStart = 8

--------------------------------------------------

i = 8, x = 5:
lastSeen[5] = 5
5 < segmentStart (8) → invalid → update lastSeen[5] = 8

i = 9, x = 3:
lastSeen[3] = 2
2 < segmentStart (8) → invalid → update lastSeen[3] = 9

--------------------------------------------------

Final Answer:
4

Valid good subsequence:
[1, 1, 4, 4]
*/
