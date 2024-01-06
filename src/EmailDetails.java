import java.util.Scanner;

public class EmailDetails
{
    //variables
    private String name;
    private String surname;
    private String school;
    private String emailAddress;
    private int mailmax = 1000;
    private int passwordLength = 12;
    private String altEmail;
    private String password;
    private String schoolSuffix = "varsitycollege.edu.za";

    public String finalDisplay()
    {
        return "FULLNAME: " + name + " " + surname
                + "\nSCHOOL EMAIL: " + emailAddress +
                "\nMAILBOX MAXIMUM: " + mailmax + "mb";
    }
    //Constructor receives for firstname and lastname
    public EmailDetails(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
        System.out.println("EMAIL HAS BEEN CREATED: " + this.name + " " + this.surname);

        //call method asking for department
        this.school = setSchool();
        System.out.println("School: " + this.school);

        //call method returning password
        this.password = passwords(passwordLength);
        System.out.println("Your password is: " + this.password );

   //combine elements to generate an email
     emailAddress = name.toLowerCase() + "." + surname.toLowerCase() + "@" + school + schoolSuffix;
        System.out.println("Your email is: " + emailAddress);
    }

    //ask for department
    private String setSchool()
    {
        System.out.print("Select the school you're in: \n1: accounting and finance\n2: education\n3: engineering\n4: humanities and social sciences\n5: IT\n6: law\n7: management\n");

        Scanner in = new Scanner(System.in);
        int Choice = in.nextInt();

        if(Choice == 1)
        {
            return "Accounting and Finance";
        }
        else if (Choice == 2)
        {
         return "Education"  ;
        }
        else if (Choice == 3)
        {
            return "Engineering";
        }
        else if (Choice == 4)
        {
            return "Humanities and Social Sciences";
        }
        else if (Choice == 5)
        {
            return "IT";
        }
        else if (Choice == 6)
        {
            return "Law";
        }
        else if (Choice == 7)
        {
            return "Management";
        }
        else
        {
            return "";
        }

    }

    //generate password
    private String passwords(int length)
    {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUV0123456789~`!@#$%^&*()_-+={[}]|:;'<,>.?/' ;";

     char[]password = new char[length];

     for(int i = 0; i < length; i++)
     {
         int randoms = (int)(Math.random() * passwordSet.length());
        password[i] = passwordSet.charAt((randoms));
     }
     return new String(password);
    }

    //set mailbox capacity
public void setMailMax(int Max)
{
    this.mailmax = Max;
}

    //set the alternate email
    public void setAlternateEmailAddy(String altMail)
    {
        this.altEmail = altMail;
    }

    //change password
    public void changePasswoed(String password)
    {
        this.password = password;
    }

    public int getMailMax()
    {
        return mailmax;
    }

    public String getAltEmail()
    {
        return altEmail;
    }

    public String getPassword()
    {
        return password;
    }


}