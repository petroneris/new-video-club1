var myIndex = -1;
var data = "";
var myTitle = "";

//load table function
function load() {
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$
			.ajax({
				url : 'listFilms',
				type : 'POST',
				success : function(response) {
					data = response.data;
					// window.alert(response.message);
					$('.tr').remove();
					for (i = 0; i < response.data.length; i++) {

						$("#table")
								.append(
										"<tr class='tr'> <td> "
												+ response.data[i].title
												+ " </td> <td> "
												+ response.data[i].genre
												+ " </td> <td> "
												+ response.data[i].year
												+ " </td> <td> <button data-my-ix= "
												+ i
												+ " data-toggle='modal' data-target='#info_modal' class='info-dialog btn btn-info active'> Info </button> </td> \n\
												<td> <button data-my-i= "
												+ i
												+ " data-toggle='modal' data-target='#update_modal' title= '"
												+ response.data[i].title
												+ "' class='edit-dialog btn btn-warning active'> Edit </button> </td> \n\
                            <td> <button data-my-title='"
												+ response.data[i].title
												+ "' class='delete-dialog btn btn-danger active' type='button' data-toggle='modal' data-target='#delete_modal'> Delete </button> </td> </tr> ");
					}
				},
				error : function(xhr, status, error) {
					window.alert("ERROR load:" + error);
					window.alert("xhr:" + xhr.responseText);
				}
			});
};

//add new film function
function submitt() {
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'saveOrUpdateFilm',
		type : 'POST',
		data : {
			title : $('#title').val(),
			genre : $('#genre').val(),
			year : $('#year').val(),
			actn : $('#addf_action').val()
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

	$('#title').val("");
	$('#genre').val("");
	$('#year').val("");
	modHideFadd();
};

//reset modal data
function subbris() {
	$('#title').val("");
	$('#genre').val("");
	$('#year').val("");
};

//call info modal
$(document).on("click", ".info-dialog", function() {	
	infoIndex = $(this).data('my-ix');

	$("#info_id").val(data[infoIndex].id);
	$("#info_title").val(data[infoIndex].title);
	$("#info_genre").val(data[infoIndex].genre);
	$("#info_year").val(data[infoIndex].year);
});

//call edit modal
$(document).on("click", ".edit-dialog", function() {
	myIndex = $(this).data('my-i');

	$("#update_id").val(data[myIndex].id);
	$("#update_title").val(data[myIndex].title);
	$("#update_genre").val(data[myIndex].genre);
	$("#update_year").val(data[myIndex].year);
});

//update film's data
function update() {
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'saveOrUpdateFilm',
		type : 'POST',
		data : {
			title : $('#update_title').val(),
			genre : $('#update_genre').val(),
			year : $('#update_year').val(),
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
	$('#update_title').val("");
	$('#update_genre').val("");
	$('#update_year').val("");
	modHideFupd();
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
			myTitle = $(this).data('my-title');
			 window.alert('myTitle:' +myTitle);
			document.getElementById('myModalLabel2').innerHTML = "Film '"
					+ myTitle + "' will be deleted.";
		});

//delete film function
function delete_() {	
	var token = $("meta[name='_csrf']").attr("content");

	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});
	$.ajax({
		url : 'deleteFilm',
		type : 'POST',
		data : {
			title : myTitle
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
	window.modHideFadd = function() {		
		$('#addf_modal .close').click();
	}
});

//closing update modal
$(document).ready(function() {
	window.modHideFupd = function() {		
		$('#update_modal .close').click();
	}
});


