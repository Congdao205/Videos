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
  	<c:url value="/admin" var="path"></c:url>
    <%@ include file="/common/nav.jsp"%>
    <div class="container min-vh-100 ">
      <div class="row">
        <!-- Sidebar -->
        <nav class="col-md-3 col-lg-2 d-md-block sidebar collapse">
          <div class="position-sticky pt-3">
            <ul class="nav flex-column nav-pills">
              <li class="nav-item"><a class="nav-link active" href="#users" data-bs-toggle="pill">Users</a></li>
              <li class="nav-item"><a class="nav-link" href="#videos" data-bs-toggle="pill">Videos</a></li>
              <li class="nav-item"><a class="nav-link" href="#favorites" data-bs-toggle="pill">Favorites</a></li>
            </ul>
          </div>
        </nav>

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <div class="content">
            <div class="tab-content">
              <!-- Users Section -->
              <div class="tab-pane fade show active" id="users">
                <ul class="nav nav-tabs" id="userTabs" role="tablist">
                  <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="user-edition-tab" data-bs-toggle="tab" data-bs-target="#user-edition" type="button" role="tab">User Edition</button>
                  </li>
                  <li class="nav-item" role="presentation">
                    <button class="nav-link" id="user-list-tab" data-bs-toggle="tab" data-bs-target="#user-list" type="button" role="tab">User List</button>
                  </li>
                </ul>
                <div class="tab-content" id="userTabsContent">
                  <div class="tab-pane fade show active" id="user-edition" role="tabpanel">
                    <form class="mt-3" action="${path}" method="post">
                    	<input type="hidden" name="type" value="user">
                      <div class="mb-3">
                        <input type="text" class="form-control" name="id" placeholder="Username?" value="${useredit.id}" >
                      </div>
                      <div class="mb-3">
                        <input type="password" class="form-control" name="password" placeholder="Password?"  value="${useredit.password}" >
                      </div>
                      <div class="mb-3">
                        <input type="text" class="form-control" name="fullname" placeholder="Fullname?"  value="${useredit.fullname}" >
                      </div>
                      <div class="mb-3">
                        <input type="email" class="form-control" name="email" placeholder="Email Address?" value="${useredit.email}" >
                      </div>
                       	<button type="submit" name="action" value="update" class="btn btn-secondary">Update</button>
    					<button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                    </form>
                  </div>
                  <div class="tab-pane fade" id="user-list" role="tabpanel">
                    <div class="table-responsive mt-3">
                      <table class="table table-striped">
                        <thead>
                          <tr >
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Admin</th>
                            <th class="text-center">Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="user" items="${user}">
                            <tr id="user-row-${user.id}">
                              <td>${user.id}</td>
                              <td>${user.password}</td>
                              <td>${user.fullname}</td>
                              <td>${user.email}</td>
                              <td>${user.admin}</td>
                              <td class="text-center">
                               <%-- 	<button class="btn btn-info btn-xs"  onclick="editUser('${user.id}')">Edit</button> --%>
                   	 			<button class="btn btn-danger btn-xs" onclick="deleteUser('${user.id}')">Del</button>
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="d-flex justify-content-between align-items-center mt-3">
                      <span>185 users</span>
                      <div>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-backward-step"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-backward"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-forward"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-forward-step"></i></button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Videos Section -->
              <div class="tab-pane fade" id="videos">
                <ul class="nav nav-tabs" id="videoTabs" role="tablist">
                  <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="video-edition-tab" data-bs-toggle="tab" data-bs-target="#video-edition" type="button" role="tab">Video Edition</button>
                  </li>
                  <li class="nav-item" role="presentation">
                    <button class="nav-link" id="video-list-tab" data-bs-toggle="tab" data-bs-target="#video-list" type="button" role="tab">Video List</button>
                  </li>
                </ul>
                <div class="tab-content" id="videoTabsContent">
                  <div class="tab-pane fade show active" id="video-edition" role="tabpanel">
                    <div class="row mt-3">
                      <div class="col-md-4">
                        <img alt="" src="https://via.placeholder.com/300" id="previewImage" width="100%" height="200px">
                      </div>
                      <div class="col-md-8">
                        <form action="${path}" method="post">
                        	<input type="hidden" name="type" value="video">
                          <div class="mb-3">
                            <input type="text" class="form-control" name="id"  placeholder="Youtube ID?">
                          </div>
                          <div class="mb-3">
                            <input type="text" class="form-control" name="title"  placeholder="Video Title?">
                          </div>
                          <div class="mb-3">
                            <input type="text" class="form-control" name="poster" id="posterInput"  oninput="updatePreviewImage()" placeholder="link href">
                          </div>
                          <div class="mb-3">
                            <input type="number" class="form-control" name="views" placeholder="View Count?" value="0">
                          </div>
                          <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="activeCheck" name="active" value="true"> <label class="form-check-label" for="activeCheck">Active</label>
                          </div>
                          <div class="mb-3">
                            <textarea class="form-control" rows="3" name="description" placeholder="Description?"></textarea>
                          </div>
                          	<button type="submit" name="action" value="create" class="btn btn-primary me-2">Create</button>
						    <button type="submit" name="action" value="update" class="btn btn-secondary me-2">Update</button>
						    <button type="submit" name="action" value="delete" class="btn btn-danger me-2">Delete</button>
						    <button type="reset" class="btn btn-outline-secondary">Reset</button>
                        </form>
                      </div>
                    </div>
                  </div>
                  <div class="tab-pane fade" id="video-list" role="tabpanel">
                    <div class="table-responsive mt-3">
                      <table class="table ">
                        <thead>
                          <tr>
                            <th>Youtube Id</th>
                            <th>Video Title</th>
                            <th>View Count</th>
                            <th>Description</th>
                            <th>Active</th>
                            <th class="text-center">Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="video" items="${video}">
                            <tr id="video-row-${video.id}">
                              <td>${video.id}</td>
                              <td>${video.title}</td>
                              <td>${video.views}</td>
                              <td>${video.description}</td>
                              <td>${video.active}</td>
                              <%-- <td class="text-center d-flex">
                               <button class='btn btn-info btn-xs' onclick="editVideo('${video.id}')">Edit</button>
                   	 			<button class="btn btn-danger btn-xs" onclick="deleteVideo('${video.id}')">Del</button>
                              </td> --%>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="d-flex justify-content-between align-items-center mt-3 my-3">
                      <span>185 users</span>
                      <div>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-backward-step"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-backward"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-forward"></i></button>
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-forward-step"></i></button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Favorites Section -->
              <div class="tab-pane fade" id="favorites">
                <ul class="nav nav-tabs" id="favoriteTabs" role="tablist">
                  <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="favorites-tab" data-bs-toggle="tab" data-bs-target="#favorites-content" type="button" role="tab">Favorites</button>
                  </li>
                  <li class="nav-item" role="presentation">
                    <button class="nav-link" id="favorite-users-tab" data-bs-toggle="tab" data-bs-target="#favorite-users" type="button" role="tab">Favorite Users</button>
                  </li>
                  <li class="nav-item" role="presentation">
                    <button class="nav-link" id="shared-friends-tab" data-bs-toggle="tab" data-bs-target="#shared-friends" type="button" role="tab">Shared Friends</button>
                  </li>
                </ul>
                <div class="tab-content" id="favoriteTabsContent">
                  <div class="tab-pane fade show active" id="favorites-content" role="tabpanel">
                    <div class="table-responsive mt-3">
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th>Video Title</th>
                            <th>Favorite Count</th>
                            <th>Latest Date</th>
                            <th>Oldest Date</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="favorite" items="${favorite}">
                            <tr>
                              <td>${favorite[0]}</td>
                              <td>${favorite[1]}</td>
                              <td>${favorite[2]}</td>
                              <td>${favorite[3]}</td>

                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div class="tab-pane fade my-4" id="favorite-users" role="tabpanel">
                    <select class="form-control">
                      <c:forEach items="${video}" var="video">
                        <option value="${video.id}">${video.title}</option>
                      </c:forEach>
                    </select>
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>Fullname</th>
                          <th>Email</th>
                          <th>likeDate</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>Lâu ghê mới gặp</td>
                          <td>Nguyễn Văn Tèo</td>
                          <td>teonv@gmail.com</td>
                          <td>11-11-2020</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="tab-pane fade" id="shared-friends" role="tabpanel">
                    <div class="table-responsive mt-3">
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th>Video Title</th>
                            <th>Sender Name</th>
                            <th>Sender Email</th>
                            <th>Receiver Email</th>
                            <th>Sent Date</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${share}" var="share">
                          	<tr>
	                          	<td>${share.video.title}</td>
	                            <td>${share.user.fullname}</td>
	                            <td>${share.user.email}</td>
	                            <td>${share.emails}</td>
	                            <td>${share.shareDate}</td>
                          	</tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
    <%@ include file="/common/footer.jsp"%>
    <script>
  function updatePreviewImage() {
    const posterInput = document.getElementById("posterInput");
    const previewImage = document.getElementById("previewImage");
    const url = posterInput.value.trim();

    if (url) {
      previewImage.src =  "https://img.youtube.com/vi/"+url+"/maxresdefault.jpg";
    } else {
      previewImage.src = "https://via.placeholder.com/300"; // Hình mặc định nếu không có link
    }
  }
  function deleteUser(userId) {
      if (confirm('Are you sure you want to delete this user?')) {
          const xhr = new XMLHttpRequest();
          xhr.open("POST", "admin", true);
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          xhr.onload = function() {
              if (xhr.status === 200) {
                  document.getElementById('user-row-' + userId).remove();
              }
          };
          xhr.send("type=user&action=delete&id=" + userId);
      }
  }
  function deleteVideo(videoId) {
      if (confirm('Are you sure you want to delete this video?')) {
          const xhr = new XMLHttpRequest();
          xhr.open("POST", "admin", true);
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          xhr.onload = function() {
              if (xhr.status === 200) {
                  document.getElementById('video-row-' + videoId).remove();
              }
          };
          xhr.send("type=user&action=delete&id=" + videoId);
      }
  }
  

</script>
  </body>

</html>