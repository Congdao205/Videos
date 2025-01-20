<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container-fluid">
                <div class="d-flex align-items-center">
                    <a class="navbar-brand " href="index.html"><img src=<c:url value='/Views/User/Images/logo.png'/> style="width: 100px;"
                            alt=""></a>
                    <input class="form-control " style="width: 250px;" type="search" placeholder="Tìm kiếm">
                    <button class="btn btn-outline-light mx-2" type="submit">Search</button>
                </div>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav mx-auto">

				<c:choose>
					<c:when test="${not empty sessionScope.CurrentUser}">
						<p class=" fs-3 text-light "
							style="font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif">Welcom ${sessionScope.CurrentUser.fullname}</p>
						<li class="nav-item "><a class="nav-link" href="index"><i
								class="fa-solid fa-house"></i><b style="padding-right: 20px;">
									Home</b></a></li>
						<li class="nav-item "><a class="nav-link" href="favorite"><i
								class="fa-solid fa-list"></i><b style="padding-right: 20px;">
									My Favorite</b></a></li>
						<c:if test="${sessionScope.CurrentUser.admin == true}">
                            <li class="nav-item"><a class="nav-link" href="admin"><i class="fa-solid fa-cogs"></i><b style="padding-right: 20px;">Manage</b></a></li>
                        </c:if>
						<li class="nav-item"><a class="nav-link" href="logout"><i
								class="fa-solid fa-film"></i><b style="padding-right: 20px;">
									LogOut</b></a></li>
						
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="login"><i
								class="fa-solid fa-circle-info"></i><b
								style="padding-right: 20px;">Login</b></a></li>
						<li class="nav-item"><a class="nav-link" href="register"><i
								class="fa-solid fa-calendar-days"></i><b
								style="padding-right: 20px;"> Register</b></a></li>
						<li class="nav-item"><a class="nav-link" href="forgotpass"><i
								class="fa-solid fa-calendar-days"></i><b
								style="padding-right: 20px;"> forgot password</b></a></li>
					</c:otherwise>
				</c:choose>

				
                    </ul>
                </div>
                
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>