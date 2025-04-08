package com.hobro11.query.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionQuantity {

    private Long saleOptionId;

    private Integer quantity;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((saleOptionId == null) ? 0 : saleOptionId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OptionQuantity other = (OptionQuantity) obj;
        if (saleOptionId == null) {
            if (other.saleOptionId != null)
                return false;
        } else if (!saleOptionId.equals(other.saleOptionId))
            return false;
        return true;
    }

}
