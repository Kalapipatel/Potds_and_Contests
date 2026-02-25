/*
Plus-Minus Split

You are given an array A of length N.

The array can be modified by performing the following operation:

Choose an index i (1 ≤ i ≤ N).

For each index j (1 ≤ j ≤ N):

- If j < i, add Ai to Aj.
- If j ≥ i, subtract Ai from Aj.

Note that every element of A must be modified.

Example:
If A = [2, 4, -1, 3] and i = 2:

A2 = 4

- Add 4 to A1 → 2 + 4 = 6
- Subtract 4 from A2 → 4 - 4 = 0
- Subtract 4 from A3 → -1 - 4 = -5
- Subtract 4 from A4 → 3 - 4 = -1

Result:
[6, 0, -5, -1]

Goal:

You want to make all elements of A equal by performing the operation
several times (possibly zero times).

Determine whether it is possible.

Constraints:

1 ≤ T ≤ 1000
1 ≤ N ≤ 100
-1000 ≤ Ai ≤ 1000

input:
3
2
1 -1
3
1 -3 2
4
5 5 5 5

output:
Yes
No
Yes

Explanation:

Test case 1:
Choose i = 2.

A2 = -1

- Add -1 to A1
- Subtract -1 from A2

Array becomes:
[0, 0]

All elements are equal.

--------------------------------------------------

Test case 2:
It can be shown that no sequence of operations can make
all elements of the array equal.

--------------------------------------------------

Test case 3:
All elements are already equal.
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
		    
		    boolean same = true;
		    for(int i=0; i<n; i++){
		        arr[i] = sc.nextInt();
		        if(i > 0 && arr[i] != arr[0]) same = false;
		    }

		    if(same){
		        System.out.println("Yes");
		        continue;
		    }
		    
		    int l = arr[0];
		    int r = arr[n-1];
		    
		    if(l != -r){
		        System.out.println("No");
		        continue;
		    }
		    
		    boolean possible = true;
		    boolean switchToR = false;
		    
		    for(int i=1; i<n; i++){
		        if(arr[i] == l ){
		            if(switchToR == true){
    		            possible = false;
	    	            break;
		            }
		        }
		        else if(arr[i] == r){
		            switchToR = true;
		        }
		        else{
		            possible = false;
		            break;
		        }
		    }
		 
		    if(possible) System.out.println("Yes");
		    else System.out.println("No");
		}

	}
}


/*
Logic Explanation

The problem asks whether we can make all elements of an array equal
using a specific operation.

--------------------------------------------------

Key Insight 1: The Target is Zero

When we perform the operation on index i, the value at that index
becomes 0 (since Ai is subtracted from itself).

Therefore:
- If we perform any operation, at least one element becomes 0.
- To make all elements equal after that, all elements must become 0.

Exception:
If the array is already equal (e.g., [5, 5, 5]), performing zero
operations is valid.

--------------------------------------------------

Key Insight 2: The "Split" Pattern

If the array is NOT already equal, we must transform it into:

[0, 0, ..., 0]

This is possible if and only if it can be done in exactly one operation.

For a single operation at index i to make the whole array zero:

- All elements to the left of i must be equal to -Ai
  (so adding Ai makes them 0)

- All elements to the right of i must be equal to Ai
  (so subtracting Ai makes them 0)

Thus, the array must follow this structure:

[-X, -X, ..., -X, X, X, ..., X]

Where:
- Left block = -X
- Right block = X
- The switch happens exactly once
- Left value L = -R

--------------------------------------------------

Summary of Conditions for "Yes"

YES if:

1) The array is already equal

OR

2) The array is perfectly split into two blocks:
   - A continuous block of L
   - Followed by a continuous block of R
   - With L = -R

--------------------------------------------------

Dry Run Examples

Example 1:
[1, -1]

Already equal? No
L = 1, R = -1
Check: L = -R → 1 = -(-1) → Yes

Split valid → Yes

--------------------------------------------------

Example 2:
[1, -3, 2]

Already equal? No
L = 1, R = 2
Check: L = -R → 1 ≠ -2 → No

Result → No

--------------------------------------------------

Example 3:
[1, 1, -2]

Already equal? No
L = 1, R = -2
Check: L = -R → 1 ≠ 2 → No

Result → No

--------------------------------------------------

Complex Example:
[-1, -1, 1, 1]

Already equal? No
L = -1, R = 1
Check: L = -R → -1 = -1 → Yes

Split pattern:
-1, -1 → Left block
1, 1   → Right block

Valid → Yes

(Operation at index 2 solves it)
*/
