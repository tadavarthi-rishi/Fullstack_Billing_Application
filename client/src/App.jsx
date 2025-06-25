import Menubar from "./components/Menubar/Menubar.jsx";
import Dashboard from "./pages/Dashboard/Dashboard.jsx";
import ManageCategory from "./pages/ManageCategory/ManageCategory.jsx";
import ManageUsers from "./pages/ManageUsers/ManageUsers.jsx";
import ManageItems from "./pages/ManageItems/ManageItems.jsx";
import Explore from "./pages/Explore/Explore.jsx";
import { Routes, Route } from "react-router-dom";

const App = () => {
    return (
        <div>
            <Menubar />
            <Routes>
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/category" element={<ManageCategory />} />
                <Route path="/users" element={<ManageUsers />} />
                <Route path="/items" element={<ManageItems />} />
                <Route path="/explore" element={<Explore />} />
                <Route path="/" element={<Dashboard />} />
            </Routes>
        </div>
    );
}

export default App;
 