public class Weapon{
    private String weaponName;
    private boolean found;
    public Weapon (String name){
        weaponName = name;
    }
    public void changeWeapon(String newWeapon){
        weaponName = newWeapon;
    }
    public String getWeapon(){
        return weaponName;
    }
}