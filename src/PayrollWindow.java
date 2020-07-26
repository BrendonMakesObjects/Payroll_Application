import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;

class PayrollWindow extends JFrame
{
    private     Employee    emp     =   null;
    
    private     Volunteer   voluntr =   null;
    
    private     Form        form    =   null;
    
    private     JPanel      centerPanel = null;
    
    private     JTable      ListTable   =   null;
    
    private     JMenuBar    menuBar     =   null;
    
    private JMenu   File    =   null;
    
    private JMenu   Edit    =   null;
        
    private JMenu   Help    =   null;

    private JMenuItem   addEmployee =   null;
    
    private JMenuItem   addVoluntr  =   null;
    
    private JMenuItem   Export_CSV  =   null;
    
    private JMenuItem   Open_File   =   null;
    
    private JMenuItem   Add_w_Form  =   null;
    
    private JMenuItem   Exit_App    =   null;
    
    private ArrayList<Employee>    employees   =   null;
        
    private int rowCounter =    0;
    
    
    PayrollWindow(){ }
    
    PayrollWindow(String title, int height, int width)
    {
        try
        {
            createCenterPanel();
            createTable();
            menuBar = new JMenuBar();
            
            createJMenuItems();
            
            createJMenu();
            
            createEmployeeList();
            
            if(title != null)
            {
                setTitle(title);
                
            }
            if(height > 0 || width > 0){
                setSize(width, height);
            }
        }
        catch(Exception e)
        {
            System.out.print("\n\nError: in Applcation Window\n\n"+ e.getMessage());
        }
        finally
        {
            setJMenuBar(menuBar);
            //menuBar.setVisible(true);
            
            setVisible(true);
            
            setResizable(false);
            
            setLayout(new BorderLayout());
            
            setJMenuBar(menuBar);
            
            add(centerPanel, BorderLayout.CENTER);

            ListTable.setValueAt("First Name",0,0);
            ListTable.setValueAt("Last Name",0,1);
            ListTable.setValueAt("Employee ID",0,2);
            ListTable.setValueAt("Emp Type",0,3);
            ListTable.setValueAt("Pay Rate",0,4);
            ListTable.setValueAt("Hours Done",0,5);


            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            /**
            *Code from 
            *https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
            **/
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            /**
            *End of used code
            ***/
        }
    }
    
    void createCenterPanel()
    {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
    }
    
    void createTable()
    {
        ListTable = new JTable(25, 6);
        ListTable.setEnabled(false); 
        centerPanel.add(ListTable, BorderLayout.CENTER);
    }
    
    void createJMenu()
    {
        File = new JMenu("File");
        File.add(Export_CSV);
        menuBar.add(File);
        
        Edit = new JMenu("Edit");
        Edit.add(addEmployee);
        Edit.add(addVoluntr);
        Edit.add(Add_w_Form);
        menuBar.add(Edit);
        
        
        Help = new JMenu("Help");
        Help.add(Exit_App);
        menuBar.add(Help);
    }
    
    void createJMenuItems()
    {
        addEmployee = new JMenuItem("Add Employee to list");
        addEmployee.addActionListener(new EmployeeAddition());
        
        addVoluntr = new JMenuItem("Add Volunteer to list");
        addVoluntr.addActionListener(new VolunteerAddition());
        
        Add_w_Form = new JMenuItem("Add");
        Add_w_Form.addActionListener(new AddWithForm());
        
        Export_CSV = new JMenuItem("Export as CSV");
        Export_CSV.addActionListener(new Export_To_CSV());
                
        Exit_App = new JMenuItem("Exit Application");
        Exit_App.addActionListener(new Exit_Application());
    }
    
