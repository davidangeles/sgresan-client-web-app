<%-- 
    Document   : newjsf
    Created on : 28/09/2014, 03:28:23 PM
    Author     : Joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
 
            <h1><h:outputText value="Hello World!"/></h1>
           <form method="POST">
                
            
        <input type="text" name="buyerMail" value="laquerenciahermanos@hotmail.com" size="50" maxlength="260" />
            
            <input type="text" name="currencyCode" value="USD" size="50"
							maxlength="260" />
            <input type="text" name="currencyCode" value="USD" size="50"
							maxlength="260" />
            <input type="text" name="paymentAction" value="Order"  />
            <input type="text" name="itemName" id="itemName"
									value="Item Name" />
            <input type="text" name="itemAmount" id="itemAmount"
									value="5.27" />
            <input type="text" name="itemQuantity" id="itemQuantity"
									value="2" />
            <input type="text" name="salesTax" id="salesTax" value="" />
            <input type="text" name="itemDescription" id="itemDescription"
									value="" />
            <input type="submit" name="SetExpressCheckoutBtn"
						value="SetExpressCheckout" />
            </form>
    </html>
</f:view>