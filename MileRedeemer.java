import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class MileRedeemer {
	ArrayList<Destination> destinationList = new ArrayList<Destination>();         //arraylist that stores destination objects
	private int remainmiles = 0;                                                   //instance variable

	public void setRemainmiles(int remainmiles) {                                  //setter for remainmiles
		this.remainmiles = remainmiles;
	}

	public ArrayList<Destination> getDestinationList() {                           //getter and setter for arraylist
		return destinationList;
	}

	public void setDestinationList(ArrayList<Destination> destinationList) {
		this.destinationList = destinationList;
	}
	
	/************************************************************
	 *                                                          *
	 *  Method Name: readDestinationInfo.                       *
	 *                                                          *
	 *  Purpose:To read the file from the user prompt and store *
	 *          them in the Destination objects. And objects    *
	 *          are stored in arraylist.                        *
	 *                                                          *
	 ************************************************************/  
	public void readDestinationInfo(Scanner fileScanner) throws Exception {
		while (fileScanner.hasNextLine()) {
			String list = fileScanner.nextLine();
			String[] List1 = list.split(";");

			Destination dest1 = new Destination();

			dest1.setDestname(List1[0]);
			dest1.setNormiles(Integer.parseInt(List1[1]));
			dest1.setFlyermiles(Integer.parseInt(List1[2]));
			dest1.setUpgrademiles(Integer.parseInt(List1[3]));
			String[] List2 = List1[4].split("-");
			dest1.setStartmonth(Integer.parseInt(List2[0]));
			dest1.setEndmonth(Integer.parseInt(List2[1]));

			destinationList.add(dest1);
		}
		fileScanner.close();
	}
	/************************************************************
	 *                                                          *
	 *  Method Name: getDestinationNames.                       *
	 *                                                          *
	 *  Purpose:arraylist to array and creates string array to  *
	 *          store the destination names by fetching them    *
	 *          from array.                                     *
	 *                                                          *
	 ************************************************************/
	public String[] getDestinationNames() {
		Destination[] destinationArray = (Destination[]) destinationList
				.toArray(new Destination[destinationList.size()]);
		String[] destname = new String[destinationList.size()];

		for (int i = 0; i < destinationArray.length; i++) {
			destname[i] = destinationArray[i].getDestname();
		}
		Arrays.sort(destname);
		return destname;
	}
	
	/************************************************************
	 *                                                          *
	 *  Method Name: redeemCustomerMiles.                       *
	 *                                                          *
	 *  Purpose: to redeem the miles and give the client his    *
	 *           possible round trip tickets and upgrading them *
	 *           to first class if necessary.                   *
	 *                                                          *
	 ************************************************************/
	public String[] redeemCustomerMiles(int miles, int month) {
		destinationList.sort(new MileageComparator());
		int i = 0, index=0;
		remainmiles = miles;
		
		String[] results = new String[destinationList.size()];
		Destination[] destinationsQualified = new Destination[destinationList.size()];
		
		for (Destination eachDestination : destinationList) {
			if (eachDestination.getStartmonth() <= month && eachDestination.getEndmonth() >= month) {
				if (eachDestination.getFlyermiles() < remainmiles) {
					results[i] = "* A trip to " + eachDestination.getDestname() + " in Economy Class";
					remainmiles -= eachDestination.getFlyermiles();
					destinationsQualified[index] = eachDestination;
					index++;
					
				}
			}else if (eachDestination.getNormiles() < remainmiles) {
				results[i] = "* A trip to " + eachDestination.getDestname() + " in Economy Class";
				remainmiles -= eachDestination.getNormiles();
				destinationsQualified[index] = eachDestination;
				index++;
			}
			i++;
		}
		
		for(int j=0; j<index;j++) {
			if(remainmiles > destinationsQualified[j].getUpgrademiles()) {
				remainmiles-=destinationsQualified[j].getUpgrademiles();
				results[j] = "* A trip to " + destinationsQualified[j].getDestname() + " in first Class";
			}
		}
		
		if(index==0) {
			results[0] = "*** Your client has not accumulated enough Frequent Flyer Miles ***";
		}else {
			results[0] = "Your client's Frequent Flyer Miles can be used to redeem the following tickets:" + "\n\n" + results[0];
		}
		
		return results;
	}
	/************************************************************
	 *                                                          *
	 *  Method Name: getLeftoverMiles.                          *
	 *                                                          *
	 *  Purpose: returns the remaining miles.                   *
	 *                                                          *
	 ************************************************************/
	public int getLeftoverMiles() {
		return remainmiles;
	}
	/************************************************************
	 *                                                          *
	 *  Class Name: MileageComparator.                          *
	 *                                                          *
	 *  Purpose:Compares the array with comparator.             *
	 *                                                          *
	 ************************************************************/
	public class MileageComparator implements Comparator<Destination> {
		public int compare(Destination d1, Destination d2) {
			return (d2.getNormiles() - d1.getNormiles());
		}
	}

}
