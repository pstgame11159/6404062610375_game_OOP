/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.File_img;
import java.util.Random;

/**
 *
 * @author koyrot
 */
public class FireBall extends Entity{
   
    private Random random;
    private int direction;
    private boolean canDmg = true;
    int top_or_down=0;
    int left_or_right=0;
    public int Speed_fire=2;
    
    public FireBall(int sX,int sY) {
        super(0, 0, sX, sY);
        loadAnimations();
    }
    
    

    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.FIREBALL);

        animations = new BufferedImage[6][6];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
    }

    @Override
    public void update() {
        fireBallMove();
        updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[direction][aniIndex],(int)x,(int)y,scaleX,scaleY,null);

    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 6){
                aniIndex = 0;
            }
        }
    }
    
    private void fireBallMove(){
        if(direction == 0){
            y+=Speed_fire;
            if(y>1000){
                random = new Random();
                direction = random.nextInt(3);
 
                x = random.nextInt(550);
                top_or_down = random.nextInt(2);
                left_or_right = random.nextInt(2);
                if(direction==0)
                {
                    x = random.nextInt(1000);
                    y= 0;
                }
                else if(direction == 1)
                {   
                    x= 1100;
                    
                }
                else if(direction == 2 )
                {
                    x= 0;
                }
                
                if(top_or_down==0&&direction !=0)
                {
                    y = 350;
                }
                else if(top_or_down==1&&direction !=0)
                    
                {
                    y = 465;
                }
         
                canDmg = true;
            }
        }
       else if(direction == 1){
            x=x-Speed_fire;
            if(x<-70){
                direction= 0;
                x = random.nextInt(900);
                y= 0;
               canDmg = true;
            }
        }
        
       else if(direction == 2){
            x=x+Speed_fire;
            if(x>1100){
                direction= 0;
                x = random.nextInt(900);
                y= 0;
               canDmg = true;
            }
        }

        
        
    }
    
    public Rectangle getCoinArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCanDmg() {
        return canDmg;
    }

    public void setCanDmg(boolean canDmg) {
        this.canDmg = canDmg;
    }
}
