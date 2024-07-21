package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {
    private final LinkedList<Customer> collection = new LinkedList<>();

    public void add(Customer customer) {
        this.collection.addFirst(customer);
    }

    public Customer take() {
        return this.collection.pollFirst();
    }
}
