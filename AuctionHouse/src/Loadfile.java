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
                String objecttype = fields[0];
                if ("Furniture".equals(objecttype) && fields.length < 12) {
                    System.err.println("Error: Missing fields in the input line for Furniture: " + inputLine);
                    System.exit(1);
                } else if ("Cars".equals(objecttype) && fields.length < 9) {
                    System.err.println("Error: Missing fields in the input line for Cars: " + inputLine);
                    System.exit(1);
                } else if ("Coins".equals(objecttype) && fields.length < 9) {
                    System.err.println("Error: Missing fields in the input line for Coins: " + inputLine);
                    System.exit(1);
                } else if ("Books".equals(objecttype) && fields.length < 10) {
                    System.err.println("Error: Missing fields in the input line for Books: " + inputLine);
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

                
                
                String ownerName = fields[4].trim();
                double price = Double.parseDouble(fields[5].trim());
                int lowEstimate = Integer.parseInt(fields[2].trim());
                int highEstimate = Integer.parseInt(fields[3].trim());
                YearEstimate year = new YearEstimate(lowEstimate, highEstimate);
                
                
                
                if ("Furniture".equals(objecttype)) {

                    String furnitureType = fields[6].trim();
                    String style = fields[7].trim();
                    String makerName = fields[8].trim();
                    double length = Double.parseDouble(fields[9].trim());
                    double height = Double.parseDouble(fields[10].trim());
                    double depth = Double.parseDouble(fields[11].trim());
    
                    Furniture furniture = new Furniture(furnitureType, style, makerName, length, height, depth, year, ownerName, condition, price);
    
                    itemsList.addFurniture(furniture);
    
                    
                }


                else if ("Cars".equals(objecttype)) {


                    String make = fields[6].trim();
                    String model = fields[7].trim();
                    boolean serviced = Boolean.parseBoolean(fields[8].trim());

                    Cars cars = new Cars(ownerName, condition, year, price, make, model, serviced);
                    itemsList.addFurniture(cars);


                }

                else if ("Books".equals(objecttype)) {



                    String title = fields[6].trim();
                    String authorname = fields[7].trim();
                    String edition = fields[8].trim();
                    String genre = fields[9].trim();

                    Books books = new Books(title, authorname, edition, genre, ownerName, condition, year, price);
                    itemsList.addFurniture(books);
                }


                else if ("Coins".equals(objecttype)) {



                    String material = fields[6].trim();
                    String PlaceOfOrigins = fields[7].trim();
                    String value = fields[8].trim();

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
