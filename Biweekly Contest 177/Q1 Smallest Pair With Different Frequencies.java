/*
You are given an integer array nums.

Consider all pairs of distinct values x and y from nums such that:

x < y
x and y have different frequencies in nums.
Among all such pairs:

Choose the pair with the smallest possible value of x.
If multiple pairs have the same x, choose the one with the smallest possible value of y.
Return an integer array [x, y]. If no valid pair exists, return [-1, -1].

The frequency of a value x is the number of times it occurs in the array.

 

Example 1:

Input: nums = [1,1,2,2,3,4]

Output: [1,3]

Explanation:

The smallest value is 1 with a frequency of 2, and the smallest value greater than 1 that has a different frequency from 1 is 3 with a frequency of 1. Thus, the answer is [1, 3].

Example 2:

Input: nums = [1,5]

Output: [-1,-1]

Explanation:

Both values have the same frequency, so no valid pair exists. Return [-1, -1].

Example 3:

Input: nums = [7]

Output: [-1,-1]

Explanation:

There is only one value in the array, so no valid pair exists. Return [-1, -1].

 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
*/

class Solution {
    public int[] minDistinctFreqPair(int[] nums) {
        int[] freq = new int[101];
        
        for (int num : nums) {
            freq[num]++;
        }
        
        for (int x = 1; x <= 100; x++) {
            if (freq[x] > 0) {
                for (int y = x + 1; y <= 100; y++) {
                    if (freq[y] > 0 && freq[x] != freq[y]) {
                        return new int[]{x, y};
                    }
                }
            }
        }
        
        return new int[]{-1, -1};
    }
}


/*
Dry Run
Input: nums = [12, 12, 12, 15, 15, 15, 20, 20, 25, 30, 30]

Frequencies:

freq[12] = 3
freq[15] = 3
freq[20] = 2
freq[25] = 1
freq[30] = 2

Execution Steps:

Outer Loop starts at x = 12: freq[12] is 3.

Inner Loop starts checking y > 12:

y = 15: freq[15] is 3. freq[12] == freq[15]. Skip.
y = 20: freq[20] is 2. freq[12] != freq[20]. Valid pair found.

Return [12, 20]: The function terminates.
*/
