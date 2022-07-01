import java.util.Objects;
public class WhippedCream extends CoffeeDecorator{

    public WhippedCream(Coffee coffee) {
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
     * @return the coffee with the topping
     */
    @Override
    public String printCoffee() {
        if (this.coffee instanceof WhippedCream) {
            return "1";
        }
        else {
            return this.coffee.printCoffee() + "-whipped cream";
        }
    }

    /**
     * Returns cost of coffee and topping combined
     * @return the cost of normal coffee plus the price of the topping
     */
    @Override
    public Double Cost() {
        if (this.coffee instanceof WhippedCream) {
            return 1.0;
        }
        else {
            return this.coffee.Cost() + 0.10;
        }
    }
}
