import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
 

public class ValueComparatorRev implements Comparator {
	Map map;
 
	public ValueComparatorRev(Map map) {
		this.map = map;
	}
 
	public int compare(Object keyA, Object keyB) {
		Comparable valueA = (Comparable) map.get(keyA);
		Comparable valueB = (Comparable) map.get(keyB);
		if (valueB.compareTo(valueA)==0) return 1;
		return -valueB.compareTo(valueA);
	}
}
