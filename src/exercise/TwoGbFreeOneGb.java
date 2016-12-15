package exercise;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TwoGbFreeOneGb implements PricingRule {

	@Override
	public void apply(Cart cart) {
		Map<String, List<Item>> items = cart.items;
		if (items == null) {
			return;
		}
		
		List<Item> productItems = items.get("ult_medium");
		if (productItems != null) {
			Item freeItem = new Item(ShoppingCart.oneGbDataPack);
			freeItem.setFree(true);
			for (Item item : productItems) {
				cart.addItem(freeItem);
			}
		}
	}

}
