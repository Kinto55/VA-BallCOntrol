import java.util.Random;

public class Playground {
    private static final int NUM_TEAMS = 3;
    private static final int NUM_TURNS = 5;
    private static final int MAX_PLAY_TIME = 500;
    private static final int MAX_WAIT_TIME = 100;
    private static Ball ball;

    public static void main(String[] args) {

        // The program defines a Ball class to represent the single ball, and a Team
        // class to represent each group of children. Each Team object is run in a
        // separate thread.

        
        ball = new Ball();

        Thread[] teams = new Thread[NUM_TEAMS];
        for (int i = 0; i < NUM_TEAMS; i++) {
            teams[i] = new Thread(new Team(i));
            teams[i].start();
        }

        try {
            for (int i = 0; i < NUM_TEAMS; i++) {
                teams[i].join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Ball {
        public Ball() {
        }
    }

    public static class Team implements Runnable {
        private int teamID;

        public Team(int teamID) {
            this.teamID = teamID;
        }

        public void run() {
            Random rand = new Random();
            for (int turn = 1; turn <= NUM_TURNS; turn++) {
                System.out.printf("%s wants the ball. Turn #%d\n", getTeamName(), turn);
                try {
                    Thread.sleep(rand.nextInt(MAX_WAIT_TIME));
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (ball) {
                    System.out.printf("%s got the ball. Turn #%d\n", getTeamName(), turn);
                    // this try is the time that the team has the ball 
                    try {
                        Thread.sleep(rand.nextInt(MAX_PLAY_TIME));
                    }
                    // this catch is the exception that is thrown when the thread is interrupted while sleeping 
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // this system should be synchronized with the ball object 
                    System.out.printf("%s is done playing with the ball. Turn #%d\n", getTeamName(), turn);
                }
            }
        }

        // Returns the name of the team based on the teamID
        private String getTeamName() {
            switch (teamID) {
                case 0:
                    return "Blue team";
                case 1:
                    return "Red team";
                case 2:
                    return "White team";
                default:
                    return "";
            }
        }
    }
}