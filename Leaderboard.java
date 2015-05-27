import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Leaderboard{
  private File f;
  private FileWriter fw;
  private BufferedWriter bw;

public Leaderboard(){

  try{
    f = new File("./leaderboard.txt");
    if(!f.exists())
    {
      fw = new FileWriter(f.getAbsoluteFile());
      bw = new BufferedWriter(fw);
      bw.write("Leaderboard:");
    }
  }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
