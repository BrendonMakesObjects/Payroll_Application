/**
*This payroll application will store all employee's and volunteers data.
*Will allow user to enter amount of pay and time an employee has worked
*Will display the full set of all employee's & volunteers data when requested
*also has the option of placing the data requested in a csv file.
**/

class Payroll
{
    public static void main(String[] args)
    {
        new PayrollWindow("PayRoll Tracker", 480, 560);
    }
}