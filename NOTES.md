# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

## Assumptions
Each user will have their own basket, which will only be acted upon by a single thread

## Design
In order to apply discounts, the basket has a DiscountCalculator which will calculate all the discounts, the discounts() method will call the calculateDiscounts() method of this class.
DiscountCalculator will have a list of discount strategies e.g. BuyOneGetOneFreeDiscount, it will apply the strategies on the items if discount product id and item product id match.

## TODO
Following strategies are outstanding:
- Buy two items for £1
- Buy three items for the price of two

In order to implement this, two further classes can be created which extend Discount and these can be added to the discountList in DiscountCalculator.

Tests also required for multi-threaded scenario with multiple baskets.

It would also have been good if there were specific unit tests to test the discount calculations only.