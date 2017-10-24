package assignment1;

import jm.util.*;
import jm.music. data.*;
import jm.JMC;
import java.awt.*;
public class Homework implements JMC {

	public static void main(String[] args) {
		ImageTool imTool = new ImageTool ();
		Image image = imTool.readImageFile ("/Users/kevin/eclipse-workspace/assignment1/src/assignment1/3.JPG");
		imTool.showImage(image);
		int[][][] pixels = imTool.imageToPixels (image);	
//	initiate the original variables 					
		int row = pixels.length;
		int col = pixels[0].length;
		int pitch = 0;
		double duration = TN;
		int dynamic = 100;	
		double panning = 0.5; 

//	I took the picture a week ago, which mainly consists of two colors: red and green	
//	so I decide to use red and green colors to set the pitches
//	play red data 
//	from all the columns in the (row/2)th row		
		Phrase redPhrase = new Phrase();
		Part pianoPart = new Part("piano", PIANO,0);
		
		for(int i = 0; i < col; i++) {

//	 pentatonic way to set the pitches of the red data
		if (0 <= pixels[row/2][i][1] && pixels[row/2][i][1] <= 51) pitch = C4; 
		else if (51 < pixels[row/2][i][1] && pixels[row/2][i][1] <= 103) pitch = D4;
		else if (103 < pixels[row/2][i][1] && pixels[row/2][i][1] <= 155) pitch = G4;
		else if (155 < pixels[row/2][i][1] && pixels[row/2][i][1] <= 207) pitch = A4;
		else if (207 < pixels[row/2][i][1] && pixels[row/2][i][1] <= 255) pitch = BS4;

//	after the first 8 pixels, accelerate the pace
		if (i / 8 == 0) duration = DDEN;
		else duration = TN;

//	set the volume as a random number times the transparency 		
		dynamic = (int) (pixels[row/2][i][0] * Math.random());
		if (dynamic >= 127) dynamic = 100;
		else if (dynamic <= 40) dynamic = 60; 
			
		Note redNote = new Note(pitch, duration, dynamic, panning); 
		redPhrase.add(redNote);	
		
		
		}
		
//	play the red data by piano		
		pianoPart.add(redPhrase);

	
//	play green data 
//	from all the rows in the (col/2)th column
		Phrase greenPhrase = new Phrase();
		Part bellPart = new Part("bells", BELLS,1);
		
		for(int i = 0; i < row; i++) {

// same as the green data			
			if (0 <= pixels[i][col/2][2] && pixels[i][col/2][2] <= 51) pitch = C4; 
			else if (51 < pixels[i][col/2][2] && pixels[i][col/2][2] <= 103) pitch = D4;
			else if (103 < pixels[i][col/2][2] && pixels[i][col/2][2] <= 155) pitch = G4;
			else if (155 < pixels[i][col/2][2] && pixels[i][col/2][2] <= 207) pitch = A4;
			else if (207 < pixels[i][col/2][2] && pixels[i][col/2][2] <= 255) pitch = BS4;
			
			if (i / 8 == 0) duration = DDEN;
			else duration = TN;
			
			dynamic = (int) (pixels[i][col/2][0] * Math.random());
			if (dynamic >= 127) dynamic = 100;
			else if (dynamic <= 40) dynamic = 60; 
				
			Note greenNote = new Note(pitch, duration, dynamic, panning); 
			greenPhrase.add(greenNote);	
		}

//	play the green data by bell		
		bellPart.add(greenPhrase);
		
		Score newScore = new Score();
		newScore.add(pianoPart);
		newScore.add(bellPart);

//	play the music		
		Play.midi(newScore);
	}
	
	
	
	
}








