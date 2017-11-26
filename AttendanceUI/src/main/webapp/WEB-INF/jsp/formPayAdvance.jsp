<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp" />
<div class="container-fluid">

	<div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<spring:url value="/savePayment" var="advancePayURL" />
			<form:form class="form-horizontal" method="post"
				modelAttribute="paymentForm" action="${advancePayURL}">
				<fieldset>
					<h2>Pay Advance to </h2>
					<hr class="colorgraph">
					<div class="form-group">
						<spring:bind path="labour_id">
							<form:input path="labour_id" class="form-control" id="labour_id"
								placeholder="" />
							<form:errors path="labour_id" class="control-label" />

						</spring:bind> 
					</div>
					
					<div class="form-group">
						<spring:bind path="industry_id">
							<form:input path="industry_id" class="form-control" id="industry_id"
								placeholder="" />
							<form:errors path="industry_id" class="control-label" />

						</spring:bind> 
					</div>
					<div class="form-group">
						<spring:bind path="amount">
							<form:input path="amount" class="form-control" id="amount"
								placeholder="" />
							<form:errors path="amount" class="control-label" />

						</spring:bind>
					</div>
					<div class="form-group">
						<spring:bind path="remarks">
							<form:textarea path="remarks" class="form-control" id="remarks"
								placeholder="remarks" />
							<form:errors path="remarks" class="control-label" />

						</spring:bind>

					</div>

					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">

							<form:button type="submit" class="btn-lg btn-primary pull-right">Pay
										</form:button>
						</div>
						<!-- <div class="col-xs-6 col-sm-6 col-md-6">
								<a href="" class="btn btn-lg btn-primary btn-block">Register</a>
							</div> -->
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp" />

