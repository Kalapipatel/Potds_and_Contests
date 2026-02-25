/*
Erase and Maximize

You have N dice.
Each die has 6 faces with values {1, 2, 3, 4, 5, 6}.

The orientation of the dice does not matter.
You are given an integer S (N ≤ S ≤ 6·N).
You must choose integers x1, x2, …, xN such that:

1 ≤ xi ≤ 6  for all i
x1 + x2 + … + xN = S

That is, you choose one face from each die so that the total sum equals S.
The constraints guarantee this is always possible.
After choosing, replace the selected face value on each die with 0.
Then again choose one face from each die.

Your score is the sum of the chosen face values.
Determine the maximum possible score after performing the operations optimally.

Constraints:

1 ≤ T ≤ 1000
2 ≤ N ≤ 100
N ≤ S ≤ 6·N

input:
4
2 8
2 11
3 18
4 4

output:
12
11
15
24

Explanation:

Test case 1:
N = 2, S = 8

Choose 4 on both dice (4 + 4 = 8) and overwrite them with 0.

Each die becomes:
{0, 1, 2, 3, 5, 6}

Now choose the maximum value from each die:
6 + 6 = 12

--------------------------------------------------

Test case 2:
N = 2, S = 11

Choose:
- 5 on the first die
- 6 on the second die

First die becomes:
{0, 1, 2, 3, 4, 6}

Second die becomes:
{0, 1, 2, 3, 4, 5}

Now choose the maximum values:
6 + 5 = 11

--------------------------------------------------

Test case 3:
N = 3, S = 18

The only possible choice is selecting 6 on all three dice.

Each die becomes:
{0, 1, 2, 3, 4, 5}

Now choose the maximum values:
5 + 5 + 5 = 15
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
		    int s = sc.nextInt();
		
		    int arr[] = new int[n];
		    int idx = 0;
		    
		    while(n > 0){
		        int div = s/n;
		        arr[idx] = div;
		        idx++;
		        s = s - div;
		        n = n - 1;
		    }
		    
		    int ans = 0;
		    for(int i=0; i<arr.length; i++){
		        if(arr[i] != 6) ans += 6;
		        else ans += 5;
		    }
		
		    System.out.println(ans);
		}

	}
}
