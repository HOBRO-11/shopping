package com.hobro11.shopping.orders;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 1518621963L;

    public static final QOrders orders = new QOrders("orders");

    public final NumberPath<Long> checkSum = createNumber("checkSum", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final SetPath<OptionQuantity, SimplePath<OptionQuantity>> optionQuantities = this.<OptionQuantity, SimplePath<OptionQuantity>>createSet("optionQuantities", OptionQuantity.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Long> orderNumber = createNumber("orderNumber", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> shopPageId = createNumber("shopPageId", Long.class);

    public final EnumPath<OrdersStatus> status = createEnum("status", OrdersStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QOrders(String variable) {
        super(Orders.class, forVariable(variable));
    }

    public QOrders(Path<? extends Orders> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrders(PathMetadata metadata) {
        super(Orders.class, metadata);
    }

}

