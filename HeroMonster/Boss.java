public class Boss{
    private int x_cor;
    private int y_cor;
    private int health;
    private int attack;
    public Boss(){
        x_cor = (int)(Math.random() * 15);
        y_cor = (int)(Math.random() * 15);
        attack = 25;
        health = 300;
    }
    public int getHealth(){
        return health;
    }
    public int getAttack(){
        return attack;
    }
    public void getHurt(int damage){
        health -= damage;
    }
    public int getX(){
        return x_cor;
    }
    public int getY(){
        return y_cor;
    }
}