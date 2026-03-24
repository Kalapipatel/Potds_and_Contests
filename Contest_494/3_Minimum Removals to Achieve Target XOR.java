/*
You are given an integer array nums and an integer target.

You may remove any number of elements from nums (possibly zero).

Return the minimum number of removals required so that the bitwise XOR of the remaining elements equals target. If it is impossible to achieve target, return -1.

The bitwise XOR of an empty array is 0.

Example 1:

Input: nums = [1,2,3], target = 2

Output: 1

Explanation:

Removing nums[1] = 2 leaves [nums[0], nums[2]] = [1, 3].
The XOR of [1, 3] is 2, which equals target.
It is not possible to achieve XOR = 2 in less than one removal, therefore the answer is 1.
Example 2:

Input: nums = [2,4], target = 1

Output: -1

Explanation:

It is impossible to remove elements to achieve target. Thus, the answer is -1.

Example 3:

Input: nums = [7], target = 7

Output: 0

Explanation:

The XOR of all elements is nums[0] = 7, which equals target. Thus, no removal is needed.

Constraints:

1 <= nums.length <= 40
0 <= nums[i] <= 104
0 <= target <= 104
*/

import java.util.Arrays;

public class Solution {
    public int minRemovals(int[] nums, int target) {
        // Step 1: Find the maximum element to bound our DP array size
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Find the smallest power of 2 that is strictly greater than maxVal
        int limit = 1;
        while (limit <= maxVal) {
            limit *= 2;
        }
        
        // Step 2: Initialize DP array
        // dp[x] = minimum elements removed to achieve an XOR sum of x
        int[] dp = new int[limit];
        Arrays.fill(dp, Integer.MAX_VALUE / 2); // Use MAX_VALUE / 2 to prevent overflow during addition
        dp[0] = 0;
        
        // Step 3: Populate DP table
        for (int num : nums) {
            int[] nextDp = dp.clone();
            for (int i = 0; i < limit; i++) {
                if (dp[i] != Integer.MAX_VALUE / 2) {
                    int nextXor = i ^ num;
                    // Taking this number means we add 1 to our removal count
                    nextDp[nextXor] = Math.min(nextDp[nextXor], dp[i] + 1);
                }
            }
            dp = nextDp;
        }
        
        // Step 4: Calculate the required XOR sum of the removed elements
        int totalXor = 0;
        for (int num : nums) {
            totalXor ^= num;
        }
        
        int req = totalXor ^ target;
        
        // If the required XOR is completely out of bounds, it's impossible
        if (req >= limit || dp[req] >= Integer.MAX_VALUE / 2) {
            return -1;
        }
        
        return dp[req];
    }
}


/*
### The Logic

* **The XOR Property:** Let the XOR of all elements in the entire array be `totalXor`. If we remove a subset of elements, the XOR of the remaining elements will 
equal `target` if and only if the XOR of the removed elements is exactly `totalXor ^ target`.
The Goal:** The problem translates to finding the shortest subsequence (subset) of `nums` whose XOR sum equals our required value (`req = totalXor ^ target`).
Dynamic Programming:** We can find this shortest subset using DP.
    * We maintain an array `dp` where `dp[x]` stores the minimum number of elements we can pick to achieve an XOR sum of `x`.
    * We initialize `dp[0] = 0` (0 elements picked gives an XOR of 0) and all other values to infinity.
    * For each number in `nums`, we update our DP table: for every previously reachable XOR sum `i`, we can create a new XOR sum `i ^ num` by spending one more element (`dp[i] + 1`).
 **Optimization:** The maximum possible XOR sum of any subset is strictly less than the smallest power of 2 that is greater than the maximum element in `nums`. We can use this limit to bound our DP array size and save memory/time.

### Dry Run: `nums = [1, 2, 3], target = 2`

**Initialization:**
* `maxVal = 3`. Smallest power of 2 > 3 is 4. `limit = 4`.
* `dp = [0, inf, inf, inf]`

---

### Processing Elements:

| Element | Previous dp | i (Reachable XOR) | New XOR (i ^ num) | New Removal Count (dp[i] + 1) | Updated nextDp Array |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1** | `[0, inf, inf, inf]` | 0 | 0 ^ 1 = **1** | 0 + 1 = 1 | `[0, 1, inf, inf]` |
| **2** | `[0, 1, inf, inf]` | 0, 1 | 0 ^ 2 = **2**, 1 ^ 2 = **3** | 0+1=**1**, 1+1=**2** | `[0, 1, 1, 2]` |
| **3** | `[0, 1, 1, 2]` | 0, 1, 2, 3 | 0^3=**3**, 1^3=**2**, 2^3=**1**, 3^3=**0** | min(2, 0+1)=**1**, min(1, 1+1)=**1**, min(1, 1+1)=**1**, min(0, 2+1)=**0** | `[0, 1, 1, 1]` |

---

### Final Calculation:
* `totalXor = 1 ^ 2 ^ 3 = 0`
* `req = totalXor ^ target = 0 ^ 2 = 2`
* **Result:** Return `dp[2]`, which is **1**. 
*(We must remove exactly 1 element to achieve the target).*
*/
