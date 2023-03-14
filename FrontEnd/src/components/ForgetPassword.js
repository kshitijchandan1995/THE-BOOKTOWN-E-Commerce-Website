import { useState } from "react";
import { useNavigate } from "react-router-dom";
import UserServices from "../service/UserServices";
import { Link } from 'react-router-dom';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol,
  MDBInput,
  MDBCheckbox
} from 'mdb-react-ui-kit';
const ForgotPassword = () => {

    let [changePass, setChangePass] = useState({ userName: "", password: "", otp: "" });

    let [genOTPStatus, setGenOTPStatus] = useState(false);
    let [showPassStatus, setShowPassStatus] = useState(false);
    let [messageStatus, setMessageStatus] = useState(false);
    let [respMessage, setRespMessage] = useState("");
    const navigate = useNavigate();

    const handlePassChange = (event) => {
        const { name, value } = event.target
        setChangePass({ ...changePass, [name]: value })
    }


    const verifyOTP = () => {
        UserServices.verifyOTPAndChangePass(changePass).then((resp) => {
            console.log(resp.data)
            setRespMessage(resp.data.message)
            setMessageStatus(true);
            navigate("/login")
        }).catch((err) => {
            console.log(err)
            setMessageStatus(true)
        })
    }

    const sendOTP = () => {
        setGenOTPStatus(true);
        UserServices.sendPassChangeOTP(changePass.userName).then((resp) => {
            console.log(resp)
        }).catch((err) => {
            console.log("Send Email", err.toJSON())
        })
    }

    // const showPassword = () => {
    //     if (document.getElementById("passwordid") != null) {
    //         document.getElementById("passwordid").type = "text"
    //     } else {
    //         document.getElementById("cngPasswordid").type = "text"
    //     }
    //     setShowPassStatus(true);
    // }

    // const hidePassword = () => {
    //     if (document.getElementById("passwordid") != null) {
    //         document.getElementById("passwordid").type = "password"
    //     } else {
    //         document.getElementById("cngPasswordid").type = "password"
    //     }
    //     setShowPassStatus(false);
    // }

    const reloadPage = () => {
        Location.reload();
    }
    return (
        <div>
            <MDBContainer className='my-5'>
      <MDBCard>
        
        <MDBRow className='g-0 d-flex align-items-center'>
        <MDBCol md='2'></MDBCol>
          <MDBCol md='4'>
            <MDBCardImage src='https://images.pexels.com/photos/1907785/pexels-photo-1907785.jpeg?auto=compress&cs=tinysrgb&w=600' 
            alt='phone' className='rounded-t-5 rounded-tr-lg-0' fluid />
          </MDBCol>
          <MDBCol md='2'></MDBCol>  
          <MDBCol md='4' >
            <MDBCardBody>
            <h3 style={{marginBottom :"20px"}}>FORGET PASSWORD</h3>
              <MDBInput wrapperClass='mb-4' label='UserName' id='user_name' name='userName' type='text' onChange={handlePassChange}/>
              <MDBBtn className="mb-4 w-100" onClick={sendOTP}>SEND OTP</MDBBtn>
              <MDBInput wrapperClass='mb-4' label='Password' id='password' name='password' type='text' onChange={handlePassChange}/>
              <MDBInput wrapperClass='mb-4' label='OTP' id='OTP' name='otp' type='text' onChange={handlePassChange}/>
              <MDBBtn  className="mb-4 w-100" onClick={verifyOTP}>Verify OTP</MDBBtn>
              <Link to={`/login`}>
              <MDBBtn className="mb-4 w-100">LOGIN</MDBBtn>
              </Link>
            </MDBCardBody>
          </MDBCol>

        </MDBRow>

      </MDBCard>
    </MDBContainer>
        </div >
    )
}

export default ForgotPassword;