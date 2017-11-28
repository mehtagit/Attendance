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
					<th>Contact No</th>
					<th>Overtime Hrs</th>
					<th>Overtime Amount</th>
					<th>Due Amount</th>
					<th>Total Salary</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labourList}" var="labour">
					<tr>
						<th scope="row">${labour.labour_id}</th>
						<td><c:out value="${labour.firstname} ${labour.lastname}" /></td>
						<td><c:out value="${labour.mobile_no}" /></td>
						<td><c:out value="20" /></td>
						<td><input class="form-control" type="number" size="16" value="2000"></td>
						<td><input class="form-control" type="number" size="16" value="3400"></td>
						<td><input class="form-control" type="number" size="16" value="27899"></td>
						<td><c:out value="Pending" /></td>
						<td><button type="button" class="btn btn-primary">Pay</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

<jsp:include page="footer.jsp" />