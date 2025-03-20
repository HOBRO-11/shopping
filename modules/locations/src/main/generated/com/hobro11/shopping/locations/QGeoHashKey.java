package com.hobro11.shopping.locations;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeoHashKey is a Querydsl query type for GeoHashKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGeoHashKey extends BeanPath<GeoHashKey> {

    private static final long serialVersionUID = 2094438675L;

    public static final QGeoHashKey geoHashKey = new QGeoHashKey("geoHashKey");

    public final NumberPath<Integer> geoHashLat = createNumber("geoHashLat", Integer.class);

    public final NumberPath<Integer> geoHashLon = createNumber("geoHashLon", Integer.class);

    public QGeoHashKey(String variable) {
        super(GeoHashKey.class, forVariable(variable));
    }

    public QGeoHashKey(Path<? extends GeoHashKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeoHashKey(PathMetadata metadata) {
        super(GeoHashKey.class, metadata);
    }

}

