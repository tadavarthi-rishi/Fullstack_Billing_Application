import './CartSummary.css'
import {useContext, useState} from "react";
import {AppContext} from "../../Context/AppContext.jsx";
import ReceiptPopup from "../ReceiptPopup/ReceiptPopup.jsx";
import {createOrder, deleteOrder} from "../../service/OrderService.js";
import toast from "react-hot-toast";
import {createRazorpayOrder, verifyPayment} from "../../service/PaymentService.js";
import {AppConstants} from "../../util/constants.js";

const CartSummary = ({customerName,mobileNumber,setMobileNumber,setCustomerName}) => {
    const {cartItems, clearCart} = useContext(AppContext);

    const [isProcessing, setIsProcessing] = useState(false);
    const [orderDetails, setOrderDetails] = useState(null);
    const [showPopup, setShowPopup] = useState(false);

    const totalAmount = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    const tax = totalAmount * 0.18;
    const Grandtotal = totalAmount + tax;

    const clearAll = () => {
        setCustomerName("");
        setMobileNumber("");
        clearCart();
    }

    const placeOrder = () => {
        setShowPopup(true);
        clearAll();
    }

    const handlePrintReceipt = () => {
        window.print();
    }

    const loadRazorpayScript = () => {
        return new Promise((resolve, reject) => {
            const script = document.createElement('script');
            script.src = 'https://checkout.razorpay.com/v1/checkout.js';
            script.async = true;
            script.onload = () => resolve(true);
            script.onerror = () => resolve(false);
            document.body.appendChild(script);
        });
    }

    const deleteOrderOnFailure = async (orderId) => {
        try{
            await deleteOrder(orderId);
        } catch (e){
            console.error(e);
            toast.error("Unable to delete order something went wrong");
        }
    }

    const completePayment = async (paymentMode) => {

        if(!customerName || !mobileNumber) {
            toast.error("Please enter customer details");
            return;
        }
        if(cartItems.length ===0){
            toast.error("Please add items to cart");
            return;
        }

        const orderData ={
            customerName,
            phoneNumber: mobileNumber,
            cartItems,
            subtotal: totalAmount,
            tax,
            Grandtotal,
            paymentMethod: paymentMode.toUpperCase()
        }
        setIsProcessing(true);
        try{

        const response = await createOrder(orderData);
        const savedData = response.data;
        if(response.status === 201 && paymentMode === "credit"){
            toast.success("Order placed successfully");
            setOrderDetails(savedData)

        } else if(response.status === 201 && paymentMode === "debit") {
            const razorpayLoaded = await loadRazorpayScript();
            if (!razorpayLoaded) {
                toast.error("Unable to load razorpay");
                await deleteOrderOnFailure(savedData.orderId);
                return;
            }

            //create razorpay order
            const razorpayResponse = await createRazorpayOrder({amount: Grandtotal, currency: "INR"});
            const options = {
                key: AppConstants.RAZORPAY_KEY_ID,
                amount: razorpayResponse.data.amount,
                currency: razorpayResponse.data.currency,
                order_id: razorpayResponse.data.id,
                name: "E-Commerce",
                description: "Payment for order",
                handler: async function (response) {
                    //TODO: verify the payment
                    await verifyPaymentHandler(response,savedData);
                },
                prefill: {
                    name: customerName,
                    contact: mobileNumber
                },
                theme: {
                    color: "#3399cc"
                },
                modal: {
                    ondismiss: async () => {
                        await deleteOrderOnFailure(savedData.orderId);
                        toast.error("Payment failed");
                    }
                },
                method:{
                    netbanking: true,
                    card: true,
                    upi: true,
                    wallet: true,
                    emi: true,
                    paylater: true,
                }
            };
            const rzp = new window.Razorpay(options);
            rzp.on("payment.failed", async (response) => {
                await deleteOrderOnFailure(savedData.orderId);
                toast.error("Payment failed");
                console.error(response.error.description);
            });
            rzp.open();
        }
        }catch(error){
            console.error(error);
            toast.error("payment processing failed");
        } finally {
            setIsProcessing(false);
        }
    }

    const verifyPaymentHandler = async (response, savedOrder) => {
        const paymentData = {
            razorpayOrderId: response.razorpay_order_id,
            razorpayPaymentId: response.razorpay_payment_id,
            razorpaySignature: response.razorpay_signature,
            orderId: savedOrder.orderId
        };
        try{
            const paymentResponse = await verifyPayment(paymentData);
            if(paymentResponse.status === 200){
                toast.success("Payment successful");
                setOrderDetails({
                    ...savedOrder,
                    paymentDetails: {
                        razorpayOrderId: response.razorpay_order_id,
                        razorpayPaymentId: response.razorpay_payment_id,
                        razorpaySignature: response.razorpay_signature,
                    },
                });
            } else {
                toast.error("Payment processing failed");
            }
        } catch (e){
            console.error(e);
            toast.error("Payment failed");
        }
    };

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
                <button className="btn btn-success flex-grow-1"
                onClick={() => completePayment("credit")}
                >
                    {isProcessing ? "Processing...": "CASH"}
                </button>
                <button className="btn btn-primary flex-grow-1"
                        onClick={() => completePayment("debit")}
                        disabled = {isProcessing}
                >
                    {isProcessing ? "Processing...": "Debit Card"}
                </button>
            </div>
        <div className="d-flex gap-3 mt-2">
            <button className="btn btn-warning flex-grow-1"
            onClick={placeOrder}
            disabled={isProcessing || !orderDetails}
            >
                Place order
            </button>
        </div>
        </div>
    )
}

export default CartSummary;