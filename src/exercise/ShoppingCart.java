package exercise;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
	static Product unliOneGb;
	static Product unliTwoGb;
	static Product unliFiveGb;
	static Product oneGbDataPack;
	static PricingRule rule1;
	static PricingRule rule2;
	static PricingRule rule3;
	static Promotion promo1;
	
	static Cart cart;
	
	static void setup() {
		cart = new Cart();
		unliOneGb = new Product();
		unliOneGb.setCode("ult_small");
		unliOneGb.setName("Unlimited 1GB");
		unliOneGb.setPrice(new BigDecimal(24.90));
		
		unliTwoGb = new Product();
		unliTwoGb.setCode("ult_medium");
		unliTwoGb.setName("Unlimited 2GB");
		unliTwoGb.setPrice(new BigDecimal(29.90));
		
		unliFiveGb = new Product();
		unliFiveGb.setCode("ult_large");
		unliFiveGb.setName("Unlimited 5GB");
		unliFiveGb.setPrice(new BigDecimal(44.90));
		
		oneGbDataPack = new Product();
		oneGbDataPack.setCode("1gb");
		oneGbDataPack.setName("1 GB Data-pack");
		oneGbDataPack.setPrice(new BigDecimal(9.90));		
		
		rule1 = new OneGbThreeForTwo();
		rule2 = new FiveGbBulk();
		rule3 = new TwoGbFreeOneGb();
		cart.addRule(rule1);
		cart.addRule(rule2);
		cart.addRule(rule3);
		promo1 = new ILoveAmaysim("I<3AMAYSIM", new BigDecimal(.10));
		cart.addPromotion(promo1);
	}
	
	static void performScenario1() {
		cart.clear();
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliFiveGb));
	}
	
	static void performScenario2() {
		cart.clear();
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliFiveGb));
		cart.addItem(new Item(unliFiveGb));
		cart.addItem(new Item(unliFiveGb));
		cart.addItem(new Item(unliFiveGb));
	}
	
	static void performScenario3() {
		cart.clear();
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(unliTwoGb));
		cart.addItem(new Item(unliTwoGb));
	}
	
	static void performScenario4() {
		cart.clear();
		cart.addItem(new Item(unliOneGb));
		cart.addItem(new Item(oneGbDataPack));
		cart.addPromoCode("I<3AMAYSIM");
	}
	
	static void print() {
		Map<String, List<Item>> items = cart.getItems();
		
		System.out.println("Cart total: " + cart.getTotalDiscountedAmount());		
		for (List<Item> productItems : items.values()) {
			System.out.println(productItems.size() + " x " + productItems.get(0).getProduct().getName());
		}
		
	}
	
	public static void main(String[] args) {
		setup();
		performScenario1();	
		print();
		performScenario2();
		print();
		performScenario3();
		print();
		performScenario4();
		print();
	}

}
