package AuctionHouse;

import java.util.Comparator;

public class ItemsComparator implements Comparator<Items> {
    // atributes
    private SortingCrit criteria;

    // constructor
    public ItemsComparator(SortingCrit criteria) {
        this.criteria = criteria;
    }

    // override the compare method
    @Override
    public int compare(Items i1, Items i2) {
        if (criteria == SortingCrit.Price) {
            return Double.compare(i1.get_startingprice(), i2.get_startingprice());
        }

        else if (criteria == SortingCrit.Year) {
            return Double.compare(i1.get_yearsoforigins().getMiddleEstimate(),
                    i2.get_yearsoforigins().getMiddleEstimate());
        }

        else {
            throw new IllegalArgumentException("Unsupported sorting criteria: " + criteria);

        }
    }

}
