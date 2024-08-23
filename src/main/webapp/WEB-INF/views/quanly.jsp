<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Document</title>
</head>
<body>
    <div class="container" style="border: 1px solid black;border-radius: 5px ; margin-top: 10px">
<%--        //detail--%>
    <h4>Quản Lý Bộ Môn / Chuyên Ngành</h4>
        <div class="" style=" border: 1px solid black; margin-top: 10px ;border-radius: 10px">
            <h5>Thông tin nhân viên</h5>
            <br>
            <div >
                <form:form action="/nhan-vien/update" method="post" modelAttribute="staff">
                    <form:input path="id"  type="hidden" ></form:input>
                    Mã Nhân Viên
                    <form:input path="staffCode" cssStyle="width: 200px; height: 40px ; border: 1px solid #000000 ; border-radius: 10px ; margin: 10px" ></form:input>

                    Tên Nhân Viên
                    <form:input  path="name"  cssStyle="width: 200px; height: 40px ; border: 1px solid #000000 ; border-radius: 10px ; margin: 10px"></form:input>

                    Email FPT
                    <form:input path="accountFpt"  cssStyle="width: 200px; height: 40px ; border: 1px solid #000000 ; border-radius: 10px ; margin: 10px"  ></form:input>

                    Email Fe
                    <form:input path="accountFe"  cssStyle="width: 200px; height: 40px ; border: 1px solid #000000 ; border-radius: 10px ; margin: 10px" ></form:input>

<%--                    <button type="submit" style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 50px;height: 40px">lưu</button>--%>
                </form:form>
            </div>
        </div>

<%--        hết detail--%>
        <div class="hh" style="border-radius: 5px ; border: 2px solid #999595 ;width: 100% ;text-align: center ;margin-top: 50px; margin-bottom: 50px">
            <div style="display: flex">
                <div class="col-md-3"><h4>Danh Sách Bộ Môn , Chuyên ngành theo cơ sở</h4></div>
                <div class="col-md-9" style="margin-left: 300px" >

                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="margin: 10px;border-radius: 20px">
                        Thêm bộ môn chuyên ngành
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form:form action="/nhan-vien/addBMCN" method="post" modelAttribute="majorFacility">
                                        Cơ Sở
                                        <form:select onchange="this.form.submit()" path="id" class="form-control">
                                            <form:options items="${listCoSo}" itemLabel="name" itemValue="id"></form:options>
                                        </form:select>
                                        <br>
                                        Bộ Môn
                                        <form:select onchange="this.form.submit()" path="id" class="form-control">
                                            <option hidden value="">---Chọn Bộ Môn---</option>
                                            <form:options items="${listDepartment}" itemLabel="name" itemValue="id"></form:options>
                                        </form:select>
                                        <br>
                                        Chuyên Ngành
                                        <form:select onchange="this.form.submit()" path="idMajor" class="form-control">
                                            <option hidden value="">---Chọn Chuyên Ngành---</option>
                                            <form:options items="${listMajor}" itemLabel="name" itemValue="id"></form:options>
                                        </form:select>
                                        <br>
                                        <hr>
                                        <div class="d-flex justify-content-between">
                                            <button type="submit" class="btn btn-primary" style="background: black; color: white; border-radius: 20px; width: 50px; height: 40px">Lưu</button>
                                            <a href="/nhan-vien/hien-thi" class="btn btn-secondary" style="background: black; color: white; border-radius: 20px; width: 100px; height: 40px; text-decoration: none;">Quay Lại</a>
                                        </div>
                                    </form:form>
                                </div>
<%--                                <div class="modal-footer">--%>
<%--                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                                    <button type="submit" class="btn btn-primary">Save changes</button>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Cơ Sở</th>
                    <th>Bộ Môn</th>
                    <th>Chuyên Ngành</th>
                    <th>Hoạt Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listStaffMajorFacility}" var="nv" varStatus="stt">
                    <tr>
                        <td>${stt.index+1}</td>
                        <td>${nv.nameFacility}</td>
                        <td>${nv.nameDepartment}</td>
                        <td>${nv.nameMajor}</td>
                        <td>
<%--                            <button><a href="/nhan-vien/delete?id=${nv.id}">delete</a>--%>
                            <%--                            </button>--%>
                    <button style="background: #b995dc; border-radius: 10px ; border: 1px #b995dc"><a href="/nhan-vien/delete/${nv.id}" style="color: white; text-decoration: none">delete</a></button>

                        </td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <h5 style="color: #e13131 ; margin-left: -280px ; margin-right: 300px">*** Chú Ý : Nhân Viên Chỉ có một chuyên ngành trong một cơ sở ***</h5>
        </div>
<%--        hết table--%>
    </div>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">

</script>
</html>