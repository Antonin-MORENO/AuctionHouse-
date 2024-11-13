package AuctionHouse.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loadfile {
    
    public static void loadFurnitureFromFile(String filePath, ItemsList furnitureList) {

        
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
                
                String condition = fields[8].trim();
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
                
                String objectType = fields[0].trim();
                String style = fields[1].trim();
                String makerName = fields[2].trim();
                double length = Double.parseDouble(fields[3].trim());
                double height = Double.parseDouble(fields[4].trim());
                double depth = Double.parseDouble(fields[5].trim());
                int year = Integer.parseInt(fields[6].trim());
                String ownerName = fields[7].trim();
                double price = Double.parseDouble(fields[9].trim());

                Furniture furniture = new Furniture(objectType, style, makerName, length, height, depth, year, ownerName, condition, price);

                furnitureList.addFurniture(furniture);

                
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error : file not found " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error : wrong format in the file " + e.getMessage());
        }
        
        
        }



}
