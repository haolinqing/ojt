/**
 * 
 */
function trim(input) {
	input.value=input.value.replace(/^\s+|\s+$/g,'');
}

function deleteForm() {
	var radio = document.getElementsByName("userId");
	var userId;
    for (i=0; i<radio.length; i++) { 
        if (radio[i].checked) { 
            userId = radio[i].value;
            break;
        }
    }
    if(userId != null) {
    	window.location.href = "delete?form&userId=" + userId;
    }
}

function deleteUser() {
	var form = document.getElementById("userForm");
	form.action = "delete";
	form.submit();
}

function deleteRedo() {
	var form = document.getElementById("userForm");
	form.action = "delete?redo";
	form.submit();
}

function updateForm() {
	var radio = document.getElementsByName("userId");
	var userId;
    for (i=0; i<radio.length; i++) { 
        if (radio[i].checked) { 
            userId = radio[i].value;
            break;
        }
    }
    if(userId != null) {
    	window.location.href = "update?form&userId=" + userId;
    }
}

function updateRedo() {
	var form = document.getElementById("userForm");
	form.action = "update?redo";
	form.submit();
}

function disabled(button) {
	button.disabled = true;
}

function registerRedo() {
	var form = document.getElementById("userForm");
	form.action = "register?redo";
	form.submit();
}

function isExistedOfUserId(inputOfUserId) {
	trim(inputOfUserId);
	var userId = inputOfUserId.value;
	if(userId.length == 4) {
		$.ajax({
			url: "isExsitedOfUserId?userId=" + userId,
			success: function(isExisted){
				if(isExisted) {
					$("#userIdError").text("ユーザーIdは既に存在しました");
				}else {
					$("#userIdError").text("");
				}
			}
	      });
	}
}
