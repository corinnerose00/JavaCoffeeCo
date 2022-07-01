public class Espresso extends CoffeeDecorator {
    public Espresso(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds a topping to the coffee order
     * @param coffee which is the coffee of the order
     */
    @Override
    public void addTopping(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     * Prints the coffee order
     * @return the coffee with the topping
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-espresso shot";
    }

    /**
     * Returns cost of coffee and topping combined
     * @return the cost of normal coffee plus the price of the topping
     */
    @Override
    public Double Cost() {
        return this.coffee.Cost() + 0.35;
    }
}
