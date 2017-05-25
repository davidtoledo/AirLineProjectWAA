package net.tolledo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OrderTable")
public class Order {

	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
        @JsonIgnore
	private List<Orderline> orderLines = new ArrayList<Orderline>();
        
        private int quantity;
        private double totalAmount;
        
	@OneToOne
	private Person person;

	public int getId() {
		return id;
	}

	public List<Orderline> getOrderLines() {
		return Collections.unmodifiableList(orderLines);
	}
        
        // David: This is Required by Json Parser
        public void setOrderLines( List<Orderline> orderLines) {
            this.orderLines = orderLines;
        }

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
            return this.quantity;
	}
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

	public double getTotalAmount() {
            return this.totalAmount;
	}
        
        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

	public void addOrderLine(Orderline orderLine) {
		orderLine.setOrder(this);
		this.orderLines.add(orderLine);
	}

	public void removeOrderLine(Orderline orderLine) {
		this.orderLines.remove(orderLine);
		orderLine.setOrder(null);
	}

	public void clearOrderLines() {
		for (Orderline orderline : orderLines) {
			orderline.setOrder(null);
		}
		orderLines.clear();
	}
}