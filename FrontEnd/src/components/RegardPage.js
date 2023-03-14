import React from 'react'
 
function RegardPage() {
  return (
    <div style={{marginBottom:"100px"}}>
        <hr></hr>
        <h1>THANK YOU FOR SHOPPING WITH US !!!!</h1>
        <div>
        <div class="container">
	      <div class="row text-center">
        <div class="col-md-8" style={{marginLeft:"200px"}}>
        <br></br>
        <h2>Dear,{sessionStorage.getItem("user_details")}</h2> 
        <h2 style={{color:"#0fad00"}}>Order placed Successfully</h2>
        
        <p style={{fontsize:"20px",color:"#5C5C5C"}}>
        Congratulations! Your order has been placed successfully on our e-commerce website.<br></br> 
        We appreciate your business and are grateful for your trust in our products and services.<br></br> 
        Our team will now process your order and ensure that it is shipped to you as soon as possible.<br></br> 
        You will receive an email confirmation shortly, which will contain all the relevant details regarding your order, including the expected delivery date. If you have any further questions or concerns, please don't hesitate to reach out to our customer service team. Thank you again for choosing our e-commerce website, and we look forward to serving you again in the future.

        </p>
        <a href="/dashboard" class="btn btn-success">Back</a>
    <br></br>
        </div>
        
	</div>
</div>
</div>  
    
    </div>
  )
}

export default RegardPage