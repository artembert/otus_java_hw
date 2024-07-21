package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    Comparator<Customer> comparator = new CustomersComparator();
    private final TreeMap<Customer, String> collection = new TreeMap<>(comparator);

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallest = this.collection.firstEntry();
        return buildNewEntity(smallest.getKey(), smallest.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> next = this.collection.higherEntry(customer);
        if (next == null) {
            return null;
        }
        return buildNewEntity(next.getKey(), next.getValue());
    }

    public void add(Customer customer, String data) {
        this.collection.put(customer, data);
    }

    private Map.Entry<Customer, String> buildNewEntity(Customer customer, String data) {
        return new Map.Entry<Customer, String>() {
            @Override
            public Customer getKey() {
                return new Customer(customer);
            }

            @Override
            public String getValue() {
                return data;
            }

            @Override
            public String setValue(String value) {
                return value;
            }
        };
    }
}
