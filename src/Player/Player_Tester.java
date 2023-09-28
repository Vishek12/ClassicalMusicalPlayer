package Player;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.*;

class Player_Tester {
	@Test 
	void testNoteConstructor0() {
		Note note1 = new Note(); 
		boolean actual = ((note1.getExceptionCode() ==  0 && note1.getNote() == 0 && note1.getOctave() == 0 && note1.getDuration() == 0) ? true : false);
		assertEquals(true, actual, "Note's default constructors or getter methods are not properly implemented!");
	}


	@Test 
	void testNoteCompareTo1() {
		Note note1 = new Note(60, 100, 4); 
		Note note2 = new Note(60, 100, 4); 
		int ret = note1.compareTo(note2); 
		boolean actual = (ret == 0? true : false) && note1.getExceptionCode() == 0; 
		assertEquals(true, actual, "Note's compareTo does not work as expected and/or getExceptionCode is not set properly");
	}

	
	@Test 
	void testNoteCompareTo4() {
		Note note1 = new Note(60, 100, 4); 
		Music note2 = new Music(); 
		int ret = note1.compareTo(note2); 
		int actual = note1.getExceptionCode();
		assertEquals(-2, actual, "getExceptionCode is not set properly for incompatible objects");
	}
	
	@Test 
	void testNoteCompareTo5() {
		Note note1 = new Note(60, 100, 4); 
		Note note2 = null; 
		int ret = note1.compareTo(note2); 
		int actual = note1.getExceptionCode();
		assertEquals(-1, actual, "getExceptionCode is not set properly for incompatible objects");
	}
	@Test 
	void testNoteCompareTo6() {
		Note note1 = new Note(60, 100, 4); 
		Note note2 = new Note(60, 100, 5);  
		int ret = note1.compareTo(note2); 
		int actual = note1.getExceptionCode();
		assertEquals(0, ret, "getExceptionCode is not set properly for incompatible objects");
	}
	
	@Test 
	void testNoteCompareTo7() {
		Note note1 = new Note(60, 100, 4); 
		Note note2 = new Note(50, 100, 4);  
		int ret = note1.compareTo(note2); 
		int actual = note1.getExceptionCode();
		assertEquals(10, ret, "getExceptionCode is not set properly for incompatible objects");
	}
	
	
	@Test
	void testValidate_1() {
		Note note1 = new Note(-3, 100, -1); 
		note1.play();
		int actual = note1.getExceptionCode();
		assertEquals(-3, actual, "play does not handle the MismatchException properly for octave -1");
	}

	// This tester checks if validateNote throws an exception. However, it does not check the  type of exception.
	 @Test
     public void testValidateNote1() {
		 Note note = new Note(70, 1000, 9);
		 try {
			 Method method = Note.class.getDeclaredMethod("validateNote", null);
			 method.setAccessible(true);
			 assertThrows(InvocationTargetException.class, ()-> method.invoke(note));
		 }
		 catch (Exception e) {		
			 System.out.println("error");
		 }
     }	
	 
	 @Test
     public void testValidateNote2() {
		 Note note = new Note(72, 1000, 5);
		 try {
			 Method method = Note.class.getDeclaredMethod("validateNote", null);
			 method.setAccessible(true);
			 assertThrows(InvocationTargetException.class, ()-> method.invoke(note));
		 }
		 catch (Exception e) {		
			 System.out.println("error");
		 }
     }	

