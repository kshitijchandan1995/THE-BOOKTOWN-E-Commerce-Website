import logo from './logo.svg';
import './App.css';
import Home from './components/Home';
import Header from './components/Header';
import Footer from './components/Footer';
import Registration from './components/Registration';
import {Link , Route , Routes ,BrowserRouter } from 'react-router-dom'
import Login from './components/Login';
import AuthorizationRouter from './components/AuthorizationRouter';
import Cart from './components/Cart';
import ShippingAdress from './components/ShippingAdress';
import Payment from './components/Payment';
import Vendor from './components/dashboard/Vendor';
import UpdateProduct from './components/UpdateProduct';
import UserList from './components/UserList';
import ProductList from './components/ProductList';
import RegardPage from './components/RegardPage';
import ProductAdd from './components/ProductAdd';
import VendorProductList from './components/VendorProductList';
import ProductDetails from './components/ProductDetails';
import ProductlistbyCategory from './components/ProductlistbyCategory';
import ForgotPassword from './components/ForgetPassword';

function App() {
  return (
    
    <div className="App">
       <Header></Header>
       <hr></hr>
       <hr></hr>
                <Routes>
                    <Route path="/" element={<Home></Home>}></Route>
                    <Route path="/home" element={<Home></Home>}></Route>
                    <Route path="/login" element={<Login></Login>}></Route>
                    <Route path="/dashboard" element={<AuthorizationRouter></AuthorizationRouter>}></Route>
                    <Route path="/registration" element={<Registration></Registration>}></Route>
                    <Route path="/cart" element={<Cart></Cart>}></Route>
                    <Route path="/shipmentAddress" element={<ShippingAdress></ShippingAdress>}></Route>
                    <Route path="/paymentpage" element={<Payment></Payment>}></Route>
                    <Route path="/vendor" element={<Vendor></Vendor>}></Route>
                    <Route path="/userList" element={<UserList></UserList>}></Route>
                    <Route path="/productList" element={<ProductList></ProductList>}></Route>
                    <Route path="/regardpage" element={<RegardPage></RegardPage>}></Route>
                    <Route path="/ProductAdd" element={<ProductAdd></ProductAdd>}></Route>
                    <Route path="/productListVendor" element={<VendorProductList></VendorProductList>}></Route>
                    <Route path="/product/:productId" element={<UpdateProduct></UpdateProduct>}></Route>
                    <Route path="/productDetails/:productId" element={<ProductDetails></ProductDetails>}></Route>
                    <Route path="/productListcategory" element={<ProductlistbyCategory></ProductlistbyCategory>}></Route>
                    <Route path="/forgetPassword" element={<ForgotPassword></ForgotPassword>}></Route>

                </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
