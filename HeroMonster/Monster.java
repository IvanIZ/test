public class Monster{
    private int attack;
    private int health;
    private int speed;
    private int x_coord;
    private int y_coord;
    private boolean see;
    
    public Monster(){
        attack = (int)(Math.random() * 40) + 1;
        health = (int)(Math.random() * 100) + 1;
        speed = (int)(Math.random() * 4);  
        x_coord = (int)(Math.random() * 15);
        y_coord = (int)(Math.random() * 15);
        see = false;
    }
    public void seeMonster(){
        see = true;
    }
    public int getx_coord(){
        return x_coord;
    }
    public int gety_coord(){
        return y_coord;
    }
    public int getHealth(){
        return health;
    }
    public int getSpeed(){
        return speed;
    }
    public int getAttack(){
        return attack;
    }
    public void getHurt(int damage){
        health -= damage;
    }
}