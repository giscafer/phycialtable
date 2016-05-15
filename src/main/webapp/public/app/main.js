$(function() {
	$.getJSON('newdata', function(newcount) {
		$("#xinzeng").text(newcount);
		if (Number(newcount) != 0) {
			$("#xinzeng").tooltip({
				"title" : "今日新增记录数",
				"placement" : "bottom"
			});
			$("#xinzeng").mouseover();
		}
	});
});

function operateFormatter(value, row, index) {
	return [
			'<a class="edit ml10" style="color:black;" href="javascript:void(0)" title="编辑">',
			'<span class="glyphicon glyphicon-edit"></span>',
			'</a>&emsp;',
			'<a class="remove ml10" style="color:black;" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
}

window.operateEvents = {
	'click .like' : function(e, value, row, index) {
		console.log(value, row, index);
	},
	'click .edit' : function(e, value, row, index) {
		var delid = row.id;
		$.getJSON('setEditData', {
			"id" : delid
		}, function(e) {
			window.location.href = "editForm";
		});
	},
	'click .remove' : function(e, value, row, index) {
		var delid = row.id;
		var studentid = row.studentid;
		if (confirm("确定删除编号为：" + studentid + "的记录吗？")) {
			$.getJSON('delete', {
				"id" : delid
			}, function(data) {
				$("#dtb").bootstrapTable('refresh');
			});
		}
	}
};

function refreshTable() {
	$("table").bootstrapTable('refresh', {
		url : $("table").attr('data-url')
	});
}

function makeparmter(params) {
	params.queryby = $("#queryby").val();
	var ret = '';
	for ( var i in params) {
		ret += i + ':' + params[i];
	}
	return params;
}

function checkAll() {
	$("#queryby").val('1');
	refreshTable();
}

function loginOut() {
	if (confirm("确定退出系统吗")) {
		window.location.href = "../exit";
	}
}
function exporyToday() {
	if (confirm("确定导出当天记录吗？")) {
		window.location.href = "user/export";
	}
}
function exporyAll() {
	if (confirm("确定导出所有记录吗？")) {
		window.location.href = "export";
	}
}
