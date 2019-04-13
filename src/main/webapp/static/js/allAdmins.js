var myIndex = -1;
var data = "";
var myUsername = "";

//load table function
function load() {
	var token = $("meta[name='_csrf']").attr("content");

	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
				url : 'listAdmins',
				type : 'POST',
				success : function(response) {
					data = response.data;
//					window.alert(response.message);
					$('.tr').remove();
					for (i = 0; i < response.data.length; i++) {

						$("#table")
								.append(
										"<tr class='tr'> <td> "
												+ response.data[i].username
												+ " </td> <td> "
												+ response.data[i].firstName
												+ " </td> <td> "
												+ response.data[i].lastName
												+ " </td> <td> "
												+ response.data[i].email
												+ " </td> <td> "
												+ response.data[i].telephone
												+ " </td> <td> <button data-my-ix= "
												+ i
												+ " data-toggle='modal' data-target='#info_modal' class='info-dialog btn btn-info active'> Info </button> </td> \n\
												<td> <button data-my-i= "
												+ i
												+ " data-toggle='modal' data-target='#updatea_modal' title="
												+ response.data[i].username
												+ " class='edit-dialog btn btn-warning active'> Edit </button> </td> \n\
                            <td> <button data-my-username="
												+ response.data[i].username
												+ " data-my-id= "
												+ response.data[i].userId
												+ " class='delete-dialog btn btn-danger disabled' type='button' data-toggle='modal' data-target='#delete_modal'> Delete </button> </td> </tr> ");
					}
				},
				error : function(xhr, status, error) {
					window.alert("ERROR load:" + error);
					window.alert("xhr:" + xhr.responseText);
				}
			});
};

//NOTE: class='delete-dialog btn btn-danger active' - for active >Delete< button in function load();

//add new Admin function
function submitt() {
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'saveOrUpdate',
		type : 'POST',
		data : {
			username : $('#adda_username').val(),
			password : $('#adda_password').val(),
			firstName : $('#adda_firstName').val(),
			lastName : $('#adda_lastName').val(),
			email : $('#adda_email').val(),
			telephone : $('#adda_telephone').val(),
			nname : $('input[name=nname]').val(),
			actn : $('#adda_action').val()
		},
		success : function(response) {
			alert(response.message);
			load();
		},
		error : function(xhr, status, error) {
			window.alert("error submit:" + error);
			window.alert("xhr:" + xhr.responseText);
		}
	});
	$('#adda_username').val("");
	$('#adda_password').val("");
	$('#adda_firstName').val("");
	$('#adda_lastName').val("");
	$('#adda_email').val("");
	$('#adda_telephone').val("");
	$('#adda_id').val("");
	$('#adda_nname').val("");
	
	modHideAdd();
};

//reset modal data
function subbris() {
	$('#adda_username').val("");
	$('#adda_password').val("");
	$('#adda_firstName').val("");
	$('#adda_lastName').val("");
	$('#adda_email').val("");
	$('#adda_telephone').val("");
	$('#adda_id').val("");
};

//call info modal
$(document).on("click", ".info-dialog", function() {
	infoIndex = $(this).data('my-ix');

	$("#info_id").val(data[infoIndex].id);
	$("#info_username").val(data[infoIndex].username);
	$("#info_firstName").val(data[infoIndex].firstName);
	$("#info_lastName").val(data[infoIndex].lastName);
	$("#info_email").val(data[infoIndex].email);
	$("#info_telephone").val(data[infoIndex].telephone);
});

//call edit modal
$(document).on("click", ".edit-dialog", function() {
	myIndex = $(this).data('my-i');

	$("#update_id").val(data[myIndex].id);
	$("#update_username").val(data[myIndex].username);
	$("#update_firstName").val(data[myIndex].firstName);
	$("#update_lastName").val(data[myIndex].lastName);
	$("#update_email").val(data[myIndex].email);
	$("#update_telephone").val(data[myIndex].telephone);
});

//update Admin's data
function update() {
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'saveOrUpdate',
		type : 'POST',
		data : {
			username : $('#update_username').val(),
			firstName : $('#update_firstName').val(),
			lastName : $('#update_lastName').val(),
			email : $('#update_email').val(),
			telephone : $('#update_telephone').val(),
			id : $('#update_id').val(),
			actn : $('#edit_action').val()
		},
		success : function(response) {
			alert(response.message);

			load();
		},
		error : function(xhr, status, error) {
			window.alert("error update:" + error);
			window.alert("xhr:" + xhr.responseText);
		}
	});
	$('#update_username').val("");

	$('#update_firstName').val("");
	$('#update_lastName').val("");
	$('#update_email').val("");
	$('#update_telephone').val("");

	modHideUpd();
};

//tooltip on edit button
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

//message in delete modal
$(document).on(
		"click",
		".delete-dialog",
		function() {
			myUsername = $(this).data('my-username');
			document.getElementById('myModalLabel2').innerHTML = "Admin '"
					+ myUsername + "' will be deleted.";
		});

//delete user function
function delete_() {	
	var token = $("meta[name='_csrf']").attr("content");

	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'deleteUser',
		type : 'POST',
		data : {
			username : myUsername
		},
		success : function(response) {
			alert(response.message);
			load();
		},
		error : function(xhr, status, error) {
			window.alert("error on delete:" + error);
			window.alert("xhr:" + xhr.responseText);
		}
	});
};

//closing add modal
$(document).ready(function() {
	window.modHideAdd = function() {		
		$('#adda_modal .close').click();
	}
});

//closing update modal
$(document).ready(function() {
	window.modHideUpd = function() {		
		$('#updatea_modal .close').click();
	}
});