package com.hobro11.shopping.sales;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "shopPage", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ShopPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String title;

    @Setter
    private URI thumbnailUri;

    @Setter
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private ShopPageStatue status;

    @Column(nullable = false, columnDefinition = "geometry(Point, 4326)")
    private Point location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "shopPage")
    private List<SaleOption> saleOptions = new ArrayList<>();

    @Builder
    public ShopPage(Long memberId, String title, URI thumbnailUri, String description, ShopPageStatue status,
            Point location) {
        this.memberId = memberId;
        this.title = title;
        this.thumbnailUri = thumbnailUri;
        this.description = description;
        this.status = status;
        this.location = location;
    }

    public void addSaleOption(SaleOption saleOption) {
        this.saleOptions.add(saleOption);
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
