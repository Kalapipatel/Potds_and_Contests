/*
Alice and Bob are having a tea party!

Of course, there must be snacks at a tea party.
Laid out on the table in front of Alice and Bob are biscuits and cakes.

Initially, there are X biscuits and Y cakes.
The two take turns eating snacks. Alice goes first.
On their turn, a person will do exactly one of the following three:

Eat one biscuit.
This reduces 
X by 1 and Y doesn't change 
Y.
Eat one biscuit and one cake.
This reduces both 
X
X and 
Y
Y by 
1
1.
Eat one cake, and then add one more biscuit to the table.
This reduces 
Y
Y by 
1
1, and increases 
X
X by 
1
1.
The number of cakes and biscuits on the table must remain non-negative at any point of time - so it's not allowed to eat a biscuit if there are no remaining biscuits, for example.

The person who eats the last snack will be satisfied.
Both Alice and Bob will choose their moves optimally in an attempt to be satisfied.

Given the initial values of 
X
X and 
Y
Y, can you decide who will be satisfied in the end?

input:
5
2 0
3 0
0 1
1 2
4 8

op:
Bob
Alice
Bob
Alice
Bob
*/


// solution

public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int T = sc.nextInt();

            while (T-- > 0) {
                int x = sc.nextInt();
                int y = sc.nextInt(); // We read Y, but we don't need it for logic
                
                // Logic: Parity of X determines the winner
                if (x % 2 != 0) {
                    System.out.println("Alice");
                } else {
                    System.out.println("Bob");
                }
            }
        }
    }

/*

Let's analyze how the number of Biscuits ($X$) changes with every move.Eat 1 Biscuit: $X$ becomes $X-1$. (Parity flips: Odd $\to$ Even, or Even $\to$ Odd).Eat 1 Biscuit 
& 1 Cake: $X$ becomes $X-1$. (Parity flips).Eat 1 Cake, Add 1 Biscuit: $X$ becomes $X+1$. (Parity flips).Key Observation:No matter which move a player chooses, the 
parity (odd/even nature) of $X$ always changes.If $X$ is Odd, after 1 move, $X$ becomes Even.If $X$ is Even, after 1 move, $X$ becomes Odd.Winning Condition:The game 
ends when the last snack is eaten. The final state is $X=0, Y=0$.To make the winning move (eating the last item), the player must move the state to $(0,0)$.Since $X=0$ 
is Even, the state before the winning move must have had an Odd $X$.(e.g., from $X=1$, you eat 1 to get $X=0$).The Winner:Alice always starts.If Alice starts with Odd 
$X$: She sees Odd. Bob sees Even. Alice sees Odd... The person who sees "Odd" is the one who can make the final move to make it "Even" (0). Alice Wins.If Alice starts 
with Even $X$: She sees Even. Bob sees Odd. Alice sees Even... Bob will get the "Odd" state required to make the final move. Bob Wins.Conclusion:If initial $X$ is Odd, 
Alice wins.If initial $X$ is Even, Bob wins.$Y$ (Cakes) does not matter at all for the winner!
*/
