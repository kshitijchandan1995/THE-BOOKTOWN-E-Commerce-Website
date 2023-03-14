import React, { useEffect, useState } from 'react'
import { Row } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import UserServices from '../service/UserServices';
import Footer from './Footer'
import ForgetPassword from './ForgetPassword';
import PageRouter from './PageRouter'
import Product from './Product'
import ProductDetails from './ProductDetails';


function Home() {

  let [product , setProduct] =useState([{}]);

  useEffect(() => {
    UserServices.getAllProduct().then((resp) => {
      console.log(resp.data[0].email+"inside home request")
        setProduct(resp.data)
    }).catch((err) => {
        console.log("Employee Profile Image Err", err)
    })
   
}, [])
const navigate =useNavigate();
useEffect(() => {
  navigate("/home ")
},[])


  return (
        
    <div>
        <PageRouter></PageRouter>
        <div className=''>
          <main className='col-lg-10' style={{margin:"25px 50px 75px 100px"}}>
            <div className='row'>
            {/* <div className='col-lg-2 col-md-6 col-sm-6'></div> */}
        {
          product.map((p)=>{
            return (
              <div className='col-lg-2 col-md-6 col-sm-6'>
                <Product Product={p}></Product>
              </div>
            )
          })

          }
           {/* <div className='col-lg-1 col-md-6 col-sm-6'></div> */}
          </div>
        </main>
       </div>
       
    </div>
  )
}

export default Home