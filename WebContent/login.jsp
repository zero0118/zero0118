<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KITWare </title>
    
    <!-- JQuery 3.3.1 CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
    <!-- BootStrap 3.3.7 js CDN -->
    <script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
	<script src="js/custom/sb-admin-2.js"></script>
	
	
	
	<!-- BootStrap 3.3.7 css CDN -->
    <link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
	<!-- Custom CSS -->
	<link href="css/custom/sb-admin-2.css" rel="stylesheet">
 


</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">KITWare Login</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="memberLoginChk" method="post" id
                        "form1" name="form1">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="ID" name="userid" id="userid" type="email" autofocus value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="userpw" id="userpw" type="password" value="" onkeydown="if(event.keyCode == 13) { fn_formSubmit();}">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Y"  checked>계정저장
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#" class="btn btn-lg btn-info btn-block" onclick="fn_formSubmit()">Login</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
