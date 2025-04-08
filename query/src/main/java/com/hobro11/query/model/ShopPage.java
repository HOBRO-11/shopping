package com.hobro11.query.model;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
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
    private ShopPageStatus status;

    @Embedded
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "shopPage")
    private List<SaleOption> saleOptions = new ArrayList<>();

    @Builder
    public ShopPage(Long memberId, String title, URI thumbnailUri, String description, ShopPageStatus status,
            Address address) {
        this.memberId = memberId;
        this.title = title;
        this.thumbnailUri = thumbnailUri;
        this.description = description;
        this.status = status;
        this.address = address;
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
