<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript">
	$(function(){
		$.get("${pageContext.request.contextPath }/user_findAllUser.action",
		function(data){
			$(data).each(function(i,n){
				$("#user").append("<option value="+n.user_id+">"+n.user_name+"</option>");
			});
		},"json");
		
		$.get("${pageContext.request.contextPath }/customer_findAllCustomer.action",
		function(data){
			$(data).each(function(i,n){
				$("#customer").append("<option value="+n.cust_id+">"+n.cust_name+"</option>");
			});
		},"json");
		//设置日期格式
		$('#datestyle1').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#datestyle2').datepick({dateFormat: 'yy-mm-dd'}); 
	});
	
	
</script>
</HEAD>
<BODY>
	<s:form id="form1" name="form1" action="salevisit_save" namespace="/" method="post" theme="simple">
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：拜访管理 &gt; 添加拜访事件</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>负责人：</td>
								<td>
									<select name="user.user_id" id="user">
										<option value="">-请选择-</option>
								    </select>
								</td>
								<td>客户：</td>
								<td>
									<select name="customer.cust_id" id="customer">
										<option value="">-请选择-</option>
								    </select>
								</td>
							</tr>	
							<TR>
								<td>拜访地点：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxLength="50" name="visit_addr" ></s:textfield>
								</td>
								<td>拜访详情 ：</td>
								<td>
									<s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxLength="50" name="visit_detail" ></s:textfield>
								
								</td>
							</TR>
							<TR>
							<td>拜访时间</td>
							<td>
								<s:textfield cssClass="textbox" id="datestyle1" readonly="readonly" cssStyle="WIDTH: 180px" maxLength="50" name="visit_time" ></s:textfield>
							</td>
							<td>下次拜访时间 ：</td>
							<td>
								<s:textfield cssClass="textbox" id="datestyle2" readonly="readonly" cssStyle="WIDTH: 180px" maxLength="50" name="visit_nexttime" ></s:textfield>
							
							</td>
							</TR>
							
							<tr>
								<td rowspan=2>
									<s:submit cssClass="button" id="sButton2" name="sButton2"></s:submit>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
</HTML>
