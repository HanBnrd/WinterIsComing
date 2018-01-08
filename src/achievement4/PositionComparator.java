package achievement4;

import java.util.Comparator;

public class PositionComparator implements Comparator<PositionWithCost> {

	public int compare(PositionWithCost o1, PositionWithCost o2) {
		if (o1.getCost() < o2.getCost())
			return -1;
		if (o1.getCost() > o2.getCost())
			return 1;
		return 0;
	}

}
