/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.GetSprirteAmount;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.*;
import utilz.File_img;

public class Player extends Entity{
    
    private int playerAction = IDLE;
    private boolean left,right,up,down;
    private boolean moving = false;
    private final float playerSpeed = 2;
    public int hp = 100 ;

    public Player(int x,int y,int sX,int sY) {
        super(x, y, sX,sY);
        loadAnimations();
        
    }
    
    @Override
    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation(); 
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y,scaleX,scaleY,null);
      //  g.drawRect((int)x,(int)y,40,40);
        g.fillRect((int)x,(int)y,hp/2,5);
        g.setFont(new Font("Agency FB",Font.BOLD,20));
        g.drawString("HP : " + hp, 700, 30);
    
    }
        @Override
    public void updateAnimationTick() {
            aniTick++;
            if(aniTick>=aniSpeed){
                aniTick = 0;
                aniIndex++;
                if(aniIndex>=GetSprirteAmount(playerAction)){
                    aniIndex = 0;
                }
            }
        }
        
    
    @Override
    public void loadAnimations() {
            BufferedImage img = File_img.GetSprites(File_img.PLAYER);
            
            animations = new BufferedImage[4][4];
            for (int j = 0; j < animations.length; j++)
                    for (int i = 0; i < animations[j].length; i++)
                            animations[j][i] = img.getSubimage((i*32)+50, (j*32)+10, 32, 32);
    }
    
    private void setAnimation() {
        if(!moving){
            playerAction = IDLE;
        }

        }
        
        private void updatePos() {
            moving = false;
            
            if(left && !right){
                x-=playerSpeed;
                playerAction = RUNNING_LEFT;
                moving = true;
            }else if(right &&!left){
                x+=playerSpeed;
                playerAction = RUNNING_RIGHT;
                moving = true;
            }
            if(up && !down){
                y-=10;
                moving = true;
            }else if(down &&!up){
                y+=10;
                moving = true;
            }
            
            if(x>940){
                x=940;
            }
            else if(x<0)
             {
              x=0;
             }
   
            if(y<400){
                y=400;
            }
            else if(y>450)
            {
                y = 450;
            }
            
                    
        }


    
    public Rectangle getPlayerArea(){
        return new Rectangle((int)x,(int)y,scaleX,scaleY);
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;      
    }
    
    public void gotDMG(int dmg){
        hp-=dmg;
    }

}
