package com.hobro11.shopping.locations;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopGeo is a Querydsl query type for ShopGeo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopGeo extends EntityPathBase<ShopGeo> {

    private static final long serialVersionUID = -2089633240L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopGeo shopGeo = new QShopGeo("shopGeo");

    public final QGeoHashKey geoHash;

    public final NumberPath<Float> latitude = createNumber("latitude", Float.class);

    public final NumberPath<Float> longitude = createNumber("longitude", Float.class);

    public final NumberPath<Long> shopPageId = createNumber("shopPageId", Long.class);

    public QShopGeo(String variable) {
        this(ShopGeo.class, forVariable(variable), INITS);
    }

    public QShopGeo(Path<? extends ShopGeo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopGeo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopGeo(PathMetadata metadata, PathInits inits) {
        this(ShopGeo.class, metadata, inits);
    }

    public QShopGeo(Class<? extends ShopGeo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.geoHash = inits.isInitialized("geoHash") ? new QGeoHashKey(forProperty("geoHash")) : null;
    }

}

