package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kata.supermarket.Item;

public final class DiscountCalculator {
	
	private final List<Discount> discountList = new ArrayList<Discount>(); 

	public BigDecimal calculateDiscounts(List<Item> items) {
		BigDecimal totalDiscount = new BigDecimal(0);
		
		Map<Item, Long> itemQuanityMap = items.
				stream().collect(Collectors.groupingBy(p -> p, 
				         Collectors.counting()));
		
		Iterator<Map.Entry<Item, Long>> it = itemQuanityMap.entrySet().iterator();
	
		while (it.hasNext()) {
			Map.Entry<Item, Long> pair = it.next();
			for(Discount discount: discountList()) {
				Item item = pair.getKey();
				Long quantity = pair.getValue();
				if(discount.getProductId()==item.getProductId()) {
					totalDiscount = totalDiscount.add(discount.calculateDiscount(item, quantity));
				}
			}		   
		}
		
		return totalDiscount;
	}
	
	List<Discount> discountList() {
		return Collections.unmodifiableList(discountList);
	    }
	
	public void addDiscount(Discount discount) {
		discountList.add(discount);
	}

}
