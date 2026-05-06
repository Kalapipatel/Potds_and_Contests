/*
Final Element

You are given an array A of N non-negative integers.

Define the following process:

1. Create a new array B of length N - 1 such that:
   Bi = Ai ⊕ A(i + 1)

   where ⊕ denotes the Bitwise XOR operator.

2. Replace A with B.

Repeat this process N - 1 times until only one element remains.

Your task is to find the final remaining element.

--------------------------------------------------

Input Format:

- The first line contains an integer T, the number of test cases.
- For each test case:
  - The first line contains an integer N.
  - The second line contains N integers:
    A1, A2, ..., AN

--------------------------------------------------

Output Format:

For each test case, print the final remaining element.

--------------------------------------------------

Constraints:

1 ≤ T ≤ 10^4
2 ≤ N ≤ 2·10^5
0 ≤ Ai < 2^30

The sum of N over all test cases does not exceed 2·10^5.

--------------------------------------------------

Sample Input:

3
3
2 3 5
4
1 1 1 1
4
1 2 3 4

--------------------------------------------------

Sample Output:

7
0
4

--------------------------------------------------

Explanation:

Test Case 1:

Initial array:
[2, 3, 5]

After first operation:
[2 ⊕ 3, 3 ⊕ 5]
= [1, 6]

After second operation:
[1 ⊕ 6]
= [7]

Final answer:
7
*/

// my code
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
		    
		    List<Integer> list = new ArrayList<>();
		    for(int i=0; i<n; i++){
		        int temp = sc.nextInt();
		        list.add(temp);
		    }
		    
		    while(list.size() > 1){
		        List<Integer> next = new ArrayList<>();
		        
		        for(int j=0; j<list.size()-1; j++){
		            next.add(list.get(j) ^ list.get(j+1));
		        }
		        
		        list = next;
		    }
		    
		    System.out.println(list.get(0));
		}

	}
}


// optimal code
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (scanner.hasNextInt()) {
            int t = scanner.nextInt();
            
            while (t-- > 0) {
                int n = scanner.nextInt();
                int finalElement = 0;
                
                for (int i = 0; i < n; i++) {
                    int a = scanner.nextInt();
                    
                    // Lucas's Theorem check for odd binomial coefficients
                    if (((n - 1) & i) == i) {
                        finalElement ^= a;
                    }
                }
                
                System.out.println(finalElement);
            }
        }
        
        scanner.close();
    }
}

/*
Method Explanation

At first glance, this problem appears to require an O(N²) simulation, which would cause a Time Limit Exceeded (TLE) error for N ≤ 2·10^5.

We need an O(N) solution.

The operation:

Bi = Ai ⊕ A(i+1)

behaves exactly like the construction of Pascal’s Triangle.

If we track how many times each element Ai contributes to the final element, it matches the binomial coefficient:

C(N-1, i)

Since XOR cancels identical values appearing an even number of times, an element affects the final answer only if:

C(N-1, i) is odd

According to Lucas’s Theorem:

C(n, k) is odd iff (n & k) == k

For this problem:

n = N - 1
k = i

So we include Ai in the final XOR if:

((N - 1) & i) == i

Complex Example Dry Run

Test Case:

N = 5
N - 1 = 4 (Binary: 100)

A = [10, 20, 30, 40, 50]
Indices = 0, 1, 2, 3, 4

Execution:

i = 0 (Binary 000)
4 & 0
100 & 000 = 000
0 == 0 -> True

Include A0 = 10

finalElement = 0 ⊕ 10 = 10
i = 1 (Binary 001)
4 & 1
100 & 001 = 000
0 == 1 -> False

Exclude A1 = 20

i = 2 (Binary 010)
4 & 2
100 & 010 = 000
0 == 2 -> False

Exclude A2 = 30

i = 3 (Binary 011)
4 & 3
100 & 011 = 000
0 == 3 -> False

Exclude A3 = 40

i = 4 (Binary 100)
4 & 4
100 & 100 = 100
4 == 4 -> True

Include A4 = 50

finalElement = 10 ⊕ 50 = 56

Final Output:

56
*/
