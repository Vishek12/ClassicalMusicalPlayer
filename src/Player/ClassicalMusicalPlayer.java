package Player;


import java.awt.event.ComponentAdapter;
import java.io.*;
import java.util.*; 

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;



/*
Full Name: Vishek Lamba	
Student Number: 218226811
Course Section: Section A
 */



public class ClassicalMusicalPlayer {

	public static void main(String[] args) {
	}

} // end of PE2






interface Art extends Comparable<Art>{

	public void play();
}






/**
 * This is an abstract class that implemnets the Art interface 
 * @author Vishek Lamba 
 *
 */
abstract class PerformingArt implements Art {

	static int exceptionCode = 0;

	/**
	 * This method reads the file and stores the file data into a list of Strings 
	 * @param fileName name of the file to be read 
	 * @return the List of the content read from the file 
	 */
	public static List<String>readFile(String fileName){

		List<String> line = new ArrayList<String>(); 

		try {
			Scanner scannerObj = new Scanner(new File(fileName));
			while (scannerObj.hasNext()) {

				line.add(scannerObj.nextLine().trim());
			}
		}catch(FileNotFoundException e) {
			exceptionCode = -1;
			System.out.println("File Does Not Exist");
			e.printStackTrace();

		}
		return line;

	}

	//Will be overriden in the subclasses of PerformingArts: Note and Music classes 
	public abstract void play(); 


}




/**
 * This class represents a Notes that can be played. 
 * Every Note has a note, the duration of how long the note is played 
 * and octave range of the note 
 * @author Vishek Lamba 
 *
 */
class Note extends PerformingArt{

	private int note;  
	private int duration;
	private int octave; 
	private int exceptionCode; 

	public Note() {
		this.note = 0; 
		this.octave = 0; 
		this.duration = 0; 
		this.exceptionCode = 0; 


	}

	public Note(int note, int duration, int octave) {

		this.note = note; 
		this.octave = octave; 
		this.duration = duration; 
		this.exceptionCode = 0; 
	}






	/**
	 * This method validates if the note corresponds to the appropiate Octave range 
	 * @throws @MismatchException if mismatch exception note does not fall in the octave range 
	 */
	void validate() throws MismatchException{

		if(this.octave == -1) {

			if(this.note< 0 || this.note>11) 
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 0) {

			if(this.note<12 || this.note>23) 
				throw new MismatchException("Octave/Note Mismatch");

		}else if(this.octave == 1) {

			if(this.note < 24 || this.note>35) 
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 2) {

			if(this.note<36 || this.note>47) 
				throw new MismatchException("Octave/Note Mismatch");

		}else if(this.octave == 3) {

			if(this.note<48 || this.note>59)
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 4) {

			if(this.note<60 || this.note>71) 
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 5) {

			if(this.note<72 || this.note>83)
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 6) {

			if(this.note<84|| this.note>95)
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 7) {

			if(this.note<96 || this.note>107)
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 8) {

			if(this.note<108|| this.note > 119) 
				throw new MismatchException("Octave/Note Mismatch"); 

		}else if(this.octave == 9) {

			if(this.note<120 || this.note>127) 

				throw new MismatchException("Octave/Note Mismatch"); 

		}

	}


	/**
	 * This method validates the code and eventually plays the Note 
	 *	@throws @MismatchException if the octave and note mismatch 
	 */
	public void play() {

		try {

			this.validate();

			try {

				Synthesizer midiSynthesizer = MidiSystem.getSynthesizer();
				midiSynthesizer.open();
				this.validate();
				Instrument[] instrument = midiSynthesizer.getDefaultSoundbank().getInstruments();
				midiSynthesizer.loadInstrument(instrument[0]); 
				MidiChannel[] midiChannels = midiSynthesizer.getChannels(); 
				midiChannels[0].noteOn(this.note, 100);
				try {

					Thread.sleep(duration);

				}catch(InterruptedException e) {

					e.printStackTrace();
				}

				midiChannels[0].noteOff(this.note);

			}
			catch (Exception e) {

				e.printStackTrace();

			}

		}catch(MismatchException e) {

			this.exceptionCode = -3; 
			System.out.println(e.getMessage());
		}


	}








