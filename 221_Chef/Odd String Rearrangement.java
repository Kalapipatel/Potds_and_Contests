/*
Problem Description:You are given a string of letters. A string is considered an "Odd String" if every pair of identical letters is separated by an odd distance. (Distance means the difference between their index positions).Basically, you cannot have two equal letters at an even distance from each other. You are allowed to rearrange the letters of the given string in any order you want. Determine if it is possible to rearrange the string to make it an "Odd String".Input:Number of test cases ($T$).For each test case:Length of the string ($N$).The string itself ($S$).Output:YES if it is possible, NO if it is not.Example:PlaintextInput:
8
1
a
2
bb
5
xxxxx
7
aabccbd
8
aabccbdc
8
abcdabcd
8
codechef
5
abcde

Output:
YES
YES
NO
YES
NO
YES
YES
YES
*/


import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		while(T-- > 0){
		    int n = sc.nextInt();
		    String s = sc.next();
		    
		    String ans = "YES";
		    int freq[] = new int[26];
		    for(int i=0; i<n; i++){
		        char ch = s.charAt(i);
		        freq[ch - 'a']++;
		        
		        if(freq[ch - 'a'] > 2){
		            ans = "NO";
		            break;
		        }
		        
		    }
		    
		    System.out.println(ans);
		    
		}

	}
}
