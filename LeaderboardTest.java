public class LeaderboardTest{

public static void main(String[] args){
  Leaderboard l = new Leaderboard();
  l.add("Player1", 500);
  l.add("player 2", 700);
  l.add("player 4", 1000);
  l.add("player 5", 1200);
  System.out.println(l);
  System.out.println(l.getTopThree());
}
}
