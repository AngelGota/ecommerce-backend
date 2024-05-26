package pro.angelogomez.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pro.angelogomez.ecommerce.backend.domain.model.OrderProduct;
import pro.angelogomez.ecommerce.backend.infrastructure.entity.OrderProductEntity;

@Mapper(componentModel = "spring")
public interface IOrderProductMapper {
    @Mappings(
            {
                @Mapping(source = "id", target = "id"),
                @Mapping(source = "quantity", target = "quantity"),
                @Mapping(source = "price", target = "price"),
                @Mapping(source = "productId", target = "productId"),
            }
    )
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    Iterable<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
