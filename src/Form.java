import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
*This will be a general form class that will be used to retrieve data from a user in an organized fashion
*The data will be returned to the main program and the form will have served it's purpose
*Created by: Brendon S Service, Start Date: 1/26/2020 11:08PM 
**/
class Form extends JFrame
{
    /*
    *Start Fields
    */
    private     JPanel      mainPanel           =   null;
    
    private     JPanel      contentPanel        =   null;
    
    private     String[]    questions           =   null;
    
    private     int         prompts             =   0;
    
    /*
    *End Fields
    */
    
    /*
    *Start Constructors
    */
    Form(){ }
    
    Form(int prompts, int width, int height, Color colour)
    {
        try
        {
            setPrompts(prompts);
            
            setTitle("Form");
            
            setSize(width, height);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            setVisible(true);
        }
    }
    
    /*
    *End Constructors
    */
    
    /*
    *Start Methods
    */
    //Setters
    private void setPrompts(int prompts)
    {
        this.prompts = prompts;
    }
    
    private void setQuestions(int size)
    {
        questions = new String[size];
        
        
    }
    
    //Getters
    public int getPrompts()
    {
        return prompts;
    }
    
    
    //toString method
    public String toString()
    {
        return "Method incomplete";
    }
    /*
    *End Methods
    */
}