/*
There are B boys and G girls who would like to take accommodation in a hotel.
The hotel has infinitely many rooms, and each room with a positive number of occupants must satisfy the following conditions:

It must contain at least X boys.
It must contain at least Y girls.
It can contain at most N people in total.
Determine the minimum number of rooms required to accommodate all B+G people while satisfying these constraints.

If it is impossible to accommodate everyone while following the constraints, output -1

input:
3
10 10 10 10 20
10 10 10 10 10
10 10 2 1 5

output:
1
-1
4

*/

import java.util.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        // Use BufferedReader for fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;
        
        int t = Integer.parseInt(st.nextToken());
        
        while (t-- > 0) {
            // Read line for each test case
            // Handle cases where input might be on new lines or same line
            while (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (!st.hasMoreTokens()) break;

            long b = Long.parseLong(st.nextToken());
            long g = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());

            // 1. Physical Constraint Check:
            // Can a single room even hold the minimum required boys (X) and girls (Y)?
            if (x + y > n) {
                out.println(-1);
                continue;
            }

            // 2. Population Constraint (Upper Bound on Rooms):
            // We cannot have more rooms than our boys/girls allow.
            // We need X boys per room -> max rooms = B/X
            // We need Y girls per room -> max rooms = G/Y
            long maxRooms = Math.min(b / x, g / y);

            // 3. Capacity Constraint (Lower Bound on Rooms):
            // We need enough rooms to fit all B + G people.
            // Rooms needed = ceil((B + G) / N)
            long totalPeople = b + g;
            long roomsNeeded = (totalPeople + n - 1) / n; // Integer formula for ceil(a/b)

            // 4. Verification:
            // If the rooms we NEED (for capacity) is greater than the rooms we CAN MAKE (population), it's impossible.
            if (roomsNeeded > maxRooms) {
                out.println(-1);
            } else {
                out.println(roomsNeeded);
            }
        }
        out.flush();
    }
}

/*
The LogicTo solve this, we must balance two conflicting constraints: Capacity (how many people fit in a room) vs. Minimum Requirements 
(how many boys/girls are required per room).Immediate Failure Check:A single room requires at least $X$ boys and $Y$ girls. If $X + Y > N$ 
(the room's capacity), it is impossible to form even a single valid room. Return -1.Constraint 1: Capacity (Lower Bound on Rooms)We have $B + G$ 
people total. Each room holds at most $N$ people.To fit everyone, we need a minimum of:$$R_{needed} = \lceil \frac{B + G}{N} \rceil$$Constraint 2: 
Scarcity (Upper Bound on Rooms)Every time we open a new room, we "spend" at least $X$ boys and $Y$ girls. We cannot open more rooms than our population 
allows.Max rooms supported by boys: $\lfloor B / X \rfloor$Max rooms supported by girls: $\lfloor G / Y \rfloor$The limiting factor is the smaller of 
the two:$$R_{possible} = \min(\lfloor \frac{B}{X} \rfloor, \lfloor \frac{G}{Y} \rfloor)$$Final Decision:If $R_{needed} > R_{possible}$, we don't have 
enough people to satisfy the minimum requirements for the number of rooms needed to hold everyone. Return -1.Otherwise, the answer is $R_{needed}$.
*/
