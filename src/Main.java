import java.util.*;

// This file handles the dictionary and returns the updated hiddenWord to the player after each move

public class Main{

    public static void main (String[] args){

        // Initializing 100 random words
        String[] dictionary = {"truck",
                "connection",
                "breakable",
                "fence",
                "spooky",
                "skirt",
                "decorous",
                "soothe",
                "mountainous",
                "dogs",
                "respect",
                "clever",
                "cry",
                "window",
                "feeling",
                "death",
                "desert",
                "dead",
                "peace",
                "structure",
                "week",
                "festive",
                "yielding",
                "quirky",
                "email",
                "explode",
                "red",
                "fluttering",
                "detect",
                "receive",
                "disgusting",
                "even",
                "chew",
                "hover",
                "frantic",
                "sour",
                "wiggly",
                "rightful",
                "ancient",
                "rat",
                "nonstop",
                "harmonious",
                "vagabond",
                "nation",
                "exotic",
                "branch",
                "bubble",
                "yellow",
                "present",
                "equal",
                "panoramic",
                "vulgar",
                "medical",
                "superb",
                "lethal",
                "underwear",
                "smile",
                "cave",
                "woozy",
                "lake",
                "subdued",
                "chubby",
                "prepare",
                "jumpy",
                "burst",
                "merciful",
                "silk",
                "support",
                "skip",
                "man",
                "quiet",
                "hand",
                "increase",
                "camera",
                "curious",
                "sleet",
                "tickle",
                "income",
                "stare",
                "zany",
                "ocean",
                "ragged",
                "trot",
                "jam",
                "care",
                "harm",
                "plate",
                "hall",
                "loose",
                "popcorn",
                "fog",
                "slippery",
                "natural",
                "wing",
                "coach",
                "loss",
                "efficacious",
                "huge",
                "great",
                "scribble"
        };
        String hash = dictionary[100-1]+(int)(Math.random()*100);
        int games = 100;
        int score = 0;

        // This is the game loop
        // It grabs a random word from the array, replaces all the letters with underscores and feeds it to the brain class
        // It then checks to see if the guess was correct, alters the hidden word accordingly and feeds it back
        // It also keeps track of the 8 lives per turn

        for(int x=0;x<games;x++){
            Random r = new Random();
            String target = dictionary[r.nextInt(100)];
            Brain mybrain = new Brain(Arrays.copyOf(dictionary, dictionary.length), "_".repeat(target.length()));
            int lives=8;
            boolean running = true;
            while(running){
                char guess = mybrain.guessLetter();
                String original = mybrain.hiddenWord;
                char[] arrayform = original.toCharArray();
                for(int i=0;i<target.length();i++){
                    if(target.charAt(i)==guess){
                        arrayform[i]=guess;
                    }
                }
                StringBuilder newform = new StringBuilder();
                for(int i=0;i<target.length();i++){
                    newform.append(arrayform[i]);
                }
                mybrain.hiddenWord= newform.toString();
                if(newform.toString().equals(original)){
                    lives=lives-1;
                }
                if(lives==0){
                    running=false;
                }
                if(mybrain.hiddenWord.equals(target)){
                    running=false;
                    score=score+1;
                }
            }
        }
        System.out.println("You got "+score+" correct out of 100");

    }
}

