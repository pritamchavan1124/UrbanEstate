import React from "react";
import Footer from "./footer/Footer";
import Header from "./Header";
import {
  Jumbotron,
  Card,
  CardImg,
  CardText,
  CardImgOverlay,
  Container,
  Form,
  Row,
  Table,
  CardTitle,
} from "reactstrap";
const AboutUs = () => {
  return (
    <div >
      <Header />
      <Container>
        <div className="fluid-container row" style={{ alignItems: "left" }}>
          {/* First Card */}
          <div >
            
              <CardTitle><h1 style={{marginTop:"20px"}}>UrbanEstate</h1></CardTitle>
             
          </div>
          <Card
            style={{
              backgroundColor: "transparent",
              color: "light",
              width: "25rem",
             border:"none",
              padding: "20px",
              marginLeft:"150px",
              margin: "100px",
            }}
          >
            <h5 className="mt-2">Ajinkya Rokade</h5>
            <CardText className="mb-2 text-muted">Business Partner</CardText>
            <CardText className="mb-2 text-muted">
              Phone: +91-7350396866
            </CardText>
            <CardText className="mb-2 text-muted">
              Email: ajinkyarokade1010@gmail.com
            </CardText>
          </Card>

          <Card
            style={{
              backgroundColor: "transparent",
              color: "light",
              width: "25rem",
             border:"none",
              padding: "20px",
              margin: "100px",
            }}
          >
            <h5 className="mt-2">Nikhil Dhage</h5>
            <CardText className="mb-2 text-muted">Business Partner</CardText>
            <CardText className="mb-2 text-muted">
              Phone: +91-9881020917
            </CardText>
            <CardText className="mb-2 text-muted">
              Email: nikhildhage1@gmail.com
            </CardText>
          </Card>
        </div>
      </Container>
            <br></br><br></br><br></br>
      <Footer />
    </div>
  );
};
export default AboutUs
;
