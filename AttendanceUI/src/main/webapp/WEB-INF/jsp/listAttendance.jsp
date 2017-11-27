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
					<th>Month</th>
					<th>Present</th>
					<th>Absents</th>
					<th>Total Hours</th>
					<th>Overtime Hours</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labourList}" var="labour">
					<tr>
						<th scope="row">${labour.labour_id}</th>
						<td><a href="<c:url value="/attendanceSummary/${labour.labour_id}" />"> <c:out value="${labour.firstname} ${labour.lastname}" /></a></td>
						<td><c:out value="Oct 2017" /></td>
						<td><c:out value="21" /></td>
						<td><c:out value="3" /></td>
						<td><c:out value="189 H" /></td>
						<td><c:out value="4 H" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

<jsp:include page="footer.jsp" />