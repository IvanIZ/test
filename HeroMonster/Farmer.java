public class Farmer{
    private int x_coord;
    private int y_coord;
    private boolean strange;
    public Farmer(){
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
        strange = true;
    }
    public int getX(){
        return x_coord;
    }
    public int getY(){
        return y_coord;
    }
    public void meet(){
        strange = false;
    }
    public boolean a(){
        return strange;
    }
}