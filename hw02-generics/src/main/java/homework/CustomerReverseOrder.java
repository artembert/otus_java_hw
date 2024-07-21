package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {
    private final LinkedList<Customer> collection = new LinkedList<>();

    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        this.collection.addFirst(customer);
    }

    public Customer take() {
        return this.collection.pollFirst();
    }
}
