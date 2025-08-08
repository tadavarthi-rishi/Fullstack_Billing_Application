import './SearchBox.css'
import {useState} from "react";

const SearchBox = ({onSearch}) => {
    const [searchText, setSearchText] = useState('');
    const handleInputChange = (e) => {
        setSearchText(e.target.value);
        onSearch(e.target.value);
    }
    return (
        <div className="input-group mb-3">
            <input type="text" className="form-control" placeholder="Search items..." value = {searchText} onChange={handleInputChange}/>
            <button className="btn btn-outline-secondary" type="button" id="button-addon2">Search</button>
        </div>
    )
}

export default SearchBox;