<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h1>Your Tasks</h1>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Status</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${tasks}" var="tasks">
				<tr>
					<td>${tasks.id}</td>
					<td>${tasks.description}</td>
					<td>${tasks.targetDate}</td>
					<td>${tasks.done}</td>
					<td><a class="btn btn-success"
				href="/update-todo?id=${tasks.id}">Update</a></td>
					<td><a class="btn btn-danger"
						href="/delete-todo?id=${tasks.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Task</a>
</div>

<%@ include file="common/footer.jspf"%>