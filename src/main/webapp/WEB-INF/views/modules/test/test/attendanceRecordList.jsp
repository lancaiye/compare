<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>测试管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/test/test/attendanceRecord/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/test/test/attendanceRecord/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/test/test/attendanceRecord/import" method="post" 
		enctype="multipart/form-data" class="form-search" style="padding-left: 20px; text-align: center;"
		onsubmit="loading('正在导入，请稍等...');">
			<br/> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br/><br/> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " /> <a
				href="${ctx}/test/test/attendanceRecord/import/template">下载模板</a>
		</form>
	</div>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/test/test/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br /> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " />
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/test/attendanceRecord/">test列表</a></li>
		<shiro:hasPermission name="test:test:attendanceRecord:edit">
			<li><a href="${ctx}/test/test/attendanceRecord/form">test添加</a></li>
		</shiro:hasPermission>
	</ul>

	<form:form id="searchForm" modelAttribute="attendanceRecord"
		action="${ctx}/test/test/attendanceRecord/" method="post"
		class="breadcrumb form-search">
		
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		
		
			 <li><label>部&nbsp;&nbsp;&nbsp;门：</label> <form:input
					path="office" htmlEscape="false" maxlength="50"
					class="input-medium" /></li> 
		<%-- 	<li><label>归属部门：</label><sys:treeselect id="office" name="AttendanceRecord.office" value="${AttendanceRecord.id}" labelName="AttendanceRecord" labelValue="${AttendanceRecord.office}" 
				title="部门" url="/sys/office/treeData?" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
				 --%>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label> <form:input path="name"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>

			<li><label>开始日期：</label>
			<form:input path="beginDate" type="text" readonly="readonly"
				maxlength="20" class="input-small Wdate"
				value="${paramMap.beginDate}"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></li>
				
			<li><label>结束日期：</label>
			<form:input path="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-small Wdate" value="${paramMap.endDate}"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> </li>



			<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /> <input
				id="btnExport" class="btn btn-primary" type="button" value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"
				value="导入" /></li>
			<li class="clearfix"></li>
		</ul>
		
		
		
		
		
		
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column office">部门</th>
				<th>姓名</th>
				<th class="sort-column attendanceNo">考勤号</th>
				<th>上班时间</th>
				<th>记录状态</th>
				<th>机器号</th>
				<th>编号</th>
				<th>工种代码</th>
				<th>对比方式</th>
				<th>备注信息</th>
				<shiro:hasPermission name="test:test:attendanceRecord:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="attendanceRecord">
				<tr>
					<td>${attendanceRecord.office}</td>
					<td>${attendanceRecord.name}</td>
					<td>${attendanceRecord.attendanceNo*1}</td>
					<%-- <td>${attendanceRecord.workingTime}</td> --%>
					<td><fmt:formatDate value="${attendanceRecord.workingTime}" type="both" /></td>
					<td>${attendanceRecord.recordStatus}</td>
					<td>${attendanceRecord.machineId}</td>
					<td>${attendanceRecord.numId}</td>
					<td>${attendanceRecord.tradesId}</td>
					<td>${attendanceRecord.compareMode}</td>
					<td>${attendanceRecord.remarks}</td>
					<shiro:hasPermission name="test:test:attendanceRecord:edit">
						<td><a
							href="${ctx}/test/test/attendanceRecord/form?id=${attendanceRecord.id}">修改</a>
							<a
							href="${ctx}/test/test/attendanceRecord/delete?id=${attendanceRecord.id}"
							onclick="return confirmx('确认要删除该test吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>