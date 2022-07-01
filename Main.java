/**
 * CS160L-01
 * @author Corinne Robison
 */
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Main class that drives the entire program. This class creates coffee orders, reads and updates inventory,
 * and creates a log of all orders. The whole program provides ways for a user to create coffee orders and
 * add toppings.
 * CS160L-01
 * @author Corinne Robison
 */
public class Main {
    public static int[] inventory;
    private static File inventoryFile = new File("Inventory.txt");

    /**
     * Main method that drives entire program. Launches application and keeps scanner open to choose options
     * @param args
     */
    public static void main(String[] args) {
        Scanner CafeApplication = new Scanner(System.in);
        ArrayList<String> item = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<>();
        Scanner userOrders = new Scanner(System.in);
        Stack<String> orders = new Stack<String>();

        System.out.println("Cafe Application Running...");
        int input = 0;
        while (input != 1) {
            System.out.println("Press 1 : Read Inventory");
            System.out.println("Press 2 : Create Coffee Order");
            System.out.println("Press 3 : Update Inventory");
            System.out.println("Press 4 : Update log file");
            System.out.println("Press 5 : Exit the application");
            switch (CafeApplication.nextLine()) {
                case "1":
                    inventoryReader();
                    break;
                case "2":
                    if (inventory[0] != 0 ) {
                        System.out.println("Coffee order created. Select toppings for the first coffee:");
                        String line = "yes";
                        do {
                            temp2 = CreateOrder();
                            inventory[0] = inventory[0] - 1;
                            for (int i = 0; i < temp2.size(); i = i+2) {
                                item.add(temp2.get(i));
                            }
                            for (int j = 1; j <= temp2.size(); j = j+2) {
                                price.add(Double.valueOf(temp2.get(j)));
                            }
                            if (inventory[0] != 0) {
                                System.out.println("Do you want to add another coffee to this order? - yes or no?");
                            } else {
                                System.out.println("Order completed. No more coffees");
                                break;
                            }
                        }
                        while (!(line = userOrders.nextLine()).equals("no"));
                    } else {
                        System.out.println("Out of coffee. Visit us later.");
                    }
                    orders.push(PrintOrder(item, price));
                case "3":
                    inventoryWriter(inventory);
                    break;
                case "4":
                    logWriter(orders);
                    break;
                case "5":
                    inventoryWriter(inventory);
                    logWriter(orders);
                    input = 1;
                    break;
                default:
                    System.out.println("Invalid Selection. Please Try Again");
            }
        }
    }

    /**
     * Prints the current order
     * @param item which is ArrayList of each item in an order
     * @param price which is ArrayList of price of each item in an order
     * @return the entire order(recipt) in the format it should be printed in
     */
    public static String PrintOrder(ArrayList<String> item, ArrayList<Double> price) {
        StringBuilder str = new StringBuilder();
        double total = 0.0;
        str.append("\n RECEIPT");
        for (int i = 1; i <= item.size(); i++) {
            str.append("\n Item " + i + ": " + item.get(i-1) + " | cost:" + price.get(i-1));
            total = total + price.get(i-1);
        }
        str.append("\n TOTAL COST OF ORDER: " + total);
        return str.toString();
    }

    /**
     * Creates the coffee orders. Allows user to choose options. Checks inventory and updates the array of
     * the inventory. Adds the options to a coffee order
     * @return the coffee order with the toppings
     */
    public static ArrayList<String> CreateOrder() {
        Scanner userFeedback = new Scanner(System.in);
        ArrayList<String> coffeeOrder = new ArrayList<String>();
        Coffee basicCoffee = new BasicCoffee();
        int in = 0;
        while (in != 1) {
            System.out.println("Enter the following values to add toppings: 1.) milk, 2.) hot water, 3.) espresso, " +
                    "4.) sugar, 5.) whipped cream, 6.) foam, e - to complete order");
            switch (userFeedback.nextLine()) {
                case "1":
                    if (inventory[1] != 0) {
                        basicCoffee = new Milk(basicCoffee);
                        inventory[1] = inventory[1] - 1;
                    } else {
                        System.out.println("Out of milk. Try a different topping");
                    }
                    break;
                case "2":
                    if (inventory[2] != 0) {
                        basicCoffee = new HotWater(basicCoffee);
                        inventory[2] = inventory[2] - 1;
                    } else {
                        System.out.println("Out of hot water. Try a different topping");
                    }
                    break;
                case "3":
                    if (inventory[3] != 0) {
                        basicCoffee = new Espresso(basicCoffee);
                        inventory[3] = inventory[3] - 1;
                    } else {
                        System.out.println("Out of espresso. Try a different topping");
                    }
                    break;
                case "4":
                    if (inventory[4] != 0) {
                        basicCoffee = new Sugar(basicCoffee);
                        inventory[4] = inventory[4] - 1;
                    } else {
                        System.out.println("Out of sugar. Try a different topping");
                    }
                    break;
                case "5":
                    if (inventory[5] != 0) {
                        basicCoffee = new WhippedCream(basicCoffee);
                        inventory[5] = inventory[5] - 1;
                    } else {
                        System.out.println("Out of whipped cream. Try a different topping");
                    }
                    break;
                case "6":
                    if (inventory[6] != 0) {
                        basicCoffee = new Foam(basicCoffee);
                        inventory[6] = inventory[6] - 1;
                    } else {
                        System.out.println("Out of foam. Try a different topping");
                    }
                    break;
                case "e":
                    in = 1;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        coffeeOrder.add(basicCoffee.printCoffee());
        coffeeOrder.add(String.valueOf(basicCoffee.Cost()));
        return coffeeOrder;
    }

    /**
     * Reads from a file to determine the amounts of items in the inventory. Adds the amounts to an array
     * @return the array of amounts
     */
    public static int[] inventoryReader() {
        String line;
        inventory = new int[7];
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Inventory.txt"));
            line = reader.readLine();
            System.out.println("Current items in the inventory:");
            while (line != null) {
                inventory[i] = Integer.valueOf(line.substring(line.indexOf("=") + 2));
                System.out.println(line);
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading inventory");
            e.printStackTrace();
        }
        return inventory;
    }

    /**
     * Overwrites the inventory.txt file with the new values. Takes in the inventory array which contains the
     * values after an order is created.
     * @param inventory which is the array of amount values
     */
    public static void inventoryWriter(int[] inventory) {
        FileWriter writer;
        try {
            writer = new FileWriter(inventoryFile);
            writer.write("\nBlack Coffee = " + inventory[0]);
            writer.write("\nMilk = " + inventory[1]);
            writer.write("\nHotWater = " + inventory[2]);
            writer.write("\nEspresso = " + inventory[3]);
            writer.write("\nSugar = " + inventory[4]);
            writer.write("\nWhippedCream = " + inventory[5]);
            writer.write("Foam = " + inventory[6]);
            System.out.println("Successfully updated the inventory");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes all the orders in the stack to a file with the date
     * @param orders which is a stack of all the orders. These orders are in a certain form like a recipt
     */
    public static void logWriter(Stack<String> orders) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try {
            FileWriter writer = new FileWriter("LogFile.txt", true);
            writer.write("\n\nWriting orders from stack " + simpleDateFormat.format(date));
            if (orders != null) {
                while (orders != null) {
                    writer.write(orders.pop());
                }
                System.out.println("Successfully updated the log file");
            }
            else {
                System.out.println("Nothing to log. Stack is empty");
            }

            writer.close();

        } catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
