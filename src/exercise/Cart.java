package exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	Map<String, List<Item>> items;
	List<PricingRule> pricingRules;
	List<Promotion> promotions;
	List<BigDecimal> discounts;
	List<String> promoCodes;
	
	public Map<String, List<Item>> getItems() {
		return items;
	}

	public void addItem(Item item) {
		if (items == null) {
			items = new HashMap<String, List<Item>>();
		}
		
		String productCode = item.getProduct().getCode();
		if (items.containsKey(productCode)) {
			List<Item> itemProducts = items.get(productCode);
			itemProducts.add(item);
		} else {
			items.put(productCode, new ArrayList<Item>(Arrays.asList(item)));
		}
	}
	
	public void addRule(PricingRule pricingRule) {
		if (pricingRules == null) {
			pricingRules = new ArrayList<PricingRule>();
		}
		
		if (!pricingRules.contains(pricingRule)) {
			pricingRules.add(pricingRule);		}
	}
	
	public void addPromotion(Promotion promotion) {
		if (promotions == null) {
			promotions = new ArrayList<Promotion>();
		}
		
		if (!promotions.contains(promotion)) {
			promotions.add(promotion);		}
	}
	
	public void addPromoCode(String promoCode) {
		if (promoCodes == null) {
			promoCodes = new ArrayList<String>();
		}
		
		if (!promoCodes.contains(promoCode)) {
			this.promoCodes.add(promoCode);
		}
	}
	
	public void clear() {
		if (items != null) {
			this.items.clear();	
		}		
	}
	
	public void addDiscount(BigDecimal discount) {
		if (discounts == null) {
			discounts = new ArrayList<BigDecimal>();
		}
		
		discounts.add(discount);
	}
	
	public int geTotalItems() {
		if (items == null || items.isEmpty()) {
			return 0;
		} else {
			return items.size();
		}
	}
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = new BigDecimal(0);
		
		for (List<Item> listItems : items.values()) {
			for (Item item : listItems) {
				total.add(item.getProduct().getPrice());
			}
		}
		
		return total;	
	}
	
	public BigDecimal getTotalDiscountedAmount() {
		BigDecimal totalDiscounted = new BigDecimal(0);
		for (PricingRule pricingRule : this.pricingRules) {
			pricingRule.apply(this);
		}
		
		for (List<Item> listItems : items.values()) {
			for (Item item : listItems) {			
				if (!item.isFree()) {
					if (item.getDiscountedPrice() != null) {
						totalDiscounted = totalDiscounted.add(item.getDiscountedPrice());							
					} else {
						totalDiscounted = totalDiscounted.add(item.getProduct().getPrice());
					}
				}				
			}					
		}
		
		for (Promotion promotion : this.promotions) {
			promotion.apply(this);
		}
		
		if (discounts != null) {
			for (BigDecimal discount : discounts) {
				totalDiscounted = totalDiscounted.subtract(totalDiscounted.multiply(discount));
			}
		}
		
		return totalDiscounted;
	}

}