	 @Test
     public void testValidateNote3() {
		 Note note = new Note(75, 1000, -1);
		 try {
			 Method method = Note.class.getDeclaredMethod("validateNote", null);
			 method.setAccessible(true);
			 assertThrows(InvocationTargetException.class, ()-> method.invoke(note));
		 }
		 catch (Exception e) {		
			 System.out.println("error");
		 }
     }	

	
		@Test 
		void testMusicConstructor0() {
			Music music = new Music();
			boolean actual = ((music.getName() == null && music.getComposer() == null && music.getScore().size() == 0) ? true : false);
			assertEquals(true, actual, "Music's default constructors or getter methods are not properly implemented!");
		}

		
		@Test 
		void testMusicCompareTo3() {
			Note [] note1 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			Note [] note2 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)};
			ArrayList<Note> score1 = new ArrayList<Note>();
			ArrayList<Note> score2 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) {
				score1.add(note1[i]);
				score2.add(note2[i]);
			}
			Music music1 = new Music("composer", "name1", score1);
			Music music2 = new Music("composer", "name2", score2);
			boolean actual = music1.compareTo(music2) == 0;
			assertEquals(false, actual, "Music's compareTo() is not properly implemented!");
		}
		
		@Test 
		void testMusicCompareTo4() {
			Note [] note1 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			Note [] note2 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)};
			ArrayList<Note> score1 = new ArrayList<Note>();
			ArrayList<Note> score2 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) {
				score1.add(note1[i]);
				score2.add(note2[i]);
			}
			Music music1 = new Music("composer", "name1", score1);
			Music music2 = new Music("composer", "name1", score2);
			boolean actual = music1.compareTo(music2) == 0;
			assertEquals(true, actual, "Music's compareTo() is not properly implemented!");
		}
		@Test 
		void testMusicCompareTo5() {
			Note [] note1 = {new Note(14, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			Note [] note2 = {new Note(4, 1000,-1), null, new Note(28 , 1000,1)};
			ArrayList<Note> score1 = new ArrayList<Note>();
			ArrayList<Note> score2 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) {
				score1.add(note1[i]);
				score2.add(note2[i]);
			}
			Music music1 = new Music("composer", "name1", score1);
			int music2 = 
			music1.compareTo(note1[0]);
			int actual = music1.getExceptionCode(); 
			assertEquals(-2, actual, "Music's compareTo() is not properly implemented!");
		}
		
		@Test 
		void testMusicCompareTo1() {
			Note [] note1 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			Note [] note2 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note()};
			ArrayList<Note> score1 = new ArrayList<Note>();
			ArrayList<Note> score2 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) {
				score1.add(note1[i]);
				score2.add(note2[i]);
			}
			Music music1 = new Music("composer", "name1", score1);
			Music music2 = new Music("composer", "name1", score2);
			int actual2 = music1.compareTo(music2);
			int actual = music1.getExceptionCode();
			assertEquals(0, actual, "Music's compareTo() is not properly implemented!");
		}
		@Test 
		void testMusicCompareTo6() {
			Note [] note1 = {new Note(14, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			Note [] note2 = {new Note(4, 1000,-1),null, new Note(28 , 1000,1)};
			ArrayList<Note> score1 = new ArrayList<Note>();
			ArrayList<Note> score2 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) {
				score1.add(note1[i]);
				score2.add(note2[i]);
			}
			Music music1 = new Music("composer", "name1", score1);
			Music music2 = null;
			int num = music1.compareTo(music2);
			int actual = music1.getExceptionCode(); 
			assertEquals(-1, actual, "Music's compareTo() is not properly implemented!");
		}
		
		
		
		
		@Test 
		void testCompositionInScoreByGetter() {
			Note [] note1 = {new Note(4, 1000,-1), new Note(15 , 1000,0), new Note(28 , 1000,1)} ;
			ArrayList<Note> score1 = new ArrayList<Note>();
			for (int i = 0; i < note1.length; i++) 
				score1.add(note1[i]);
			Music music1 = new Music("composer", "name1", score1);
			boolean actual = music1.getScore() != score1 && music1.getScore().get(0) != score1.get(0);
			assertEquals(true, actual, "Composition was not implemented correctly in the getter method");
			
		}
	
		@Test
		void testReadFile1() {
			PerformingArt.readFile("AFileThatDoesnotExist");
			assertEquals(-1, PerformingArt.exceptionCode, "readFile does not handle the exception correctly");
		}
		
		@Test
		void testReadFile3() {
			//For this tester to works properly, create a file called music1 and store the content of list2 (below) in the file.
			List<String> list = (ArrayList<String>) PerformingArt.readFile("Music1"); 
			String[] list2 = {"48 1000 3", "68 1000 4", "2 1000 -1", "108 1000 8", "51 1000 3", "59 1000 3", "33 1000 1", "48 1000 3", "93 1000 6", "102 1000 7"};
			boolean equal = list2.length == list.size(); 
			if (equal)
				for (int i = 0; i < list2.length; i++)
					if((list2[i].trim()).compareTo((list.get(i).trim())) != 0){
						equal = false; 
						break;
					}
			assertEquals(false, equal, "readFile does not read the file correctly");
		}


}
