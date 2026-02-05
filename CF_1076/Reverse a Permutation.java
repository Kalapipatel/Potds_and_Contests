/*
A permutation of length n is an array consisting of n distinct integers from 1 to n in arbitrary order.

For example, [2, 3, 1, 5, 4] is a permutation, but [1, 2, 2] and [1, 3, 4] are not permutations.

You are given a permutation p of length n.

You can perform the following operation exactly once:

- Choose two integers l and r (1 ≤ l ≤ r ≤ n).
- Reverse the segment [l, r] in the permutation p.

Your task is to output the lexicographically maximum permutation that can be obtained by performing this operation.

A permutation a is lexicographically greater than a permutation b if, for the first position i where they differ, it holds that a[i] > b[i].

*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        
        // Check if there is input
        String tStr = sc.next();
        if (tStr == null) return;
        int T = Integer.parseInt(tStr);

        while (T-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // Step 1: Precompute the index of the maximum element in the suffix starting at i
            int[] suffixMaxIdx = new int[n];
            suffixMaxIdx[n - 1] = n - 1;

            for (int i = n - 2; i >= 0; i--) {
                // If the current element is greater than the max seen from the right, 
                // update the index. Otherwise, keep the previous max index.
                if (arr[i] > arr[suffixMaxIdx[i + 1]]) {
                    suffixMaxIdx[i] = i;
                } else {
                    suffixMaxIdx[i] = suffixMaxIdx[i + 1];
                }
            }

            // Step 2: Find the first position where the current element is NOT the suffix max
            for (int i = 0; i < n; i++) {
                int maxIdx = suffixMaxIdx[i];
                
                // If the current element is not the maximum possible for this position
                if (arr[i] != arr[maxIdx]) {
                    // Reverse the segment from current position 'i' to the max position 'maxIdx'
                    reverse(arr, i, maxIdx);
                    break; // We can only perform the operation once
                }
            }

            // Output the result
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
            System.out.println(sb);
        }
    }

    // Helper function to reverse array segment
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    // Fast I/O Class
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            String s = next();
            if (s == null) return 0;
            return Integer.parseInt(s);
        }
    }
}

/*
The Logic Recap

To make a permutation lexicographically maximum, you want the largest possible numbers to appear as early as possible (towards the left).

Scan from Left to Right:
Look at each position i from 1 to n.

Find the Mismatch:
Check whether the number at position i is the maximum value in the remaining suffix A[i…n].

The Trigger:
The moment you find a number that is NOT the maximum of the remaining elements, you must fix it.

The Operation:
Find the position j where that maximum value is located.
Reverse the segment from i to j.
This brings the maximum possible value to position i.

--------------------------------------------------

Complex Example 1: Skipping the "Good" Prefix

Input:
[9, 8, 2, 4, 3, 6, 5, 1, 7]

Step-by-Step Analysis:

Index 1:
Current value = 9  
Max of {9, 8, 2, 4, 3, 6, 5, 1, 7} = 9  
Status: Perfect. Move on.

Index 2:
Current value = 8  
Max of {8, 2, 4, 3, 6, 5, 1, 7} = 8  
Status: Perfect. Move on.

Index 3:
Current value = 2  
Max of {2, 4, 3, 6, 5, 1, 7} = 7  
Status: Mismatch found.

Action:
l = 3 (value 2)  
Find value 7 → located at index 9  
Reverse segment [3, 9]

Execution:
Original: 9, 8, [2, 4, 3, 6, 5, 1, 7]  
Reversed: [7, 1, 5, 6, 3, 4, 2]

Result:
9, 8, 7, 1, 5, 6, 3, 4, 2

Why this is optimal:
Any other operation would either keep 2 at index 3 or bring a smaller number than 7.
The sequence 9, 8, 7… is lexicographically greater.

--------------------------------------------------

Complex Example 2: The Max is Buried in the Middle

Input:
[5, 2, 8, 3, 1, 4]

Step-by-Step Analysis:

Index 1:
Current value = 5  
Max of {5, 2, 8, 3, 1, 4} = 8  
Mismatch found.

Action:
l = 1  
Value 8 is at index 3  
Reverse segment [1, 3]

Execution:
Original: [5, 2, 8], 3, 1, 4  
Reversed: [8, 2, 5]

Result:
8, 2, 5, 3, 1, 4

--------------------------------------------------

Complex Example 3: Already Optimal (The Trap)

Input:
[5, 4, 3, 2, 1]

Step-by-Step Analysis:
Index 1 → 5 (max of suffix) ✔  
Index 2 → 4 (max of suffix) ✔  
Index 3 → 3 ✔  
Index 4 → 2 ✔  
Index 5 → 1 ✔  

Action:
The permutation is already lexicographically maximum.
Since exactly one operation is required, reverse a segment of length 1.

Example:
l = 1, r = 1

Result:
5, 4, 3, 2, 1

--------------------------------------------------

Dry Run of the Algorithm

Example:
[9, 8, 2, 4, 3, 6, 5, 1, 7]

Initial State:
Array:   [9, 8, 2, 4, 3, 6, 5, 1, 7]
Indices:  0  1  2  3  4  5  6  7  8

--------------------------------------------------

Step 1: Precompute suffixMaxIdx

We scan from right to left.
At each index i, store the index of the maximum value in A[i…n-1].

i = 8 → max = 7  → suffixMaxIdx[8] = 8  
i = 7 → max = 7  → suffixMaxIdx[7] = 8  
i = 6 → max = 7  → suffixMaxIdx[6] = 8  
i = 5 → max = 7  → suffixMaxIdx[5] = 8  
i = 4 → max = 7  → suffixMaxIdx[4] = 8  
i = 3 → max = 7  → suffixMaxIdx[3] = 8  
i = 2 → max = 7  → suffixMaxIdx[2] = 8  
i = 1 → max = 8  → suffixMaxIdx[1] = 1  
i = 0 → max = 9  → suffixMaxIdx[0] = 0  

Resulting suffixMaxIdx array:
[0, 1, 8, 8, 8, 8, 8, 8, 8]

--------------------------------------------------

Step 2: Find the Reverse Range

Scan from left to right.

Index 0:
Current = 9  
Max available = 9  
OK

Index 1:
Current = 8  
Max available = 8  
OK

Index 2:
Current = 2  
Max available = 7  
Mismatch found

Set:
L = 2  
R = 8

--------------------------------------------------

Step 3: Reverse the Segment

Reverse subarray from index 2 to 8.

Original:
9, 8, [2, 4, 3, 6, 5, 1, 7]

After reverse:
9, 8, 7, 1, 5, 6, 3, 4, 2

--------------------------------------------------

Step 4: Final Output

9 8 7 1 5 6 3 4 2
*/
