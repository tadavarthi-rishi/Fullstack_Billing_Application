import Menubar from "./components/Menubar/Menubar.jsx";
import Dashboard from "./pages/Dashboard/Dashboard.jsx";
import ManageCategory from "./pages/ManageCategory/ManageCategory.jsx";
import ManageUsers from "./pages/ManageUsers/ManageUsers.jsx";
import ManageItems from "./pages/ManageItems/ManageItems.jsx";
import Explore from "./pages/Explore/Explore.jsx";
import { Routes, Route, useLocation } from "react-router-dom";
import {Toaster} from "react-hot-toast";
import Login from "./pages/Login/Login.jsx";
import OrderHistory from "./pages/OrderHistory/OrderHistory.jsx";
import {useContext} from "react";
import {AppContext} from "./Context/AppContext.jsx";

const App = () => {
    const location = useLocation();
    const {auth} = useContext(AppContext);

    const  LoginRoute = ({element}) => {
        if(auth.token){
            return <Navigate to = "/dashboard" replace />;
        }
        return element;
    }

    const ProtectedRoute = ({element, allowedRoles}) => {
        if(!auth.token){
            return <Navigate to = "/login" replace />;
        }

        if(allowedRoles && !allowedRoles.includes(auth.role)) {
            return <Navigate to = "/dashboard" replace />;
        }
        return element;
    }

    return (
        <div>
            {location.pathname !== "/login" && <Menubar />}
            <Toaster />
            <Routes>
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/category" element={<ManageCategory />} />
                <Route path="/users" element={<ManageUsers />} />
                <Route path="/items" element={<ManageItems />} />
                <Route path="/explore" element={<Explore />} />
                <Route path="/" element={<Dashboard />} />
                <Route path={"/login"} element={<Login />} />
                <Route path={"/orders"} element={<OrderHistory />} />


            </Routes>
        </div>
    );
}

export default App;
 