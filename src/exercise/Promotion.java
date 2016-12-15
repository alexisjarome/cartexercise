package exercise;

import java.math.BigDecimal;


public abstract class Promotion implements PricingRule {
	
	protected String promotionCode;
	protected BigDecimal discount;
	
	public abstract void apply(Cart cart);

}
