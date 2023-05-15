<%--
  Created by IntelliJ IDEA.
  User: Thinkpad
  Date: 11/05/2023
  Time: 10:09 CH
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
    <%--    <c:param name="message" value="delete_success"></c:param>--%>
</c:url>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
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
                    <form id="formSubmit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="categoryCode" name="categoryCode">

                                    <c:if test="${not empty model.categoryCode}">
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.code}"
                                                    <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
                                                    ${item.name}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty model.categoryCode}">
                                        <option>Chon loai bai viet</option>
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.code}">${item.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="thumbnail" name="thumbnail"
                                       value="${model.thumbnail}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="shortDescription" name="shortDescription"
                                       value="${model.shortDescription}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                            <div class="col-sm-9">
                                <textarea rows="" cols="" id="content" name="content"
                                          style="width: 820px;height: 175px">${model.content}</textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" value="Cap nhat bai viet" id="btnAddOrUpdateNew"
                                           name=""></input>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" value="Them moi bai viet" id="btnAddOrUpdateNew"
                                           name=""></input>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('content');
    });
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();//submit dung url can den
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        data["content"] = editor.getData();//lay noi dung tu ckeditor
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });

    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',//kieu du lieu client gui cho server
            data: JSON.stringify(data),//chuyen kieu tu javascrip object thanh kieu json
            dataType: 'json',//kieu du lieu tu sever gui ve cho client
            success: function (result) {
                window.location.href = "${NEWurl}&id=" + result.id + "&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${NEWurl}&message=error_system";
            },
        });
    }

    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NEWurl}&id=" + result.id + "&message=update_success";
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
