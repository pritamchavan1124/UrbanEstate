import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Footer from "./footer/Footer";
import Header from "./Header";
import { toast } from 'react-toastify';
const Appointments =()=>{

    let token = sessionStorage.getItem("token");
    let userid = sessionStorage.getItem("userID");
    const [owner , setOwner]=useState({firstName:"", lastName:"", email:"", contactNumber:""});
    const [appt, setAppt]=useState([]);
    const navigate=useNavigate();
    useEffect(()=>{
        axios.get("http://localhost:8080/appointment/showbuyer/"+userid,{headers:{
            Authorization:token
        }}).then((response)=>{
            console.log(response.data)
            setAppt(response.data);
        })

    },[])
    const cancelAppointment=(id)=>{
        axios.delete("http://localhost:8080/appointment/cancel/"+id,{headers:{
            Authorization:token
        }}).then((response)=>{
            console.log(response.data)
            
            window.location.reload();
            toast.success("Appointment has been cancelled");
           
        })
    }

    return(
        <div>
            <Header></Header>
            {appt.length!=0 &&(
            <div>
            <h1>All Appointments</h1>
            <div className="container">
                <div className="row">
                
                    {appt.map((apps) => (
                        <div className="col-md">
                            <div class="card text-white bg-dark mb-3" style={{ "max-width": "18rem", margin: "auto", marginTop: "50px" }}>
                                <div class="card-body">
                                    <h5 class="card-title">Property:{apps.property.description}</h5>
                                    <p><strong>Owner:</strong> {apps.buyer.firstName}&nbsp;{apps.buyer.lastName}</p>
                                    <p><strong>Date:</strong> {apps.date}</p>
                                    <p><strong>Status:</strong> {apps.status}</p></div>
                                    

                                <button className="btn btn-success" onClick={() => cancelAppointment(apps.id)}>Cancel</button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            </div>
            )}
            {appt.length==0 && (<div style={{marginTop:"300px"}}><h3>No appointments pending</h3>
            <button className="btn btn-dark"><a href="/Wishlist" style={{color:"white", textDecoration:"none"}}>Book appointments</a></button></div>
            )}<br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
            <Footer></Footer>
        </div>
    );
};
export default Appointments;