import React, { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Image } from "react-bootstrap";
import {
  Card,
  CardBody,
  //CardTitle,
  CardSubtitle,
  CardText,
  //CardFooter,
  Button,
  CardImg,
  //Container,
} from "reactstrap";

const USER_API_BASE_URL = "http://localhost:8080/";
const Properties = ({ prop }) => {

  let counter=2;
  const [owner, setOwner]=useState({id:""});
  const sendId = (id) => {
    navigate("/PropertyDetails/"+id);
  };

  const navigate = useNavigate();
  let userRole = JSON.parse(sessionStorage.getItem("user"));
  const addToWishlistHandler = () => {
    // if (userRole.role === "ROLE_ADMIN") {
    //   navigate("/Admin");
    // } 
    if (sessionStorage.getItem("token")) {
      console.log("user is logged in");
      if(userRole==='ROLE_ADMIN'){
        navigate('/Admin')
      }
      toast.success("Property added to wishlist successfully");

      axios.get(USER_API_BASE_URL+"buyer/property/addwishlist/"+sessionStorage.getItem("userID")+"/"+prop.id).then((res) => {
        console.log(res.data);
      });
    } else {
      console.log("user is not logged in");
      // alert("Kindly Login First");
      toast.warning("Kindly Login First");
      navigate("/Login");
    }
  };

  const RemoveHandler = (id) => {
    // if (userRole.role === "ROLE_ADMIN") {
    //   navigate("/Admin");
    // } 
    let token= sessionStorage.getItem("token");
    if (sessionStorage.getItem("token")) {
      console.log("user is logged in");
      let URL="http://localhost:8080/owner/get/"+id;
      axios.get(URL,{
        headers:{
            Authorization:token
        }
     }).then(
        (response)=>{
        console.log(response.data)
        setOwner(response.data)
        }
     )
  

      axios.delete(USER_API_BASE_URL+"owner/property/"+owner.id+"/"+id).then((res) => {
        console.log(res.data);
        toast.success("Property Removed Successfully");
        window.location.reload();
      });
    } else {
      console.log("user is not logged in");
      // alert("Kindly Login First");
      toast.warning("Kindly Login First");
      navigate("/Login");
    }
  };
  return (
    <div style={{ backgroundColor: "transparent" }}>
      <Card
        onClick={() => sendId(prop.id)}
        style={{
          backgroundColor: "white",
          color: "light",
          width: "18rem",
          height: "30rem",
          margin: "-5px",
          // padding: "-2px"
        }}
      >
        <CardSubtitle className="title">
          <a href="/PropertyDetails"></a> {prop.propType}
        </CardSubtitle>
        <Image src={prop.imageURL} style={{margin:"auto"}} className="cardImage"></Image>
        {/* <img alt="Sample" style={{margin:"auto"}} className="cardImage" src={require('../images/p-2.jpg')} /> */}
        <span style={{ background: prop.propertyFor === "SELL" ? "#25b5791a" : "#ff98001a", color: prop.propertyFor === "SELL" ? "#25b579" : "#ff9800", width:"50px", margin:"auto" }}><b>{prop.propertyFor}</b></span>
        <CardText className="body"><b>{prop.description} </b></CardText>
        <CardText className="body"><b>{prop.area} &nbsp;Sqft</b></CardText>
        {prop.propertyFor==="SELL"&&(
        <CardText className="body"><b>{prop.price}₹/Sqft</b></CardText>)}
         {prop.propertyFor==="RENT"&&(
        <CardText className="body"><b>{prop.price}₹/Pm</b></CardText>)}
        <CardText className="body"><b>{prop.address.city}</b></CardText>
       <br></br>
       </Card>
       <br></br>
       {(sessionStorage.getItem("token") && sessionStorage.getItem("userRole").includes("ADMIN")) 
       ?
       <button className="btn btn-warning" onClick={()=>RemoveHandler(prop.id)}
       >Remove</button>
      
      :<Button
      style={{ margin: "5px" }}
      color="primary"
      onClick={addToWishlistHandler}
    >
      Add to Wishlist
    </Button>
}  
    </div>
  );
};
export default Properties;