	//setter methods of Note

	/**
	 * This method sets the octave value of a Note 
	 * @param octave entered by client 
	 */
	public void setOctave(int octave) {

		this.octave = octave; 
	}

	/**
	 * This method sets the note of Note Object 
	 * @param note entered by the user
	 */
	public void setNote(int note) {

		this.note =  note; 

	}
	/**
	 * This method sets the duration of a Note 
	 * @param duration entered by the user
	 */
	public void setDuration(int duration) {

		this.duration = duration; 
	}

	/**
	 * This method sets the exceptionCode for the user 
	 * @param except is the entered exception amount 
	 */
	public void setExceptionCode(int except) {

		this.exceptionCode = except; 

	}





	//getter methods of Note 
	/**
	 * 
	 * @return the exceptionCode of the object
	 */
	public int getExceptionCode() {
		// TODO Auto-generated method stub
		return this.exceptionCode; 
	}
	/**
	 * 
	 * @return the duration of a given Note
	 */
	public int getDuration() {

		return this.duration; 
	}

	/**
	 * 
	 * @return the note of the Note Object
	 */
	public int getNote() {
		return this.note; 
	}

	/**
	 * 
	 * @return the octave of a Note
	 */
	public int getOctave() {
		return this.octave; 
	}

	/**
	 * This method checks if 2 Notes are the same if they have the same octave and note 
	 * @return 0 if objects have same octave/note, otherwise return the difference of their note values 
	 * @param is the object being compared 
	 * @throws @ClassCastException if the object can't be compared, exceptionCode = -2
	 * @throws @NullPointerException if we compare a null object exceptionCode = -1
	 */
	public int compareTo(Art obj) {

		int ext = -1;  


		try {

			Note n1 = (Note)obj; 
			if(this.note == n1.note && this.octave == n1.octave) {
				ext = 0; 

			}else{

				ext = this.note-n1.note;
			}

			return ext; 

		}catch(NullPointerException e) {

			this.exceptionCode = -1; 
			return ext;

		}catch(ClassCastException e) {

			this.exceptionCode = -2; 
			return ext;
		}

	}

}

/**
 * This class is an exception class that is used
 * when there is an Octave/note mismatch.
 * @author Vishek Lamba 
 */
class MismatchException extends Exception{


	public MismatchException() {
		super(); 
	}

	public MismatchException(String str) {

		super(str); 
	}

}

/**
 * This class represents Music that is to be played.
 * Every music object has a composer (who writes the music), name of song 
 * and score of notes of the song  
 * @author Vishek Lamba 
 *
 */
class Music extends PerformingArt{

	private String composer; 
	private String name; 
	private List<Note> score; 
	private int exceptionCode; //REMOVE LATER 

	//Music Constructors 

	public Music(){

		this.composer = null;
		this.exceptionCode = 0; 
		this.name = null; 
		this.score = new ArrayList<Note>();
	}

	public Music(String composer, String name, ArrayList<Note>score) {

		this.composer = new String(composer); 
		this.name = new String(name); 
		this.score = new ArrayList<Note>(score.size());
		this.exceptionCode = 0; 

		for(int i = 0; i<score.size(); i++) {

			this.score.add(new Note(score.get(i).getNote(), score.get(i).getDuration(), score.get(i).getOctave()));

		}
	}



	public Music(String composer, String name, List<String>fileNotes) {

		this.composer = new String(composer); 
		this.name = new String(name); 
		this.exceptionCode = 0; 


		//Read the list of string file
		this.score = new ArrayList<Note>(); 				

		for(int i = 0; i<fileNotes.size(); i++) {       	/**
		 * Every string is then read for it's content, the note, the duration and 
		 * each is stored as integer at the following indexes of the array 
		 * note[0] = note 
		 * note[1] = duration
		 * note[2] = octave 
		 */

			int [] note = new int[3]; 
			note = Music.getNumArray(fileNotes.get(i)); 

			//Following composition we then add the notes with their content into the score 
			this.score.add(new Note(note[0], note[1], note[2])); 

		}


	}

