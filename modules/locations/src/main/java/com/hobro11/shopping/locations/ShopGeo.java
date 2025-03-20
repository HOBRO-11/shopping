package com.hobro11.shopping.locations;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "shop_geo", indexes = {
        @Index(name = "idx_shop_geo_geo_hash", columnList = "geoHashLat, geoHashLon")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class ShopGeo {

    @Id
    private Long shopPageId;

    @Column(precision = 6, nullable = false)
    private Float latitude;

    @Column(precision = 7, nullable = false)
    private Float longitude;

    @Embedded
    private GeoHashKey geoHash;

}