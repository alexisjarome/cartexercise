package exercise;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class FiveGbBulk implements PricingRule {
	private static final int MIN_PRODUCT_COUNT = 3;
	private static final BigDecimal DISCOUNTED_PRICE = new BigDecimal(39.90);

	@Override
	public void apply(Cart cart) {
		Map<String, List<Item>> items = cart.items;
		if (items == null) {
			return;
		}
		
		List<Item> productItems = items.get("ult_large");
		if (productItems != null && productItems.size() > 3) {
			for (Item item : productItems) {
				item.setDiscountedPrice(DISCOUNTED_PRICE);
			}
		}		
	}

}
