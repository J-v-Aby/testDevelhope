<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="develhope.caption"/></b>
</p>
<portlet:defineObjects />
<%
String setupTextId = (String) renderRequest.getAttribute("setupTextId");
String deliveryTextId = (String) renderRequest.getAttribute("deliveryTextId");
String alertMessage = (String) renderRequest.getAttribute("alertMessage");
 %>
<portlet:actionURL var="testResourceURL">
    <portlet:param name="javax.portlet.action" value="test" />
</portlet:actionURL>

<aui:form action="<%=testResourceURL.toString() %>" method="post">
    <aui:button type="submit" value="test" />
</aui:form>

<h3>setup: <%= setupTextId %></h3>
<p>delivery: <%= deliveryTextId%></p>
<p>alertMessage: <%= alertMessage%></p>