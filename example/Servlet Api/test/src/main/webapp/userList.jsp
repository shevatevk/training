<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>

</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="menu.jsp"></jsp:include></td>
			<td>
				<table id="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Address</th>
						<th>Date</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="user" items="${sessionScope.userList}">
						<tr>
							<td>${user.name}</td>
							<td>${user.address}</td>
							<td>${user.date}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#table').DataTable();
	});
</script>
</body>
</html>