
import React , {useState,useEffect} from 'react'
import { useParams } from 'react-router-dom'
import UserServices from '../service/UserServices';
import VendorServices from '../service/VendorServices';
import { Link } from 'react-router-dom';
function ProductDetails() {
    let [img, setImg] = useState({})
    const {productId} = useParams();
    let [product,setProduct] = useState({productName:"",price:"",description:"",productCategoryId:"",availableItemCount:""});
    useEffect(() => {
        console.log(productId)
          UserServices.getProfileImageByUserName(productId).then((resp) => {
              setImg(URL.createObjectURL(resp.data))
          }).catch((err) => {
             console.log(" Err in getting images", err)
          })
      }, [productId])
      useEffect(() => {
        VendorServices.getProductById(productId).then((res)=>{
            console.log(res.data)
            setProduct(res.data)
            console.log(product)
        })
  }, [])
  return (
    <div style={{marginTop:"50px"}}>
        
        <div className='col-lg-3 col-md-3 col-sm-2' style={{margin:"0 auto"}}>
        <div class="card mb-3">
  <img class="card-img-top" style={{height:"300px",width:"250px" ,paddingLeft:"75px",paddingTop:"20px"}} src={img} alt="Card image cap"/>
  <div class="card-body">
    <h3 class="card-title">{product.productName}</h3>
    <p class="card-text"><b>Description</b> : {product.description}</p>
    <h4 class="card-text">Price : Rs.{product.price}</h4>
    <h5 class="card-text">Quantity : {product.availableItemCount}</h5>
  </div>
  <Link to={`/home`}>
         <button type="button" class="btn btn-secondary btn-rounded">Back</button>
         </Link>
         <h1></h1>
</div>
</div>
 
    </div>
  )
}

export default ProductDetails