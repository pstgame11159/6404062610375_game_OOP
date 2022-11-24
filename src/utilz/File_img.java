package utilz;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class File_img {
    public static final String PLAYER = "JK_withbg.png";

     public static final String BG_Start = "BG_Start.jpg";
    public static final String BG2_1 = "BG2_1.jpg";
    public static final String BG_Game_Over = "BG_Game_Over.jpg";
    public static final String FIREBALL = "FireBalls.png";
    public static final String FIREBALL2 = "FireBalls2.png";
    public static final String POTION = "Potions.png";
    
    public static BufferedImage GetSprites(String filename){
        BufferedImage img = null;
        InputStream is = File_img.class.getClassLoader().getResourceAsStream("ress\\" + filename);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
    }