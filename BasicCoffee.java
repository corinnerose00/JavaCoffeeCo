public class BasicCoffee implements Coffee{
    /**
     * Adds topping
     * @param coffee the coffee of the order
     */
    @Override
    public void addTopping(Coffee coffee) {
    }

    /**
     * Prints the coffee order
     * @return "Black coffee" prints Black coffee because its a simple coffee
     */
    @Override
    public String printCoffee() {
        return "Black coffee";
    }

    /**
     * Returns the cost of the coffee
     * @return 0.85 which is the cost of a black coffee
     */
    public Double Cost() {
        return 0.85;
    }
}
