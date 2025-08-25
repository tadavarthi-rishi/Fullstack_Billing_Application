import "./Dashboard.css"
import {useEffect, useState} from "react";
import {fetchDashboardData} from "../../service/Dashboard.js";
import toast from "react-hot-toast";

const Dashboard = () => {
    const [data,setData] = useState(null);
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        const loadData = async () => {
            try{
                const response = await  fetchDashboardData();
                setData(response.data);
            } catch(error){
                console.log(error);
                toast.error("Unable to fetch data");
            } finally {
                setLoading(false);
            }
        }
        loadData();
    }, []);

    return (
        <div>Dashboard</div>
    )
}

export default Dashboard;