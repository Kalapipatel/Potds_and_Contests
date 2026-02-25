/*
Bank Glitch

In Chefland, there are 2 currencies in use: currency 1 and currency 2.
Normally, one unit of currency 1 is worth exactly one unit of currency 2.

Chef currently has A units of currency 1 and B units of currency 2.

However, he has found a glitch in the banking system, which allows him to trade
X units of currency 1 for Y units of currency 2, where X < Y.
This makes the trade profitable.

Chef may perform this trade multiple times.
However, the trade must be done using exactly X units of currency 1 for
Y units of currency 2.
Partial trades are not allowed.

For example, if X = 3 and Y = 6, exchanging 2 units of currency 1 for
4 units of currency 2 is not allowed.

Determine the maximum amount of money Chef can end up with.
The total money is measured as the sum of currency 1 and currency 2.

--------------------------------------------------

Constraints:

1 ≤ T ≤ 100
1 ≤ A, B ≤ 100
1 ≤ X < Y ≤ 100

input:
3
5 2 4 10
5 2 6 10
4 4 1 2

output:
13
7
12

Explanation:

Test Case 1:
Chef can perform the trade once.

Trade:
6 units of currency 1 → 10 units of currency 2

Final holdings:
Currency 1 = 1
Currency 2 = 12

Total money:
1 + 12 = 13

--------------------------------------------------

Test Case 2:
Chef cannot perform any trades.
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
		    int a = sc.nextInt();
		    int b = sc.nextInt();
		    int x = sc.nextInt();
		    int y = sc.nextInt();
            
            int div = a / x;
            if(div == 0){
                System.out.println(a + b);
                // continue;
            }
            else{
                int num = (div * y) + b;
                int remain = a - (div*x);
                System.out.println(num + remain);
            }
            	
            		    
		    // while ends here
		}

	}
}
