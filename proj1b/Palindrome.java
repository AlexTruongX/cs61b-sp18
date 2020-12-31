public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> newDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            newDeque.addLast(letter);
        }
        return newDeque;
    }

    /* Return true if given word is palindrome, false otherwise. */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }
    private boolean isPalindrome(Deque charList) {
        if (charList.size() <= 1) {
            return true;
        } else if (charList.removeFirst() != charList.removeLast()) {
            return false;
        }
        return isPalindrome(charList);
    }

    /* Returns true if palindrome is off by one. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        // Method returns true if the word is a palindrome according to character comparison test
        // provided by the CharacterComparator passed in as argument cc
        if (word == null) {
            return false;
        } else if (cc == null) {
            isPalindrome(word);
        }
        return isPalindrome(wordToDeque(word), cc);
    }
    private boolean isPalindrome(Deque letters, CharacterComparator cc) {
        if (letters.size() <= 1) {
            return true;
        } else if (!cc.equalChars((char)letters.removeFirst(), (char)letters.removeLast())) {
            return false;
        }
        return isPalindrome(letters, cc);
    }
}
