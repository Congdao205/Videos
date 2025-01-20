<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	 <%@ include file="/common/nav.jsp"%>
	 <section class="bg-dark p-3 p-md-4 p-xl-5 min-vh-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6 col-xxl-5">
                    <div class="card border rounded-4">
                        <div class="card-body p-3 p-md-4 p-xl-5">
                            <div class="mb-5">
                                <div class="text-center mb-4">
                                    <a href="index">
                                        <img src=<c:url value='/Views/User/Images/logo.png'/> alt="Logo" width="200" height="80">
                                    </a>
                                </div>
                                <h4 class="text-center fs-1" style="font-family: cursive;">Login</h4>
                            </div>
                            <form action="<c:url value='/login'/>" method="post">
                                <div class="row gy-3 overflow-hidden">
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="text" class="form-control" name="id" id="id"
                                                placeholder="" required>
                                            <label for="id" class="form-label">Account</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" class="form-control" name="password" id="password"
                                                value="" placeholder="Password" required>
                                            <label for="password" class="form-label">Password</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn bsb-btn-xl btn-primary" type="submit">Log in </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <p style="color:red">${message}</p>
                            <div class="row">
                                <div class="col-12">
                                    <hr class="mt-5 mb-4 border-secondary-subtle">
                                    <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end">
                                        <a href="register" class="link-secondary text-decoration-none">Create new account</a>
                                        <a href="#!" class="link-secondary text-decoration-none">Forgot password</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="/common/footer.jsp"%>
</body>
</html>