public class BlackCoffee extends CoffeeDecorator{
    public BlackCoffee(Coffee coffee) {
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
     * @return returns the coffee using printCoffee() method
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee();
    }

    /**
     * Prints instructions on how to pour cofee
     */
    public void instructions() {
        System.out.println("Pour coffee from pot into cup");
    }

    /**
     * Returns cost of coffee
     * @return the cost of the coffee
     */
    @Override
    public Double Cost() {
        return this.coffee.Cost();
    }
}
