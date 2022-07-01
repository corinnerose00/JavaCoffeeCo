public class HotWater extends CoffeeDecorator{
    public HotWater(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds a topping to the coffee order
     * @param coffee which is the coffee of the order
     */
    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
    }

    /**
     * Prints the coffee order
     * @return the hot water because it is not a coffee
     */
    @Override
    public String printCoffee() {
        return "Hot Water";
    }

    /**
     * Returns price of order
     * @return 0 because hot water is free
     */
    @Override
    public Double Cost() {
        return 0.0;
    }
}
