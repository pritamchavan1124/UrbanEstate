import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardTitle, Container } from "reactstrap";
import Footer from "./footer/Footer";
import Header from "./Header";
import './Home.css';
import { toast } from "react-toastify";


const PropertyDetails=()=>{
    const[date, setDate]=useState("");
  const [dateFlag, setDateFlag]=useState(false);
    let navigate=useNavigate();
    const sendBack=()=>{
        navigate('/');
    }

    let token= sessionStorage.getItem("token");
    let [property,setProperty]=useState({address:{line1:"", line2:"", city:"", state:"", pincode:""}});
    let {id} = useParams();
    console.log("ajinkya")
    console.log({id});
     let URL="http://localhost:8080/properties/get/"+id;


     const datechange=(e)=>{
        setDate(e.target.value);
        console.log(date);
      }


      const bookAppointment=(id)=>{
        if (sessionStorage.getItem("token")) {
            console.log("user is logged in");
            if(!dateFlag){
                setDateFlag("true");}
                else{
                let token = sessionStorage.getItem("token");
                let userid = sessionStorage.getItem("userID");
                
                axios.post("http://localhost:8080/appointment/book/"+id+"/"+userid+"/"+date, {
                  headers: { Authorization: token },}).then((response)=>{
                    toast.warning(response.data)
                    navigate('/')
                  },(error)=>{
                    toast.warning("appointment already booked");
                })
              }
          } else {
            console.log("user is not logged in");
            // alert("Kindly Login First");
            toast.warning("Kindly Login First");
            navigate("/Login");
          }
        ;
       
      }


    useEffect(()=>{
     axios.get(URL,{
        headers:{
            Authorization:token
        }
     }).then(
        (response)=>{
        console.log(response.data)
        setProperty(response.data)
        }
     )
    },[])
    const showOwnertDetails=(id)=>{
        if (sessionStorage.getItem("token")) {
            console.log("user is logged in");
            navigate("/OwnerDetails/"+id)
          } else {
            console.log("user is not logged in");
            // alert("Kindly Login First");
            toast.warning("Kindly Login First");
            navigate("/Login");
          }
        ;
        
      }
    return(
        <div className="Home">
            <Header/>
                <h1  style={{margin:"30px", fontFamily:"fantasy"}}>Property Details</h1>
                <Container>
                    <Card>
                    <div className="productDetails" >
                    <img style={{margin:"auto", width:"900px"}} src={property.imageURL}/>
                    </div>
                    <div className="title">
                        <h4>Name : {property.name}</h4>
                        <h4>Description : {property.description}</h4>
                        <h4>Type of property : {property.propType}</h4>
                        <h4>For : {property.propertyFor}</h4>
                        <h4>Carpet Area : {property.area}</h4>
                        <h4>No Of bedrooms : {property.bedrooms}</h4>
                        <h4>Address : {property.address.line1}, {property.address.line1}, {property.address.city}, {property.address.state}, {property.address.pincode}</h4>
                        <h4>Price : {property.price}</h4>
                        <h4>Amenities : {property.amenities}</h4>
                    </div>
                    </Card>
                    <div style={{margin:"30px"}}>
                    <Button size="lg" className="btn btn-dark" onClick={sendBack}> Back</Button>&nbsp;
                    <Button  size="lg" className="btn btn-dark" onClick={()=>showOwnertDetails(property.id)}>Contact Owner</Button><br></br>
                    {dateFlag==="true"&&( <div> <br></br><div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true"/>
  <input placeholder="Select date" type="date" id="example" class="form-control" name="dates" onChange={datechange}/></div>)}<br></br>
                    <Button size="lg" className="btn btn-dark" onClick={()=>bookAppointment(property.id)}>Book appointment</Button><br></br><br></br>
                    </div>
                    
                </Container>
            <Footer/>
        </div>
    )
}
export default PropertyDetails;

//{product.productName}