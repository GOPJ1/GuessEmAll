import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main_game {
    private static ArrayList<String> remainingWords;
    private static ArrayList<String> guessedWords;
    private static int guessed_words;
    private static HashMap<String, String> Users;
    private static final String FILE_NAME = "data/words.txt";

    /**
     * Checks if the users input is valid, if so increment number of guess words, remove the word from the list, and update the players profile
     * @param word - The word that was guessed
     * @return Boolean if the guess is valid or not
     */

    private static boolean check_input(String word, User user){
        if( remainingWords.contains( word )){
            guessedWords.add( word );
            guessed_words++;
            remainingWords.remove( word );
            user.correct_guess( word );
            System.out.println( "Success!" );
            return true;
        }
        else if( guessedWords.contains( word ) ){
            System.out.println( "That word has been guessed already." );
            return false;
        }
        System.out.println( "Invalid word." );
        return false;
    }

    private static int words_left(){
        return remainingWords.size();
    }


    private static void read_input( String fileName ){
        try{
            URL path = main_game.class.getResource(FILE_NAME);
            File file = new File( path.getFile() );
            BufferedReader br = new BufferedReader( new FileReader( file ));
            String word;
            while( (word=br.readLine()) != null ){
                remainingWords.add( word.toLowerCase() );
            }
        }catch ( FileNotFoundException fe ){
            System.out.println( "Invalid file name." );
            System.exit( 1 );
        }catch ( IOException ioe ){
            System.out.println( "Invalid input file." );
            System.exit( 1 );
        }
        catch (NullPointerException npe){
            System.out.println( "Invalid file." );
        }
    }

    public static void main(String[] args) {
        remainingWords = new ArrayList<>();
        guessed_words = 0;
        Users = new HashMap<>();
        guessedWords = new ArrayList<>();
        read_input( FILE_NAME );
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Guess any word in the english language, every word can only be used once!\nType /help to see a list of commands" );
        System.out.print( "\nGuess a word: " );
        String input = scanner.nextLine();
        input = input.toLowerCase();
        User user = new User( "GOP" );
        while( true ) {
            if( input.equals( "/quit" ) ) {
                break;
            }
            else if( input.equals( "/help") ){
                System.out.println("Here's a list of commands:\n/help - display the help page\n/quit - Quit the game\n/words - See a list of words used");
            }
            else if( input.equals( "/words" ) ){
                System.out.println(guessedWords);
            }
            else {
                check_input(input, user);
            }
            System.out.print( "\nGuess a word: " );
            input = scanner.nextLine();
            input = input.toLowerCase();
        }

    }
}
