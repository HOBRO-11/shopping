package com.hobro11.command.domain.orders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "cart", uniqueConstraints = @UniqueConstraint(columnNames = { "memberId" }))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Set<OptionQuantity> optionQuantities = new HashSet<>();

    private LocalDateTime updatedAt;

    @Builder
    public Cart(Long memberId) {
        this.memberId = memberId;
    }


    public void addOptionQuantity(OptionQuantity optionQuantity) {
        this.optionQuantities.add(optionQuantity);
        this.updateUpdatedAt();
    }

    public void removeOptionQuantity(OptionQuantity optionQuantity) {
        this.optionQuantities.remove(optionQuantity);
        this.updateUpdatedAt();
    }

    @PreUpdate
    public void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
