package kata.supermarket.discount;

import java.math.BigDecimal;

import kata.supermarket.Item;

public abstract class Discount {
	
	private final Integer productId;

	public Integer getProductId() {
		return productId;
	}

	public Discount(Integer productId) {
		this.productId=productId;
	}
	
	public abstract BigDecimal calculateDiscount(Item item, Long quantity);
	
	

}
