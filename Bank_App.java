import java.util.Scanner;

public class Bank_App {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String clear = "\033[H\033[2J";
    public static final String COLOR_BLUE_BOLD = "\033[1;34m";
    public static final String COLOR_YELLOW_BOLD = "\033[1;33m";
    public static final String COLOR_RESER = "\033[0m";
    public static final String COLOR_RED = "\033[31m";
    public static boolean valid = true;
    public static String id = "";
    public static String deposists ="";
    public static String[][] customers = new String[0][];

    public static void main(String[] args) {

        final String DASHBOARD = "Welcome Smart Banking App";
        final String OPEN_NEW_ACC = "Open New Account";
        final String REMOVE_ACCOUNT = "Drop Existing Account";
        final String CHECK_ACC_BALANCE = "Check Account Balance";
        final String DEPOSIT= "Deposit Money";
        final String WITHDRAW= "Withdraw Money";
        final String TRANSFER= "Transfer Money";
        String screen = DASHBOARD;
        String mydeposists ="";

        do{
            final String Apptitle = String.format(" %s%s%s \n", COLOR_BLUE_BOLD,screen,COLOR_RESER );
            System.out.println(clear);
            System.out.printf("-".repeat(55).concat("\n"));
            System.out.printf(" ".repeat(12).concat(Apptitle));
            System.out.printf("-".repeat(55).concat("\n"));
            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1] Open New Account");
                    System.out.println("\t[2] Deposit Money");
                    System.out.println("\t[3] Withdraw Money");
                    System.out.println("\t[4] Transfer Money");
                    System.out.println("\t[5] Check Account Balance");
                    System.out.println("\t[6] Drop Existing Account");
                    System.out.println("\t[7] Exit");
                    System.out.printf("-".repeat(55).concat("\n"));
                    System.out.print("\tEnter an Option to continue: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option){
                        case 1: screen = OPEN_NEW_ACC; break;
                        case 2: screen = DEPOSIT; break;
                        case 3: screen = WITHDRAW; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = CHECK_ACC_BALANCE; break;
                        case 6: screen = REMOVE_ACCOUNT; break;
                        case 7: System.out.println("Have a nice day!!!"); System.exit(0);
                        default: continue;
                    }
                    break;


                case OPEN_NEW_ACC :
                    System.out.printf("Account Number: SDB-%05d \n",(customers.length +1));
                 

                    String accname;
                    double dipAmount;

                    do{
                        valid = true;
                        System.out.print("Enter Customer Name: ");
                    
                    accname = scanner.nextLine().strip();
                    if(accname.isBlank()){
                        System.out.printf("%sNames Can't be Empty%s \n",COLOR_RED,COLOR_RESER);
                        valid = false;
                        continue;
                    }

                    for (int i = 0; i < accname.length(); i++) {
                        if(!(Character.isLetter(accname.charAt(i))|| Character.isSpaceChar(accname.charAt(i)))){
                            System.out.printf("%sInvalid Name%s \n",COLOR_RED,COLOR_RESER);
                            valid = false;
                            break;

                        }
                    }
                    boolean index = true;
                    do{
                        
                    System.out.printf("Enter Initial Deposit Amount: Rs.");
                    dipAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if(!(dipAmount>5000) ){
                        System.out.printf("%sShould be Deposit more than Rs.5000.00%s \n",COLOR_RED,COLOR_RESER);
                        index = false;
                        valid = false;
                        continue;}
                    else{
                        index = true;
                    }
                    mydeposists =  String.format("Rs.%,.2f", dipAmount);
                    deposists  = dipAmount+"";
                    
                    }while(!index);
                    String newid;

                    String[][] newCustomers = new String[customers.length +1][3];

                    for (int i = 0; i < customers.length; i++) {
                        id = String.format("SDB-%05d",(i+1));
                        customers[i][0] = id;
                        newCustomers[i] = customers[i];
                        }

                    newid = String.format("SDB-%05d",(customers.length+1));
                      
                    newCustomers[newCustomers.length-1][0] = newid;
                    newCustomers[newCustomers.length-1][1] = accname;
                    newCustomers[newCustomers.length-1][2] = deposists;
    
                    customers = newCustomers;

                    System.out.printf("\033[1;32m%s\033[0m\033[32m your new accounthas been added successfully!.\nInitial amount is %s\n\033[0mDo you need to add another name [Y/n]? ",accname,mydeposists);
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }while(!valid);
                
                break;

                case DEPOSIT:
                    boolean index3 = true;
                do{

                    String DepoAccId = IdValidator(id); 

                    for (int i = 0; i < customers.length; i++) {
                        String newAccID = customers[i][0];
 
                        if(newAccID.equals(DepoAccId))
                        {
                        //System.out.printf("Your Account name: %s\n Initial Deposit: %s\n",customers[i][1],customers[i][2]);
                        boolean index =true;  
                        do{
                            
                            System.out.printf("Enter Deposit Amount: Rs.");
                            dipAmount = scanner.nextDouble();
                            scanner.nextLine();
                            if(!(dipAmount>1500.00) ){
                                System.out.printf("%sMinimum Deposit  is Rs.1500.00%s \n",COLOR_RED,COLOR_RESER);
                                index = false;
                                valid = false;
                                continue;}
                            else{
                                index = true;
                            }

                        }while(!index);

                        double newAccointbalance = dipAmount + Double.valueOf(customers[i][2]);
                        customers[i][2] = newAccointbalance + "";
                        System.out.println(String.format("Rs.%,.2f", newAccointbalance));
                        index3 = false;

                        }
                    }

                    System.out.println("Do you need to check another Account [Y/n]? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }while(index3);

                break;

                case CHECK_ACC_BALANCE:

                boolean index2 = true;

                do{
                        String newdeposists = "";
                        String AccId = IdValidator(id);

                        for (int j = 0; j < customers.length; j++) {
                            if(AccId.equals(customers[j][0])){
                                double withdraAmount = Double.valueOf(customers[j][2]);

                                double newAmount = withdraAmount - 500.00;
                                if(newAmount>500.00){
                                newdeposists  = String.format("Rs.%,.2f", newAmount);
                                System.out.printf("\tYour withdrawable Balanace is: %s\n", newdeposists);
                                index2 = true;
                                continue;
                                } else{ 
                                    System.out.printf("\t%sYour withdrawable Balanace is less than Rs.500.00. Can't withdraw from this account%",COLOR_RED,COLOR_RESER );
                                    index2 = false;
                                    
                                }
                            }
                        }
                        
                        System.out.println("Do you need to check another Account [Y/n]? ");
                        if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                        screen = DASHBOARD;

                        break;
                }while(index2);

                break;

                case WITHDRAW:

                boolean index4 = true;
                do{

                    String DepoAccId = IdValidator(id); 

                    for (int i = 0; i < customers.length; i++) {
                        String newAccID = customers[i][0];
 
                        if(newAccID.equals(DepoAccId))
                        {

                        boolean index =true;  
                        do{
                            
                            System.out.printf("Enter Withdraw Amount: Rs.");
                            dipAmount = scanner.nextDouble();
                            scanner.nextLine();
                            if(!(dipAmount>100.00) ){
                                System.out.printf("%sMinimum withdrawable amount  is Rs.100.00%s \n",COLOR_RED,COLOR_RESER);
                                index = false;
                                valid = false;
                                continue;}
                            else{
                                index = true;
                            }

                        }while(!index);

                        double newAccointbalance = Double.valueOf(customers[i][2]) - dipAmount;
                        if(newAccointbalance > 500.00){
                            customers[i][2] = newAccointbalance + "";
                            System.out.println(String.format("Current Account Balance is Rs.%,.2f\n", newAccointbalance));
                            index3 = false;}
                        else{
                            System.out.println(String.format("Account Balanse shold have more than Rs.500.00\n", customers[i][2]));
                            index3 = false;
                            break;
                        }

                        }
                    }

                    System.out.println("Do you need to withdroaw again [Y/n]? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }while(index4);

                break;

            case REMOVE_ACCOUNT:

                boolean index5 = true;
                int deleteIndex = 0;

                do{

                    String delAccId = IdValidator(id); 

                    for (int i = 0; i < customers.length; i++) {
                        String newAccID = customers[i][0];
 
                        if(newAccID.equals(delAccId))
                        {
                            deleteIndex = i;
                            break;
                        }else{
                            System.out.printf("%sAccoun number not in the System%s \n",COLOR_RED,COLOR_RESER);
                            index5 = false;
                            break;
                            }
                    
                        }
                    String[][] newCustomers = new String[customers.length +1][3];

                    for (int i = 0; i < customers.length-2; i++) {
                        if(i == deleteIndex){
                            continue;
                        }else{
                            newCustomers[i][0] = customers[i][0];
                            newCustomers[i][1] = customers[i][1];
                            newCustomers[i][2] = customers[i][2];

                        }
                    }
    
                    customers = newCustomers;
                    System.out.printf("%sMinimum Deposit  is Rs.1500.00%s \n",COLOR_YELLOW_BOLD,COLOR_RESER);
                    System.out.println("Do you want to Delete another acoount[Y/n]? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

            }while(index5);
            
        }
                
        }while(true);
      
    }

    public static String IdValidator(String id1){
        String id;
        do {
            valid = true;
            System.out.print("Enter Account Number: "); 
            id = scanner.nextLine().toUpperCase().strip();
            if (id.isBlank()){
                System.out.printf("%sAccoun number can't be blank%s \n",COLOR_RED,COLOR_RESER);
                valid = false;
            }else if (!id.startsWith("SDB-") || !(id.length() == 9)){
                System.out.printf("%sInvalid Account Number%s \n",COLOR_RED,COLOR_RESER);
                valid = false;
            }else{
                String number = id.substring(4);
                for (int i = 0; i < number.length(); i++) {
                    if (!Character.isDigit(number.charAt(i))){
                        System.out.printf("%sInvalid Account Number%s \n",COLOR_RED,COLOR_RESER);
                        valid = false;
                        break;
                    }
                }
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i][0].equals(id)){
                        System.out.printf("\tAccount name: %s \n", customers[i][1]);
                        double amount = Double.valueOf(customers[i][2]);
                        String newdeposists  = String.format("Rs.%,.2f", amount);
                        System.out.printf("\tCurrent Balance: %s \n", newdeposists);
                        valid = false;
                        break;

                    }else{
                         System.out.printf("%sThis account has Deleted%s \n",COLOR_RED,COLOR_RESER);
                         valid = false;
                    
                    break;

                    }
                }
            }
        }while (valid);

        return id;
    }
}