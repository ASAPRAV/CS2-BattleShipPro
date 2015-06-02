import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard extends ArrayList<PlayedGame>{

  private static String filename;

  public void setFileName(String fileName){

    if(fileName == "")
      this.filename = "leaderboard.txt";
    else
      this.filename = fileName;
  }
  public boolean add(PlayedGame g){
    int i = 0;
    for(PlayedGame game: this){
      if(game.getScore() <= g.getScore()){
        this.add(i,g);
        return true;
      }
      i++;
    }
      if(i!=0){
        add(i,g);
      }else{
        add(0,g);
      }
      return true;
    }
    public boolean add(String name, int score){
      PlayedGame g = new PlayedGame(name,score);
      return this.add(g);
    }
    public void save(){
      try{
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
      }catch(IOException io){
        io.printStackTrace();
      }
    }
    public void load(){
      Leaderboard temp = null;
      try{
        FileInputStream fileIn = new FileInputStream(this.filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        temp = (Leaderboard) in.readObject();
        in.close();
        fileIn.close();
      }catch(Exception e){
        e.printStackTrace();
        return;
      }
      this.clear();
      this.addAll(temp);
    }
    public String getTopThree(){
		int length = this.size();
		String result ="";
		if(length < 3){
			for(int i = 0; i < length; i++){
				result += this.get(i) + "\n";
			}
		}else{
			for(int i=0; i< 3; i++){
				result += this.get(i) + "\n";
			}
		}
		return result;

	}
  }
