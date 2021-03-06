import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
//Miles Cumiskey, Hackettstown High School
//4.25.17 - edits to the PictureLab project

public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/** method to sets everything not blue to 0 **/
	public void onlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}
	
	 /**
	 * The red value is set to 255 minus the current red value. 
	 * The green value to 255 minus the current green value.  
	 * The blue value to 255 minus the current blue value. 
	 * 
	 **/
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(255 - (pixelObj.getBlue()));
				pixelObj.setRed(255 - (pixelObj.getRed()));
				pixelObj.setGreen(255 - (pixelObj.getGreen()));


			}
		}
	}	
	
	/**
	 * makes the blue fish more visible by adding more reds + less green and adding outlines
	 */
	public void fixUnderwater() {
		
		Pixel[][] colorFix = this.getPixels2D();
		for (Pixel[] rowArray : colorFix) {
			for (Pixel pixelObj : rowArray) {
				
				pixelObj.setRed(150);
				pixelObj.setGreen(pixelObj.getGreen()-25);
			}
		}	
		
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > 3)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setAlpha(Color.TRANSLUCENT);
			}
		}
	}
	
	/**
	 * Takes the average RGB values and sets all the RGB values to that
	 */
	public void toGrayscale() {
		
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(pixelObj.getAverage());
				pixelObj.setGreen(pixelObj.getAverage());
				pixelObj.setBlue(pixelObj.getAverage());
			}
		}		
	}

	/**
	 * mirrors left -> right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}
	
	/**
	 * mirrors right half to the left half
	 */
	public void mirrorVerticalRightToLeft() {
		 Pixel[][] pixels = this.getPixels2D();
		 Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}
	
	/**
	 * Mirrors the top half of an image down to the bottom half
	 */
			
	public void mirrorHorizontal()
	  {
	      Pixel[][] pixels = this.getPixels2D();
	      Pixel topPixel = null;
	      Pixel bottomPixel = null;
	      int height = pixels.length;
	      for (int row = 0; row < height; row++)
	      {
	          for (int col = 0; col < pixels[0].length; col++)
	          {
	              topPixel = pixels[row][col];
	              bottomPixel = pixels[height - 1 - row][col];
	              bottomPixel.setColor(topPixel.getColor());
	          }
	      }
	  }
	/**
	 * mirrors the bottom half of an image up to the top half
	 */
	public void mirrorHorizontalBottomToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[height - 1 - row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}
	
	/**
	 *  mirrors from top right half to the bottom left half
	 */
	
	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topRightPixel = null;
		Pixel bottomLeftPixel = null;
		int maxLength;
		if (pixels.length < pixels[0].length) {
			maxLength = pixels.length;
		} else {
			maxLength = pixels[0].length;
		}

		for (int row = 0; row < maxLength; row++) {
			for (int col = row; col < maxLength; col++) {
				topRightPixel = pixels[row][col];
				bottomLeftPixel = pixels[col][row];
				bottomLeftPixel.setColor(topRightPixel.getColor());
			}
		}
	}
	  
	/** 
	 * Fix a broken temple using mirroring
	 */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {
				count++; //Increment count each time
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
		System.out.println(count); //print the final loop amount
	}

	/** 
	 * Mirrors the arms of the snowman 
	 */
	public void mirrorArms() {
		int mirrorPoint = 193;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// Left arm
		for (int row = 158; row < mirrorPoint; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 103; col < 170; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}

		int mirrorPoint2 = 198;
		Pixel topPixel2 = null;
		Pixel bottomPixel2 = null;

		// Right arm
		for (int row = 171; row < mirrorPoint2; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 239; col < 294; col++) {
				topPixel2 = pixels[row][col];
				bottomPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2][col];
				bottomPixel2.setColor(topPixel2.getColor());
			}
		}
	}
	/**
	 * Add another seagull using mirrors
	 */
	public void mirrorGull() {
		int mirrorPoint = 345;
		Pixel rightPixel = null;
		Pixel leftPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		
		for (int row = 235; row < 323; row++) {
			for (int col = 238; col < mirrorPoint; col++) {
				rightPixel = pixels[row][col];
				leftPixel = pixels[row][mirrorPoint - col + mirrorPoint / 3];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}
	
	/**
	 * copy from the specified startRow and startCol to the endCol and endRow
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 * @param endRow
	 * 				the row to stop copying at           
	 * @param endCol 
	 * 				the col to stop copying at
	 */
	public void copyPart(Picture fromPic, int startRow, int startCol, int endRow, int endCol){
		
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow;             
			fromRow < fromPixels.length       //from 0 to the array size              
			&& toRow < endRow;                //while you are between toRow && endrow
			fromRow++, toRow++) {             //increment each row
			
			for (int fromCol = 0, toCol = startCol; 
					fromCol < fromPixels[0].length   //from 0 to array size
					&& toCol < endCol;               //while you are between toCol and endCol
					fromCol++, toCol++) {            //increment each col
				
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}
	

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}
	
	public void makeMyCollage(){
		Picture plainStars = new Picture ("io.jpg");
		Picture inverseStars = new Picture ("io.jpg");
		Picture oddBall = new Picture ("io.jpg");
		
		this.copyPart(plainStars, 250, 250, 300, 275);
		this.copyPart(plainStars, 600, 670, 700, 700);
		this.copyPart(plainStars, 96, 706, 240, 750);
		
		inverseStars.negate();
		
		this.copyPart(inverseStars, 301, 275, 340, 300);
		this.copyPart(inverseStars, 700, 680, 720, 710);
		this.copyPart(inverseStars, 500, 150, 810, 200);
		
		oddBall.fixUnderwater();
		oddBall.toGrayscale();
		oddBall.mirrorHorizontalBottomToTop();
		
		this.copyPart(oddBall, 418, 300, 479, 480);

		 this.write("collage.jpg");
		
		}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    Pixel topPixel = null;       //added top + bottom edge detection
	    Pixel bottomPixel = null;
	    
	    Pixel[][] pixels = this.getPixels2D();
	    for (int row = 0; row < pixels.length - 1; row++) {
	    	
	      for (int col = 0; col < pixels[0].length-1; col++) {
	    	  
	        leftPixel = pixels[row][col];
	        rightPixel = pixels[row][col+1];
	        topPixel = pixels[row][col];
	        bottomPixel = pixels[row + 1][col];
	        
	        if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist ||
	            topPixel.colorDistance(bottomPixel.getColor()) > edgeDist){ 
	        	
	          leftPixel.setColor(Color.BLACK);
	        } else {
	          leftPixel.setColor(Color.WHITE);
	        }
	      }
	    }
	  }
	
	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    Pixel topPixel = null;       
	    Pixel bottomPixel = null;
	    
	    Pixel[][] pixels = this.getPixels2D();
	    for (int row = 0; row < pixels.length - 1; row++) {
	    	
	      for (int col = 0; col < pixels[0].length-1; col++) {
	    	  
	        leftPixel = pixels[row][col];
	        rightPixel = pixels[row][col+1];
	        topPixel = pixels[row][col];
	        bottomPixel = pixels[row + 1][col];
	        
	        if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist ||
	            topPixel.colorDistance(bottomPixel.getColor()) > edgeDist){ 
	        	
	          leftPixel.setColor(Color.BLACK);
	        } else {
	          leftPixel.setColor(Color.WHITE);
	        }
	      }
	    }
	  }
	
	
	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

	

} // this } is the end of class Picture, put all new methods before this
