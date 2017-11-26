<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp" />
<div class="container-fluid">
	<c:if test="${!empty labourList}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Advance Taken</th>
					<th>Last Advance</th>
					<th>Last Adv Date</th>
					<th>Remarks</th>
					<th>#</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labourList}" var="labour">
					<tr>
						<th scope="row">${labour.labour_id}</th>
						<td><c:out value="${labour.firstname} ${labour.lastname}" /></td>
						<td><c:out value="INR 2000" /></td>
						<td><c:out value="INR 300" /></td>
						<td><c:out value="25-NOV-2017" /></td>
						<td><c:out value="Loan" /></td>
						<td><a href="#">Take More</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

<jsp:include page="footer.jsp" />


