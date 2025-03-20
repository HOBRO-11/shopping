package com.hobro11.shopping.sales;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleOption is a Querydsl query type for SaleOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaleOption extends EntityPathBase<SaleOption> {

    private static final long serialVersionUID = 1824345569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleOption saleOption = new QSaleOption("saleOption");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QShopPage shopPage;

    public final EnumPath<SaleOptionStatus> status = createEnum("status", SaleOptionStatus.class);

    public QSaleOption(String variable) {
        this(SaleOption.class, forVariable(variable), INITS);
    }

    public QSaleOption(Path<? extends SaleOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleOption(PathMetadata metadata, PathInits inits) {
        this(SaleOption.class, metadata, inits);
    }

    public QSaleOption(Class<? extends SaleOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shopPage = inits.isInitialized("shopPage") ? new QShopPage(forProperty("shopPage"), inits.get("shopPage")) : null;
    }

}

