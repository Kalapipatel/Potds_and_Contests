/*
You are given an integer n.

A number is called digitorial if the sum of the factorials of its digits is equal to the number itself.

Determine whether any permutation of n (including the original order) forms a digitorial number.

Return true if such a permutation exists, otherwise return false.

Note:

The factorial of a non-negative integer x, denoted as x!, is the product of all positive integers less than or equal to x, and 0! = 1.
A permutation is a rearrangement of all the digits of a number that does not start with zero. Any arrangement starting with zero is invalid.
 

Example 1:
Input: n = 145
Output: true

Explanation:
The number 145 itself is digitorial since 1! + 4! + 5! = 1 + 24 + 120 = 145. Thus, the answer is true.

Example 2:
Input: n = 10
Output: false

Explanation:​​​​​​​
10 is not digitorial since 1! + 0! = 2 is not equal to 10, and the permutation "01" is invalid because it starts with zero.

Constraints:

1 <= n <= 109
*/

class Solution {
    public int fact(int d){
        if(d == 0) return 1;
        else if(d == 1) return 1;
        else if(d == 2) return 2;
        else if(d == 3) return 6;
        else if(d == 4) return 24;
        else if(d == 5) return 120;
        else if(d == 6) return 720;
        else if(d == 7) return 5040;
        else if(d == 8) return 40320;
        else return 362880;
    }
    public boolean isDigitorialPermutation(int n) {
        int f[] = new int[10];
        int sum = 0;

        while(n > 0){
            int dig = n % 10;
            f[dig]++;
            sum += fact(dig);
            n /= 10;
        }

        while(sum > 0){
            int dig = sum % 10;
            f[dig]--;
            sum/=10;
        }

        for(int i=0; i<10; i++){
            if(f[i] != 0) return false;
        }

        return true;
    }
}
