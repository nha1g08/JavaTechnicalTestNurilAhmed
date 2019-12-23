package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import kata.supermarket.discount.BuyOneGetOneFreeDiscount;
import kata.supermarket.discount.Discount;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        Discount ryvitaDiscount = new BuyOneGetOneFreeDiscount(3);
        basket.addDiscount(ryvitaDiscount);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                aSingleRyvitaPricedPerUnit(),
                multipleRyvitaPricedPerUnit(),
                threeRyvitaPricedPerUnit(),
                fiveRyvitaAndPintofMilkPricedPerUnit()
        );
    }
    
    private static Arguments aSingleRyvitaPricedPerUnit() {
		return Arguments.of("1 packs of Ryvita with buy one get one free offer", "1.25",
                Arrays.asList(aPackOfRyvita()));
	}

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }
    
    private static Arguments multipleRyvitaPricedPerUnit() {
    	return Arguments.of("2 packs of Ryvita with buy one get one free offer", "1.25",
                Arrays.asList(aPackOfRyvita(), aPackOfRyvita()));
	}
    
    private static Arguments threeRyvitaPricedPerUnit() {
    	return Arguments.of("3 packs of Ryvita with buy one get one free offer", "2.50",
                Arrays.asList(aPackOfRyvita(), aPackOfRyvita(), aPackOfRyvita()));
	}
    
    private static Arguments fiveRyvitaAndPintofMilkPricedPerUnit() {
    	return Arguments.of("5 packs of Ryvita with buy one get one free offer and a pint of milk", "4.24",
                Arrays.asList(aPackOfRyvita(), 
                		aPackOfRyvita(), 
                		aPackOfRyvita(),
                		aPackOfRyvita(),
                		aPackOfRyvita(),
                		aPintOfMilk()
                		));
	}
    

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), 1).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), 2).oneOf();
    }
    
    private static Item aPackOfRyvita() {
        return new Product(new BigDecimal("1.25"), 3).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}