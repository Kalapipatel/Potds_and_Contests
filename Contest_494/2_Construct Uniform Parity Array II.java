/*
You are given an array nums1 of n distinct integers.

You want to construct another array nums2 of length n such that the elements in nums2 are either all odd or all even.

For each index i, you must choose exactly one of the following (in any order):

nums2[i] = nums1[i]​​​​​​​
nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
Return true if it is possible to construct such an array, otherwise return false.

Example 1:

Input: nums1 = [1,4,7]

Output: true

Explanation:​​​​​​​​​​​​​​

Set nums2[0] = nums1[0] = 1.
Set nums2[1] = nums1[1] - nums1[0] = 4 - 1 = 3.
Set nums2[2] = nums1[2] = 7.
nums2 = [1, 3, 7], and all elements are odd. Thus, the answer is true.
Example 2:

Input: nums1 = [2,3]

Output: false

Explanation:

It is not possible to construct nums2 such that all elements have the same parity. Thus, the answer is false.

Example 3:

Input: nums1 = [4,6]

Output: true

Explanation:

Set nums2[0] = nums1[0] = 4.
Set nums2[1] = nums1[1] = 6.
nums2 = [4, 6], and all elements are even. Thus, the answer is true.
 

Constraints:

1 <= n == nums1.length <= 105
1 <= nums1[i] <= 109
nums1 consists of distinct integers.
*/

class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int min = Integer.MAX_VALUE;

        for(int x: nums1){
            min = Math.min(min, x);
        }

        if(min % 2 != 0) return true;

        for(int x : nums1){
            if(x % 2 != 0){
                return false;
            }
        }

        return true;
    }
}

/*
### The Logic: Parity Rules
The core of this problem lies in these four mathematical rules regarding subtraction and parity:

* Even - Even = Even
* Odd - Odd = Even
* Even - Odd = Odd
* Odd - Even = Odd

We have two potential goals: make all elements **Even** or make all elements **Odd**.

---

#### Scenario A: Making all elements Even
1. If an element is already even, we can keep it.
2. If an element is odd, we must subtract another odd number from it to make it even (Odd - Odd = Even).

**The Constraint:** To turn the smallest odd number in the array into an even number, we would need an even smaller odd number to subtract from it. If the smallest odd number has no odd numbers smaller than it, it can never become even.

#### Scenario B: Making all elements Odd
1. If an element is already odd, we can keep it.
2. If an element is even, we must subtract an odd number from it to make it odd (Even - Odd = Odd).

**The Constraint:** To turn an even number into an odd one, we need at least one odd number in the array that is smaller than that even number.

---

### The "Gatekeeper" Strategy
The result depends entirely on the minimum value in the array:

* **If the minimum value is Odd:** We can make every even number odd by subtracting this minimum value from them. Thus, we can make the whole array odd. **Result: True.**
* **If the minimum value is Even:** We cannot make this minimum value odd (no smaller odd number exists). 

Therefore, the only way to succeed is to make the entire array **Even**. As established in Scenario A, this is only possible if there are no odd numbers in the array at all. If even one odd number exists, we can't make it even because the smallest element is even.


## Dry Run: `nums1 = [4, 5, 8]`

| Step | Operation | Result / Observation |
| :--- | :--- | :--- |
| 1 | Find Min | `minVal = 4` |
| 2 | Check Parity of Min | `4 % 2 == 0` (Even). We must try to make everything Even. |
| 3 | Check element 4 | Already even. OK. |
| 4 | Check element 5 | Odd. To make it even, we need an odd number < 5. |
| 5 | Search for odd < 5 | None exist (only 4 is smaller). |
| 6 | Conclusion | Cannot make 5 even; cannot make 4 odd. **Return False.** |

---

## Complexity Analysis

* **Time Complexity:** O(N), where N is the length of the array. We iterate through the array a maximum of twice.
* **Space Complexity:** O(1), as we only use a few integer variables regardless of input size.
*/
