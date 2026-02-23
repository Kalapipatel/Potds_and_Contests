/*
You are given two binary strings s and t​​​​​​​, each of length n.

You may rearrange the characters of t in any order, but s must remain unchanged.

Return a binary string of length n representing the maximum integer value obtainable by taking the bitwise XOR of s and rearranged t.

 

Example 1:

Input: s = "101", t = "011"

Output: "110"

Explanation:

One optimal rearrangement of t is "011".
The bitwise XOR of s and rearranged t is "101" XOR "011" = "110", which is the maximum possible.
Example 2:

Input: s = "0110", t = "1110"

Output: "1101"

Explanation:

One optimal rearrangement of t is "1011".
The bitwise XOR of s and rearranged t is "0110" XOR "1011" = "1101", which is the maximum possible.
Example 3:

Input: s = "0101", t = "1001"

Output: "1111"

Explanation:

One optimal rearrangement of t is "1010".
The bitwise XOR of s and rearranged t is "0101" XOR "1010" = "1111", which is the maximum possible.
 

Constraints:

1 <= n == s.length == t.length <= 2 * 105
s[i] and t[i] are either '0' or '1'.
*/

class Solution {
    public String maximumXor(String s, String t) {
        int n = s.length();
        int s1 = 0;
        int t1 = 0;
        int s0 = 0;
        int t0 = 0;

        for(int i=0; i<n; i++){
            char schar = s.charAt(i);
            char tchar = t.charAt(i);

            if(schar == '1') s1++;
            if(schar == '0') s0++;
            if(tchar == '0') t0++;
            if(tchar == '1') t1++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            char sch = s.charAt(i);

            if(sch == '1'){
                if(t0 > 0){
                    t0--;
                    sb.append('1');
                }
                else{
                    t1--;
                    sb.append('0');
                }
            }
            else{
                if(t1 > 0){
                    t1--;
                    sb.append('1');
                }
                else{
                    t0--;
                    sb.append('0');
                }
            }
        }

        return sb.toString();
    }
}
