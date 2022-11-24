package main;

import entities.FireBall;
import entities.FireBall1;
import entities.Player;
import entities.Potion;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


public class Game {

        int count=0;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final int FPS_SET = 120;
        private final int UPS_SET = 200;
        private Player player;
        private Potion potion;
        private ArrayList<FireBall> fire = new ArrayList<FireBall>();
        private ArrayList<FireBall1> fire1 = new ArrayList<FireBall1>();
        public int score = 0;
        public int level = 1;
       public int show_game =0;
       public int High_score = 0;
        
        
	public Game() {
            initClasses();
            gamePanel = new GamePanel(this);
            gameWindow = new GameWindow(gamePanel);
            gamePanel.requestFocus();
            
            startGameLoop();
	}
        
        
        private void initClasses() {
              player = new Player(265,200,60,60);
              fire.add(new FireBall(80,80));
              fire1.add(new FireBall1(80,80));
              potion =new Potion(40,40);//40 40 size
        }

	private void startGameLoop() {
            
                
		player_thread .start();
                fire_ball_Thread.start();
                fire_ball_Thread1.start();
	}
        
     
        
        public void render(Graphics g){
            player.render(g);
            fire.get(0).render(g);
            fire1.get(0).render(g);
            potion.render(g);
            g.setFont(new Font("Agency FB",Font.BOLD,20));
            g.drawString("Score : " + score, 890, 50);
            g.setFont(new Font("Agency FB",Font.BOLD,20));
            g.drawString("High_Score : " + High_score, 890, 30);
        }
        Thread player_thread  = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                      if(show_game==1)
                            {
                            player.update();
                            potion.update();

                            check_score();        
                            checkPlayerGetDmg();
                            checkPlayerGetpotion();
                            check_Hp();
                             gamePanel.repaint();
                            Thread.sleep(3);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            });

                Thread fire_ball_Thread = new Thread(new Runnable() {
                public void run() {

		while (true) {
                      try {
                      if(show_game==1)
                            {
                            fire.get(0).update();
                            gamePanel.repaint();
                            Thread.sleep(3);
                            }
                        } catch (Exception e) {
                        }
		}

	}
        });
          Thread fire_ball_Thread1 = new Thread(new Runnable() {
                public void run() {

		while (true) {
              
                      try {
                      if(show_game==1)
                            {
                            fire1.get(0).update();
                             gamePanel.repaint();
                            Thread.sleep(3);
                            }
                        } catch (Exception e) {
                        }
		}

	}
        });      
                
                
        public Player getplayer(){
            return player;
        }
        
        public void windowFocusLost(){
            player.resetDirBooleans();
        }
        
        public void check_score(){
             count++;
            if(count>2*60)
            {
                score++;
                if(High_score<score)
                {
                    High_score = score;
                }
                count=0;
            }
            if(score>level*5)
            {
              
                 fire.get(0).Speed_fire =  fire.get(0).Speed_fire +1;
                  level = level+1;
                
            }
            
            
        }
        public void checkPlayerGetDmg(){
            if(player.getPlayerArea().intersects(fire.get(0).getCoinArea()) && fire.get(0).isCanDmg() == true){
                player.gotDMG(9);
                fire.get(0).setCanDmg(false);
            }
        }
        public void checkPlayerGetpotion(){
            if( player.getPlayerArea().intersects(potion.getPotionArea())   ){
                player.hp = player.hp+11;
                if(player.hp>100)
                 {
                    player.hp=100;
                 }
                potion.delay=false;
            }
        }
          public void check_Hp(){
               if(player.hp<=0)
               {
                 show_game =2;
                 player.hp=100;
                 score=0;
                 fire.get(0).Speed_fire = 2;
                 level =1;

               }
        }
          

        
 
}