    void createEmployeeList()
    {
        employees = new ArrayList<Employee>();
    }
    /**
    *This method will allow user to add a full row of data with a single form.
    **/
    private class AddWithForm implements ActionListener
    {
        public void actionPerformed(ActionEvent awf)
        {
            form = new Form(300, 300);
            /**
            ListTable.setValueAt( Form.getFirstName(), (rowCounter+1), 0);
            ListTable.setValueAt( Form.getLastName(), (rowCounter+1), 1);
            ListTable.setValueAt( Form.getEmployeeID(), (rowCounter+1), 2);
            ListTable.setValueAt( Form.getEmployeeType(), (rowCounter+1), 3);
            ListTable.setValueAt( Double.toString(Form.getPay()), (rowCounter+1), 4);
            ListTable.setValueAt( Double.toString(Form.getTime()) , (rowCounter+1), 5);
            */
        }
    }
    private class EmployeeAddition implements ActionListener
    {
        public void actionPerformed(ActionEvent empadd)
        {
            String eFName= JOptionPane.showInputDialog(null, "Enter Employee's First Name.");
            String eLName= JOptionPane.showInputDialog(null, "Enter Employee's Last Name.");
            String eId= JOptionPane.showInputDialog(null, "Enter Employee's ID");
            String eType = JOptionPane.showInputDialog(null, "Enter Employee's Status (Full or Part time)");
            double pay = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter Employee Payrate by hour"));
            double time = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter Hours Completed by Employee for the week"));
            
            Employee newEmp = new Employee(eFName, eLName, eId, pay, time);
            employees.add(rowCounter, newEmp);
            
            ListTable.setValueAt( eFName , (rowCounter+1) , 0);
            ListTable.setValueAt( eLName , (rowCounter+1) , 1);
            ListTable.setValueAt( eId , (rowCounter+1) , 2);
            ListTable.setValueAt( eType, (rowCounter+1) , 3);
            ListTable.setValueAt( Double.toString(pay)+"/hr" , (rowCounter+1) , 4);
            ListTable.setValueAt( Double.toString(time) , (rowCounter+1) , 5);
            
            rowCounter += 1;
        }
    }
    
    private class VolunteerAddition implements ActionListener
    {
        public void actionPerformed(ActionEvent voladd)
        {
            
            /*
            String vFName = JOptionPane.showInputDialog(null,"Enter Volunteer's First Name.");
            String vLName = JOptionPane.showInputDialog(null, "Enter Volunteer's Last Name.");
            int hours = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Hours Completed by Volunteer."));
            *
            Employee newVolntr = new Employee(vFName, vLName, hours);
            employees.add(rowCounter, newVolntr);
            /* *
            ListTable.setValueAt(vFName, (rowCounter+1) ,0);
            ListTable.setValueAt(vLName, (rowCounter+1) ,1);
            ListTable.setValueAt("N/A", (rowCounter+1) ,2);
            ListTable.setValueAt("Volunteer", (rowCounter+1) ,3);
            ListTable.setValueAt("N/A", (rowCounter+1) ,4);
            ListTable.setValueAt(Integer.toString(hours), (rowCounter+1) ,5);
            /**/
            rowCounter += 1;
        }
    }
    private class Export_To_CSV implements ActionListener
    {
        public void actionPerformed(ActionEvent etc)
        {
            //JOptionPane.showMessageDialog(null, "Function Unavailable!");
            String personalTitle = JOptionPane.showInputDialog(null, "Enter desired title for file.");
            File theFile = new File(personalTitle+".csv");
            try
            {
                
                PrintWriter empList = new PrintWriter(theFile);
                
                String header = "First Name , Last Name , Employee ID , Employee Type , Employee Pay Rate , Employee Hours(Weekly)";
                empList.println(header);
                for(int i = 0; i < employees.size() ; i++)
                {
                    String stuff =    employees.get(i).getFirstName()+" , "+
                                        employees.get(i).getLastName()+" , "+
                                        employees.get(i).getEmpID()+" , "+
                                        employees.get(i).getEmpType()+" , "+
                                        Double.toString(employees.get(i).getPayRate())+" , "+
                                        Double.toString(employees.get(i).getPayTime());
                    
                    empList.println(stuff);
                }
                empList.close();
            }
            catch(Exception e)
            {
                System.out.print("Error While converting to CSV file");
                e.printStackTrace();
            }
            finally
            {
                //empList.close();
                //theFile.close();
            }
        }
    }
        
    private class Exit_Application implements ActionListener
    {
        public void actionPerformed(ActionEvent ea)
        {
            int ans = JOptionPane.showConfirmDialog( Exit_App, "Do you want to close the app?\n"
            +"If you do not export as CSV before you \n"
            +"close application all data will be lost!");
            
            if(ans == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }
    
    
}