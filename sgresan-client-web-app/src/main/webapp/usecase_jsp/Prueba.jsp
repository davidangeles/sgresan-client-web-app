<%-- 
    Document   : Prueba
    Created on : 02/10/2014, 10:24:07 AM
    Author     : Joel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                    
        
            <title>JSP Page</title>
        </head>
        <body   >
 
            <form method="POST">
                <div id="request_form">
                    <div class="params">
                        <div class="param_name">
                            <b>BuyerMail</b>
                        </div>
                        <div class="param_value">
                            <input type="text" name="buyerMail"
                                   value="laquerenciahermanos@hotmail.com" size="50" maxlength="260" />
                        </div>
                    </div>
                    <br>
                    <div class="section_header">
                        <b><u>Payment Details:</u>
                        </b>
                    </div>
                    <br>


                    <div class="params">
                        <div class="param_name">Order description</div>
                        <div class="param_value">
                            <textarea cols="40" rows="5" name="orderDescription">Express Checkout</textarea>
                        </div>
                    </div>
                    <div class="params">
                        <div class="param_name">CurrencyCode</div>
                        <div class="param_value">
                            <input type="text" name="currencyCode" value="USD" size="50"
                                   maxlength="260" />
                        </div>
                    </div>
                    <div class="params">
                        <div class="param_name">PaymentAction</div>
                        <div class="param_value">
                            <input type="text" name="paymentAction" value="Order"  readonly/>
                        </div>
                    </div>
                    <div class="param_name">Item Details</div>
                    <table class="params">
                        <tr>
                            <th class="param_name">Name</th>
                            <th class="param_name">Cost</th>
                            <th class="param_name">Quantity</th>

                            <th class="param_name">Item Category</th>

                        </tr>

                        <tr>
                            <td><div class="param_value">
                                    <input type="text" name="itemName" id="itemName"
                                           value="Item Name" />
                                </div>
                            </td>

                            <td><div class="param_value">
                                    <input type="text" name="itemAmount" id="itemAmount"
                                           value="${reservaBean.reserva.precio}" />
                                </div>
                            </td>

                            <td><div class="param_value">
                                    <input type="text" name="itemQuantity" id="itemQuantity"
                                           value="2" />
                                </div>
                            </td>



                            <td><div class="param_value">
                                    <select name="itemCategory">
                                        <option Value="Physical">Physical</option>
                                        <option Value="Digital">Digital</option>
                                    </select>
                                </div>
                            </td>

                        </tr>
                    </table>

                    <br>
                    <div class="submit">
                        <input type="submit" name="SetExpressCheckoutBtn"
                               value="SetExpressCheckout" /> <br />
                    </div>
                    <a href="index.html">Home</a>
                </div>
            </form> 
        </body>
    </html>
</f:view>
