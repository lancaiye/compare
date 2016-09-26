<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效模板管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/ktemplate/">绩效模板列表</a></li>
		<li class="active"><a href="${ctx}/oa/ktemplate/form?id=${ktemplate.id}">绩效模板<shiro:hasPermission name="oa:ktemplate:edit">${not empty ktemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:ktemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ktemplate" action="${ctx}/oa/ktemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属机构：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${ktemplate.office.id}" labelName="office.name" labelValue="${ktemplate.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表此数据可用，“否”则表示此数据不可用</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">绩效类别表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>绩效项目</th>
								<th>绩效类别</th>
								<th>指标定义</th>
								<th>总分</th>
								<th>计算方式</th>
								<shiro:hasPermission name="oa:ktemplate:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="optionsList">
						</tbody>
						<shiro:hasPermission name="oa:ktemplate:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#optionsList', optionsRowIdx, optionsTpl);optionsRowIdx = optionsRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="optionsTpl">//<!--
						<tr id="optionsList{{idx}}">
							<td class="hide">
								<input id="optionsList{{idx}}_id" name="optionsList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="optionsList{{idx}}_delFlag" name="optionsList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="optionsList{{idx}}_name" name="optionsList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="required"/>
							</td>
							<td>
								<select id="optionsList{{idx}}_type" name="optionsList[{{idx}}].type" data-value="{{row.type}}" class="input-normal required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('kpi_type')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<textarea id="optionsList{{idx}}_definition" name="optionsList[{{idx}}].definition" rows="4" maxlength="255" >{{row.definition}}</textarea>
							</td>
							<td>
								<input id="optionsList{{idx}}_totalScore" name="optionsList[{{idx}}].totalScore" type="text" value="{{row.totalScore}}" maxlength="4" class="input-mini "/>
							</td>
							<td>
								<textarea id="optionsList{{idx}}_scope" name="optionsList[{{idx}}].scope" rows="4" maxlength="255" >{{row.scope}}</textarea>
							</td>

							<shiro:hasPermission name="oa:ktemplate:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#optionsList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
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
		<div class="form-actions">
			<shiro:hasPermission name="oa:ktemplate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>