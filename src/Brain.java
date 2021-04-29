import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This brain
class Brain{

    public String[] dictionary;
    public String hiddenWord="_____";

    //This will be used to cehck the letter has not been guessed already
    StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

    public Brain(String[] wordlist, String target){
        dictionary = wordlist;
        hiddenWord = target;
    }

    public char guessLetter(){

        int len = hiddenWord.length();
        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
        List<String> possibleWords = new ArrayList<String>();

        // This loop constructs a regex from the hiddenWord so it is suitable for pattern matching
        String matchString = new String("");
        char [] arr = hiddenWord.toCharArray();
        for(int i =0; i<arr.length; i++) {
            if(arr[i] == '_'){
                matchString += "[a-zA-Z]";
            } else {
                matchString += arr[i];
            }
        }

        // This iterates over each string in the dictionary and checks if they're the same length as the hidden word
        // It then increases a counter to keep track of how big the array should be to hold the possible words
        Pattern p = Pattern.compile(matchString);

        int arrayLength = 0;
        for(String possible : dictionary) {
            if(possible.length() == len) {

                Matcher match = p.matcher(possible);
                boolean correctMatch = match.find();
                if(correctMatch){
                    arrayLength++;
                }
            }
        }

        String[] possibleArray = new String[arrayLength];
        int pos = 0;

        // This loop checks to see if the possible words are viable using regex pattern matching. It then adds them to the array
        for(int i =0; i < dictionary.length; i++) {
            if(dictionary[i].length() == hiddenWord.length()){
                Matcher m = p.matcher(dictionary[i]);
                boolean matchCorrect = m.find();

                if(matchCorrect) {
                    possibleArray[pos] = dictionary[i];
                    pos++;
                }
            }
        }
        dictionary = possibleArray;

        // This iterates over the possible words ArrayList, convert each one to a char array
        // The characters are then iterated over and are stored with their frequency as a key value pair in a hashmap
        for (String possible : possibleArray) {
            for (char letter : possible.toCharArray()) {
                if (!frequency.containsKey(letter)) {
                    frequency.put(letter, 1);
                } else {
                    frequency.put(letter, frequency.get(letter) + 1);
                }
            }
        }

        //Sorting the letters in order of least frequent to most frequent
        Map<Character, Integer> sorted = sortValues(frequency);
        Set<Character> keySet = sorted.keySet();

        // Creating an ArrayList of the characters
        // by passing the keySet to the ArrayList
        ArrayList<Character> listOfKeys
                = new ArrayList<Character>(keySet);
        int index = listOfKeys.size() - 1;


        //Checks the alphabet string to see if the letter has been guessed before
        // If it has it checks the next most frequent letter
        while(true) {
            int i = 1;
            if(alphabet.indexOf(String.valueOf(listOfKeys.get(index))) == -1) {
                index -= i++;
            } else {
                break;
            }
        }

        char guessLetter = listOfKeys.get(index);

        // Removes the guessLetter from the alphabet string
        for(int i =0; i<alphabet.length(); i++) {
            if(alphabet.charAt(i) == guessLetter) alphabet.deleteCharAt(i);
        }

        return guessLetter;

    }

    // This is a custom comparator method which converts the hashmap to a LinkedList, sorts it in ascending order, converts it back to a hashmap and returns the sorted HashMap
    private HashMap<Character, Integer> sortValues(HashMap<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());
        
        //Custom Comparator
        list.sort((Comparator<? super Map.Entry<Character, Integer>>) (o1, o2) ->
                ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue()));

        //copying the sorted list in HashMap to preserve the iteration order
        HashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;

    }
}
