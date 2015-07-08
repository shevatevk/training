<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<form action="CalculatorServlet" method="post">
					<table>
						<tr>
							<td><input type="text" name="result" value="${sessionScope.resultInSession}"></td>
						</tr>
						<tr>
							<td><input type="text" name="input"></td>
						</tr>
						<tr>
							<td><button type="submit" name="operator" value="add">+</button></td>
						</tr>
					</table>


				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>