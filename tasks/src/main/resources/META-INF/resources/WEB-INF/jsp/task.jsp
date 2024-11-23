<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<title>Add Task Page</title>		
	</head>
	<body>
		<div class="container">
			<h1>Enter Task Details</h1>			
			
			<form:form method="post" modelAttribute="task">
			<fieldset class="mb-3">
				<form:label path="description">Description</form:label>
				<form:input type="text" path="description" required="required" />
				<form:errors path="description" cssClass="text-warning" />
			</fieldset>
			<fieldset class="mb-3">
				<form:label path="targetDate">Target Date</form:label>
				<form:input type="text" path="targetDate" required="required" />
				<form:errors path="targetDate" cssClass="text-warning" />
			</fieldset>

			<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="done"/>
				<input type="submit" class="btn btn-success"/>
			</form:form>
			
			</div>
			<%@ include file="common/footer.jspf"%>
			<script type="text/javascript">
				$('#targetDate').datepicker({
					format : 'yyyy-mm-dd'
				});
			</script>
</body>
</html>