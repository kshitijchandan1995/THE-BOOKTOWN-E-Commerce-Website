import axios from 'axios';


class CartServices {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }
    getCartItem(username){
        return axios.get(this.baseUrl+"cart/cartItems/"+username)
    }

    deleteCartItem(cartItemId){
        return axios.delete(this.baseUrl+"cart/cartItem/"+cartItemId);
    }
    addshippingaddress(address){
        return axios.post(this.baseUrl+"address",address)
    }
    addorder(user){
        console.log(user.userName+"gggggggggg")
    return axios.post(this.baseUrl+"order",user)
    }
    setincreamentquantity(cartItemId){
        return axios.get(this.baseUrl+"cart/setincreamentquantity/"+cartItemId)
    }
    setdecreamentquantity(cartItemId){
        return axios.get(this.baseUrl+"cart/setdecreamentquantity/"+cartItemId)
    }
    sentmail(userName){
    return axios.get(this.baseUrl+"sendmail/"+userName)

    }

}
export default new CartServices();