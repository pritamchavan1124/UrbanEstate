// import React from "react"
import "./Home.css";
import axios from "axios";
import Home from "./Home";
import React, { useState } from "react";
import Login from "./Login";
import {navigate,useNavigate} from "react-router-dom";
import Header from "./Header";
import Footer from "./footer/Footer";
import { toast } from "react-toastify";


const SignUpSeller = () => {
  const [data, setData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    dob: "",
    mobNo: "",
    line1:"",
    line2:"",
    pincode:"",
    city: "",
    state:""
  });

  const [address, setAddress]=useState({
    line1:"",
    line2:"",
    pincode:"",
    city: "",
    state:""
  })

const handleChange = (e) => {
  const value = e.target.value;
      setData({
      ...data,
      [e.target.name]: value,
      
  });
  setAddress({...address, [e.target.name]: value}) //line1:, line2:data.line2, city:data.city, state:data.state, pincode:data.pincode})
};

let navigate=useNavigate();
const USER_API_BASE_URL = "http://localhost:8080/api/auth/owner/signup";
const handleSubmit = (e) =>
{
  e.preventDefault();
    
    const userData = {
      email: data.email,
      password: data.password,
      firstName: data.firstName,
      lastName: data.lastName,
      contactNumber: data.contactNumber,
      address:address,
      role:"ROLE_BUYER"
    };
  axios.post(USER_API_BASE_URL, userData).then(
    (response)=>{
        console.log(response);
        JSON.stringify(response);
        toast.success("You have been Registered successfully");
        navigate("/Login");
        
    },
    
).catch((error)=>
console.log(error.response),
{
  "firstName":"Please check FirstName cannot be less than 4 letters"

}
)
}


  return (
    <div>
<div>
<Header></Header>

    <div className="SignUp" >
      
      <div className="Auth-form-container"  >
      <div className=" col-md-4 col-lg-6 bg-image" style={{margin:"auto"}}>
      <div className="col-md-8 col-lg-6"></div>
      <div className="login d-flex align-items-center py-5" >
      <form className="Auth-form" onSubmit={handleSubmit} style={{marginTop:"100px", margin:"auto"}}>
      <h3 className="Auth-form-title">Register here</h3>
      <div className="form-group mt-2"></div>
      <label>Email address</label>
          <input
            type="email"
            name="email"
            value={data.email}
            onChange={handleChange}
            className="form-control mt-2"
            placeholder="Enter Email" style={{width:"400px" ,margin:"auto"}}
          />
          <div className="form-group mt-2" >
          <label style={{textAlign : 'left'}}>Password</label>
            <input
               type="password"
                name="password"
                value={data.password}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter Password"style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            < div className="form-group mt-3" >
            <label style={{textAlign : 'left'}}>First Name</label>
            <input
               type="text"
                name="firstName"
                value={data.firstName}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter Firstname" style={{width:"400px" ,margin:"auto"}}
              />
            
            
            <label style={{textAlign : 'left'}}>Last Name</label>
            <input
               type="text"
                name="lastName"
                value={data.lastName}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter Lastname" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>Mobile Number</label>
            <input
               type="text"
                name="contactNumber"
                value={data.contactNumber}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter Mobile Number" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>Address Line 1</label>
            <input
               type="text"
                name="line1"
                value={address.line1}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter address line1" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>Address Line 2</label>
            <input
               type="text"
                name="line2"
                value={address.line2}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter state" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>City</label>
            <input
               type="text"
                name="city"
                value={address.city}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter city" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>State</label>
            <input
               type="text"
                name="state"
                value={address.state}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter state" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            <div className="form-group mt-2" >
            <label style={{textAlign : 'left'}}>Pincode</label>
            <input
               type="text"
                name="pincode"
                value={address.pincode}
                onChange={handleChange}
                className="form-control mt-1"
                placeholder="Enter state" style={{width:"400px" ,margin:"auto"}}
              />
            </div>
            
            <div/>
            
        <br></br>
        <button type="submit" className="btn btn-dark">Sign Up</button>
        
        <p className="forgot-password text-right mt-2">
        <p>Already Registered ?</p>
        <a href="Login" style={{textDecoration:"None"}}>Login here</a>
        </p>
        
        
      </form>
      </div>
      </div>
      </div>
    </div>
    
    
    </div>
    <br></br><br></br><br></br><br></br><br></br><br></br>
    <Footer></Footer>
    </div>
  );

}
export default SignUpSeller;

  