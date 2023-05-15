<%--
  Created by IntelliJ IDEA.
  User: Thinkpad
  Date: 10/05/2023
  Time: 9:22 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Đăng nhâp</title>
</head>
<body id="LoginForm">
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-${alert}">
            <strong>${message}!</strong>
        </div>
    </c:if>
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3>Sign In</h3>
                <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                </div>
            </div>
            <form action="<c:url value='/dang-nhap'/>" id="formLogin" method="POST">
                <div class="card-body">
                    <form>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" id="userName" name="userName"
                                   placeholder="username">

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="password">
                        </div>
                        <div class="row align-items-center remember">
                            <input type="checkbox">Remember Me
                        </div>
                        <input type="hidden" value="login" id="action" name="action">
                        <div class="form-group">
                            <button type="submit" value="Login" class="btn float-right login_btn">Login</button>
                        </div>
                    </form>
                </div>
            </form>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    Don't have an account?<a href="#">Sign Up</a>
                </div>
                <div class="d-flex justify-content-center">
                    <a href="#">Forgot your password?</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
