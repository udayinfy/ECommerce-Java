<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <head>
        <script type="text/javascript">
            var __adrum_fbt = new Date().getTime();
        </script>
        <script type="text/javascript" src="/cc/appd_rum.js"></script>
    </head>
    <body>
        <h1>Enter Flight Numbers Criteria</h1>
        <form method="post" action="<%= request.getContextPath()%>/FlightSearch">
            <select name="airline">
                <option value="UA">United</option>
                <option value="AA">American</option>
                <option value="DL">Delta</option>
                <option value="BA">British Airways</option>
                <option value="LA">Lufthansa</option>
                <option value="JA">Japan Airlines</option>
                <option value="US">US Airways</option>
            </select><br/>

            All Airlines:<input type="checkbox" name="all_airlines"/><br/>
            Flight 1: <input type="text" name="flight1" value="45" size="10"/><br/>
            Flight 2: <input type="text" name="flight2" value="232" size="10"/><br/>
            Flight 3: <input type="text" name="flight3" value="789" size="10"/><br/>
            <input type="hidden" name="flightCount" value="3"/>
            <input type="submit" name="add_flight" value="Search Flights"/>
        </form>
        <% if (request.getParameter("invalid") != null) {%>
        <% if ("true".equals(request.getParameter("invalid"))) {%>
        <h1> Missing Flight Number</h1>
        <%}%>
        <%}%>
        <% if (request.getParameter("submitted") != null) {%>
        <% if ("true".equals(request.getParameter("submitted"))) {%>
        <h1> Successfully Submitted For Update</h1>
        <%}%>
        <%}%>    
        <h1>Update Criteria</h1>
        <form method="post" action="<%= request.getContextPath()%>/FlightStatusUpdate">
            <select name="airline">
                <option value="UA">United</option>
                <option value="AA">American</option>
                <option value="DL">Delta</option>
                <option value="BA">British Airways</option>
                <option value="LA">Lufthansa</option>
                <option value="JA">Japan Airlines</option>
                <option value="US">US Airways</option>
            </select><br/>

            Flight Update: <input type="text" name="flight" value="45" size="10"/><br/>
            <input type="submit" name="add_flight" value="Update Flight"/>
        </form>    
    </body>
</html>
