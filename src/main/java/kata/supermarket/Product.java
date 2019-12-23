package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;
    private final Integer id;

    public Product(final BigDecimal pricePerUnit, final Integer id) {
        this.pricePerUnit = pricePerUnit;
        this.id=id;
    }

	BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
    
    public Integer getId() {
 		return id;
 	}

	
    @Override
	public String toString() {
		return "Product [pricePerUnit=" + pricePerUnit + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pricePerUnit == null) ? 0 : pricePerUnit.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pricePerUnit == null) {
			if (other.pricePerUnit != null)
				return false;
		} else if (!pricePerUnit.equals(other.pricePerUnit))
			return false;
		return true;
	}
}
