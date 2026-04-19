/*
You are given a positive integer array sides of length 3.

Determine if there exists a triangle with positive area whose three side lengths are given by the elements of sides.

If such a triangle exists, return an array of three floating-point numbers representing its internal angles (in degrees), sorted in non-decreasing order. Otherwise, return an empty array.

Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input: sides = [3,4,5]

Output: [36.86990,53.13010,90.00000]

Explanation:

You can form a right-angled triangle with side lengths 3, 4, and 5. The internal angles of this triangle are approximately 36.869897646, 53.130102354, and 90 degrees respectively.
Example 2:

Input: sides = [2,4,2]
Output: []
Explanation:

You cannot form a triangle with positive area using side lengths 2, 4, and 2.

Constraints:

sides.length == 3
1 <= sides[i] <= 1000
*/

class Solution {
    public double[] internalAngles(int[] arr) {
        Arrays.sort(arr);
        double a = arr[0];
        double b = arr[1];
        double c = arr[2];

        if(a + b <= c) return new double[]{};
        double[] ans = new double[3];

        double A = Math.acos(((b*b) + (c*c) - (a*a)) / (2*b*c));
        double B = Math.acos(((a*a) + (c*c) - (b*b)) / (2*a*c));
        double C = Math.acos(((b*b) + (a*a) - (c*c)) / (2*b*a));

        ans[0] = Math.toDegrees(A);
        ans[1] = Math.toDegrees(B);
        ans[2] = Math.toDegrees(C);
        Arrays.sort(ans);
        
        return ans;
    }
}
