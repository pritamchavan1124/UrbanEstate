import React, { useState, useEffect } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";

import Footer from "./footer/Footer";
import Header from "./Header";
import { Button, Container, Nav, Table } from "reactstrap";
import "./Home.css";
import { toast } from "react-toastify";


const USER_API_BASE_URL = "http://localhost:8080/buyer/";

const Remove_Cart = "http://localhost:8080/medi/api/cart/remove/";
const Remove_Cart_Items = "http://localhost:8080/medi/api/cart/remove";

const Wishlist = () => {

  let navigate = useNavigate();
  const[date, setDate]=useState("");
  const [dateFlag, setDateFlag]=useState(false);
  const [Count, setCount] = useState(0);
  const [properties, setProperties] = useState([]);
  useEffect(() => {
    getWishlist() 
  },[Count])

//============================================================================================================
  
    const removeFromWishlist = (id) => {
    const token = sessionStorage.getItem("token");
    let cid = sessionStorage.getItem("userID");
    let navigate = useNavigate;
    let propertyID = id;
    let customerID = cid;

    const options = {
      method: "GET",
      url: "http://localhost:8080/buyer/property/removewishlist/"+cid+"/"+id,
      headers: {
        Authorization: token,
      },
      // data: { productId: productID, customerId: customerID },
    };

    axios
      .request(options)
      .then(function (response) {
        console.log(response.data);
        toast.success("Property has been removed from wishlist");
        // getCartItems();
      
           window.location.reload();
           
        
      })
      .catch(function (error) {
        console.error(error);
      });
  };
  
//===========================================================================================================

 
  useEffect(() => {
    getWishlist();
  }, []);
//==================================================================================================================
const datechange=(e)=>{
  setDate(e.target.value);
  console.log(date);
}
  //===========================================================================================================
  const getWishlist = () => {
    let id = sessionStorage.getItem("userID");
    console.log(id);
    let token = sessionStorage.getItem("token");
    console.log(token);
    if (sessionStorage.getItem("token")) {
      axios
        .get(USER_API_BASE_URL + "property/getWishlist/"+id, {
          headers: { Authorization: token },
        })
        .then((response) => {
          console.log(response);
          JSON.stringify(response);
          setProperties(response.data);
          let properties = response.data;
          sessionStorage.setItem("properties", properties);
        });
    } else {
      console.error("unexpected error");
    }
  };
  // ==========================================

  const showOwnertDetails=(id)=>{

    navigate("/OwnerDetails/"+id)
  }
  //================================
const bookAppointment=(id)=>{
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
}

//==================================================================================================================
  return (
    <div className="Home">
      <Header />
       
        {
          properties.length==0 && <div className="title" style={{marginTop:"300px"}}><h4>Your Wishlist is Empty</h4> <br/>
          <a href="/">
          <Button color="success">Search for Properties</Button>
          </a>
          </div>
        }
        
        {properties.length > 0 && <>
        <div style={{display:"flex"}}>
        <Container>
        <div className="product-container" style={{margin:"50px"}}><h4>Lets Book your new Property</h4></div>

            <div className="container">
              <div className="row">
              { properties.map((item) => (
                <div className="col-md">
                <div class="card" style={{ "width": "18rem", "margin": "auto" }}>
                  <img class="card-img-top" src={item.imageURL} alt="Card image cap" />
                  <div class="card-body">
                    <h5 class="card-title">{item.propType}</h5>
                    <span style={{ background: item.propertyFor === "SELL" ? "#25b5791a" : "#ff98001a", color: item.propertyFor === "SELL" ? "#25b579" : "#ff9800" }}>{item.propertyFor}</span>

                    <p class="card-text">{item.description}</p>
                    <h5 class="card-title">{item.price}&nbsp;&nbsp; ₹</h5>
                    <h5 class="card-title">{item.area}&nbsp;&nbsp;SqFt</h5>
                    <hr></hr>
                    <button className="btn btn-dark" onClick={()=>showOwnertDetails(item.id)}>Contact Owner</button><br></br><br></br>
                    {dateFlag==="true"&&( <div> <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true"/>
  <input placeholder="Select date" type="date" id="example" class="form-control" name="dates" onChange={datechange}/></div>)}<br></br>
                    <button className="btn btn-dark" onClick={()=>bookAppointment(item.id)}>Book appointment</button><br></br><br></br>
                    <a onClick={() => removeFromWishlist(item.id)}><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                      </svg></a> &nbsp;
                  </div>
                </div>
                </div>
                ))
                  }
                  </div>
                  </div><br></br><br></br><br></br>
                  <a href="/">
          <Button color="dark">Search for More Properties</Button>
          </a>
        </Container>
      </div>
      </>}
      <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br>
      <Footer />
    </div>

  );
};
export default Wishlist;
