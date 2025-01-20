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
	<main class="container my-5 min-vh-100">
		<h1>My Favorite</h1>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach var="video" items="${favoritevideos}">
            <div class="col">
                <div class="card h-100">
                    <iframe width="100%" height="300px" class="rounded" src="https://www.youtube.com/embed/${video.poster}"></iframe>
                    <div class="card-body">
                        <h5 class="card-title">${video.title}</h5>
                        <p class="card-text">${video.description}</p>
                    </div>
                </div>
            </div>
		</c:forEach>
            </div>
    </main>
     <%@ include file="/common/footer.jsp"%>
</body>
</html>