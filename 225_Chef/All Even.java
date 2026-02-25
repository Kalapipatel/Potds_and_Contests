/*
There are N numbers written on a blackboard.
These N values are denoted A1, A2, …, AN from left to right.

You can modify the blackboard using the following three-step procedure, as long as N ≥ 2:

1. Add AN to AN−1.
2. Erase AN from the blackboard.
3. Reduce N by 1.

In simpler words, you add the last value to the second-last value, then remove the last value.

You like even numbers, so you would like to know:

Is it possible to repeatedly perform this procedure several times (possibly zero times),
so that eventually there are only even numbers written on the blackboard?

input:
4
3
2 1 1
3
1 2 3
4
1 3 1 2
5
2 2 1 4 1

output:
Yes
Yes
No
Yes

Explanation:

Test case 1:
Initially, A = [2, 1, 1].

Perform the operation once:
Add A3 to A2 → 1 + 1 = 2
Erase the last element.

Now, A = [2, 2].

All elements are even.

--------------------------------------------------

Test case 2:
Initially, A = [1, 2, 3].

Perform the operation once:
Add A3 to A2 → 2 + 3 = 5
Erase the last element.

Now, A = [1, 5].

Perform the operation again:
Add A2 to A1 → 1 + 5 = 6
Erase the last element.

Now, A = [6].

All elements are even.

--------------------------------------------------

Test case 3:
It can be shown that A will always contain at least one odd number,
no matter how many operations are performed.
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
		    ArrayList<Integer> list = new ArrayList<>();
		    
		    int even = 0;
		    int len = n;
		    for(int i=0; i<n; i++){
		        int num = sc.nextInt();
		        list.add(num);
		        if(num % 2 == 0) even++;
		    }
            
            if(even == n){
                System.out.println("Yes");
                continue;
            }
            
            while(list.size() > 1){
                if(even == len){
                    System.out.println("Yes");
                    break;
                }
                else{
                    int last = list.get(len-1);
                    int slast = list.get(len-2);
                    
                    if(last % 2 == 0) even--;
                    if(slast%2==0 && slast + last % 2 != 0) even--;
                    if(slast%2!=0 && slast + last % 2 == 0) even++;
                    
                    int sum = slast + last;
                    list.remove(len-1);
                    list.set(len-2, sum);
                    len--;
                }
            }
		    
		    if(list.get(0) % 2 == 0) System.out.println("Yes");
		    else System.out.println("No");
		    // while ends here
		}

	}
}
