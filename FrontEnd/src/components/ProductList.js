import React,{useEffect,useState} from 'react'
import { Link } from 'react-router-dom';
import UserServices from '../service/UserServices';
import ProductForAdmin from './ProductForAdmin';
function ProductList() {
    let [product , setProduct] =useState([{}]);
    useEffect(() => {
  UserServices.getAllProduct().then((resp) => {
    console.log(resp.data[0].email+"inside home request")
      setProduct(resp.data)
  }).catch((err) => {
      console.log("Employee Profile Image Err", err)
  })
}, [])
    return (
    <div>
        <main className='col-lg-10' style={{margin:"25px 50px 75px 100px"}}>
          <div className='row'>
          {/* <div className='col-lg-2 col-md-6 col-sm-6'></div> */}
      {
        product.map((p)=>{
          return (
            <div className='col-lg-2 col-md-6 col-sm-6'>
              <ProductForAdmin Product={p}></ProductForAdmin>
            </div>
          )
        })

        }
         {/* <div className='col-lg-1 col-md-6 col-sm-6'></div> */}
      
        </div>
      </main>
      <Link to={`/dashboard`}>
         <button type="button" class="btn btn-secondary btn-rounded">Back</button>
         </Link>
         <h3></h3>
    </div>
  )
}

export default ProductList