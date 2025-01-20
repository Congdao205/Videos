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
                                <h4 class="text-center fs-1" style="font-family: cursive;">Edit Profice</h4>
                            </div>
                            <form action="<c:url value='/changepass'/>" method="post">
                                <div class="row gy-3 overflow-hidden">
                                    <div class="col-12"> 
                                        <div class="form-floating mb-3">
                                            <input type="text" class="form-control" name="id" id="id"
                                                placeholder=""disabled>
                                            <label for="id" class="form-label" >Account</label>
                                        </div>
                                    </div>
                                    <div class="col-12"> 
                                        <div class="form-floating mb-3">
                                            <input type="text" class="form-control" name="id" id="id"
                                                placeholder="" required>
                                            <label for="id" class="form-label">Fullname</label>
                                        </div>
                                    </div>
                                    <div class="col-12"> 
                                        <div class="form-floating mb-3">
                                            <input type="text" class="form-control" name="id" id="id"
                                                placeholder="" required>
                                            <label for="id" class="form-label">Email</label>
                                        </div>
                                    </div>
                                    <div class="col-12"> 
                                        <div class="form-floating mb-3">
                                            <input type="text" class="form-control" name="id" id="id"
                                                placeholder="" required>
                                            <label for="id" class="form-label">Password</label>
                                        </div>
                                    </div>
                                    
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn bsb-btn-xl btn-primary" type="submit">Edit</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="/common/footer.jsp"%>
</body>
</html>