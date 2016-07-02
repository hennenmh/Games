
package business;

/**
 *
 * @author Mark H
 */
public interface Game {
    String getGameName();
    String getInstructions();
    String getMoveResults();
    String getErrorMsg();
    String getEndMsg();
    
    void setMove(String move);
    
    boolean isGameOver();
}
