<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/ktemplate/">绩效模板列表</a></li>
		<shiro:hasPermission name="oa:ktemplate:edit"><li><a href="${ctx}/oa/ktemplate/form">绩效模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ktemplate" action="${ctx}/oa/ktemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板名称</th>
				<th>归属部门</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:ktemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ktemplate">
			<tr>
				<td><a href="${ctx}/oa/ktemplate/form?id=${ktemplate.id}">
					${ktemplate.name}
				</a></td>
				<td>${ktemplate.office.name}</td>
				<td>
					<fmt:formatDate value="${ktemplate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:ktemplate:edit"><td>
					<a href="${ctx}/oa/ktemplate/assign?id=${ktemplate.id}">分配</a>
    				<a href="${ctx}/oa/ktemplate/form?id=${ktemplate.id}">修改</a>
					<a href="${ctx}/oa/ktemplate/delete?id=${ktemplate.id}" onclick="return confirmx('确认要删除该绩效模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>