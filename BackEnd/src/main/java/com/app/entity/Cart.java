package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends DateAudit {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "CART_ID", updatable = false, nullable = false)
    private Long cartId;

    @NotEmpty
    @NotNull
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();
    
    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;

    public void dismissChild(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    @PreRemove
    public void dismissChild() {
        this.cartItems.forEach(CartItem::dismissParent); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.cartItems.clear();
    }
}