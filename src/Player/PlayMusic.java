package Player;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;


public class PlayMusic {

	public static void main(String [] args) throws MismatchException {

		Music music = new Music("Beethoven", "Fur Elise", PerformingArt.readFile("FurElise")); 
		
		music.play();
		
		
	}
}
	
	

	



