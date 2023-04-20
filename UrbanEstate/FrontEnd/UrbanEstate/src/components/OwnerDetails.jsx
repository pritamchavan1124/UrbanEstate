import React, { useEffect, useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate, useParams } from "react-router-dom";
import { toast } from 'react-toastify';
import Header from './Header';
import Footer from './footer/Footer';
const OwnerDetails = ({ }) => {
  let token= sessionStorage.getItem("token");
  const [owner, setOwner]=useState({
    firstName:"",
    lastName:"",
    email:"",
    contactNumber:""
  });
    let {id} = useParams();
    
     let URL="http://localhost:8080/owner/get/"+id;
    useEffect(()=>{
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
    },[])
  return (
    <div>
    <Header />
    {/* <Container className="mt-4">
      <Row>
        <Col>
          <h4>Property Owner Details</h4>
        </Col>
      </Row>
      <Row>
        <Col>
          <p><strong>Name:</strong> {owner.firstName}&nbsp;{owner.lastName}</p>
          <p><strong>Email:</strong> {owner.email}</p>
          <p><strong>Phone:</strong> {owner.contactNumber}</p>
        </Col>
      </Row>
    </Container> */}
    <div class="card text-white bg-dark mb-3" style={{"max-width": "18rem", margin:"auto" , marginTop:"50px"}}>
  <div class="card-header"><b>Property Owner Details</b></div>
  <div class="card-body">
    <h5 class="card-title">Name:{owner.firstName}&nbsp;{owner.lastName}</h5>
    <p><strong>Email:</strong> {owner.email}</p>
    <p><strong>Phone:</strong> {owner.contactNumber}</p>  </div>
    <br></br>
   
</div>
<button className='btn btn-dark'><a href='/' style={{color:"white", textDecoration:"none"}}>Back</a></button> <br></br> <br></br>
    <Footer/>
    </div>
  );
};

export default OwnerDetails;
