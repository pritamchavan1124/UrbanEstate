import React, { useEffect, useState } from "react";
import AllProperties from "./AllProperties";
import Products from "./Properties";
import Header from "./Header";
import "./Home.css";
import Footer from "./footer/Footer";
import Login from "./Login";
import SignUp from "./SignUp";
import Example from "./Header";
import Crousel from "./Crousel";
import { useNavigate } from "react-router-dom";
import Admin from './Admin';
import Featured from "./featured/Featured"
const Home = () => {
  let userRole = JSON.parse(sessionStorage.getItem("user"));
  
  let navigate = useNavigate();

//   useEffect(() => {
//     if (userRole.role === null) {
//       navigate("/Home");
//     } else if(userRole.role === 'ROLE_ADMIN') {
//       navigate("/Admin");
//     }
//   }, []);

  return (
    <div className="Home">
      {
        <div>
          <Header />
          <div className="title" style={{ margin: "50px" }}>
          </div>
          <h1 style={{fontFamily:"fantasy"}}>Search for your Properties ends here..</h1>
          {!sessionStorage.getItem("token")&&
          <Crousel></Crousel>}
          {!sessionStorage.getItem("token")&&
          <Featured></Featured>}
          {/* {sessionStorage.getItem("token")&& */}
          <AllProperties />
                <br></br>
                <br></br>
                <br></br>
          <Footer/>
        </div>
      }
    </div>
  );
};
export default Home;

//
