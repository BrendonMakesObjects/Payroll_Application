class Employee
{
    private     double      payRate     =    0;
    
    private     double      payTime     =   0;

    private     String      empID       =   null;
    
    private     String      empType     =   null;
    
    private     String      firstName   =   null;
    
    private     String      lastName    =   null;
    
    
    Employee(){ }
    Employee(String firstName, String lastName, double payTime)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setPayTime(payTime);
    }
    Employee(String firstName, String lastName, String empID, double payRate, double payTime)
    {
        setPayRate(payRate);
        setPayTime(payTime);
        setEmpID(empID);
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    void setPayRate(double payRate)
    {
        this.payRate = payRate;
    }
    
    void setPayTime(double payTime)
    {
        this.payTime = payTime;
    }
    
    void setEmpID(String empID)
    {
        this.empID = empID;
    }
    
    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    void setEmpType(String empType)
    {
        this.empType = empType;
    }
    
    String getEmpType()
    {
        return empType;
    }
    
    String getLastName()
    {
        return lastName;
    }
    
    String getFirstName()
    {
        return firstName;
    }
    
    String getEmpID()
    {
        return empID;
    }
    
    double getPayTime()
    {
        return payTime;
    }
    
    double getPayRate()
    {
        return payRate;
    }
    
    double calculatePay()
    {
        return ( getPayRate() * getPayTime() );
    }
    
    //String toString(){ }
}