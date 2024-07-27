package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    private final Deque<Customer> collection = new ArrayDeque<>();

    public void add(Customer customer) {
        this.collection.addFirst(customer);
    }

    public Customer take() {
        return this.collection.pop();
    }
}
