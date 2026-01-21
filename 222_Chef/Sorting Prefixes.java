/*
You are given an array A, which is a permutation of the integer [1, N].
your task is to sort the array using the following operations as many times as want.

choose an index i and then pay Ai coins to sort the prefix A[1, i] of the array. (note the cost is levied before the sorting operation is performaed
Here, A[1, i] represents the prefix [A1, A2, .. ,Ai]

what is the minimum cost to sort the array

input:
4
3
1 2 3
3
3 2 1
5
2 3 1 4 5
6
2 1 4 3 5 6

output:
0
1
1
3

Explanation:
Test Case 1 : The given array is already sorted.

Test Case 2 : We can choose 
i=3 and sort the prefix 
A[1,3] which is also the entire array. The cost is A3=1 (before sorting).
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read number of test cases
        if (sc.hasNextInt()) {
            int T = sc.nextInt();
            
            while (T-- > 0) {
                int N = sc.nextInt();
                int[] A = new int[N];
                
                // Read the array elements
                for (int i = 0; i < N; i++) {
                    A[i] = sc.nextInt();
                }
                
                long ans = 0;
                
                // Iterate from right to left (N-1 down to 0)
                // We check if the element matches its 1-based index (i + 1)
                for (int i = N - 1; i >= 0; i--) {
                    if (A[i] != i + 1) {
                        // We found the rightmost unsorted element.
                        // The cost is the value of this element.
                        ans = A[i];
                        break; 
                    }
                }
                
                System.out.println(ans);
            }
        }
    }
}
