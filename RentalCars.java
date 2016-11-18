package rentalcars;



import java.io.File;
import java.io.IOException;
import java.util.Scanner;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.Gson;





public class RentalCars {

	private static String jsonFile = "C:/Users/dan/Documents/rental.txt";
	
	private static String type(String s){
		String[] type = {"Mini", "Eonomy", "Compact", "Intermediate", "Standard", "Full size", "Premium", "Luxury", "Special"};
		
		if (s.charAt(0)== 'M'){
		    return type[0];		    
		}else if (s.charAt(0)=='E'){
			return type[1];
		}else if (s.charAt(0)=='C'){
			return type[2];
		}else if (s.charAt(0)=='I'){
			return type[3];	
		}else if (s.charAt(0)=='S'){
			return type[4];
		}else if (s.charAt(0)=='F'){
			return type[5];
		}else if (s.charAt(0)=='P'){
			return type[6];
		}else if (s.charAt(0)=='L'){
			return type[7];
		}else {
			return type[8];
		}
		
	
	}
	
	private static String doors(String s){
		String[] doors = {"2 doors","4 doors", "5 doors","Estate","Convertible","SUV","Pick up","Passenger Van"};
		
        
		if (s.charAt(1)== 'B'){
		    return doors[0];		    
		}else if (s.charAt(1)=='C'){
			return doors[1];
		}else if (s.charAt(1)=='D'){
			return doors[2];
		}else if (s.charAt(1)=='W'){
			return doors[3];	
		}else if (s.charAt(1)=='T'){
			return doors[4];
		}else if (s.charAt(1)=='F'){
			return doors[5];
		}else if (s.charAt(1)=='P'){
			return doors[6];
		}else  {
			return doors[7];
		}
	
			
	}
	
	private static String transmission(String s){
		String[] transmission = {"Manual", "Automatic"};
        
		if (s.charAt(2)== 'M'){
		    return transmission[0];		    
		}else  {
			return transmission[1];
		}
		
			
	}
	
	private static String fuel(String s){
		String[] fuel = {"Petrol/no AC", "Petrol/AC"};
      
		if (s.charAt(3)== 'N'){
		    return fuel[0];		    
		}else {
			return fuel[1];
		}
		
			
	}
	
	private static int score(String s){
		if ((transmission(s) == "Manual") && (fuel(s) == "Petrol/no AC")){
			return 1;
		}else if ((transmission(s) == "Automatic") && (fuel(s) == "Petrol/no AC")){
			return 5;
		}else if ((transmission(s) == "Manual") && (fuel(s) == "Petrol/AC")){
			return 3;
		}else {
			return 7;
		}
	
      
		
		
			
	}

	public static void main(String[] args) {
		

		 Gson gson = new Gson();

		    try {

		        Scanner scanner = new Scanner( new File("C:/Users/dan/Documents/rental.txt") );
		        String text = scanner.useDelimiter("\\A").next();
		        
		        scanner.close();
                JsonObject result = gson.fromJson(text,JsonElement.class).getAsJsonObject();
		        
                JsonElement parent = (JsonElement) result.get("Search");
            
                
                JsonArray newJson = (JsonArray)parent.getAsJsonObject().getAsJsonArray("VehicleList");
                
                System.out.println("PART1");
                
                for (int i =0; i < newJson.size(); i++){
                	
                	
                	double u = newJson.get(i).getAsJsonObject().get("price").getAsDouble();
                	System.out.printf("%-20s%-20s\n",newJson.get(i).getAsJsonObject().get("name"), u);
                }
                System.out.println("---------------------------------------------------------------------");
                for (int i =0; i < newJson.size(); i++){
                	String sipp = newJson.get(i).getAsJsonObject().get("sipp").getAsString();
                	String name = newJson.get(i).getAsJsonObject().get("name").getAsString();
                	
                	System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",name,type(sipp),doors(sipp), transmission(sipp),fuel(sipp));
                }
                
                System.out.println("---------------------------------------------------------------------");
               
                for (int i =0; i < newJson.size(); i++){
                	String sipp = newJson.get(i).getAsJsonObject().get("sipp").getAsString();
                	double rating = newJson.get(i).getAsJsonObject().get("rating").getAsDouble();
                	String name = newJson.get(i).getAsJsonObject().get("name").getAsString();
                	String supplier = newJson.get(i).getAsJsonObject().get("supplier").getAsString();
                	System.out.printf("%-20s%-20s%-20s%-20s\n",name,type(sipp),supplier,rating);
                }
                
		        
                System.out.println("---------------------------------------------------------------------");
                for (int i =0; i < newJson.size(); i++){
                	String name = newJson.get(i).getAsJsonObject().get("name").getAsString();
                	double rating = newJson.get(i).getAsJsonObject().get("rating").getAsDouble();
                	String sipp = newJson.get(i).getAsJsonObject().get("sipp").getAsString();
                	int vehicleScore = score(sipp);
                	double sum_Of_Score = vehicleScore + rating;
                	
                	System.out.printf("%-20s%-20s%-20s%-20s\n",name,vehicleScore,rating,sum_Of_Score);
                }
                

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

}
