<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>David Airlines Web App</title>
        
        <script src="<%=request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
        <script>
            var ctxPath = "<%=request.getContextPath() %>";
            $(function(){                
                $("#postPerson, #postMessage").on("click", function(){
                    $.ajax({
                        url: $(this).attr("id") === "postMessage" ? ctxPath+"/ws/message/post" : ctxPath+"/ws/person/post",
                        type: "POST",
                        data: '{"firstName":"Michael", "lastName":"Jordan"}',
                        contentType: "application/json",
                        cache: false,
                        dataType: "json"
                    });
                });                
            });
        </script>
                
   	</head>

	<body>
	   <h1>David Airlines Web App Java REST Services</h1>
	   <ul>
               <li><a href="InitServlet">Call InitServlet</a></li>
               
               <li><a href="<%=request.getContextPath() %>/ws/airline/findAll"><%=request.getContextPath() %>/ws/airline/findAll</a></li>
               <li><a href="<%=request.getContextPath() %>/ws/airplane/findAll"><%=request.getContextPath() %>/ws/airplane/findAll</a></li>
               <li><a href="<%=request.getContextPath() %>/ws/airport/findAll"><%=request.getContextPath() %>/ws/airport/findAll</a></li>
               <li><a href="<%=request.getContextPath() %>/ws/flight/findAll"><%=request.getContextPath() %>/ws/flight/findAll</a></li>
               <br><br><br>
               
	       <!--
               <li><a href="<%=request.getContextPath() %>/ws/message"><%=request.getContextPath() %>/ws/message</a></li>
	       <li><a href="<%=request.getContextPath() %>/ws/message/ping"><%=request.getContextPath() %>/ws/message/ping</a></li>
	       <li><a href="<%=request.getContextPath() %>/ws/person/get"><%=request.getContextPath() %>/ws/person/get</a></li>
               <br><br>
               <li><button id="postPerson">Post Person</button></li>
               <li><button id="postMessage">Post Message</button></li>
               -->
	   </ul>
           
    </body>
    
</html>