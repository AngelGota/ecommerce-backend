package pro.angelogomez.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pro.angelogomez.ecommerce.backend.domain.model.Order;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.OrderEntity;

@Mapper(componentModel = "spring", uses = IOrderMapper.class)
public interface IOrderMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "orderProducts", target = "orderProducts"),
                    @Mapping(source = "orderState", target = "orderState"),
                    @Mapping(source = "userEntity.id", target = "userId"),
            }
    )
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrders(Iterable<OrderEntity> orderEntities);
    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
