<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="id" description="Id" required="true"%>
<%@ attribute name="name" description="Name" required="true"%>
<input type="text" id="${id}" name="${name}">
<script>
	$(function() {

		$("#${id}").autocomplete({
			source : "CountryServlet",
			minLength : 2
		});
	});
</script>