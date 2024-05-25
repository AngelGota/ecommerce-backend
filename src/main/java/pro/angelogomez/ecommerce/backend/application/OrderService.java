package pro.angelogomez.ecommerce.backend.application;

import pro.angelogomez.ecommerce.backend.domain.model.Order;
import pro.angelogomez.ecommerce.backend.domain.port.IOrderRepository;

public class OrderService {
    private final IOrderRepository iOrderRepository;

    public OrderService(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }
    public Order save(Order order) {
        return iOrderRepository.save(order);
    }
    public Order findById(Integer id) {
        return iOrderRepository.findById(id);
    }
    public Iterable<Order> findAll() {
        return iOrderRepository.findAll();
    }
    public Iterable<Order> findByUserId(Integer userId) {
        return iOrderRepository.findByUserId(userId);
    }
    public void updateStateById(Integer id, String state) {
        iOrderRepository.updateStateById(id, state);
    }
}
