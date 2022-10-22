<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 14.10.2022
  Time: 10:04
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
        }
        .change{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 0;
        }
        .form{
            font-family: "Times New Roman", serif;
            color: #e9f4fb;
            text-align: center;
            font-size:120%;
            font-style: oblique;
            padding: 1em;
        }
        .to_cabinet{
            text-align: center;
            font-size: 100%;
            color: #e9f4fb;
        }
        .center{
            text-align: center;
            font-size: 90%;
            color: #e9f4fb;
        }
        #my_cabinet{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 30px;
        }
        #to_create_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #to_delete_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #to_edit_magazine{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }
        #block_user{
            text-align: center;
            color: azure;
            text-decoration: none;
            font-size: 20px;
        }


    </style>
    <title>Admin</title>
</head>
<body>
<div class="to_cabinet">
<a id="my_cabinet" href="cabinet.jsp">My cabinet</a>
</div>
<hr>
<div class="change">
    <form action="AddCategoryServlet" method="post">
        <input type="text" name="category_name"  required>
        <span></span>
        <label>Category name</label>

        <p><input type="submit" value="Add Category"></p>
    </form>
</div>
<div class="form">
    <form action="AddPublisherServlet" method="post">
        <input type="text" name="publisher_name" required>
        <span></span>
        <label>Publisher name</label>

        <p><input type="submit" value="Add Publisher"></p>
    </form>
</div>
<hr>
<div class="center">
<h3>Or you can create new magazine with exist category and publisher </h3>
<a id="to_create_magazine" href="add_magazine.jsp">Create</a>
<hr>
<h3>Delete magazine</h3>
<a id="to_delete_magazine" href="delete_mag.jsp">Delete</a>
<hr>
<h3>Editing magazine</h3>
<a id="to_edit_magazine" href="edit_mag.jsp">Edit</a>
<hr>
<h3>Block User</h3>
<a id="block_user" href="block_user.jsp">Block</a>
</div>
</body>
</html>
