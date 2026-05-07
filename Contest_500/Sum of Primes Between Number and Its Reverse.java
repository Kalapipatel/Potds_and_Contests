/*
You are given an integer n.

Let r be the integer formed by reversing the digits of n.

Return the sum of all prime numbers between min(n, r) and max(n, r), inclusive.

 

Example 1:

Input: n = 13

Output: 132

Explanation:

The reverse of 13 is 31. Thus, the range is [13, 31].
The prime numbers in this range are 13, 17, 19, 23, 29, and 31.
The sum of these prime numbers is 13 + 17 + 19 + 23 + 29 + 31 = 132.
Example 2:

Input: n = 10

Output: 17

Explanation:

The reverse of 10 is 1. Thus, the range is [1, 10].
The prime numbers in this range are 2, 3, 5, and 7.
The sum of these prime numbers is 2 + 3 + 5 + 7 = 17.
Example 3:

Input: n = 8

Output: 0

Explanation:

The reverse of 8 is 8. Thus, the range is [8, 8].
There are no prime numbers in this range, so the sum is 0.
 

Constraints:

1 <= n <= 1000
*/

class Solution {
    public int reverse(int n){
        int rev = 0;

        while(n > 0){
            rev = (n%10) + 10*rev;
            n /= 10;
        }

        return rev;
    }
    public boolean isPrime(int n){
        if(n <= 1) return false;
        if(n <=3 ) return true;

        if(n % 2 == 0 || n % 3 == 0) return false;

        for(int i=5; i*i <= n; i+=6){
            if(n % i == 0 || n % (i+2) == 0) return false;
        }
        return true;
    }
    public int sumOfPrimesInRange(int n) {
        int temp = n;
        int rev = reverse(temp);

        int mini = Math.min(n, rev);
        int maxi = Math.max(n, rev);

        int sum = 0;
        for(int i=mini; i<=maxi; i++){
            if(isPrime(i)){
                sum += i;
            }
        }

        return sum;
    }
}
