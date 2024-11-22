package Test;

import AuctionHouse.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ItemsListTest {

        private ItemsList itemsList;

        @BeforeEach
        void setup() {
                itemsList = new ItemsList();
        }

        @Test
        @DisplayName("Standard Case: Multiple Items")
        void testStandardCase() {
                itemsList.addFurniture(new Furniture("Chair", "Modern", "ABC", 1.2, 0.8, 0.5,
                                new YearEstimate(1900, 2010), "John", "Mint condition", 500));
                itemsList.addFurniture(new Furniture("Table", "Classic", "XYZ", 1.5, 0.9, 0.6,
                                new YearEstimate(1850, 1950), "Jane", "Restored", 700));
                itemsList.addFurniture(new Furniture("Desk", "Vintage", "LMN", 1.4, 0.7, 0.4,
                                new YearEstimate(1920, 1980), "Alice", "Needs restoring", 800));
                itemsList.addFurniture(new Furniture("Sofa", "Vintage", "ikea", 1.8, 1.7, 1.4,
                                new YearEstimate(1920, 1990), "Alice", "Needs restoring", 800));

                ArrayList<Items> top3 = itemsList.getTop3ItemsByEstimateDifference();
                assertEquals(3, top3.size(), "Should return 3 items");
                assertEquals(110, top3.get(0).get_yearsoforigins().getDifference(),
                                "First item's difference should be largest");
                assertEquals(100, top3.get(1).get_yearsoforigins().getDifference(),
                                "Second item's difference should be the second largest");
                assertEquals(70, top3.get(2).get_yearsoforigins().getDifference(),
                                "Third item's difference should be the third largest");
        }

        @Test
        @DisplayName("Empty List")
        void testEmptyList() {
                ArrayList<Items> top3 = itemsList.getTop3ItemsByEstimateDifference();
                assertEquals(0, top3.size(), "Should return empty list");
        }

        @Test
        @DisplayName("Identical Differences with More Than 3 Items")
        void testIdenticalDifferences() {

                itemsList.addFurniture(new Furniture("Chair", "Modern", "ABC", 1.2, 0.8, 0.5,
                                new YearEstimate(1900, 2000), "John", "Mint condition", 500));
                itemsList.addFurniture(new Furniture("Table", "Classic", "XYZ", 1.5, 0.9, 0.6,
                                new YearEstimate(1900, 2000), "Jane", "Restored", 700));
                itemsList.addFurniture(new Furniture("Desk", "Vintage", "LMN", 1.4, 0.7, 0.4,
                                new YearEstimate(1900, 2000), "Alice", "Good condition", 800));
                itemsList.addFurniture(new Furniture("Stool", "Minimalist", "DEF", 0.9, 0.6, 0.4,
                                new YearEstimate(1900, 2000), "Bob", "Restored", 300));

                ArrayList<Items> top3 = itemsList.getTop3ItemsByEstimateDifference();

                assertEquals(3, top3.size(), "Should return only 3 items");
        }

}
