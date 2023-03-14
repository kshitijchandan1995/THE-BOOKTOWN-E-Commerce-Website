import React ,{useState,useEffect}from 'react'
import { Link } from 'react-router-dom'
import UserServices from '../service/UserServices'
import ProductForVendor from './ProductForVendor'
function VendorProductList() {
    let [product,setProduct]=useState([{}])
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
      {
        product.map((p)=>{
          return (
            <div className='col-lg-2 col-md-6 col-sm-6'>
              <ProductForVendor Product={p}></ProductForVendor>
            </div>
          )
        })
        }
        </div>
        <Link to={`/dashboard`}>
         <button type="button" class="btn btn-secondary btn-rounded" style={{marginTop:"25px"}}>Back</button>
         </Link>
      </main>
      
    </div>
  )
}

export default VendorProductList