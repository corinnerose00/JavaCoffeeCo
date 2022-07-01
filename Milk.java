public class Milk extends CoffeeDecorator{
    public Milk(Coffee coffee) {
        super(coffee);
    }

    /**
     * Prints the coffee order
     * @return the coffee with the topping
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + "-milk";
    }

    /**
     * Adds a topping to the coffee order
     * @param coffee which is the coffee of the order
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    /**
     * Returns cost of coffee and topping combined
     * @return the cost of normal coffee plus the price of the topping
     */
    @Override
    public Double Cost() {
        return this.coffee.Cost() + 0.15;
    }
}
