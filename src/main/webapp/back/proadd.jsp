<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/23
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>

</script>

<div style="text-align: center">
    <form id="proadd" method="post" enctype="multipart/form-data">
        商品名：<input class="easyui-validatebox" name="name" data-options="required:true" /></br>
        价格：<input class="easyui-validatebox" name="price" data-options="required:true" /></br>
        封面图片：<input class="easyui-filebox" name="aa" data-options="required:true" style="width:220px"></br>
        内容简介：<textarea name="descript" style="width:200px;height:80px;">这里写内容</textarea></br>
    </form>
</div>

