import './CartSummary.css'
import {useContext} from "react";
import {AppContext} from "../../Context/AppContext.jsx";
import ReceiptPopup from "../ReceiptPopup/ReceiptPopup.jsx";

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

            <div className="d-flex gap-3">
                <button className="btn btn-success flex-grow-1">
                    Credit Card
                </button>
                <button className="btn btn-primary flex-grow-1">
                    Debit Card
                </button>
            </div>
        <div className="d-flex gap-3 mt-2">
            <button className="btn btn-warning flex-grow-1">
                Place order
            </button>
        </div>
        </div>
    )
}

export default CartSummary;