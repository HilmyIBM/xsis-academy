package xa.pos289.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;

@Entity
@Table(name="order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;

	@ManyToOne
	@JoinColumn(name="order_header_id", insertable=false, updatable=false)
	public OrderHeader orderheader;
	
	@NotNull
	@Column(name="order_header_id", length = 10)
	private Long OrderHeaderId;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable=false, updatable=false)
	public Product product;

	@NotNull
	@Column(name="product_id", length = 10)
	private Long ProductId;

	@NotNull
	@Column(name="quantity", length = 10)
	private String Quantity;
	
	@NotNull
	@Column(name="price", length = 50)
	private String price;
	
	@NotNull
	@Column(name="active")
	private Boolean Active;
	
	@NotNull
	@Column(name="create_by", length = 50)
	private String Create_by;
	
	@NotNull
	@Column(name="create_date")
	private LocalDateTime Create_date;
	
	@Nullable
	@Column(name="modify_by", length = 50)
	private String Modify_by;
	
	@Nullable
	@Column(name="modify_date")
	private LocalDateTime Modify_date;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getOrderHeaderId() {
		return OrderHeaderId;
	}

	public void setOrderHeaderId(Long orderHeaderId) {
		OrderHeaderId = orderHeaderId;
	}

	public Long getProductId() {
		return ProductId;
	}

	public void setProductId(Long productId) {
		ProductId = productId;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	public String getCreate_by() {
		return Create_by;
	}

	public void setCreate_by(String create_by) {
		Create_by = create_by;
	}

	public LocalDateTime getCreate_date() {
		return Create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		Create_date = create_date;
	}

	public String getModify_by() {
		return Modify_by;
	}

	public void setModify_by(String modify_by) {
		Modify_by = modify_by;
	}

	public LocalDateTime getModify_date() {
		return Modify_date;
	}

	public void setModify_date(LocalDateTime modify_date) {
		Modify_date = modify_date;
	}
	
	

}
