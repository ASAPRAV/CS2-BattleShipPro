public class PlayedGame{
  private String name;
  private int score;

  public PlayedGame(String name, int score){
  this.name = name;
  this.score = score;
}
  public String toString(){
  return (this.name + " " + this.score);
}
public boolean equals(Object o){
  PlayedGame g = (PlayedGame) o;
  if(!this.name.equals(g.name))
    return false;
  if(this.score != g.score)
    return false;
  return true;
}
public String getName(){
  return this.name;
}
public int getScore(){
  return this.score;
}
}
