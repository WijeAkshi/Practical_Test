$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentsAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();
}
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
	
			$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDSave').val());
			$("#app_Id").val($(this).closest("tr").find('td:eq(0)').text());
			$("#cardType").val($(this).closest("tr").find('td:eq(1)').text());
			$("#nameOnCard").val($(this).closest("tr").find('td:eq(2)').text());
			$("#cardno").val($(this).closest("tr").find('td:eq(3)').text());
			$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
			$("#expdate").val($(this).closest("tr").find('td:eq(5)').text());
			$("#amount").val($(this).closest("tr").find('td:eq(6)').text());
			$("#status").val($(this).closest("tr").find('td:eq(7)').text());
		});

// REMOVE ====================================================

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "PaymentsAPI",
		type : "DELETE",
		data : "id=" + $(this).data("paymentid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});
function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// appointment id
	if ($("#app_Id").val().trim() == "") {
		return "Insert Item Code.";
	}
	// card type
	if ($("#cardType").val().trim() == "") {
		return "Insert card type.";
	}
	// name on card
	if ($("#nameOnCard").val().trim() == "") {
		return "Insert name on card.";
	}
	// card number
	if ($("#cardno").val().trim() == "") {
		return "Insert card number.";
	}
	// phone
	if ($("#phone").val().trim() == "") {
		return "Insert phone.";
	}
	// expire date
	if ($("#expdate").val().trim() == "") {
		return "Insert expire date.";
	}
	
	//amount
	if ($("#amount").val().trim() == "") {
		return "Insert the total amount.";
	}
	// is numerical value
	var tmpPrice = $("#amount").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Item Price.";
	}
	// convert to decimal price
	$("#amount").val(parseFloat(tmpPrice).toFixed(2));
	
	

	return true;

}
