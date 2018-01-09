import java.util.*;
import java.io.*;
public class Tester{
    public static void main(String args[]){
        Object[][] map = new Object[15][15];
        fillMap(map);
        boolean play = true;
        
        System.out.println("Welcome to the Hero & Monsters");
        
        int monsterKilled = 0;
       
        //create monster
        Monster[] monsters = new Monster[8];
        int a = 0; 
        while( a < 8 ){
            monsters[a] = new Monster();
            if(map[monsters[a].getx_coord()][monsters[a].gety_coord()] == "[  ?  ]"){
                map[monsters[a].getx_coord()][monsters[a].gety_coord()] = monsters[a];
                map[monsters[a].getx_coord()][monsters[a].gety_coord()] = "[@ _ @]";
                a++;
            }
        }
        System.out.println("Monsters created!");
    
        
        //create farmers
        Farmer[] farmers = new Farmer[2];
        int f = 0;
        while( f < 2 ){
            farmers [f] = new Farmer();
            if(map[farmers[f].getX()][farmers[f].getY()] == "[  ?  ]"){
                //map[monsters[a].getx_coord()][monsters[a].gety_coord()] = monsters[a];
                map[farmers[f].getX()][farmers[f].getY()] = "farmer ";
                f++;
            }
        }
                      
        //create potion
        Potion[] potions = new Potion[2];
        int p = 0;
        while( p < 2 ){
            potions [p] = new Potion();
            if(map[potions[p].getX()][potions[p].getY()] == "[  ?  ]"){
                map[potions[p].getX()][potions[p].getY()] = "potion ";
                p++;
            }
        }
        
        //create trader
        TraderJoes[] trader = new TraderJoes[1];
        boolean t = true;
        while(t){
          trader[0] = new TraderJoes();
          if(map[trader[0].getX()][trader[0].getY()] == "[  ?  ]"){
              map[trader[0].getX()][trader[0].getY()] = "Trader ";
              t = false;
          }
        }   
        
        //hide everything
        fillMap(map);
        
        //create boss
        boolean b = true;
        Boss boss  = new Boss();
        while(b){
          boss = new Boss();
          if(map[boss.getX()][boss.getY()] == "[  ?  ]"){
              map[boss.getX()][boss.getY()] = "  Boss ";
              b = false;
          }
        }   
        System.out.println("Boss created");
        
        //create hero
        Hero hero = new Hero();
        System.out.println("Hero created! ");
        updateHero(map, hero);
        
        //start playing
        int monsterNum = 0;
        while(play){
            hero.checkHealth();
            System.out.println("\n\n");
            check(hero, monsters, farmers);
            printMap(map);
            
            boolean meetMonster = false;
            boolean meetPotion = false;
            
            Scanner kbReader = new Scanner(System.in);
            System.out.println("Please enter direction(a,d,s,w)");
            System.out.println("Enter i to view hero information; e to eat cake; o to drink");
            
            //move input
            String direction = kbReader.next();
            if(direction.equals("a") || direction.equals("d") ||direction.equals("s") ||direction.equals("w")){
               if(map[hero.getX()][hero.getY()] != "[X _ X]" && map[hero.getX()][hero.getY()] != "[@ _ @]"&& map[hero.getX()][hero.getY()] != "farmer " && map[hero.getX()][hero.getY()] != "Trader "&& map[hero.getX()][hero.getY()] != "  Boss "){
                   map[hero.getX()][hero.getY()] = "[     ]"; 
               }
            }
            hero.move_hori(direction);
            if(hero.getX() > 14){
                hero.setX(14);
            }else if(hero.getX() < 0){
                hero.setX(0);
            }else if(hero.getY() > 14){
                hero.setY(14);
            }else if(hero.getY() < 0){
                hero.setY(0);
            }
            
            if(direction.equals("i")){
                System.out.println(hero.toString());
            }
            
            //use potion
            if(direction.equals("h")){
                if(hero.countPotion() > 0){
                    hero.usePotion();
                    System.out.println("Hero uses a potion! Full health resotred");
                }else{
                    System.out.println("Hero does not have any potion. Go and find it! ");
                }
            }
            
            //drink
            if(direction.equals("o")){
                if(hero.drinkCount() > 0){
                    System.out.println("Hero drinks. 5 health restored");
                    hero.drink();
                }else{
                    System.out.println("Hero does not have any drink");
                }
            }
            
            //eat cake
            if(direction.equals("e")){
                if(hero.cakeCount() > 0){
                    System.out.println("Hero eats a cake. 10 health restored");
                    hero.eatCake();
                }else{
                    System.out.println("Hero does not have any cake");
                }
            }
            
            //update on map
            updateHero(map, hero);
            //check(hero, monsters, farmers);
            
            //meet monster
            monsterNum = 0; 
            boolean runFlight = true;
            for(int i = 0; i < monsters.length; i++){
                if(hero.getX() == monsters[i].getx_coord() && hero.getY() == monsters[i].gety_coord() && monsters[i].getHealth() > 0){
                    meetMonster = true;
                    monsterNum = i;
                }
            }
            if(meetMonster){
                System.out.println("\n\n");
                System.out.println("Hero encounters a monster! The monster engages! The monster information: ");  
                System.out.println("Monster Attack: " + monsters[monsterNum].getAttack() + "\nMonster Health: " + monsters[monsterNum].getHealth() + "\nMonster Speed: " +monsters[monsterNum].getSpeed() );
                map[hero.getX()][hero.getY()] = "H vs. M";
                printMap(map);
                while(runFlight){
                  System.out.println("\n\n");
                  System.out.println("Fight or Run? \nPress f to fight!    Press direction keys to run(a,d,s,w)!");
                  String command = kbReader.next();
                  if(command.equals("f")){
                      //fight monster
                      System.out.println("Ready to fight! ");
                      hero.getHurt(monsters[monsterNum].getAttack());
                      monsters[monsterNum].getHurt(hero.doDamage());
                      System.out.println("Hero's health goes down to " + hero.getHealth() + "   The monster's health goes down to " + monsters[monsterNum].getHealth());
                  }else if(command.equals("a") || command.equals("d") ||command.equals("s") ||command.equals("w")){
                      //chance
                      int runChance;
                        if(monsters[monsterNum].getSpeed() == 0){
                        runChance = 75;
                      }else if(monsters[monsterNum].getSpeed() == 1){
                        runChance = 50;
                      }else if(monsters[monsterNum].getSpeed() == 2){
                        runChance = 25;
                      }else{
                        runChance = 0;
                      }
                      int run = (int)(Math.random() * 101);
                       if(run <= runChance){
                        System.out.println("The hero has successfully escaped from the monseter. \n\n");
                        map[monsters[monsterNum].getx_coord()][monsters[monsterNum].gety_coord()] = "[@ _ @]";
                        runFlight = false;
                        hero.move_hori(command);
                        updateHero(map, hero);
                      }else{
                        System.out.println("The monster is too fast! The hero failed to escape! The monster hits the hero");
                        hero.getHurt(monsters[monsterNum].getAttack());
                        System.out.println("Hero's health goes down to " + hero.getHealth() + "\n\n");
                      }                  
                  }else{
                      System.out.println("Invalid input. Please reenter.");
                  }
                  if(monsters[monsterNum].getHealth() <=0){
                      hero.gainExp();
                      if(hero.getExp() >= hero.getLevelQ()){
                          hero.upgrade();
                          System.out.println("Congratulations! Hero level upgraded. Attack +3, received damege -1");
                      }
                      monsterKilled ++;
                      System.out.println("Brave hero! You have killed the monster! Now you can move on");
                      hero.getGold(15);
                      runFlight = false;
                      map[hero.getX()][hero.getY()] = "[X _ X]";
                      if(monsterKilled == 9){
                          System.out.println("Congradulations! The Hero has killed all the monsters! ");
                          play = false;
                      }
                  }
                  if(hero.getHealth() <= 0){
                      System.out.println("The hero is killed by the monster");
                      System.out.println("GAME OVER!! ");
                      runFlight= false;
                      play = false;
                  }
                }
                
            }
            
            //meet farmer 
            int farmerNum = 3;
            for(int i = 0; i < 2; i++){
                if(hero.getX() == farmers[i].getX() && hero.getY() == farmers[i].getY() && farmers[i].a()){
                    farmerNum = i;
                }
            }
            if(farmerNum == 0){
                if(monsterKilled >= 2){
                    System.out.println("You are a true hero! here is my bronze amor. Take it. You deserve it!  ");
                    hero.switchArmor();
                    System.out.println("Hero armor equipped. Reduced attack damage by 1/3");
                    farmers[farmerNum].meet();
                }else if(monsterKilled <2){
                    System.out.println("My sheep have been taken… My family is gone and I have nothing left except for bronze amor I have buried. Only a true hero will receive this.");
                    System.out.println("You shall take my bronze amor if you prove yourself as a true hero. Go kill 2 monsters!");
                    map[hero.getX()][hero.getY()] = "farmer "; 
                }           
            }
            if(farmerNum == 1){
                if(monsterKilled >=4){
                    System.out.println("You are a real hero! Here is my broad sword. Take it. You deserve it! ");
                    hero.switchWeapon();
                    System.out.println("Hero broad sword equipped. Hero attack increased!");
                    farmers[farmerNum].meet();                    
                }else if(monsterKilled < 4){
                    System.out.println("My family was killed by the monsters,broad sword and they took all my money. I have nothing but a powerful broad sword. I will give a to a real hero who deserves it");
                    System.out.println("You shall take my broad sword if you prove yourself as a real hero. Go kill 4 mmonsters");
                    map[hero.getX()][hero.getY()] = "farmer "; 
                }
            }
            
            //found potion
            for(int i = 0; i < 2; i++){
                if(hero.getX() == potions[i].getX() && hero.getY() == potions[i].getY() && potions[i].newP()){
                    potions[i].found();
                    System.out.println("Hero finds a health potion! Press h to use it");
                    hero.getPotion();
                }
            }
            
            //MEET Trader
            if(hero.getX() == trader[0].getX() && hero.getY() == trader[0].getY() ){
                map[trader[0].getX()][trader[0].getY()] = "Trader ";
                System.out.println("Old Trader \"Hey Hero! Do you want to buy something? \"");
                System.out.println("Old Trader: What do you want to buy? \n 1. Cake: 30 gold for each. Restore 20 health\n2. Drink: 20 golds for each. Restore 15 health.");
                printMap(map);
                boolean buying = true;
                while(buying){
                    String purchase = kbReader.next();
                    if(purchase.equals("1")){
                        map[trader[0].getX()][trader[0].getY()] = "Trader ";
                        if(hero.goldNum() >= 30){
                            System.out.println("Purchased 1 cake. Item is stored in hero's backpack. Press e to eat cake");
                            hero.buyCake();
                            buying = false;
                        }else{
                            System.out.println("You don't have enough gold! Go kill some monsters!");
                            buying = false;
                        }
                    }else if(purchase.equals("2")){
                        map[trader[0].getX()][trader[0].getY()] = "Trader ";
                        if(hero.goldNum() >= 20){
                            System.out.println("Purchased 1 drink. Item is stored in hero's backpack. Press o to drink");
                            hero.buyDrink();
                            buying = false;
                        }else{
                            System.out.println("You don't have enough gold! Go kill some monsters!");
                            buying = false;
                        }
                    }else if(purchase.equals("a") || purchase.equals("s") ||purchase.equals("w") ||purchase.equals("d")){
                        hero.move_hori(purchase);
                        updateHero(map, hero);
                        buying = false;
                    }else{
                        System.out.println("Invalid input. What do you want? ");
                    }
                }
                
            }
            
            if(hero.getX() == boss.getX() && hero.getY() == boss.getY()){
                map[hero.getX()][hero.getY()] = "H vs. B";
                printMap(map);
                System.out.println("Hero encounters the boss! ");
                //map[boss.getX()][boss.getY()] = "  Boss ";
                boolean fightBoss = true;
                while(fightBoss){
                     System.out.println("Press f to fight! Press direction keys to run.");
                     String command = kbReader.next();
                     if(command.equals("f")){
                         hero.getHurt(boss.getAttack());
                         boss.getHurt(hero.doDamage());
                         System.out.println("Hero's health goes down to " + hero.getHealth() + "   The Boss's health goes down to " + boss.getHealth());
                     }
                     if(command.equals("a") || command.equals("d") ||command.equals("s") ||command.equals("w")){
                        System.out.println("The hero has successfully escaped from the Boss. \n\n");
                        map[boss.getX()][boss.getY()] = "  Boss ";
                        fightBoss = false;
                        hero.move_hori(command);
                        updateHero(map, hero);
                     }
                     if(hero.getHealth() <= 0){
                          System.out.println("The hero is killed by the Boss");
                          System.out.println("GAME OVER!! ");
                          fightBoss = false;
                          play = false;
                     }
                     if(boss.getHealth() <=0){
                          hero.gainExp();
                          hero.gainExp();
                          if(hero.getExp() >= hero.getLevelQ()){
                              hero.upgrade();
                              System.out.println("Congratulations! Hero level upgraded. Attack +3, received damege -1");
                          }
                          monsterKilled ++;
                          System.out.println("Brave hero! You have killed the Boss! Now you can move on");
                          hero.getGold(40);
                          fightBoss = false;
                          map[hero.getX()][hero.getY()] = "[X _ X]";
                     }
                     if(monsterKilled == 9){
                          System.out.println("Congradulations! The Hero has killed all the monsters! ");
                          play = false;
                     }
                }
                
            }
        }
    }
    
