import axios from 'axios';


class VendorServices {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }
    AddProduct(product){
        return  axios.post(this.baseUrl+"product/create",product)
    }
    AddImage(image,Productid){
    //  return   axios.post(this.baseUrl+"product/"+Productid+"/image",imageFile,{
    //         headers :{
    //             'Content-Type': 'multipart/form-data'
    //         }
    //     })
    console.log(Productid+"11111111111"+image)
        const profile = new FormData()
        profile.append("image",image)
        return axios.post(this.baseUrl + "product/" +Productid+"/image",profile,{ headers:{
            "Accept":"*/*",
            "content-type": "multipart/form-data"
        }}
          )
    }
    UpdateProduct(product){
        return axios.put(this.baseUrl+"product/product",product)

        
    }
    getProductById(productId){
        return axios.get(this.baseUrl+"product/product/"+productId)
    }
}
export default new VendorServices();