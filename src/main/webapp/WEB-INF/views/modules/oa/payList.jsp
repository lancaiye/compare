<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>薪资管理</title>
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
		<li class="active"><a href="${ctx}/oa/pay/${pay.self?'self':''}">薪资列表</a></li>
		<c:if test="${!pay.self}">
			<shiro:hasPermission name="oa:pay:edit">
				<li><a href="${ctx}/oa/pay/form">薪资添加</a></li>
			</shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="pay"
		action="${ctx}/oa/pay/${pay.self?'self':''}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>标题：</label> <form:input path="title"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<c:if test="${!requestScope.pay.self}">
				<li><label>状态：</label> <form:radiobuttons path="status"
						items="${fns:getDictList('oa_pay_status')}" itemLabel="label"
						itemValue="value" htmlEscape="false" /></li>
			</c:if>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>状态</th>
				<th>查阅状态</th>
				<th>更新时间</th>
				<c:if test="${!pay.self}">
					<shiro:hasPermission name="oa:pay:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pay">
				<tr>
					<td><a
						href="${ctx}/oa/pay/${requestScope.pay.self?'view':'view'}?id=${pay.id}">
							${fns:abbr(pay.title,50)} </a></td>  <!--view':'form'  -->
					<td>${fns:getDictLabel(pay.status, 'oa_pay_status', '')}</td>
					<td><c:if test="${requestScope.pay.self}">
						${fns:getDictLabel(pay.readFlag, 'oa_pay_read', '')}
					</c:if> <c:if test="${!requestScope.pay.self}">
						${pay.readNum} / ${pay.readNum + pay.unReadNum}
					</c:if></td>
					<td><fmt:formatDate value="${pay.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<c:if test="${!requestScope.pay.self}">
						<shiro:hasPermission name="oa:pay:edit">
							<td><a href="${ctx}/oa/pay/form?id=${pay.id}">修改</a> <a
								href="${ctx}/oa/pay/delete?id=${pay.id}"
								onclick="return confirmx('确认要删除该通知吗？', this.href)">删除</a></td>
						</shiro:hasPermission>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>