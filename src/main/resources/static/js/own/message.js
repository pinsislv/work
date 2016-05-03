$(function() {
	$("#message-li").addClass("active");
	$.ajax({
		"type" : "get",
		"dataType" : "json",
		"url" : "/message/all",
		"success" : function(rep) {
			var data = rep.data;
			for (var i = 0; i < data.length; i++) {
				$("#message-table").append(generateTr(data[i]));
			}
		}
	});
	
	$("#add-message").click(function(){
		var message = {
				"title":$("#modal-title").val(),
				"content":$("#modal-content").val()
		};
		$.ajax({
			"type" : "post",
			"dataType" : "json",
			"url" : "/message/add",
			"data" : message,
			"success" : function(rep) {
				window.location.reload();
			}
		});
	});
	
	$("#update-message").click(function(){
		var message = {
				"id":$("#update-id").html(),
				"title":$("#update-title").val(),
				"content":$("#update-content").val()
		};
		$.ajax({
			"type" : "post",
			"dataType" : "json",
			"url" : "/message/update",
			"data" : message,
			"success" : function(rep) {
				window.location.reload();
			}
		});
	});
});
/**
 * 
 * <tr>
		<td>title</td>
		<td>content</td>
		<td>data</td>
		<td><button>1</button><button>2</button></td>
	</tr>
 */
function generateTr(message) {
	var result = "<tr>";
	result += "<td>" + message.title + "</td>";
	result += "<td>" + message.content + "</td>";
	result += "<td>" + message.createdDate + "</td>";
	result += "<td><button type=\"button\" class=\"btn btn-primary\" onclick=showModal("+message.id+")>修改</button>" +
			"<button type=\"button\" class=\"btn btn-danger\" onclick=deleteByid("+message.id+")>删除</button></td>"
	return result;
}
function showModal(id){
	$.ajax({
		"type" : "post",
		"dataType" : "json",
		"url" : "/message/find/"+id,
		"success" : function(rep) {
			if(rep.code == 1){
				$("#update-id").html(rep.data.id);
				$("#update-title").val(rep.data.title);
				$("#update-content").val(rep.data.content);
				$("#updateModal").modal("show");
			}
		}
	});
}
function deleteByid(id){
	$.ajax({
		"type" : "post",
		"dataType" : "json",
		"url" : "/message/delete/"+id,
		"success" : function(rep) {
			location.reload(true);
			if(rep.code == 2){
				$("#warning").show();
				$("#warning-lable").html(rep.data);
			}
		}
	});
}