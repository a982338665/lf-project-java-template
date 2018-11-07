<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
${guest}|
<#--不可以使用关键字time-->
${timevar?string("yyyy-MM-dd HH:mm:ss zzzz")}
${timevar?string('yyyy-MM-dd')}
<ul>
<#list list as s>
    <li>${s.id}---------> ${s.name}</li>
</#list>
</ul>


</body>
</html>