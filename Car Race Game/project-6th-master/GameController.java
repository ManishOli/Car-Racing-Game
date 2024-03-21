public class GameController {
    private final Game game;
    private int count = 1;
    private int c = 1;


    public GameController(Game game) {
        this.game = game;
    }

    public void runGameLoop() {
        while (true) {

                game.moveRoad(count);

            while (c <= 1) {
                game.repaint();
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    System.out.println(e);
                }
                c++;
            }
            c = 1;
            count++;

            if(game.nOpponent < 4 && count % 200 == 0){ //if there is less than 4 cars and count timer reaches 200
                game.imageLoc[game.nOpponent] = "images/car_left_"+((int)((Math.random()*100)%3)+1)+".png"; //assign images to the opponent cars
                game.lx[game.nOpponent] = 499; //set opponent cars start positions
                int p = (int)(Math.random()*100)%4;
                if(p == 0){     
                    p = 250;    //place the car in the fourth lane
                }
                else if(p == 1){
                    p = 300;    //place the car in the second lane
                }
                else if(p == 2){
                    p = 185;    //place the car in the third lane
                }
                else{
                    p = 130;    //place the car in the fourth  lane
                }
                game.ly[game.nOpponent] = p;
                game.speedOpponent[game.nOpponent] = (int)(Math.random()*100)%2 + 2; //sets the speed of the new opponent car
                game.nOpponent++; //add the car to the game
            }
        }
    }
}
