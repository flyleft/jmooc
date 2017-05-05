<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Your request cannot be processed</title>
    <link rel="stylesheet" th:href="@{/css/error.css}">
</head>
<body>
<div class="container">
    <h1>${status!'404'}<span>:(</span></h1>

    <p>${title!'发生异常'}</p>

    <span>${error!'请稍后再试'}</span>
    <br/>
</div>
</body>
</html>