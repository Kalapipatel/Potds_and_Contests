/*
**Problem Statement:** Given a string of length `n`, you can cyclically rotate it any number of times. A "block" is a contiguous substring of identical 
characters. The goal is to find the maximum possible number of blocks in the string after rotating it optimally.

**Core Logic ($O(n)$ time):**
Instead of physically rotating the string and counting blocks ($O(n^2)$), we treat the string as a continuous circle. 
1. We count the number of "transitions" (where a character differs from the next one circularly).
2. The best place to cut the circle to form a straight line is between two identical characters. This splits them to opposite ends, adding exactly 1 extra block.
3. The maximum number of blocks is simply `transitions + 1` (capped at `n`), unless all characters are identical, in which case the answer is `1`.

Dry Run
Input: s = "abbc", n = 4

Initial setup: transitions = 0

Loop through the string circularly:

i = 0: Compare s[0] ('a') with s[1] ('b'). They are different. transitions = 1.

i = 1: Compare s[1] ('b') with s[2] ('b'). They are the same. transitions = 1.

i = 2: Compare s[2] ('b') with s[3] ('c'). They are different. transitions = 2.

i = 3: Compare s[3] ('c') with s[0] ('a'). They are different. transitions = 3.

Calculate Result: * transitions is 3 (not 0).

Result = Math.min(4, 3 + 1) = 4.

Output: 4.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            
            int transitions = 0;
            // Count adjacent differences, treating the string as circular
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != s.charAt((i + 1) % n)) {
                    transitions++;
                }
            }
            
            if (transitions == 0) {
                System.out.println(1); // All characters are the same
            } else {
                System.out.println(Math.min(n, transitions + 1));
            }
        }
        sc.close();
    }
}
