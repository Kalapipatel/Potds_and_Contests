/*
You are given a string s consisting of lowercase English characters.

Rearrange only the vowels in the string so that they appear in non-increasing order of their frequency.

If multiple vowels have the same frequency, order them by the position of their first occurrence in s.

Return the modified string.

Vowels are 'a', 'e', 'i', 'o', and 'u'.

The frequency of a letter is the number of times it occurs in the string.

 

Example 1:

Input: s = "leetcode"

Output: "leetcedo"

Explanation:​​​​​​​

Vowels in the string are ['e', 'e', 'o', 'e'] with frequencies: e = 3, o = 1.
Sorting in non-increasing order of frequency and placing them back into the vowel positions results in "leetcedo".
Example 2:

Input: s = "aeiaaioooa"

Output: "aaaaoooiie"

Explanation:​​​​​​​

Vowels in the string are ['a', 'e', 'i', 'a', 'a', 'i', 'o', 'o', 'o', 'a'] with frequencies: a = 4, o = 3, i = 2, e = 1.
Sorting them in non-increasing order of frequency and placing them back into the vowel positions results in "aaaaoooiie".
Example 3:

Input: s = "baeiou"

Output: "baeiou"

Explanation:

Each vowel appears exactly once, so all have the same frequency.
Thus, they retain their relative order based on first occurrence, and the string remains unchanged.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters
*/

class Solution {

    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c =='i' || c== 'o' || c== 'u';
    }
    public String sortVowels(String s) {
        int[] freq = new int[26];
        int[] first = new int[26];
        Arrays.fill(first, -1);

        // Step 1: count frequency + first occurrence       
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if(isVowel(c)){
                int idx = c-'a';
                freq[idx]++;
                if(first[idx] == -1){
                    first[idx] = i;
                }
            }
        }

        // Step 2: collect vowels
        List<Character> list = new ArrayList<>();
        for(char c : "aeiou".toCharArray()){
            if(freq[c-'a'] != 0){
                list.add(c);
            }
        }

        // Step 3: sort vowels
        list.sort((a,b) -> {
            if(freq[a - 'a'] != freq[b - 'a']){
                return freq[b-'a'] - freq[a - 'a']; // higher freq first
            }
            else{
                return first[a-'a'] - first[b-'a']; // earlier index first
            }
        });

        // Step 4: build sorted vowels list
        List<Character> sorted = new ArrayList<>();
        for(char c : list){
            int count = freq[c - 'a'];
            while(count > 0){
                sorted.add(c);
                count--;
            }
        }

        // Step 5: rebuild string
        StringBuilder sb = new StringBuilder(s);
        int j = 0;

        for(int i = 0 ; i <  s.length() ; i++){
            if(isVowel(s.charAt(i))){
                sb.setCharAt(i,sorted.get(j++));
            }
        }

        return sb.toString();

        
    }
}