	/**
	 * 
	 * @param reads the string of a Note that has it's note, duration and octave 
	 * @return an array of integers representing the Note characteristics 
	 */
	public static int [] getNumArray(String str) {

		int [] NoteRepresented = new int[3]; 

		String strNew =str.trim(); //will remove any trailing space before first characters of the string 

		/**
		 * Under the assumption that we only have 3 pieces of content (note, duration, octave) 
		 * we convert it an array using the split method . Elements are substrings of strNew. A space indicates a new Element 
		 */
		String[] strArray  = strNew.split("\\s+"); 


		for(int i = 0; i<NoteRepresented.length; i++) {

			NoteRepresented[i] = Integer.parseInt(strArray[i]); //convert string to int
		}

		return NoteRepresented; 
	}




	//Mutator/Accessor methods for for composer
	/**
	 * This method returns the name of the composer of the music 
	 * @return the name of the composer the music 
	 */
	public String getComposer() {
		try {

			return new String(this.composer);
		}catch(Exception e) {
			return null; //in the case of null 
		}

	}

	/**
	 * This method sets the composer for specific music 
	 * @param name of the composer 
	 */
	public void setComposer(String composer) {



		this.composer = new String(composer);
	}


	//Mutator/Acessor methods for name 

	/**
	 * This method returns name of the song being played 
	 * @return name of the song of the music 
	 */
	public String getName() {
		try {
			return new String(name);
		}catch(Exception e) {

			return null;
		}

	}

	/**
	 * This method sets the name of the song for music 
	 * @param name name of the music 
	 */
	public void setName(String name) {
		this.name = new String(name);
	}



	//Mutator/Accessor methods for score 

	/**
	 * This method returns the score of Notes 
	 * @return the score of notes of the music 
	 */
	public ArrayList<Note> getScore() {

		ArrayList<Note> tscore = new ArrayList<Note>(this.score.size());

		for(int i = 0; i<this.score.size(); i++) {

			tscore.add(new Note(this.score.get(i).getNote(), this.score.get(i).getDuration(), this.score.get(i).getOctave()));

		}

		return tscore; 



	}

	/**
	 * This method sets the score of music 
	 * @param score the score to set
	 */
	public void setScore(List<Note> score) {
		for(int i = 0; i<score.size(); i++) {
			this.score.add(new Note(score.get(i).getNote(), score.get(i).getDuration(), score.get(i).getOctave()));
		}
	}

	/**
	 * This method plays all the Notes from the score 
	 */
	public void play() {

		for(int i = 0; i<this.score.size(); i++) {

			this.score.get(i).play();
		}
	}


	/**
	 * This method returns the exceptionCode of a Music object 
	 * @return the exceptionCode of an instance of Music class 
	 */
	public int getExceptionCode() {
		return this.exceptionCode;
	}


	/**
	 * This method compares 2 Music Objects and returns 0 if Music objects have the same
	 * name and composer, else -1 is returned 
	 * @return the comparison of the objects 
	 * @param the object being compared 
	 * @throws @ClassCastException if any of the objects can't be compared 
	 * @throws @NullPointerException if any of the Music Objects are null 
	 */
	@Override
	public int compareTo(Art o) {
		// TODO Auto-generated method stub

		try {

			Music obj = (Music)o; 

			boolean scoreFlag = false; //flag is going to indicate that both scores are the same 
			//false= scores and all notes are equal 
			//true = at least 1 note is different from the scores 

			for(int i = 0; i<this.score.size(); i++) {

				if(this.score.get(i).compareTo(obj.score.get(i)) != 0) {

					scoreFlag = true; 
					break;
				}
			}


			if(this.composer.equals(obj.composer) && this.name.equals(obj.name) && scoreFlag == false && this.score.size() == obj.score.size()) {
				return 0; 
			}else {
				return -1; 
			}

		}catch(NullPointerException e) {
			this.exceptionCode = -1; 
			e.printStackTrace();
			return -1;
		}catch(ClassCastException e) {

			this.exceptionCode = -2; 
			e.printStackTrace();
			return -1;
		}

	}


}







