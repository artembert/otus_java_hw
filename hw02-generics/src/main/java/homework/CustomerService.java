package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CustomerService {
    Comparator<Customer> comparator = new CustomersComparator();
    private final Map<Customer, String> collection = new TreeMap<>(comparator);

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Optional<Map.Entry<Customer, String>> smallest = this.collection.entrySet().stream().findFirst();
        return smallest.orElse(null);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return null; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        this.collection.put(customer, data);
    }
}
