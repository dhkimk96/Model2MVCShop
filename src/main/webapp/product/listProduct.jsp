<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">

function fncGetList(currentPage) {
	$("#currentPage").val(currentPage);
	$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${param.menu}").submit();
}


$(function() {
	 $( "td.ct_btn01:contains('�˻�')" ).on("click" , function(event) {
		 fncGetList(1);
		    if(event.keyCode == '13'){
		        fncGetList(1);
		    }
	});
	
	$(document).on("click", ".ct_list_pop td:nth-child(3)", function () {
		var prodNo = $(this).find("input[name='prodNo']").val();
		self.location = "/product/getProduct?prodNo="+prodNo+"&menu=${param.menu}"
		
	});
	
	$(document).on("mouseenter", ".ct_list_pop td:nth-child(3)", function () {
		var prodNo = $(this).find("input[name='prodNo']").val();
		
		$.ajax({
						url : "/product/json/getProduct/"+prodNo,
						method : "GET",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData, status) {

							//Debug...
							//alert(status);
							//Debug...
							//alert("JSONData : \n" + JSONData);

							var displayValue =
									"<h3>" 
									+ "��ǰ��ȣ : "+ JSONData.prodNo + "<br/>" 
									+ "��ǰ�� : "+ JSONData.prodName + "<br/>" 
									+ "������ : "+ JSONData.prodDetail + "<br/>" 
									+ "��   �� : "+ JSONData.price + "<br/>"
									+ "�������� : "+ JSONData.manuDate + "<br/>"
									+ "��ǰ�̹��� : "
									+"<br/>"
									+"<img src = /images/uploadFiles/"+JSONData.fileName+" width='300' height='300'/>"
									+"<br/>"
									+"</h3>";
							//Debug...									
							//alert(displayValue);
							$("h3").remove();
							$("#" + JSONData.prodNo + "").html(displayValue);
							
						}
					});

				});

		//==> userId LINK Event End User ���� ���ϼ� �ֵ��� 
		$(".ct_list_pop td:nth-child(3)").css("color", "red");
		$("h7").css("color", "red");

		//==> �Ʒ��� ���� ������ ������ ??
		$(".ct_list_pop:nth-child(4n+6)").css("background-color", "whitesmoke");

		$(".ct_list_pop td:nth-child(9)").on("click",function() {
					var prodNo = $(this).find("input[name='prodNo']").val();
					//alert($(this).find("input[name='prodNo']").val());
					self.location = "/product/updateTranCodeByProd?prodNo="
							+ prodNo + "&tranCode=200"
				});

	});
	
$(function(){	
	
	$('#searchKeyword').on("keyup" , function(event){
	    if(event.keyCode == '13'){
	        fncGetList(1);
	    }
	});
	
	var array = [];
	
	$.ajax(
			{
				url : "/product/json/Autocomplete" ,
				method : "GET" ,
				dataType : "json" ,
				success : function(JSONData) {
					
					array = JSONData;
					
					$('#searchKeyword').autocomplete({
						source : array
					});
					
				}							 
	});
});

var page = 1;

$(window).on("scroll" , function(){
	if($(window).scrollTop() >= $(document).height() - $(window).height()){
							$.ajax({
										url : "/product/json/listProduct?menu=${param.menu}",
										method : "POST",
										data : JSON.stringify({
												searchCondition : $("#searchCondition").val(),
												searchKeyword : $("#searchKeyword").val(),
												currentPage : (++page),
												}),
										dataType : "json",
										headers : {
											"Accept" : "application/json",
											"Content-Type" : "application/json"},
										success : function(data) {
												var productList = data.list;
												var listNo = $("#listNo").text();
												$.each(productList, function(index ,product){
													var displayValue =  
																		"<tr class='ct_list_pop'>"
																		+"<td align='center'>"+(++listNo)+"</td>"
																		+"<td></td>"
																		+"<td align='left'>"
																		+"<input type='hidden' id='prodNo' name='prodNo' value="+product.prodNo+">"
																		+"</input>"
																		+product.prodName
																		+"</td>"
																		+"<td></td>"
																		+"<td align='left'>"
																		+"<img src = /images/uploadFiles/"+product.fileName+" width='150' height='150'/>"
																		+"</td>"
																		+"<td></td>"
																		+"<td align='center'>"+product.price+"</td>"
																		+"<td></td>"
																		+"<td align='left'>"+product.regDate+"</td>"
																		+"<td></td>"
																		+"<td align='left'>"
																		+(product.proTranCode == null ? "�Ǹ���" : "��� ����")
																		+"</td>"
																		+"</tr>"
																		+"<tr>"
																		+"<td id="+product.prodNo+" colspan='11' bgcolor='D6D7D6' height='1'></td>"
																		+"</tr>"
																			
																	$("#prodRow").append(displayValue);
																});
												}
											})
									}
	$(".ct_list_pop:nth-child(4n+6)").css("background-color", "whitesmoke");
						});
					


</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
							${param.menu.equals('manage') ? "��ǰ����" : "��ǰ �����ȸ"}
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	
		<td align="right">
			<select id="searchCondition" name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>��ǰ����</option>
			</select>
			
			<!-- <label for = "searchBox"></label> -->
  			<label for="searchKeyword"></label>
			<input type="text" id="searchKeyword" name="searchKeyword" class="ui-widget"value= "${! empty search.searchKeyword ? search.searchKeyword : ""}" style="width:200px; height:19px" />
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table id="prodList" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tbody id="prodRow" >
	<tr>
		<td colspan="11" >
		��ü  ${resultPage.totalCount } �Ǽ�,	���� ${resultPage.currentPage } ������
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ�̹���</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
	<tr class="ct_list_pop">
		<td align="center">${ i }</td>
		<td></td>
		<td align="left">
			<input type="hidden" id="prodNo" name="prodNo" value="${ product.prodNo}"/>
			<c:if test="${empty product.proTranCode}">
				${product.prodName }
			</c:if>
		</td>
		<td></td>
		<td align="left">
			<img src = "/images/uploadFiles/${product.fileName}" width="150" height="150"/>
		</td>
		<td></td>
		<td align="left">${product.price }</td>
		<td></td>
		<td align="left">${product.regDate }	</td>
		<td></td>
		<c:if test="${param.menu.equals('manage')}">
				<c:if  test="${product.proTranCode == 100}">
					<td align="left">
					���ſϷ�
					����ϱ�
					</td>
				</c:if>
				<c:if  test="${product.proTranCode == 200}">
					<td align="left">
					��� ��
					</td>
				</c:if>
				<c:if  test="${product.proTranCode == 300}">
					<td align="left">
					��ۿϷ�
					</td>
				</c:if>
				<c:if  test="${empty product.proTranCode}">
					<td align="left">
					</td>
				</c:if>
			</c:if>
		<c:if test="${param.menu.equals('search')}">
			<td align="left">${ empty product.proTranCode ? "�Ǹ���" : "��� ����"}</td>
		</c:if>
	</tr>
	<tr>
		<td id="${product.prodNo}" colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</tbody>
</table>
	
<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<jsp:include page="../../../webapp/common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>

</body>
</html>
