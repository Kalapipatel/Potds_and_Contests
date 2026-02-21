/*
**Problem Statement:** Given a binary string, you can choose an index `i` at most once. The bit at `i` remains the same, but all other bits in the string are flipped. The goal is to make all bits `0` and print the chosen indices, or return `-1` if it is impossible.

**Core Logic ($O(n)$ time):**
This is a parity (odd/even) problem. We group the indices based on whether they initially contain a `1` or a `0`.
1. If the count of `1`s is **even**, we can achieve all `0`s by selecting all the indices that currently have `1`s.
2. If the count of `0`s is **odd**, we can achieve all `0`s by selecting all the indices that currently have `0`s.
3. If both conditions fail, it is mathematically impossible to make all bits `0`, and we output `-1`.
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            
            List<Integer> ones = new ArrayList<>();
            List<Integer> zeros = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    ones.add(i + 1);
                } else {
                    zeros.add(i + 1);
                }
            }
            
            boolean onesValid = (ones.size() % 2 == 0);
            boolean zerosValid = (zeros.size() % 2 != 0);
            
            List<Integer> ans = null;
            
            if (onesValid && zerosValid) {
                ans = ones.size() < zeros.size() ? ones : zeros;
            } else if (onesValid) {
                ans = ones;
            } else if (zerosValid) {
                ans = zeros;
            }
            
            if (ans == null) {
                System.out.println("-1");
            } else {
                System.out.println(ans.size());
                if (!ans.isEmpty()) {
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < ans.size(); i++) {
                        out.append(ans.get(i)).append(i == ans.size() - 1 ? "" : " ");
                    }
                    System.out.println(out.toString());
                }
            }
        }
        sc.close();
    }
}
