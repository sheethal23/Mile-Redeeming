import java.io.File;
import java.util.*;

public class MileRedemptionApp {

	public static void main(String[] args) throws Exception {          //main method that drives the app
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);


		System.out.print("Please enter the name of the file:");        //getting filename from the user 
		String file = input.next();
		File text = new File(file);

		Scanner fileScan = new Scanner(text);                           

		System.out.println("\n----------------------------------------------------------------");
		System.out.println("WELCOME TO THE JAVA AIRLINES FREQUENT FLYER MILES REDEMPTION APP");
		System.out.println("----------------------------------------------------------------\n");



		System.out.println("List of destinations you can travel: \n");

		MileRedeemer obj = new MileRedeemer();                         //object of the MileRedeemer class
		obj.readDestinationInfo(fileScan);                             //calling the method readDestinationInfo

		String destinations[] = obj.getDestinationNames();             //creating string array and storing destination names
		for(int i=0;i<destinations.length;i++)
		{
			System.out.println(destinations[i]);
		}


		char ip = 'y';
		Scanner scMiles = new Scanner( System.in );                                            
		Scanner scMonth = new Scanner( System.in );                     

		do {
			System.out.println("\n----------------------------------------------------------------\n");
			System.out.print("Please enter your client's accumulated Frequent Flyer Miles:");
			int ffmiles = scMiles.nextInt();                            //user input for miles 
			System.out.print("\nPlease enter your client's month of departure (1-12): ");
			int deptmonth = scMonth.nextInt();                          //user input for month
			System.out.println(" ");
			
			String results[] = obj.redeemCustomerMiles(ffmiles, deptmonth); //call method redeemCustomerMiles and storing them in string array
			
			for (int i = 0; i < results.length; i++)
			{    
				if (results[i] != null)
				{  	
					System.out.println(results[i]);	
				}
			}
			obj.redeemCustomerMiles(ffmiles, deptmonth);                //calling method
			System.out.printf("\nYour client's remaining Frequent Flyer Miles: %s\n",obj.getLeftoverMiles());
			System.out.println("\n----------------------------------------------------------------");	

			System.out.print("\nDo you want to continue (y/n)? ");     //asking to continue or not
			String ip1 = input.next(); 
			ip = ip1.charAt(0);
		}while(ip != 'n');
		input.close();
		scMiles.close();
		scMonth.close();
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("THANK YOU FOR USING THE JAVA AIRLINES FREQUENT FLYER MILES REDEMPTION APP");
		System.out.println("-------------------------------------------------------------------------");
	}

}
