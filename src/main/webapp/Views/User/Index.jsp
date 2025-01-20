<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<div class="container-fluid p-0 min-vh-100 bg-secondary">
		<%@ include file="/common/nav.jsp"%>
		<div class="container bg-light min-vh-100 rounded-3 mt-3">
			<div class="row row-cols-3 g-3 m-2">
				<c:forEach var="video" items="${videos }">
					<div class="col">
						<div class="card h-100 bg-secondary">
							<div class="card-body">
								<%-- <iframe width="100%" height="200px"src="https://www.youtube.com/embed/${video.poster}"></iframe> --%>
								<a
									href="<c:url value='/video?action=video&id=${video.poster}' />"
									class="nav-link">	
									<img alt="" src='<c:url value='https://img.youtube.com/vi/${video.poster}/maxresdefault.jpg'></c:url>' width="100%" height="200px">
									<p class="fs-4 fw-bold text-light mt-2"
										style="white-space: nowrap; overflow: hidden;">${video.title}</p>
									<p class="fs-5 text-light mt-2" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;overflow: hidden;">
										${video.description}
									</p>
								</a>
								 <c:set var="favorite" value="${fdao.findIdUserVideo(sessionScope.CurrentUser.id, video.id)}" />
								<c:if test="${not empty sessionScope.CurrentUser}">
								    <a href="<c:url value='/video?action=like&id=${video.poster}' />" class="btn btn-none text-light">
								        <i class="fa-solid fa-heart"></i> 
								        <span class="fw-bold">${favorite.likeDate != null ? 'Unlike' : 'Like'}</span>
								    </a>
								    <button type="button" class="btn btn-none text-light">
								        <a href="" class="nav-link"> <i class="fa-solid fa-share"></i>
								            <span class="fw-bold">Share</span>
								        </a>
								    </button>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<nav aria-label="Page navigation example" class="my-3">
				<ul class="pagination justify-content-center">
						<li class="page-item"><a class="page-link " href="?page=${currentpage - 1 < 1 ? 1 : currentpage - 1}"><i class="fa-solid fa-arrow-left-long"></i></a></li>
						
					<c:forEach varStatus="i" begin="1" end="${maxpage}">
						<li class="page-item"><a
							class=" page-link ${currentpage == i.index ? 'active' : ''}"
							href="index?page=${i.index}">${i.index }</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="?page=${currentpage + 1 > maxpage ? maxpage : currentpage + 1}"><i class="fa-solid fa-arrow-right-long"></i></a></li>
				</ul>
			</nav>
		</div>
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>

</html>