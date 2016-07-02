
package business;

/**
 *
 * @author Mark H
 */
public class GuessHotCold extends GuessingGame{
    public static final String GAMENAME = "Guessing by Hot/Cold";
    public String result, errmsg;
    public boolean over;
    public int prevcat, prevdiff, category, diff, gcount;
    
    public GuessHotCold() {
        super();
        over = false;
    }
    
    @Override
    public void setMove(String move){
        int guess = 0;
        result = "";
        errmsg = "";

        try {
            guess = Integer.parseInt(move);
            diff = Math.abs(super.getRandNo() - guess);

            if (guess < 0 || guess > 100) {
                errmsg = "Guess must be between 1 and 100\n";
                over = false;
            } else if (guess == 0) {
                result = "Sorry. You did not guess my number " + 
                        super.getRandNo() + " in " + gcount + " guesses.";
                over = true;
            } else {
                if (diff >= 75) {
                    gcount++;
                    category = 1;
                    result = "Your guess of " + guess + " is very cold";
                } else if (diff >= 50) {
                    gcount++;
                    category = 2;
                    result = "Your guess of " + guess + " is cold";
                } else if (diff >= 25) {
                    gcount++;
                    category = 3;
                    result = "Your guess of " + guess + " is warm";
                } else if (diff >= 13) {
                    gcount++;
                    category = 4;
                    result = "Your guess of " + guess + " is very warm";
                } else if (diff >= 1) {
                    gcount++;
                    category = 5;
                    result = "Your guess of " + guess + " is HOT";
                } else {
                    gcount++;
                    result = "You guessed my number " + super.getRandNo() +
                            " (in " + gcount + " guesses!)";
                    over = true;
                    return;
                }
                if (category == prevcat) {
                    //need additional msg for direction
                    if (prevdiff == diff ) {
                        result += " (same degree).";
                    } else {
                        switch (category){
                            case 1:  //very cold
                            case 2: //cold and same as case 1 so moved under it
                                if (diff > prevdiff) {
                                    result += " and getting colder.";
                                } else {
                                    result += " but getting warmer.";
                                }
                                break; // end the switch  
                            case 3: // warm
                            case 4:
                                if (diff > prevdiff) {
                                    result += " but getting colder.";
                                } else {
                                    result += " and getting warmer.";
                                }
                                break;
                            case 5: //hot
                                if (diff > prevdiff){
                                    result += " but getting colder.";
                                }else {
                                    result += " and getting hotter";
                                }
                                break;
                        }
                    }

                }
            prevcat = category;
            prevdiff = diff;
            result += "\n";
            }
        } catch (Exception e) {
            errmsg = "Illegal guess.\n";
        }

         
    }
    
    @Override
    public String getErrorMsg() {
        return errmsg;
    }
    
    @Override
    public boolean isGameOver() {
        return over;
    }
    
    @Override
    public String getMoveResults(){
        return result;
    }
    
    @Override
    public String getGameName(){
        return GAMENAME;
    }
}
