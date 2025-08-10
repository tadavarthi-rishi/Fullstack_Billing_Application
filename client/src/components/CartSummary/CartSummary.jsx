import './CartSummary.css'
import {useContext} from "react";
import {AppContext} from "../../Context/AppContext.jsx";

const CartSummary = ({customerName,mobileNumber,setMobileNumber,setCustomerName}) => {
    const {cartItems} = useContext(AppContext);
    const totalAmount = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    const tax = totalAmount * 0.18;
    const Grandtotal = totalAmount + tax;
    return (
        <div className={"mt-2"}>
            <div className="cart-summary-details">
                <div className="d-flex justify-content-between mb-2">
                    <span className={"text-light"}>Item: </span>
                    <span className="text-light">${totalAmount.toFixed(2)}</span>
                </div>
                <div className="d-flex justify-content-between mb-2">
                    <span className="text-light">Tax (0.18%)</span>
                    <span className="text-light">${tax.toFixed(2)}</span>
                </div>
                <div className="d-flex justify-content-between mb-4">
                    <span className="text-light">Grand Total</span>
                    <span className="text-light">${Grandtotal.toFixed(2)}</span>
                </div>
            </div>
        </div>
    )
}

export default CartSummary;