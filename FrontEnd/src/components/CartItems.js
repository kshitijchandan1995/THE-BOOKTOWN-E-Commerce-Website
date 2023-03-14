import React, { useEffect, useState } from 'react'
import CartServices from '../service/CartServices';
import UserServices from '../service/UserServices';

function CartItems(props) {
    // let [cartItems,setCartItems]=useState([{}])
    let [img, setImg] = useState({})
    let [qty,setQty] =useState(1);
    let [totalprice,setTotalprice] =useState();
     const CartItems =props.CartItems; 
     const total = CartItems.itemPrice * CartItems.quantity;   
    // const username=sessionStorage.getItem("user_details");
    const deleteCartItem=(cartItemId)=>{
        CartServices.deleteCartItem(cartItemId).then(()=>{console.log("cartItem Deleted")})
    }
    useEffect(() => {
        console.log(CartItems.cartItemId)
          UserServices.getProfileImageByUserName(CartItems.productId).then((resp) => {
              setImg(URL.createObjectURL(resp.data))
          }).catch((err) => {
             console.log(" Err in getting images", err)
          })
      }, [CartItems.productId])

      const increment = ()=>{
        CartServices.setincreamentquantity(CartItems.cartItemId).then((resp)=>{
          console.log(resp.data)
        })
        setQty(qty+1)
      }

      const decrement = ()=>{
        CartServices.setdecreamentquantity(CartItems.cartItemId).then((resp)=>{
          console.log(resp.data)
          setQty(qty-1)
        })
      }

    return (
    <div class="card rounded-3 mb-4">
          <div class="card-body p-4">
            <div class="row d-flex justify-content-between align-items-center">
              <div class="col-md-2 col-lg-2">
                <img
                  src={img}
                  class="img-fluid rounded-3"/>
              </div>
              <div class="col-md-3 col-lg-3">
                <p class="lead fw-normal mb-2">{CartItems.productName}</p>
              </div>
              {/* <div class="col-md-3 col-lg-2 ">
              
              </div>
              <div class="col-md-3 col-lg-2 ">
                <h5 class="mb-0"></h5>
              </div> */}
              <div class="col-md-3 col-lg-2 d-flex">
              <button class="btn btn-link px-2" onClick={decrement}><i class="fas fa-minus"></i>
                </button>

                <input id="form1" min="0" name="quantity" value={qty} type="number"
                  class="form-control form-control-sm" />

                <button class="btn btn-link px-2" onClick={increment}>
                  <i class="fas fa-plus"></i>
                </button>
                {/* <h5 class="mb-0"></h5> */}
              </div>
              <div class="col-md-3 col-lg-3 ">
                <h5 class="mb-0">Rs.{total}</h5>
              </div>
              <div class="col-md-1 col-lg-1 text-end" >
                
                <a href="#!" class="text-danger"><i class="fas fa-trash fa-lg" onClick={()=>deleteCartItem(CartItems.cartItemId)}></i></a>
              </div>
              <div class="col-md-1 col-lg-1 text-end" >
                
              </div>
            </div>
          </div>
        </div>
            )
  
}

export default CartItems