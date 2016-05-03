function generateTr(message{
	var result="<tr>";
	result+="<td>"+message.title+"</td>";
	result+="<td>"+message.content+"</td>";
	result+="<td>"+message.cratedDate+"</td>";
	result+="<td><button type=\"button\" class=\"btn btn-primary\" onclick=showModal("+message.id+")>修改</button>"+
			"<button type=\"button\" class=\"btn btn-danger\" onclick=deleteByid("+message.id+")>删除</button></td>"
	return result;
});
function showModal(id){
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/find"+id,
		"success":function(rep){
			if(rep.code==1){
				$("#updata-id").html(rep.data.id);
				$("#updata-title").val(rep.data.title);
				$("#updata-content").val(rep.data.content);
				$("#updatModal").modal("show");
			}
		}
	});
}
function deleteByid(id){
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/delete"+id,
		"success":function(rep){
			if(rep.code==2){
				$("#warning").show();
				$("#warning-lable").html(rep.data);
			}
		}
	});
}
function getdata(toPage,pageSize){
	$("#.li-on:frist").removeClass("li-on");
	$("#pageSize"+pageSize).addClass("li-on");
	$("#message-table tbody").html("");
	$.ajax({
		"type":"get",
		"dataType":"json",
		"url":"/message/page",
		"data":{"currentPage":toPage,"pageSize":pageSize},
		"success":function(response){
			var data=response.data;
			var messageData=data.messages;
			var maxLength=messageData.length;
			for(var i=0;i<maxLength;i++){
				$("#message-table").append(generateTr(messageData[i]));
			}
			//总记录数
			$("#totalRecords").html(data.total);
			$("#currPage").html(data.currentPage);
			if(data.currentPage==1){
				$("#toLeft").attr("disabled",true);
			}else{
				$("#toRight").removeAttr("disabled");
			}
			if(data.currentPage==data.pageCount){
				$("#toRight").attr("disabled",ture);
			}else{
				$("#toRight").removeAttr("disabled");
			}
		}
	});
}