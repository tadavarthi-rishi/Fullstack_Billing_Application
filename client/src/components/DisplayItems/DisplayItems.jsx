import './DisplayItems.css'
import {useContext, useState} from "react";
import {AppContext} from "../../Context/AppContext.jsx";
import Item from "../Item/Item.jsx";
import SearchBox from "../SearchBox/SearchBox.jsx";

const DisplayItems = () => {
    const {itemsData} = useContext(AppContext);
    const [searchText, setSearchText] = useState('');
    const filteredItems = itemsData.filter((item) =>{
        return item.name.toLowerCase().includes(searchText.toLowerCase());
    })
    return (
                <div className="p-3">
                    <div className="d-flex justify-content-between align-items-center mb-4 border-bottom">
                        <div>

                        </div>
                        <div>
                            <SearchBox onSearch={setSearchText}/>
                        </div>
                    </div>
                    <div className="row g-3">
                        {filteredItems.map((item, index) => (
                            <div key = {index} className="col-md-4 col-sm-6">
                                <Item
                                itemName = {item.name}
                                itemImage = {item.imgUrl}
                                itemPrice = {item.price}
                                itemId = {item.itemId}
                                />
                            </div>
                        ))}
                    </div>
                </div>
    )
}

export default DisplayItems;