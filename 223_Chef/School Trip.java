/*
There are N students in a school, and X of them have said they want to go on the school trip.
The school buses can each carry K people, and every bus that is sent to the school trip must be completely full of students. No partially full buses are allowed. 
Hence, the number of students going on the trip must be divisible by K. There are sufficient number of school buses.
As their teacher, you can convince some students to change their mind (i.e. either they did not want to go, and now they do; or vice versa). You want to change the minimum number of student's 
minds so that you can have a successful school trip. 

What is this minimum number?

input:
4
5 4 2
5 5 3
5 1 3
5 2 3

output:
0
2
1
1

Test Case 1 : With 2 buses, everyone, who wanted to, is able to go on the school trip.
Test Case 2 : We need to convince 2 people to not go, and then the other 3 people end up going on the trip.
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
            int x = sc.nextInt();
            int k = sc.nextInt();
            
            if (x % k == 0) {
			    System.out.println(0);
			    continue;
			}
            
            int sub = x % k;
            int add = k-sub;
            
            int temp = Math.min(sub, add);
            if(temp == add){
                if(add + x <= n){
                    System.out.println(add);
                }
                else{
                    System.out.println(sub);
                }
            }
            else{
                System.out.println(sub);
            }
            
        }

	}
}
