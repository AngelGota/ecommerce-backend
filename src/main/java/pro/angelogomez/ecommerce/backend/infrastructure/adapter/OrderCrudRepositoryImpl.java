package pro.angelogomez.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import pro.angelogomez.ecommerce.backend.domain.model.Order;
import pro.angelogomez.ecommerce.backend.domain.model.OrderState;
import pro.angelogomez.ecommerce.backend.domain.port.IOrderRepository;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.OrderEntity;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.UserEntity;
import pro.angelogomez.ecommerce.backend.infrastructure.mapper.IOrderMapper;
@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {
    private final IOrderMapper iOrderMapper;
    private final IOrderCrudRepository iOrderCrudRepository;

    public OrderCrudRepositoryImpl(IOrderMapper iOrderMapper, IOrderCrudRepository iOrderCrudRepository) {
        this.iOrderMapper = iOrderMapper;
        this.iOrderCrudRepository = iOrderCrudRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);

        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );

        return iOrderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return iOrderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order id: "+id+" not found")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return iOrderMapper.toOrders(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return iOrderMapper.toOrders(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED.toString())){
            iOrderCrudRepository.updateStateById(id, OrderState.CANCELLED);
        }else {
            iOrderCrudRepository.updateStateById(id, OrderState.CONFIRMED);
        }
    }
}
