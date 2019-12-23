package kata.supermarket.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kata.supermarket.Item;

public class BuyOneGetOneFreeDiscount extends Discount {

	public BuyOneGetOneFreeDiscount(Integer productId) {
		super(productId);
	}

	@Override
	public BigDecimal calculateDiscount(Item item, Long quantity) {
		Long effectiveItems = Long.divideUnsigned(quantity, 2) + Long.remainderUnsigned(quantity, 2);
		BigDecimal effectivePrice = item.price().
				multiply(BigDecimal.valueOf(effectiveItems)).
				setScale(2, RoundingMode.HALF_UP);
		BigDecimal totalDiscount = item.price().multiply(BigDecimal.valueOf(quantity)).subtract(effectivePrice);
		return totalDiscount;
	}

}
