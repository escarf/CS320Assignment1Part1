import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaParser {

	public static void main(String[] args) throws Exception {
        File file = new File("A.java");        
        Scanner scanner = new Scanner(file);  
        
        String text = "";      		
        while(scanner.hasNextLine()) {
        	text += scanner.nextLine();
        	text +="\n";        
        }
       
        
        // ((ab)|(aq))
        Pattern pattern = Pattern.compile("((\\s([a-zA-Z][a-zA-Z0-9]*)\\s([a-zA-Z][a-zA-Z0-9]*)\\s=\\s(.+);\\s)|(\\s+([a-zA-Z][a-zA-Z0-9]*)\\s+([a-zA-Z][a-zA-Z0-9]*);\\s+))");
        
        Matcher matcher = pattern.matcher(text);
        
        // Maybe 2 separate patterns, but both add to same List?
        // if not doing that^ garbage then you'd need some kind of OR condition, but that would
        // result in different group indexes. Maybe if the value is NULL (so not initialized)
        //		//(String would == null)
        // then have a condition in while loop that uses different groups. Would this hurt later on?
        // If doing the latter, what happens if you set value to null? Nothing good, but, when dealing 
        // with potentially uninitialized variables, you could check to see if they already exist.
        
  

        while(matcher.find()) {
        	
        	/*
        	 * Because of the pattern design, parsing the declaration of an uninitialized variable 
        	 * would mean that groups 3-5 are null. We can use this to tell us whether or not a variable
        	 * was initialized, and handle each case accordingly.
        	 */
        	if(matcher.group(3) == null) {
        		System.out.println("Type: " + matcher.group(7));
        		System.out.println("Name: " + matcher.group(8)); 
        		System.out.println("Value: null");
        	}
        	
        	
        	else {
        		System.out.println("Type: " + matcher.group(3));
        		System.out.println("Name: " + matcher.group(4)); 
        		System.out.println("Value: " + matcher.group(5));
        	}           	
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
        
        scanner.close();
	}

}
