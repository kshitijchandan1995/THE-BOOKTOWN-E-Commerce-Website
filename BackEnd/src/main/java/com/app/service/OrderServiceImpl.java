package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.dto.web_product.Address.GetAddressResponse;
import com.app.dto.web_product.cart.Card;
import com.app.dto.web_product.cart.CreateOrderRequest;
import com.app.dto.web_product.cart.CreateOrderResponse;
import com.app.dto.web_product.cart.PreviewOrderRequest;
import com.app.dto.web_product.cart.PreviewOrderResponse;
import com.app.entity.AddressDao;
import com.app.entity.Cart;
import com.app.entity.Order;
import com.app.entity.OrderBillingAddress;
import com.app.entity.OrderItem;
import com.app.entity.OrderShippingAddress;
import com.app.entity.User;
import com.app.exception.RunTimeExceptionPlaceHolder;
import com.app.repository.AddressRepository;
import com.app.repository.OrderBillingAddressRepository;
import com.app.repository.OrderItemRepository;
import com.app.repository.OrderRepository;
import com.app.repository.OrderShippingAddressRepository;
import com.app.repository.UserRepository;
import com.app.util.CommonUtilityMethods;



@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    OrderRepository orderRepository;
    

    
    @Autowired
    OrderItemRepository orderItemRepository;
    
    


    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;
    
    @Autowired
    UserRepository userrepo;
    
    @Autowired
    AddressRepository addressrepo;
    

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
    	System.out.println("0");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         Optional<User> user =userrepo.findByUserName(createOrderRequest.getUserName());

        //TODO make transactional
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
System.out.println("1");
        //Get Shipping Address
        AddressDao Address = null;
        if (createOrderRequest.getAddressId() != null && !(createOrderRequest.getAddressId()==null)) {
        	Address=addressrepo.findByAddressId(createOrderRequest.getAddressId()).get();
            createOrderResponse.setAddress(Address);
        }
System.out.println("2");
        //Get Cart
        Cart cart = cartService.getCartByUserName(user.get().getUserName());

        if(cart.getCartItems().size()==0){
            throw new RuntimeException("Cart is Empty");
        }

        Order order = new Order();
        order.setUserName(user.get().getUserName());
        order.setUserId(user.get().getUserId());

        cart.getCartItems()
                .forEach(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setOrderExtendedPrice(cartItem.getExtendedPrice());
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setQuantity(cartItem.getQuantity());
                    order.getOrderItems().add(orderItem);
                    createOrderResponse.getOrderItems().add(orderItem);
                });

        //HarCode to 10%
        double itemsPrice = createOrderResponse.getOrderItems().stream().mapToDouble(OrderItem::getOrderExtendedPrice).sum();
        createOrderResponse.setItemsTotalPrice(itemsPrice);
        order.setTotalItemsPrice(itemsPrice);

        Double taxPrice = (itemsPrice * 10) / 100;
        createOrderResponse.setTaxPrice(taxPrice);
        order.setTaxPrice(taxPrice);

        //Hardcode to 10
        Double shippingPrice = 10D;
        createOrderResponse.setShippingPrice(shippingPrice);
        order.setShippingPrice(shippingPrice);

        double totalPrice = itemsPrice + taxPrice + shippingPrice;
        createOrderResponse.setTotalPrice(totalPrice);
        order.setTotalOrderPrice(totalPrice);
        

        Order save = orderRepository.save(order);


        createOrderResponse.setOrderId(save.getOrderId());
        createOrderResponse.setCreated_at(save.getCreatedAt());


        //Clear cart
        cartItemService.removeAllCartItems(cart.getCartId());
        return createOrderResponse;
    }

    @Override
    public PreviewOrderResponse previewOrder(PreviewOrderRequest previewOrderRequest) {

        PreviewOrderResponse previewOrderResponse = new PreviewOrderResponse();

        if(previewOrderRequest.getAddressId() != null){
        	AddressDao Address=addressrepo.findByAddressId(previewOrderRequest.getAddressId()).get();

            
            previewOrderResponse.setAddress(Address);
        }


        Cart cart = cartService.getCart();

        cart.getCartItems()
                .forEach(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setOrderExtendedPrice(cartItem.getExtendedPrice());
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setQuantity(cartItem.getQuantity());
                    previewOrderResponse.getOrderItems().add(orderItem);
                });

        //HardCode to 10%
        double itemsPrice = previewOrderResponse.getOrderItems().stream().mapToDouble(OrderItem::getOrderExtendedPrice).sum();
        previewOrderResponse.setItemsTotalPrice(itemsPrice);

        Double taxPrice = (itemsPrice * 10 ) / 100;
        previewOrderResponse.setTaxPrice(taxPrice);

        //Hardcode to 10
        Double shippingPrice = 10D;
        previewOrderResponse.setShippingPrice(shippingPrice);

        previewOrderResponse.setTotalPrice(itemsPrice + taxPrice + shippingPrice);

        return previewOrderResponse;
    }


}
