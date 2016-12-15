package exercise;

import java.math.BigDecimal;

public class ILoveAmaysim extends Promotion {
	public ILoveAmaysim(String promoCode, BigDecimal discount) {
		this.promotionCode = promoCode;
		this.discount = discount;
	}
	
	@Override
	public void apply(Cart cart) {
		if (cart.promoCodes != null && cart.promoCodes.contains(this.promotionCode)) {
			cart.addDiscount(this.discount);
		}
		
	}

}
