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
					<th>First Name</th>
					<th>Last Name</th>
					<th>Contact No</th>
					<th>Joining Date</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labourList}" var="labour">
					<tr>
						<th scope="row">${labour.labour_id}</th>
						<td><c:out value="${labour.firstname}" /></td>
						<td><c:out value="${labour.lastname}" /></td>
						<td><c:out value="${labour.mobile_no}" /></td>
						<td><c:out value="${labour.createdOn}" /></td>
						<td><a href="#">Take More</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

<jsp:include page="footer.jsp" />


