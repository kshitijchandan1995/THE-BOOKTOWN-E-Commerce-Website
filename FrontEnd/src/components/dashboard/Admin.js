import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import UserServices from "../../service/UserServices";
import ProductForAdmin from "../ProductForAdmin";
import UserList from "../UserList";


const Admin = () => {
    return (
        <div className="dashboardBody" style={{height :"540px"}}>
          <hr></hr>
          <h1>WELCOME ADMIN ,{sessionStorage.getItem("user_details")}</h1>
  <div>
    <div className="d-grid gap-2 d-md-flex justify-content-md-center" style={{marginTop:"10px"}}>
      <Link to={`/userList`}>
    <button type="button" className="btn btn-primary" style={{marginRight:"100px"}}>userlist</button>
    </Link>
    <Link to={`/productList`}>
    <button type="button" className="btn btn-primary">productlist</button>
    </Link>
    </div>
        


    


  </div>


</div>      
    )
}

export default Admin;