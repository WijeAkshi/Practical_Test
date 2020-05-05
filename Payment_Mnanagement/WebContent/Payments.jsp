<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.PaymentDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1>Payment Management</h1>
				
				<form id="formPayment" name="formPayment">
				
					Appointment no: <input id="app_Id" name="app_Id" type="text"
						class="form-control form-control-sm"> <br>
						
					 Card Type: <input id="cardType" name="cardType" type="text"
						class="form-control form-control-sm"> <br>
						
					 Name on Card: <input id="nameOnCard" name="nameOnCard" type="text"
						class="form-control form-control-sm"> <br>
						
				     Card no: <input id="cardno" name="cardno" type="text"
						class="form-control form-control-sm"> <br>
						
					Phone: <input id="phone" name="phone" type="text"
						class="form-control form-control-sm"> <br>
						
					Expire date: <input id="expdate" name="expdate" type="date"
						class="form-control form-control-sm"> <br>
						
					Amount: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br>
						
					Status: <input id="status" name="status" type="text"
						class="form-control form-control-sm"> <br>
						
					 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
					<input type="hidden"id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
                <div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divPaymentGrid">
					<%
						PaymentDetails paymentdetails = new PaymentDetails();
						out.print(paymentdetails.readPaymentDetails());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>