    public static void fillMap(Object[][] map){
        for(int i = 0; i < map.length; ++i){
            for(int j = 0; j < map[0].length; ++j){
                map[i][j] = "[  ?  ]";
            }
        }
    }
    public static void printMap(Object[][] map){
        for(int i = 0; i < map.length; ++i){
            for(int j = 0; j < map[0].length; ++j){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void updateHero(Object[][] map, Hero hero){
        map[hero.getX()][hero.getY()] = " Hero  ";
    }
    public static void check(Hero hero, Monster monsters[],  Farmer farmers[]){
        for(int i = 0; i < monsters.length; i++){
                if(hero.getX() == monsters[i].getx_coord() &&  monsters[i].getHealth() > 0){
                    if(hero.getY() == monsters[i].gety_coord() - 1 || hero.getY() == monsters[i].gety_coord() + 1){
                        System.out.println( "Hero:\"I sensed the smell of monster! It must be very close! \" ");
                    }
                }else if(hero.getY() == monsters[i].gety_coord() && monsters[i].getHealth() > 0){
                    if(hero.getX() == monsters[i].getx_coord() - 1 || hero.getX() == monsters[i].getx_coord()+ 1){
                        System.out.println( "Hero:\"I sensed the smell of monster! It must be very close! \" ");
                    }
                }
        }
        for(int i = 0; i < 2; i++){
                if(hero.getX() == farmers[i].getX()  && farmers[i].a()){
                    if(hero.getY() == farmers[i].getY() - 1 || hero.getY() == farmers[i].getY() + 1 ){
                        System.out.println("Hero:\" Listen! I can hear the cry of a farmer. He must be very close to me.\"");
                    }    
                }else if(hero.getY() == farmers[i].getY()&& farmers[i].a()){
                    if(hero.getX() == farmers[i].getX() -1 || hero.getX() == farmers[i].getX() + 1){
                        System.out.println("Hero:\" Listen! I can hear the cry of a farmer. He must be very close to me.\"");                        
                    }
                }
                
        }

    }
    
    
    
    
    
}