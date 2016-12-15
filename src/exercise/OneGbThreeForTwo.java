package exercise;

import java.util.List;
import java.util.Map;


public class OneGbThreeForTwo implements PricingRule {

	@Override
	public void apply(Cart cart) {
		Map<String, List<Item>> items = cart.items;
		if (items == null) {
			return;
		}
		
		List<Item> productItems = items.get("ult_small");
		if (productItems != null && productItems.size() >= 3) {
		    for (int start = 0; start < productItems.size(); start += 3) {
		    	
		        int end = Math.min(start + 3, productItems.size());
		        List<Item> sublist = productItems.subList(start, end);
		        if (sublist.size() == 3) {
		        	sublist.get(2).setFree(true);
		        }
		    }
		}
	}

}
