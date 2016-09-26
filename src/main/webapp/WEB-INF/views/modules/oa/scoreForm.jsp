<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自评得分管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/score/">自评得分列表</a></li>
		<li class="active"><a href="${ctx}/oa/score/form?id=${score.id}">自评得分<shiro:hasPermission name="oa:score:edit">${not empty score.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:score:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="score" action="${ctx}/oa/score/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<div class="control-group">
			<label class="control-label">归属机构：</label>
			<div class="controls">
				<input type="hidden" name="office" value="${office.id}"> 
				<input value="${office.name}" disabled="disabled">
				<%-- <sys:treeselect id="office" name="office.id" value="${office.id}" labelName="office.name" labelValue="${office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/> --%>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
			<td><a href="${ctx}/oa/ktemplate/form?id=${ktemplate.office_id}">
					${ktemplate.name}
				</a></td>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绩效类别表：</label>
			<div class="controls">
					<table id="kpiOptionsId" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>绩效项目</th>
								<th>绩效类别</th>
								<th>指标定义</th>
								<th>总分</th>
								<th>计算方式</th>
								<th>自评得分</th>
								<%-- <shiro:hasPermission name="oa:ktemplate:edit"><th width="10">&nbsp;</th></shiro:hasPermission> --%>
							</tr>
						</thead>
						<tbody id="optionsList">
						</tbody>
					</table>
					
					<script type="text/template" id="optionsTpl">
					</script>
					<script type="text/javascript">
						var optionsRowIdx = 0, optionsTpl = $("#optionsTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(ktemplate.optionsList)};
							for (var i=0; i<data.length; i++){
								addRow('#optionsList', optionsRowIdx, optionsTpl, data[i]);
								optionsRowIdx = optionsRowIdx + 1;
							}
						});
					</script>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">自评：</label>
			<div class="controls">
				<form:input path="selfScore" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div> --%>
		
		<div class="form-actions">
			<shiro:hasPermission name="oa:score:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>