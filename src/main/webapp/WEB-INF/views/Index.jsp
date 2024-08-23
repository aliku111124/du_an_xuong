<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <div class="hh" style="border-radius: 5px ; border: 2px solid #999595 ;width: 100% ;text-align: center">
        <div style="display: flex">
            <div class="col-md-3"><h4>Danh Sách Nhân Viên</h4></div>
            <div class="col-md-9"  >
<%--                <button style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 200px;height: 50px">Import Nhân Viên</button>--%>
<%--                -----------------------------------------------------------------------------%>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 200px;height: 50px">
                    Import Nhân Viên
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
                                <h1>Import Staff</h1>
                                <form method="POST" enctype="multipart/form-data" action="/nhan-vien/import-staff">
                                    <input type="file" name="file" accept=".xlsx">
                                    <button type="submit" style="border-radius: 10px;border: 1px solid red ; background: red ; color: white">Upload</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
<%--                --------------------------------------------------------------%>
                <button style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 200px;height: 50px"><a href="/nhan-vien/download/excel-template" style="text-decoration: none; color: white">Download Template</a></button>
                <button style="background: black ; margin: 5px ; border-radius: 20px; width: 200px;height: 50px"><a href="/nhan-vien/viewAdd" style="text-decoration: none; color: white">Thêm Nhân Viên</a></button>
<%--                <button style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 200px;height: 50px">Xem lịch sử Import</button>--%>

    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 200px;height: 50px">
        Xem lịch sử Import
    </button>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ngày Tạo</th>
                            <th>Nội Dung</th>
                            <th>Đường dẫn</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listNhanVien}" var="nv" varStatus="stt">
                            <tr>
                                <td>${stt.index+1}</td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Understood</button>
                </div>
            </div>
        </div>
    </div>


            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã Nhân Viên</th>
                <th>Tên Nhân Viên</th>
                <th>Email FPT</th>
                <th>Email Fe</th>
                <th>Trạng Thái</th>
                <th>action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listNhanVien}" var="nv" varStatus="stt">
                <tr>
                    <td>${stt.index+1}</td>
                    <td>${nv.staffCode}</td>
                    <td>${nv.name}</td>
                    <td>${nv.accountFpt}</td>
                    <td>${nv.accountFe}</td>
                    <td>${nv.status == 1 ? "Đang Hoạt Động" : "Ngừng Hoạt Động"}</td>
                    <td>
                        <button style="background: #d91818;border-radius: 10px ; border: 1px #d91818"><a href="/nhan-vien/viewUpdate/${nv.id}" style="color: white;text-decoration: none ">update</a></button>
                        <button  style="background: #259bc2;border-radius: 10px ; border: 1px #259bc2"><a href="/nhan-vien/setStatus/${nv.id}" style="color: white;text-decoration: none ">đổi trạng thái</a></button>
                        <button style="background: #ded325;border-radius: 10px; border: 1px #ded325 "><a href="/nhan-vien/detail/${nv.id}" style="color: white;text-decoration: none ">detail</a></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">

</script>
</html>