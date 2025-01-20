<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
<style type="text/css">
textarea {
	overflow: hidden;
	min-height: 50px;
	resize: none;
}

.thumbnail {
	border: 1px solid #dee2e6;
	padding: 10px;
	margin-bottom: 10px;
	display: flex;
	gap: 10px;
	text-decoration: none;
	color: inherit;
}

.thumbnail:hover {
	background-color: #0c3a68;
}

.poster-placeholder {
	width: 120px;
	height: 68px;
	background: #f8f9fa;
	border: 1px solid #dee2e6;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-shrink: 0;
}
</style>
</head>
<body>
	<div class="container-fluid p-0 bg-secondary">
		<%@ include file="/common/nav.jsp"%>
		<div class="container min-vh-100 bg-dark mt-3 rounded-3">
			<div class="row">
				<div class="col-8">
					<div class="radio ratio-16x9 bg-black rounded mt-3">
						<iframe width="100%" height="400px" class="rounded"
							src="https://www.youtube.com/embed/${video.poster} "></iframe>
					</div>
					<div class="d-flex justify-content-between">
						<h3 class="mt-3 text-light">${video.title }</h3>

						<div class="d-flex mt-3">
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
							<button type="button" class="btn btn-none text-light"
								style="margin-right: 32px;">
								<i class="fa-solid fa-comment"></i> <span class="fw-bold">Comment</span>
							</button>
						</div>
					</div>
					<button class="btn btn-outline-light mb-3" type="button"
						data-bs-toggle="collapse" data-bs-target="#Info">Xem nội
						dung</button>
					<div id="Info" class="collapse">
						<p class="lead mb-4 w-100 fs-7 text-light">${video.description}</p>
					</div>
					<h5 class="mt-4">Bình luận</h5>
					<div class="mb-3 d-flex">
						<textarea name="" class="form-control" oninput="autoGrow(this)"
							placeholder="Thêm bình luận..."></textarea>
					</div>
					<button class="btn btn-danger ">Gửi</button>
				</div>
				<div class="col-4 pt-3">
					<c:forEach items="${listvideo}" var="vd"> 
					<div class="d-flex flex-column">
						<a href="<c:url value='/video?action=video&id=${vd.poster}' />" class="thumbnail">
							<div class="poster-placeholder">
								<img src='<c:url value='https://img.youtube.com/vi/${vd.poster}/maxresdefault.jpg'></c:url>'
									class="w-100">
							</div>
							<div class="video-info">
								<span class="text-light">${vd.title}</span>
							</div>
						</a> 
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<%@ include file="/common/footer.jsp"%>
	</div>
	
</body>
</html>