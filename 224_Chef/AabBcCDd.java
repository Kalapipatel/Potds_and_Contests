/*
During Advitiya, the Coding Club is designing a digital banner made up of English letters.
Each letter can appear in either uppercase or lowercase.
You are given a string S of length N, denoting the current state of the banner.
To maintain visual uniformity, the design team allows the following two types of transformations:

Operation 1: You may convert any uppercase letter to its corresponding lowercase letter.
Formally, choose an index (1≤i≤N) such that ​
  is an uppercase letter, and convert it to the corresponding lowercase letter (so A→a,B→b, and so on.)
This operation can be performed any number of times.
Operation 2: You may choose exactly one lowercase letter and convert all of its occurrences into another lowercase letter.
For example, if S="aAbaCad", converting all the occurrences of ’a’ into ’e’ will result in the string "eAbeCed".
This operation can be performed only once.
The score of the banner is defined to be the maximum number of occurrences of any single lowercase letter present in it.
For example, the score of "aAbaCad" is 3, because the character ’a’ appears three times.

Find the maximum possible score the banner can attain by performing the above operations.
Note that the second type of operation can only be performed once, while the first type can be performed any number of times.

input:
3
8
AabBcCDd
2
ab
3
XXY

output:
4
2
3

Test case 
1
1: One optimal sequence of operations is as follows:

Apply operation 
1
1 on index 
1
1, converting 
A
→
a
A→a. The string is now 
"aabBcCDd"
"aabBcCDd".
Apply operation 
1
1 on index 
3
3, converting 
B
→
b
B→b. The string is now 
"aabbcCDd"
"aabbcCDd".
Apply operation 
2
2 and choose to convert all occurrences of 
b
b to 
a
a. The string is now 
"aaaacCDd"
"aaaacCDd".
There are now 
4
4 occurrences of 
’a’
’a’. It can be proved that this is optimal.

Test case 
2
2: Apply operation 
2
2 to convert all occurrences of 
a
a into 
b
b. The string becomes 
"bb"
"bb", with a score of 
2
2.

Test case 
3
3: The string can be converted into 
"yyy"
"yyy" by applying operation 
1
1 on all three indices, and then converting all occurrences of 
x
x into 
y
y, giving a score of 
3
3.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
    {
        static class Pair implements Comparable<Pair>{
        char ch;
        int freq;
    
        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
        
        @Override
        public int compareTo(Pair o){
            return o.freq - this.freq;
        }
    }

	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		while(t-- > 0){
		    int n = sc.nextInt();
		    String s = sc.next();
		    
		    s = s.toLowerCase();
		    
		    int freq[] = new int[26];
		    for(int i=0; i<s.length(); i++){
		        char ch = s.charAt(i);
		        
		        freq[ch - 'a']++;
		    }
		    
		    PriorityQueue<Pair> pq = new PriorityQueue<>();
		    for(int i=0; i<26; i++){
		        pq.add(new Pair((char)('a'+i) , freq[i]));
		    }
		    
		    int num1 = pq.poll().freq;
		    int num2 = pq.peek().freq;
		    
		    System.out.println(num1 + num2);
		    // while ends 
		}

	}
}
