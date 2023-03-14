import React, { useEffect, useState } from 'react'
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown'
import { NavDropdown } from 'react-bootstrap';
import {
    MDBContainer,
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarToggler,
    MDBIcon,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBNavbarLink,
    MDBBtn,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBCollapse,
    MDBInputGroup
  } from 'mdb-react-ui-kit'
import { isAdmin ,isUserLoggedIn} from '../service/common';
import AuthenticationService from '../service/AuthenticationService';
import { useNavigate } from 'react-router-dom';
function Header() {
  const navigate =useNavigate();
  const [userInfo, setuserInfo] = useState("");
  const [role, setrole] = useState("");
  const [bool,setBool] =useState(false);
  useEffect(() => {
    isUserLoggedIn()?(setuserInfo(sessionStorage.getItem("user_details"))):setuserInfo("");
  },[bool])
  
  
    const [showBasic, setShowBasic] = useState(false);

    const logout=()=>{
        // ProfileBoolTrue()
        AuthenticationService.removeUserDetails();
        navigate("/home")
        setBool(true);
      //  window.location.reload(false)
    }
    
    const handleSelect=(e)=>{
      console.log(e)
      // setProduct({...product,productCategoryId:e});
       console.log(e);
      navigate("/productListcategory", { state: e })
      
    }

  return (
    <div>
    <MDBNavbar expand='lg' light bgColor='light' className='fixed-top'>
    <MDBContainer fluid>
      <MDBNavbarBrand href='/'>THE BOOKTOWN</MDBNavbarBrand>

      <MDBNavbarToggler
        aria-controls='navbarSupportedContent'
        aria-expanded='false'
        aria-label='Toggle navigation'
        onClick={() => setShowBasic(!showBasic)}
      >
        <MDBIcon icon='bars' fas />
      </MDBNavbarToggler>

      <MDBCollapse navbar show={showBasic}>
        <MDBNavbarNav right fullWidth={false} className='mr-auto mb-2 mb-lg-0'>
          <MDBNavbarItem>
            
            <MDBNavbarLink active aria-current='page' href='/'>
              HOME
            </MDBNavbarLink>
          </MDBNavbarItem>
          
         {!userInfo?
          <MDBNavbarItem>
            <MDBNavbarLink active aria-current='page' href='/login'>
              LOGIN 
            </MDBNavbarLink>
          </MDBNavbarItem>:
          <item>
          <MDBNavbarItem>
            <MDBNavbarLink active aria-current='page' href="/dashboard">

            {userInfo}
            </MDBNavbarLink>
          </MDBNavbarItem>
          </item>
         }
         {userInfo?
           <MDBNavbarItem>
           <MDBNavbarLink active aria-current='page' onClick={()=>logout()}>

           Logout
           </MDBNavbarLink>
         </MDBNavbarItem>:""
}

          {/* <MDBNavbarItem>
              <MDBDropdown>
                <MDBDropdownToggle tag='a' className='nav-link b' role='button'>
                  Category
                </MDBDropdownToggle>
                <MDBDropdownMenu>
                  <MDBDropdownItem link>CAT1</MDBDropdownItem>
                  <MDBDropdownItem link>CAT2</MDBDropdownItem>
                  <MDBDropdownItem link>CAT3</MDBDropdownItem>
                </MDBDropdownMenu>
              </MDBDropdown>
            </MDBNavbarItem> */}
        
          {/* <MDBNavbarItem>
            <MDBNavbarLink active aria-current='page' href='/cart'>
              CART<a href="/cart"><i class="fas fa-shopping-cart text-dark"></i></a>
            </MDBNavbarLink>
          </MDBNavbarItem> */}
        <NavDropdown
      alignRight
      title="Select category"
      id="dropdown-menu-align-right"
      onSelect={handleSelect}
        >
              <Dropdown.Item eventKey="1">Fiction</Dropdown.Item>
              <Dropdown.Item eventKey="2">Classics</Dropdown.Item>
              <Dropdown.Item eventKey="3">Adventures</Dropdown.Item>
              <Dropdown.Divider />
      </NavDropdown>

          <MDBNavbarItem>
            <MDBNavbarLink href='/cart'>
            <a href="/cart"><i class="fas fa-shopping-cart text-dark"></i></a>
            </MDBNavbarLink>
          </MDBNavbarItem>
          {/* <MDBInputGroup tag="form" className='d-flex w-auto mb-3'>
              <input className='form-control' placeholder="Search Product" aria-label="Search" type='Search' />
              <MDBBtn outline >Search</MDBBtn>
            </MDBInputGroup> */}
        </MDBNavbarNav>
      </MDBCollapse>
    </MDBContainer>
  </MDBNavbar>
  </div>
  )
}
export default Header
