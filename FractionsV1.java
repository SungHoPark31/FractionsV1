import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * This program will read from a text file which contains a number of 
 * fractions. This program will also display the number occurrances of each
 * fraction when it is reduced. 
 * 
 * @author Sung Ho Park
 * Section: CSS 143 B
 * Assignment: FractionsV1
 */
public class FractionsV1 
{
    /**
     * This is the main method. This will read the text file and display 
     * the fraction occurances in the file.
     */
    public static void main(String[] args)
    {
        //Initialize Scanner 
        Scanner inputStream = null;

        //Read from the text file and if not, catch the exception
        try
        {
            inputStream = new Scanner(new FileInputStream("fractions.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
            System.exit(0);
        }

        //Loop through to get each line 

        //Initialize arrays with a set value.
        int[] occurances = new int[80];
        int[] num = new int[80];
        int[] deno = new int[80];
        int index = 0;

        //If a line in the text file can be read
        while(inputStream.hasNextLine())
        {
            //Read a line from  the text file.
            String readALine = inputStream.nextLine();

            //Split the fractions so you have integers are in an array
            String fractionArray[] = readALine.split("/");

            //Put the array numbers in numerator and denominator
            // and convert those numbers from a String to an integer
            
            int numerator = Integer.parseInt(fractionArray[0]);
            int denominator = Integer.parseInt(fractionArray[1]);
            boolean found = false;

            //If there is not a denominator of zero execute the code
            if(!(denominator == 0))
            {
                //This for loop is going through the arrays as index increases 
                
                for (int i = 0; i < index; i++)
                {
                    //if found, then don't put it in and add to the counter
                    //using cross multiplication
                    
                    if(numerator * deno[i] == denominator * num[i])
                    {
                        //Since i = 0, if the equivalent unique fraction is 
                        //found,then add the counter by 1, but in this case,
                        //it is the occurances
                        
                        occurances[i] += 1;
                        found = true;
                    }
                }

                //If an occurance isn't found, then just add the numbers to 
                //the numerator array and the denominator array.
                if(!found)
                {   
                    num[index] = numerator;
                    deno[index] = denominator;

                    //I had trouble with this, but it is good to remind myself
                    //This will take the current index in occurances and add 
                    //by 1
                    occurances[index] += 1;

                    //increment index
                    index++;
                }

            }
            else
            {
                //Just in case if a denominator of zero is found
                
                System.out.println("Invalid Numbers");
                System.exit(0);
            }
        }

        //Print out the values of the arrays because the only way to access 
        //the values is with a for loop
        
        for(int i = 0; i < index; i++)
        {
            System.out.println(num[i] + "/" + deno[i] + " has a count of " +
                occurances[i]);
        }

        //Close the input stream
        inputStream.close();
    }

}