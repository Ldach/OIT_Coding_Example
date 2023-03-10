import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {

    //Contains the string representation of ascii art for the hangman
    String[] asciiList = {" +----+\n |    |\n      |\n      |\n      |\n ======"," +----+\n |    |\n 0    |\n      |\n      |\n ======", " +----+\n |    |\n 0    |\n |    |\n      |\n ======", " +----+\n |    |\n 0    |\n/|    |\n      |\n ======"," +----+\n |    |\n 0    |\n/|\\   |\n      |\n ======"," +----+\n |    |\n 0    |\n/|\\   |\n/     |\n ======"," +----+\n |    |\n 0    |\n/|\\   |\n/ \\   |\n ======"};

    int asciiHelper; // Keeps track of which ascii picture to print

    String[] wordList = {"grapefruit", "tally", "joint", "bounce", "tremor", "done", "wyrm", "lighten", "lucky", "fireplace"}; // List of words, can be added to without changing anything else
    int lastInt = -1; //Keeps track of the last word used, so it is not repeated for the next play through
    int chosenInt; //Stores the index of the randomly selected word

    String guess; //Stores the user's guess input
    char guessedChar; //Stores the first char of the user's guess
    StringBuilder allChars =new StringBuilder(); //Keeps track of
    int count; //Tracks the number of times a guessed letter appears
    int correctGuesses; //Tracks the number of correct guesses
    int wrongGuesses; //Tracks the number of incorrect guesses

    String theWord; //Holds the word randomly chosen from the word list
    StringBuilder hiddenWord; //Holds the string of correct guesses and dashes
    boolean won; //Keeps track of whether the user has guessed the word
    boolean playing = true; //Flags for whether the game loop should continue

    Scanner input=new Scanner(System.in);

    //Introduces the user to the game and rules
    System.out.println("Welcome! Ready to play hangman?");
    System.out.println("(If your guess is more than one character, only the first will be guessed)");

    while (playing) //The game loop
    {
      //Resets number of guesses
      correctGuesses = 0;
      wrongGuesses = 0;

      do //Randomly picks an index number limited to the size of the word list
      {
        chosenInt = (int)(Math.random() * (wordList.length));
      } while (chosenInt == lastInt);
      lastInt = chosenInt;

      theWord = wordList[chosenInt]; //Sets the word

      //Initializes the hidden word to the correct number of spaces
      hiddenWord=new StringBuilder();
      for (int i = 0; i <theWord.length(); i++)
      {
        hiddenWord.append("-");
      }

      //Initializes variables to starting conditions
      won = false;
      asciiHelper = 0;
      allChars.delete(0,allChars.length());

      System.out.println("Your word is " + theWord.length() + " letters long"); //Prints length of the word to be guessed

      do
      {
        //Prints relevant info for every guess
        System.out.println("Guessed Letters: " + allChars);
        System.out.println("Correct Guesses: " + correctGuesses);
        System.out.println("Incorrect Guesses: " + wrongGuesses);
        System.out.println(asciiList[asciiHelper]);
        System.out.println(hiddenWord);

        System.out.println("Enter your guess:");

        do //Reads input from user and checks to make sure it is valid
        {
          guess = input.nextLine().toLowerCase();
          if(guess.isEmpty())
          {
            guess = "!";
          }

          guessedChar = guess.charAt(0); //Stores the first value of the input string

          if (!Character.isLetter(guessedChar)) //If the input is invalid, asks for a new input
          {
            System.out.println("Invalid guess, try again");
          }

          for (int i = 0; i < allChars.length(); i++) //If the character was already guessed, asks for a new guess
          {
            if (allChars.charAt(i) == guessedChar)
            {
              System.out.println("Already guessed " + guessedChar + " try again");
              guessedChar = '!';
            }
          }

        }while (!Character.isLetter(guessedChar));

        allChars.append(guessedChar); //Adds the new guess to the list of guessed letters

        count = 0;

        for (int i = 0; i < theWord.length(); i++) //Checks to see if the guessed letter was in the word and changes the hiddenWord variable accordingly
        {
          if (theWord.charAt(i) == guessedChar)
          {
            count++;
            hiddenWord=new StringBuilder(hiddenWord.substring(0, i) + guessedChar + hiddenWord.substring(i + 1));
          }
        }

        if (count == 0) //If the guess failed
        {
          System.out.println("Sorry, there were no " + guessedChar);
          wrongGuesses++;

          if (asciiHelper != 6)
          {
            asciiHelper++;
          }
        }

        else //If the guess succeeded
        {
          System.out.println("Yes, there was " + count + " " + guessedChar);
          correctGuesses++;
        }


        if (theWord.equals(hiddenWord.toString())) //Checks to see if they have guessed every letter
        {
          won = true;
        }

      }while (!won);


      System.out.println("Congrats! You correctly guessed the word: " + theWord);

      if (asciiHelper == 6) //Prints if the ascii art finished printing
      {
        System.out.println("Sadly the poor guy died before you guessed it!");
      }

      System.out.println("It took you " + (correctGuesses+wrongGuesses) + " guesses to guess " + theWord);


      System.out.println("Want to keep playing? Y or N"); //Repeats if they keep playing or closes if they do not
      do
      {
        guess = input.nextLine().toLowerCase();

      }while (!((guess.charAt(0)== 'n') || (guess.charAt(0)== 'y')));
      if (guess.toLowerCase().charAt(0) == 'n')
      {
        System.out.println("Thanks for playing!");
        playing = false;
      }
      guess = "";
    }

  }

}