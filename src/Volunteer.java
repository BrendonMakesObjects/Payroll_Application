class Volunteer
{
    private     String      firstName   =   null;
    
    private     String      lastName    =   null;
    
    private     int         hoursComp   =   0;
    
    Volunteer(){ }
    
    Volunteer(String firstName, String lastName, int hoursComp)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setHoursCompleted(hoursComp);
    }
    
    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    void setHoursCompleted(int hoursComp)
    {
        this.hoursComp = hoursComp;
    }
    
    int getHoursCompleted()
    {
        return hoursComp;
    }
    
    String getLastName()
    {
        return lastName;
    }
    
    String getFirstName()
    {
        return firstName;
    }
    
    //String toString(){    }
}