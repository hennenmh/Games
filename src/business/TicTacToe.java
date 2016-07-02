
package business;

/**
 *
 * @author Mark H
 */
public class TicTacToe implements Game{
    private int[] a = new int[9];
    private String player, finish, errmsg, endmsg;
    private boolean gameover;
    public static final String GAMENAME = "Tic-Tac-Toe";
    
    public TicTacToe() {
        player="X";
        gameover = false;
        endmsg = "Player turn: " + player;
        finish = "X";
        for (int i=0; i<9; i++) { a[i]=0; }
    }

    public String getPlayer() {
        return player;
    }

    public boolean TakeSquare(int s) {
        if (gameover) { return false; }
        if (a[s-1] !=0 ) { return false; }

        if (player.equalsIgnoreCase("X")) {
            a[s-1] = 1;
        }else{
            a[s-1] = -1;
        }
    //check for game-over (tie or winner)
        isGameOver();
        if (!gameover) {

            if (player.equalsIgnoreCase("X")) {
                player = "O";
            }else{
                player = "X";
            }
            endmsg = "Player turn: " + player;
        }
        return true;
    }

    public String getSquare(int s) {
        String square = "";
        switch (a[s-1]) {
            case -1:
                square = "O";
                break;
            case 1:
                square = "X";
                break;
            default:
                square = " ";
                break;
        }
        return square;
    }
    
    @Override
    public void setMove(String move){
        errmsg = "";
        int m = 0;
        
        if (!gameover) {
            try {
                m = Integer.parseInt(move);
            } catch (Exception e) {
                errmsg = e.getMessage();
            }

            if (TakeSquare(m)) {
                finish = getSquare(m);
            } //else {
//                finish = player;
//            }
        }
    }
    
    @Override
    public String getGameName() {
        return this.GAMENAME;
    }
    
    @Override
    public String getInstructions(){
        return "Two Players: 'X' and 'O' take turns clicking squares; first "
                + "player with 3 across, down, or diagonal wins.  Player X "
                + "starts.";
    }
    
    @Override
    public String getMoveResults(){
        return finish;
    }
    
    @Override
    public String getErrorMsg(){
        return errmsg;
    }
    
    @Override
    public String getEndMsg(){
        return endmsg;
    }
    
    
    
    @Override
    public boolean isGameOver(){
        boolean winner = false, draw = true;
        if (Math.abs(a[0]+a[1]+a[2]) == 3) { winner = true; }
        if (Math.abs(a[3]+a[4]+a[5]) == 3) { winner = true; }
        if (Math.abs(a[6]+a[7]+a[8]) == 3) { winner = true; }
   
        if (Math.abs(a[0]+a[3]+a[6]) == 3) { winner = true; }
        if (Math.abs(a[1]+a[4]+a[7]) == 3) { winner = true; }
        if (Math.abs(a[2]+a[5]+a[8]) == 3) { winner = true; }
                
        if (Math.abs(a[0]+a[4]+a[8]) == 3) { winner = true; }
        if (Math.abs(a[2]+a[4]+a[6]) == 3) { winner = true; }
        
                if (winner) {
                    draw = false;
                    endmsg = "Player " + player + " wins.";
                }else{
                    for (int i=0; i<=8; i++) {
                        if (a[i] == 0) {draw = false;}
                    }
                if (draw) { endmsg = "Game is a draw.";}
                }
        if (winner || draw) {
                gameover = true;
        }else{
            gameover = false;
        }
        return winner;
    }
    
}
