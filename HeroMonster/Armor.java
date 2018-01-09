public class Armor{
    private String armorName;
    private int x_cood;
    private int y_cood;
    public Armor(String name){
        armorName = name;
    }
    public void changeArmor(String newArmor){
        armorName = newArmor;
    }
    public String getArmor(){
        return armorName;
    }
    public int getX(){
        return x_cood;
    }
    public int getY(){
        return y_cood;
    }
}