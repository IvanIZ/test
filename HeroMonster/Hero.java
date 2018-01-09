public class Hero{
    private int health;
    private int x_coord;
    private int y_coord;
    private Weapon heroWeapon;
    private Armor heroArmor;
    private int numPotion;
    private int numDrink;
    private int numCake;
    private int gold;
    private int levelQ;
    private int exp;
    private int level;
    private int firstAid;
    public Hero(){
        health = 100;
        heroWeapon = new Weapon("dagger");
        heroArmor = new Armor("None");
        x_coord = 14;
        y_coord = 0;
        numPotion = 0;
        numDrink = 0;
        numCake = 0;
        gold = 0;
        firstAid = 0;
        
        level = 0;
        levelQ = 30;
        exp = 0;
    }
    public void gainExp(){
        exp += 20;
    }
    public int getExp(){
        return exp;
    }
    public int getLevelQ(){
        return levelQ;
    }
    public void upgrade(){
        level ++;
        levelQ += 10;
        exp = 0;
        health += 15;
        if(health > 100){
            health = 100;
        }
    }
    public void checkHealth(){
        if(health >= 100){
            health = 100;
        }
    }
    public void switchWeapon(){
        heroWeapon.changeWeapon("sword");
    }
    public void switchArmor(){
        heroArmor.changeArmor("armor");
    }
    public void getGold(int get){
        gold += get;
    }
    public int goldNum(){
        return gold;
    }
    public void buyDrink(){
        numDrink ++;
        gold -= 20;
    }
    public void drink(){
        health += 5;
        numDrink --;
    }
    public int drinkCount(){
        return numDrink;
    }
    public void buyCake(){
        numCake ++;
        gold -= 30;
    }
    public void eatCake(){
        health += 10;
        numCake --;
    }
    public int cakeCount(){
        return numCake;
    }
    
    public void move_hori(String move){
        if(move.equals("w")){
            x_coord -= 1;
        }else if(move.equals("s")){
            x_coord += 1;
        }else if(move.equals("d")){
            y_coord +=1;
        }else if(move.equals("a")){
            y_coord -=1;
        }
    }
    public int doDamage(){
        int damage = 0;
        if(heroWeapon.getWeapon().equals("dagger")){
            damage = (int)(Math.random() * 20 + 11) + (level * 2);
        }else{
            damage =  (int)(Math.random() * 30 + 21) + (level * 1);
        }      
        return damage; 
    }
    public int getHealth(){
        return health;
    }
    public int getX(){
        return x_coord;
    }
    public int getY(){
        return y_coord;
    }
    public void getHurt(int attackDamage){
        if(heroArmor.getArmor().equals("armor")){
            health -= ((attackDamage /3) * 2 - level);
        }else{
            health -= (attackDamage - level);
        }
    }
    public void usePotion(){
        health = 100;
        numPotion --;
    }
    public void getPotion(){
        numPotion ++;
    }
    public String toString(){
        return "Hero health:" + health + "\nHero Weapon: " + heroWeapon.getWeapon() + "\nHero Armor: " + heroArmor.getArmor() + "\nHero Potion: " + numPotion + "\nHero Gold: "+ gold + "\nHero Drink: "+ numDrink + "\nHero cake: " + numCake + "\nHero Level: " + level;
    }
    public void setX(int x){
        x_coord = x;
    }
    public void setY(int y){
        y_coord = y;
    }
    public int countPotion(){
        return numPotion;
    }
    
}