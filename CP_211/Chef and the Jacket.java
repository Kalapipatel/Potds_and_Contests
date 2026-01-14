/*
Problem Description:Chef wants to stay comfortable while checking the temperature for $N$ days. He starts without a jacket.Without a jacket: He is comfortable if the temperature is greater than or equal to $A$.With a jacket: He is comfortable if the temperature is less than or equal to $B$.The Overlap ($A$ to $B$): If the temperature is between $A$ and $B$, he is comfortable in either state (he keeps the jacket on if he’s wearing it, or stays without it if he isn't).Chef only takes an "action" when he puts on the jacket. Removing it does not count as an action. Find the minimum number of times Chef must put on his jacket to stay comfortable for all $N$ days.Input:First line: Number of test cases ($T$).For each test case:Three numbers: $N$ (days), $A$ (cold limit), $B$ (hot limit).$N$ numbers representing the temperature for each day.Output:Minimum number of times Chef puts on the jacket.Example:PlaintextInput:
2
5 20 30
15 25 35 28 10
4 10 15
12 9 15 16

Output:
2
1
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
		    int a = sc.nextInt();
		    int b = sc.nextInt();
		    
		    int ans = 0;
		    boolean wear = false;
		    for(int i=0; i<n; i++){
		        int temp = sc.nextInt();
		        
		        if(wear == true && temp <= b) continue;
		        if(temp < a){
		            ans++;
		            wear = true;
		        }
		        else wear = false;
		    }
		    
		    System.out.println(ans);
		}

	}
}
