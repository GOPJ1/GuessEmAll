import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<String> wordsGuessed;
    private int num_points;

    public User(String userName ){
        this.userName = userName;
        this.wordsGuessed = new ArrayList<>();
        num_points = 0;
    }

    public void correct_guess( String word ){
        wordsGuessed.add( word );
        num_points += word.length();
    }
}
