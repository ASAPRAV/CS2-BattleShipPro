import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Leaderboard{
  private File f;
  private FileWriter fw;
  private BufferedWriter bw;
  private Scanner in;

public Leaderboard(){
//Creates the Leaderboard text file
  try{
    f = new File("./leaderboard.txt");
    if(!f.exists())
    {
      fw = new FileWriter(f.getAbsoluteFile());
      bw = new BufferedWriter(fw);
      bw.write("Leaderboard:");
      bw.close();
    }
  }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  //parses throgh the file and returns an arraylist of each line
  public ArrayList<String> readLeaderboard(){
    ArrayList<String> scores = new ArrayList<String>();
    try{
      in = new Scanner(f);
      while(in.hasNext()){
        scores.add(in.nextLine());
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return scores;
  }

// writes a single score to the end of the leaderboard text file
public void writeScores(String Score){
    try{
      f = new File("./leaderboard.txt");
      if(!f.exists()){
        System.out.println("File doesnt exist, create first");
      }
      else{
        fw = new FileWriter(f.getAbsoluteFile(),true);
        bw = new BufferedWriter(fw);
        bw.write(Score);
        bw.close();

      }
    }catch(Exception e){
      e.printStackTrace();
    }
}
}
