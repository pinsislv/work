var totalData;
$(function(){
	//左侧消息管理样式
	$("#message-li").addClass("active");
	//默认内容显示
	$.ajax({
		"type":"get",
		"dataType":"json",
		"url":"/message/all",
		"success":function(rep){
			totalData=rep.data;
			$("#totalRecords").html(totalData.length);
			getData(1,5);
		}
	});

//添加按钮事件
$("#add-message").click(function(){
	var message={
			"title":$("#modal-title").val(),
			"content":$("#modal-content").val()
	};
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/add",
		"data":message,
		"success":function(rep){
			window.location.reload();
		}
	});
});
//更新按钮事件
$("#update-message").click(function(){
	var message={
		"id":$("#update-id").html(),
		"title":$("#update-title").val(),
		"content":$("#update-content").val()
	};
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/update",
		"data":message,
		"success":function(rep){
			window.location.reload();
		}
	});
});
//点击每页3条记录的事件
$("#pageSize3").click(function(){
	getData(1,3);
});
//点击每页5条记录的事件
$("#pageSize5").click(function(){
	getData(1,5);
});
//点击每页10条记录的事件
$("#pageSize10").click(function(){
	getData(1,10);
});
$("#toRight").click(function(){
	var toPage=parseInt($("#currPage").html())+1;
	var pageSize=parseInt($(".li-on:first").html());
	getData(toPage,pageSize);
});
$("#toLeft").click(function(){
	var toPage=parseInt($("#currPage").html())-1;
	var pageSize=parseInt($(".li-on:first").html());
	getData(toPage,pageSize);
});
$("#jumpPage").click(function(){
	var toPage=$("#toPage-input").val();
	var pageSize=parseInt($(".li-on:first").html());
	var totalPage=parseInt((totalData.length-1)/pageSize)+1;
	if(toPage>totalPage){
		toPage=totalPage;
		$("#toPage-input").val(totalPage);
	}
	var pageSize=parseInt($(".li-on:first").html());
	getData(toPage,pageSize);
});
});
//表格数据格式
function generateTr(message){
	var result="<tr>";
	result+="<td>"+message.title+"</td>";
	result+="<td>"+message.content+"</td>";
	result+="<td>"+message.createdDate+"</td>";
	result+="<td><button type=\"button\" class=\"btn btn-primary\" onclick=showModal("+message.id+")>修改</button>"+
			"<button type=\"button\" class=\"btn btn-danger\" onclick=deleteByid("+message.id+")>删除</button></td>";
	return result;
}
//展示数据（插入表格标签）
function showModal(id){
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/find/"+id,
		"success":function(rep){
			if(rep.code==1){
				$("#update-id").html(rep.data.id);
				$("#update-title").val(rep.data.title);
				$("#update-content").val(rep.data.content);
				$("#updateModal").modal("show");
			}
		}
	});
}
//删除数据
function deleteByid(id){
	$.ajax({
		"type":"post",
		"dataType":"json",
		"url":"/message/delete/"+id,
		"success":function(rep){
			window.location.reload();
			if(rep.code==2){
				$("#warning").show();
				$("#warning-lable").html(rep.data);
				
			}
		}
	});
}
//自定义函数，分页的方法
function getData(toPage,pageSize){
	//将之前显示的消息内容置为空，（避免新内容直接在旧内容下显示）
	$("#message-table tbody").html("");
	//移除已有这个样式的标签的class
	$(".li-on:first").removeClass("li-on");
	//如果toPage>=总页数 下一页设置为不可点击
	var totalPage=parseInt((totalData.length-1)/pageSize)+1;
	if(toPage>=totalPage){
		$("#toRight").attr("disabled",true);
	}else{
		$("#toRight").removeAttr("disabled");
	}
	//如果toPage==1 上一页设置不可用
	if(toPage==1){
		$("#toLeft").attr("disabled",true);
	}else{
		$("#toLeft").removeAttr("disabled");
	}
	//判断当前页之前所有消息总数
	var maxLength=toPage*pageSize<totalData.length?toPage*pageSize:totalData.length;
	//插入本页的内容
	for(var i=(toPage-1)*pageSize;i<maxLength;i++){
		$("#message-table").append(generateTr(totalData[i]));
	}
	$("#pageSize"+pageSize).addClass("li-on");
	$("#currPage").html(toPage);
	//for(int i=(当前页面-1)*页面显示长度;i<当前页面*页面显示长度;i++)
	//table.append();
}