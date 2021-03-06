package java膨胀腐蚀;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 图像的腐蚀操作
 * 
 * 算法设计思路：
 * 		
 * 	（1）输入一个二值化图像
 * 	（2）对二值化图像进行扫描，对白色区域边界进行腐蚀算法思路为：
 * 			
 * 			当一个白色区域周围存在一个或者以上的黑色或其他颜色时对其周围，与一个给定的黑色图像进行 &操作，将其变成黑色
 * 			从而实现腐蚀功能
 *  （3）创建缓冲区
 *  （4）将相应图像输出
 */

public class imerode {

	BufferedImage grayImage;
	BufferedImage imerodeImage;
	public imerode(String string, Graphics g) throws IOException {
		// TODO Auto-generated constructor stub
		grayImage = ImageIO.read(new File(string));
		imerodeImage = new BufferedImage(grayImage.getWidth(),
										 grayImage.getHeight(),
										 grayImage.getType());
		
//		//使用同一内存
//		imerodeImage = grayImage;
		
		for (int i = 0; i < grayImage.getWidth(); i++) {
			for (int j = 0; j < grayImage.getHeight(); j++) {
				imerodeImage.setRGB(i, j, grayImage.getRGB(i, j));
			}
		}
		int[][] origin = getCircle(18);
		
//		BufferedImage testImage = new BufferedImage(origin.length,
//													origin[0].length,
//													grayImage.getType());
//		for (int j = 0; j < origin.length; j++) {
//			for (int j2 = 0; j2 < origin[0].length; j2++) {
//				testImage.setRGB(j, j2, origin[j][j2]);
//			}
//		}
//		g.drawImage(testImage, 0, 0, 300, 300, null);
		imerodeImage(origin,5);
		
		//画图
		g.drawImage(grayImage, 0, 0, 380, 400,null);
	    g.drawImage(imerodeImage,400,0, 380,400,null);
		
	}
	private int[][] getCircle(int i) {
		// TODO Auto-generated method stub
		int Circle[][];
		Circle = new int[i][i];
		
		for (int j = 0; j < Circle.length; j++) {
			for (int j2 = 0; j2 < Circle[0].length; j2++) {
				if (Math.abs((i/2-j)*(i/2-j)+(i/2-j2)*(i/2-j2)) <= (i/2)*(i/2)) {
					Circle[j][j2] = 0;
				}
				else Circle[j][j2] = 1;
			}
		}
		return Circle;
	}
	
	private void imerodeImage(int[][] origin, int k) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < imerodeImage.getWidth(); i++) {
			for (int j = 0; j < imerodeImage.getHeight(); j++) {
				int boundary = 0xffaa0000;
				if (i <= k || j <= k 
					|| i >= imerodeImage.getWidth()-k
					|| j >= imerodeImage.getHeight()-k) 
					imerodeImage.setRGB(i,j, 0xff000000);
				if (grayImage.getRGB(i, j) >= 0xff000001
					&& (   grayImage.getRGB(i-1, j) <= boundary
						|| grayImage.getRGB(i, j-1) <= boundary
						|| grayImage.getRGB(i+1, j) <= boundary
						|| grayImage.getRGB(i, j+1) <= boundary)) {
					
					for (int l = 0; l < origin.length; l++) {
						for (int l2 = 0; l2 < origin[0].length; l2++) {
//							imerodeImage.setRGB(i-origin.length/2, j-origin[0].length/2,
//									grayImage.getRGB(i-origin.length/2, j-origin[0].length/2) 
//									& origin[l][l2]);
							//当origin比较大时，可能出现对边界以外元素的访问
							if (origin[l][l2] == 0) {
								imerodeImage.setRGB(i-l+origin.length/2, j-l2+origin[0].length/2, 0xff000000);
							}
							
						}
					}
					
				}
				
			}//for (int j = 0; j < imerodeImage.getHeight(); j++)
		}//for (int i = 0; i < imerodeImage.getWidth(); i++) 
		 
	}

}
