/*
Problem Description:You are given an integer number $N$. Your goal is to make this number Odd. You can use two types of operations:Digit Subtraction: 
Pick any digit currently in the number and subtract it from the number.Digit Reordering: Shuffle the digits to make a new valid number (you cannot have leading zeros).Find the minimum number of operations required to make $N$ odd. If it is impossible, return -1.Input:Number of test cases ($T$).For each test case, a single integer $N$.Output:Minimum operations, or -1 if impossible.Example:PlaintextInput:
5
246
10
8
343
42

Output:
3
1
-1
0
2
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		while(t-- > 0){
		    int num = sc.nextInt();
		    
		    if(num % 2 != 0){
		        System.out.println(0);
		        continue;
		    }
		    else if(hasOddDigit(num)){
		        System.out.println(1);
		        continue;
		    }
		    else{
		        System.out.println(fun(num));
		    }
		    
		    
		}

	}
	
	public static int fun(int startNode) {
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        
        queue.add(new int[]{startNode, 0});
        visited.add(startNode);
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int val = current[0];
            int steps = current[1];
            
            if(steps > 10) continue;
            
            ArrayList<Integer> digits = getDigits(val);
            
            for(int d : digits){
                int next = val - d;
                
                if(next <= 0) continue; 
                if(visited.contains(next)) continue;
                
                if(hasOddDigit(next)){
                    return steps + 1 + 1;
                }
                
                visited.add(next);
                queue.add(new int[]{next, steps + 1});
            }
        }
        
        return -1; 
    }

    public static boolean hasOddDigit(int n) {
        while(n > 0){
            int digit = n % 10;
            if(digit % 2 != 0) return true;
            n /= 10;
        }
        return false;
    }
    

    public static ArrayList<Integer> getDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        boolean[] seen = new boolean[10];
        while(n > 0){
            int d = n % 10;
            if(!seen[d]){
                digits.add(d);
                seen[d] = true;
            }
            n /= 10;
        }
        return digits;
    }
}

/*
Check Parity (0 operations): If $N$ is already odd, answer is 0.Check Digits (1 operation): If $N$ is even but contains at least one odd digit, we can 
either:Subtract that odd digit ($Even - Odd = Odd$).Rearrange digits to move the odd digit to the end.Answer: 1.All Digits Even (Search): If $N$ is even 
and all its digits are even (e.g., 246, 8, 420), we cannot make it odd in one step. We must subtract an available digit to change the number.Since 
$Even - Even = Even$, the result will still be even.We need to keep subtracting digits until we generate a number that contains an odd digit (triggering Case 2).
Since we want the minimum operations, we use a Breadth-First Search (BFS). We explore subtracting every possible digit to see which path leads to an 
"odd digit" state fastest.

Explanation with Example ($N=246$)Check 1: Is $246$ odd? No.Check 2: Does $246$ have an odd digit? (Digits: 2, 4, 6). 
No.Start BFS:Queue: [{246, 0}]Pop 246: Available digits $\{2, 4, 6\}$.$246 - 6 = 240$. (All digits even). Add {240, 1} to 
queue.$246 - 4 = 242$. (All digits even). Add {242, 1} to queue.$246 - 2 = 244$. (All digits even). Add {244, 1} to queue.
Pop 240: Available digits $\{2, 4, 0\}$.$240 - 4 = 236$.Check $236$: 
It has an odd digit 3!Found Solution!Cost = steps (1) + current_sub (1) + final_fix (1) = 3.Why BFS?Since we can subtract any digit, 
simply picking the largest digit (Greedy) might not always work if a smaller digit sets up a better chain reaction. BFS explores all options 
level-by-level, ensuring the minimum operations are found.
*/
