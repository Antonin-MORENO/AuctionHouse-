package AuctionHouse.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loadfile {
    
    public static void loadFurnitureFromFile(String filePath, ItemsList itemsList) {

        
        try {

            File f = new File(filePath);
            Scanner scanner = new Scanner(f);
            String[] validConditions = { "Mint condition", "Restored", "Needs restoring", "Good condition" };

            while (scanner.hasNextLine()) {

                
                String inputLine = scanner.nextLine();

                String[] fields = inputLine.split(",");

                if (fields.length < 10) {
                    System.err.println("Error: Missing fields in the input line: " + inputLine);
                    System.exit(1);

                }

                String condition = fields[1].trim();
                boolean isValidCondition = false;

                for (String validCondition : validConditions) {
                    if (validCondition.equals(condition)) {
                        isValidCondition = true;
                        break;
                    }
                }

                    
                if (!isValidCondition) {
                    System.err.println("Error: Invalid condition in the input line: " + inputLine);
                    System.exit(1); 
                }

                String objecttype = fields[0];
                int year = Integer.parseInt(fields[2].trim());
                String ownerName = fields[3].trim();
                double price = Double.parseDouble(fields[4].trim());

                if ("Furniture".equals(objecttype)) {


                    String furnitureType = fields[5].trim();
                    String style = fields[6].trim();
                    String makerName = fields[7].trim();
                    double length = Double.parseDouble(fields[8].trim());
                    double height = Double.parseDouble(fields[9].trim());
                    double depth = Double.parseDouble(fields[10].trim());
    
                    Furniture furniture = new Furniture(furnitureType, style, makerName, length, height, depth, year, ownerName, condition, price);
    
                    itemsList.addFurniture(furniture);
    
                    
                }


                else if ("Cars".equals(objecttype)) {

                    String make = fields[5].trim();
                    String model = fields[6].trim();
                    boolean serviced = Boolean.parseBoolean(fields[7].trim());

                    Cars cars = new Cars(ownerName, condition, year, price, make, model, serviced);
                    itemsList.addFurniture(cars);


                }

                else if ("Books".equals(objecttype)) {


                    String title = fields[5].trim();
                    String authorname = fields[6].trim();
                    String edition = fields[7].trim();
                    String genre = fields[8].trim();

                    Books books = new Books(title, authorname, edition, genre, ownerName, condition, year, price);
                    itemsList.addFurniture(books);
                }


                else if ("Coins".equals(objecttype)) {


                    String material = fields[5].trim();
                    String PlaceOfOrigins = fields[6].trim();
                    String value = fields[7].trim();

                    Coins coins = new Coins(material, PlaceOfOrigins, value, ownerName, condition, year, price);

                    itemsList.addFurniture(coins);

                }

                else {
                    System.err.println("Error: Invalid object in the input line: " + inputLine);
                    System.exit(1); 
                }

                }

                
                

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error : file not found " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error : wrong format in the file " + e.getMessage());
        }
        
        
        }



}
