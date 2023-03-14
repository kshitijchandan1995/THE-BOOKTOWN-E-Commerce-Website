import { useState ,useEffect } from "react";
import ProductAdd from "../ProductAdd";
import ProductForVendor from "../ProductForVendor";
import UserServices from "../../service/UserServices";
import { Link } from "react-router-dom";

const Vendor = () => {
    return (
      <div className="dashboardBody" style={{height :"540px" }}>
      <hr></hr>
      <h1>WELCOME ,{sessionStorage.getItem("user_details")}</h1>
<div className="d-grid gap-2 d-md-flex justify-content-md-center" style={{marginTop:"30px"}}>
  <Link to={`/ProductAdd`}>
<button type="button" className="btn btn-primary" style={{marginRight:"100px"}}>ADD PRODUCT</button>
</Link>
<Link to={`/productListVendor`}>
<button type="button" className="btn btn-primary">PRODUCT LIST</button>
</Link>
</div>
</div>
      
    )
}

export default Vendor;