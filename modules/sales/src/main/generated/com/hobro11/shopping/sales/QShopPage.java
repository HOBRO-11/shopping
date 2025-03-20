package com.hobro11.shopping.sales;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopPage is a Querydsl query type for ShopPage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopPage extends EntityPathBase<ShopPage> {

    private static final long serialVersionUID = 196525610L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopPage shopPage = new QShopPage("shopPage");

    public final QAddress address;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final ListPath<SaleOption, QSaleOption> saleOptions = this.<SaleOption, QSaleOption>createList("saleOptions", SaleOption.class, QSaleOption.class, PathInits.DIRECT2);

    public final EnumPath<ShopPageStatue> status = createEnum("status", ShopPageStatue.class);

    public final ComparablePath<java.net.URI> thumbnailUri = createComparable("thumbnailUri", java.net.URI.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QShopPage(String variable) {
        this(ShopPage.class, forVariable(variable), INITS);
    }

    public QShopPage(Path<? extends ShopPage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopPage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopPage(PathMetadata metadata, PathInits inits) {
        this(ShopPage.class, metadata, inits);
    }

    public QShopPage(Class<? extends ShopPage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
    }

}

