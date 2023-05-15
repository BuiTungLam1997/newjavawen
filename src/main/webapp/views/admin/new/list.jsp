<%--
  Created by IntelliJ IDEA.
  User: Thinkpad
  Date: 06/05/2023
  Time: 10:02 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NEWurl" value="/admin-new">
    <c:param name="page" value="1"></c:param>
    <c:param name="maxPageItem" value="3"></c:param>
    <%--    <c:param name="sortName" value="title"></c:param>--%>
    <%--    <c:param name="sortBy" value="asc"></c:param>--%>
    <c:param name="type" value="list"></c:param>
    <c:param name="message" value="delete_success"></c:param>
</c:url>
>

<html>
<head>
    <title>Danh sach bai viet</title>
</head>
<body>
<div class="main-content">
    <form id="formSubmit" action="/admin-new" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert}">
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="table-btn-controls">
                                        <div class="pull-right tableTools-container">
                                            <div class="dt-buttons btn-overlap btn-group">
                                                <a flag="info"
                                                   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                   data-toggle="tooltip"
                                                   title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                                </a>
                                                <button id="btnDelete" type="button"
                                                        class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                        data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="checkAll"></th>
                                        <th>Tên bài viết</th>
                                        <th>Mô tả ngắn</th>
                                        <th>Nội dung</th>
                                        <th>Hình ảnh</th>
                                        <th>Ngày tạo</th>
                                        <th>Người tạo</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${model.listResult}">
                                        <tr>
                                            <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                            <td>${item.title}</td>
                                            <td>${item.shortDescription}</td>
                                            <td>${item.content}</td>
                                            <td>${item.thumbnail}</td>
                                            <td>${item.createdDate}</td>
                                            <td>${item.createdBy}</td>
                                            <td>
                                                <c:url var="editURL" value="/admin-new">
                                                    <c:param name="type" value="edit"/>
                                                    <c:param name="id" value="${item.id}"/>
                                                </c:url>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Cập nhật bài viết" href='${editURL}'><i
                                                        class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <ul class="pagination" id="pagination"></ul>
                                <input type="hidden" value="" id="page" name="page">
                                <input type="hidden" value="" id="maxPageItem" name="maxPageItem">
                                <input type="hidden" value="" id="sortName" name="sortName">
                                <input type="hidden" value="" id="sortBy" name="sortBy">
                                <input type="hidden" value="" id="type" name="type">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div><!-- /.main-content -->


<script type="text/javascript">
    var currentPage = ${model.page};
    var totalPage = ${model.totalPage};
    var limit = 3;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            startPage: currentPage,
            visiblePages: 3,//so itempage(ko phai item bài viet) trong  trang
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('title');
                    $('#sortBy').val('desc');
                    $('#type').val('list');
                    $('#formSubmit').submit();
                }
            }
        });
    });
    $('#btnDelete').click(function () {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();//lay ra gia tri cua checkbox
        data['ids'] = ids;
        deleteNew(data);
    });

    function deleteNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${NEWurl}&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${NEWurl}&message=error_system";
            },
        });
    }
    $(".alert").delay(2000).slideUp(200, function() {
        $(this).alert('close');
    });
</script>
</body>

</html>
