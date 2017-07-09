<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/23
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Lucene-Test</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script>
        $(function () {
            $("#proList").datagrid({
                url: "${pageContext.request.contextPath}/product/showAll",
                striped: true,
                columns: [[
                    {title: "id", field: "id", width: 200, align: 'center'},
                    {title: "name", field: "name", width: 200, align: 'center'},
                    {title: "pic", field: "path", width: 200, align: 'center'},
                    {title: "price", field: "price", width: 200, align: 'center'},
                    {
                        title: "索引", field: "ind", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "否";
                            } else {
                                return "是";
                            }
                        }
                    },
                    {
                        title: "操作", field: "options", width: 300, align: 'center',
                        formatter: function (value, row, index) {
                            if (row.ind == 1) {
                                return "<a class='cre' onClick=\"creat('" + row.id + "')\" href='javascript:;'>创建</a>&nbsp;&nbsp;" +
                                        "<a class='edit' style='color: gray' href='javascript:;'>删除</a>";
                            } else {
                                return "<a class='upd' onClick=\"upd('" + row.id + "')\" href='javascript:;'>更新</a>&nbsp;&nbsp;" +
                                        "<a class='edit' onClick=\"del('" + row.id + "')\"  href='javascript:;'>删除</a>";
                            }

                        }
                    }
                ]],
                pagination: true,
                pageNumber: 1,
                pageSize: 2,
                pageList: [2, 4, 6, 8, 10],
                toolbar: '#ff'
            })
        })

        function del(id) {
            $.messager.confirm("提示", "您确定要删除吗？", function (r) {
                if (r) {
                    $.get('${pageContext.request.contextPath}/product/dindex', {'id': id}, function () {
                        $("#proList").datagrid('reload');
                    })
                }
            })
        }

        function creat(id) {
            $.messager.confirm("提示", "您确定要创建吗?", function (r) {
                if (r) {
                    $.get('${pageContext.request.contextPath}/product/cindex', {'id': id}, function () {
                        $("#proList").datagrid('reload');
                    })
                }
            })
        }

        function upd(id) {
            $.messager.confirm("提示", "您确定要更新吗?", function (r) {
                if (r) {
                    $.get('', {'id': id}, function () {
                        $("proList").datagrid('reload');
                    })
                }
            })
        }

        function addPro() {
            $('#dd').dialog({
                title: '用户详情',
                width: 600,
                height: 500,
                closed: false,
                cache: false,
                href: '${pageContext.request.contextPath}/back/proadd.jsp',
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-save',
                    handler: savePro
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $("#dd").dialog('close');
                    }
                }]
            });
        }

        function savePro(){
            $("#proadd").form('submit',{
                url:'${pageContext.request.contextPath}/product/save',
                success:function(){
                    $("#dd").dialog('close',true);
                    $("#proList").datagrid('reload');

                }
            })
        }
    </script>
</head>
<body>
<div id="ff"><a href="javascript:;" class="easyui-linkbutton" onclick="addPro()"
                data-options="iconCls:'icon-add',plain:true">商品添加</a></div>
<table id="proList"></table>
<div id="dd"></div>
</body>
</html>
