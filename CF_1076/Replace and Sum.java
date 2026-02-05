/*
Today, KQ has an exam at the Grail Academy. A strict teacher gave a task that KQ could not solve.

He was given two arrays a and b of length n.

KQ is allowed to perform the following operations on the arrays:

1. Choose an index i (1 ≤ i < n) and replace a[i] with a[i + 1].
2. Choose an index i (1 ≤ i ≤ n) and replace a[i] with b[i].

Now he has q queries. Each query is described by two numbers l and r.

His task is to find the maximum value of the sum:

a[l] + a[l + 1] + a[l + 2] + ... + a[r]

for each query, if he can perform any number of operations on any elements of the array.

*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int t = sc.nextInt();
            while(t-- > 0){
                int n = sc.nextInt();
                int q = sc.nextInt();
                
                // Use long to prevent overflow during processing
                long[] a = new long[n];
                long[] b = new long[n];
                
                for(int i=0; i<n; i++){
                    a[i] = sc.nextLong();
                }
                for(int i=0; i<n; i++){
                    b[i] = sc.nextLong();
                }
                
                // 1. Fix: Include original a[i] in the comparison
                // Base case for the last element
                a[n-1] = Math.max(a[n-1], b[n-1]);
                
                // Suffix maximum logic
                for(int i=n-2; i>=0; i--){
                    long fromRight = a[i+1];
                    long fromB = b[i];
                    long originalA = a[i];
                    
                    // We take the best of: keeping original, taking from B, or copying from right
                    a[i] = Math.max(originalA, Math.max(fromB, fromRight));
                }
                
                // 2. Fix: Use long for prefix sums
                long[] prefixSum = new long[n];
                long sum = 0;
                for(int i=0; i<n; i++){
                    sum += a[i];
                    prefixSum[i] = sum;
                }
                
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<q; i++){
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    
                    l--; 
                    r--;
                    
                    long ans;
                    if(l == 0) ans = prefixSum[r];
                    else ans = prefixSum[r] - prefixSum[l-1];
                    
                    sb.append(ans).append(" ");
                }
                
                // Handle trailing space for cleaner output (optional but good practice)
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }
                System.out.println(sb);
            }
        }
    }
}
