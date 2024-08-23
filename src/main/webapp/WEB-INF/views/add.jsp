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
<div class="container" style="width: 500px; border: 1px solid black; margin-top: 10px ;border-radius: 10px">
    <div >
        <form:form action="/nhan-vien/add" method="post" modelAttribute="staff">
            Mã Nhân Viên
            <form:input path="staffCode" class="form-control"></form:input>
            <form:errors path="staffCode" class="form-control" cssStyle="color: red"></form:errors>
            <br>
            Tên Nhân Viên
            <form:input path="name" class="form-control"></form:input>
            <form:errors path="name" class="form-control" cssStyle="color: red"></form:errors>
            <br>
            Email FPT
            <form:input path="accountFpt" class="form-control"></form:input>
            <form:errors path="accountFpt" class="form-control" cssStyle="color: red"></form:errors>
            <br>
            Email Fe
            <form:input path="accountFe" class="form-control"></form:input>
            <form:errors path="accountFe" class="form-control" cssStyle="color: red"></form:errors>
            <br>
            <hr>
            <button type="submit" style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 50px;height: 40px">lưu</button>
            <button style="background: black ; color: white; margin: 5px ; border-radius: 20px; width: 100px;height: 40px"><a href="/nhan-vien/hien-thi" style="text-decoration: none;color: white">quay lại</a></button>
        </form:form>
    </div>
</div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">

</script>
</html>