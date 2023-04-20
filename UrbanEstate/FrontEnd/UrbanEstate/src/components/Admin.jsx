import { useNavigate } from "react-router-dom";
import { Button, Card, Container } from "reactstrap";
import Footer from "./footer/Footer";
import Header from "./Header";
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer,
    AreaChart,
    Area,
    BarChart,
    Bar,
  } from "recharts";
import AllProperties from "./AllProperties";

const Admin = () => {
  let navigate = useNavigate();
  const data = [
    {
      "name": "JAN",
      
      "UC": 2400,
      "amt": 2400
    },
    {
      "name": "FEB",
      "UC": 1398,
      "amt": 2210
    },
    {
      "name": "MAR",
      "UC": 9800,
      "amt": 2290
    },
    {
      "name": "APR",
      
      "UC": 3908,
      "amt": 2000
    },
    {
      "name": "MAY",
      
      "UC": 4800,
      "amt": 2181
    },
    {
      "name": "JUN",
      
      "UC": 3800,
      "amt": 2500
    },
    {
      "name": "JUL",
     
      "UC": 4300,
      "amt": 2100
    },
    {
        "name": "AUG",
        
        "UC": 4000,
        "amt": 3300
      }
  ]
  const addShipment = () => {};
  return (
    <div style={{backgroundRepeat:"no-repeat",backgroundSize:" 1000px", margin:"auto"}} >

      <Header />

      <div style={{ margin: "auto" }}>
        <Container>
          <Card style={{height:"300px",background:"transparent"}}>
        <div>
            <h1>Yearly Traffic</h1>
        </div>
        <AreaChart
          width={1000}
          height={250}  
          data={data}
          style={{margin:"auto"}}
        >
          <defs>
            <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8} />
              <stop offset="95%" stopColor="#8884d8" stopOpacity={0} />
            </linearGradient>
            <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8} />
              <stop offset="95%" stopColor="#82ca9d" stopOpacity={0} />
            </linearGradient>
          </defs>
          <XAxis dataKey="name" />
          <YAxis />
          <CartesianGrid strokeDasharray="3 3" />
          <Tooltip />
          <Area
            type="monotone"
            dataKey="UC"
            stroke="#8884d8"
            fillOpacity={1}
            fill="url(#colorUv)"
          />
         
        </AreaChart>
        </Card>
        </Container>
        <AllProperties></AllProperties>
      </div>
      <Footer />
    </div>
  );
};
export default Admin;
