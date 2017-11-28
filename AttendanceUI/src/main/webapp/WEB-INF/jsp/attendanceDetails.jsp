<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp" />

<div class="container-fluid">
	<c:if test="${!empty listAttendanceHistory}">

		<table class="table table-hover">
			<thead>
				<tr>
					<th>Date</th>
					<th>In Time</th>
					<th>Out Time</th>
					<th>Minutes</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listAttendanceHistory}" var="attendance">
					<tr>
						<td><c:out value="${fn:substring(attendance.datetime, 0, 10)}" /></td>
						<td><c:out value="${fn:substring(attendance.in_time, 11, 19)}" /></td>
						<td><c:out value="${fn:substring(attendance.out_time, 11, 19)}" /></td>
						<td><c:out value="${attendance.working_minutes/60} Hr ${attendance.working_minutes%60} Min" /></td>
						<td><c:out value="${attendance.status}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

<jsp:include page="footer.jsp" />