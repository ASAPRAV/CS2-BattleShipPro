import java.io.BufferedWriter;
import
public class Leaderboard{
  private File f;
  private FileWriter fw;
  private BufferedWriter bw;

public Leaderboard{

  try{
    f = new File("./leaderboard.txt");
    if(!f.exists()){
      fw = new FileWrtier(f.getAbsoluteFile());
      bw = new BufferedWriter(fw);
      bw.write("Leaderboard:");
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}


}
