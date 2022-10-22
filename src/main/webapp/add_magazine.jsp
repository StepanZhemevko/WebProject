<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 18.10.2022
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 70vh;
            text-align: center;
        }
        .change{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 2em;
        }

        .txt_field input{
            width: 100%;
            padding: 0 5px;
            height: 40px;
            font-size: 16px;
            border: none;
            background: none;
            outline: none;
        }
        .txt_field label{
            position: absolute;
            top: 50%;
            left: 5px;
            color: #adadad;
            transform: translateY(-50%);
            font-size: 16px;
            pointer-events: none;
            transition: .5s;
        }

        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 35px;
        }
    </style>
    <title>Add Magazines</title>
</head>
<div class="to_cabinet">
<a id="my_cabinet" href="cabinet.jsp" >My Cabinet</a>
<hr>
</div>
<body>
<div class="change">
    <form action="AddMagazineServlet" method="post">
        <label>Magazine name</label>
        <p><input type="text" name="magazine_name"  required></p>
        <span></span>

        <label>Prise</label>
        <p><input type="number" name="prise" required></p>
        <span></span>

        <label>Description</label>
        <p><input type="text" name="description"></p>
        <span></span>

        <label>Image link</label>
        <p><input type="text" name="image_link" required></p>
        <span></span>

        <label>Categories</label>
        <p><input type="text" name="category"  required> </p>
        <span></span>

        <label>Publisher</label>
        <p><input type="text" name="publisher"  required>  </p>
        <span></span>

        <p><input type="submit" value="Add Magazine"></p>
    </form>
</div>
</body>
</html>
