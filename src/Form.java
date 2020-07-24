import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
*This will be a general form class that will be used to retrieve data from a user in an organized fashion
*The data will be returned to the main program and the form will have served it's purpose
*Created by: Brendon S Service, Start Date: 1/26/2020 11:08PM 
**/
class Form extends JFrame
{
    /**Start Fields**/
    private     JPanel      mainPanel           =   null;
    
    private     JPanel      content[]           =   null;
    
    private     JLabel      labels[]            =   null;
    
    private     JTextArea   responses[]         =   null;
    
    private     String      questions[]         =   null;
    
    private     int         prompts             =   0;
    /**End Fields**/
    /**Start Constructors**/
    Form(){ }//Blank constructor
    //custom constructor
    Form(int width, int height)
    {
        try
        {
            setQuestions(10);
            
            setPrompts(prompts);
            
            setTitle("Form");
            
            setSize(width, height);
            //ADD THE FUCKING PANELS WITH THE JLABELS AND JTEXTAREAS YOU FUCK!!!
            createPanels();
            createLabels();
            createTextAreas();
            //
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally
        {
            /**
            *Code from 
            *https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
            **/
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

            setVisible(true);
        }
    }
    /**End Constructors**/
    /**Start Methods**/
    //Setters
    private void setPrompts(int prompts)
    {
        this.prompts = prompts;
    }
    //This method houses the array that will contain the questions to be added to the JLabels
    private void setQuestions(int size)
    {
        try
        {
            int p = 0;
            questions = new String[size];
            File file = new File("PayRollQuestions.txt");
            Scanner scan = new Scanner(file);
            for(int i=0;scan.hasNext();i++)
            {
                questions[i] = scan.nextLine();
                p = i;
            }
            scan.close();
            setPrompts(p);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(null,ioe.getMessage());
        }
    }
    //Getters
    /*private int getQuestions()
    {
        return questions.length;
    }*/
    private int getPrompts()
    {
        return prompts;
    }
    /*****/
    private void createPanels()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        content = new JPanel[getPrompts()];
        //call createLabels and createTextAreas here
        for(int i = 0; i < getPrompts(); i++)
        {
            content[i] = new JPanel();
        }
        //add labels and text areas to content then add content to main
    }
    /************/
    private void createLabels()
    {
        labels = new JLabel[getPrompts()];
        for(int i = 0; i < getPrompts(); i++)
        {
            labels[i] = new JLabel(questions[i]);
        }
    }
    /************/
    private void createTextAreas()
    {
        responses = new JTextArea[getPrompts()];
    }
    //toString method
    public String toString(){return null;}
    /*
    *End Methods
    */
}
/***Fin***/