package com.hobro11.shopping.orders;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "cart", uniqueConstraints = @UniqueConstraint(columnNames = { "memberId" }))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Set<OptionQuantity> optionQuantities = new HashSet<>();

    @Builder
    public Cart(Long memberId) {
        this.memberId = memberId;
    }

    public void addOptionQuantity(OptionQuantity optionQuantity) {
        this.optionQuantities.add(optionQuantity);
    }

    public void removeOptionQuantity(OptionQuantity optionQuantity) {
        this.optionQuantities.remove(optionQuantity);
    }

}
