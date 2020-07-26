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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
    
    private     JPanel      buttons             =   null;
    
    private     JLabel      labels[]            =   null;
    
    private     JTextArea   responses[]         =   null;
    
    private     String      questions[]         =   null;
    
    private     String      answers[]           =   null;
    
    protected   JButton     save                =   null;
    
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
            
            setTitle("Form");
            
            setSize(width, height);
            //ADD THE FUCKING PANELS WITH THE JLABELS AND JTEXTAREAS YOU FUCK!!!
            setPrompts(prompts);
            createLabels();
            createTextAreas();
            createButtons();
            createPanels();
            //JOptionPane.showMessageDialog(null, toString());
            //
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally
        {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
    protected void setAnswers()
    {
        answers = new String[getPrompts()];
        for(int i = 0; i < getPrompts(); i++)
        {
            answers[i] = responses[i].getText();
        }
    }
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
            for(int i = 0; scan.hasNext(); i++)
            {
                questions[i] = scan.nextLine();
                p = i + 1;
            }
            scan.close();
            
            setPrompts(p);
            
            //JOptionPane.showMessageDialog(null, "Number of prompts added:"+getPrompts());
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(null,ioe.getMessage());
        }
    }
    //Getters
    private int getPrompts()
    {
        return prompts;
    }
    /**
    *This function will allow the given answers to be passed on the calling event
    *   that started this whole damn window in the first god damn place. 
    **/
    protected void clearTextAreas()
    {
        for(int i = 0; i < getPrompts(); i++)
        {
            responses[i].setText("");
        }
    }
    protected String[] getAnswers(){ return answers; }
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
            content[i].add(labels[i]);
            content[i].add(responses[i]);
            mainPanel.add(content[i]);
        }
        buttonPanel();
        mainPanel.add(buttons);
        add(mainPanel);
        //add labels and text areas to content then add content to main
    }
    /************/
    private void createLabels()
    {
        labels = new JLabel[getPrompts()];
        for(int i = 0; i < labels.length; i++)
        {
            labels[i] = new JLabel(questions[i]);
        }
    }
    /************/
    private void createTextAreas()
    {
        responses = new JTextArea[getPrompts()];
        for(int i = 0; i < responses.length; i++)
        {
            responses[i] = new JTextArea();
            responses[i].setLineWrap(true);
            responses[i].setColumns(1);
            responses[i].setRows(1);
            responses[i].setTabSize(0);
        }
    }
    private void createButtons()
    {
        save = new JButton("Save");
    }
    private void buttonPanel()
    {
        buttons = new JPanel();
        
        buttons.add(save);
    }
    //toString method
    public String toString()
    {
        String acclimatedString = "";
        for(int i = 0; i < questions.length; i++)
        {
            acclimatedString += "\n"+questions[i]+"\n";
        }
        return acclimatedString;
    }
    /*
    *End Methods
    */
}
/***Fin***/