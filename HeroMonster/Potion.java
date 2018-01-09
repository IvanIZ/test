public class Potion{
    private int x_coor;
    private int y_coor;
    private boolean newPotion;
    public Potion(){
        x_coor = (int)(Math.random() * 15);
        y_coor = (int)(Math.random() * 15);
        newPotion = true;
    }
    public int getX(){
        return x_coor;
    }
    public int getY(){
        return y_coor;
    }
    public void found(){
        newPotion = false;
    }
    public boolean newP(){
        return newPotion;
    }
}