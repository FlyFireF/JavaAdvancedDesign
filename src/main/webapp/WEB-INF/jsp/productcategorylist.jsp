<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="common/head.jsp" %>
<%--zhr: 添加了“添加一级标签”，“添加二级标签”，“删除一级标签”，“删除二级标签”的界面，并添加了触发的按钮，修改了添加和删除三级标签的逻辑--%>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <nav class="pull-left">
                    <strong>你现在所在的位置是:</strong>&nbsp;&nbsp;&nbsp; <span>商品分类列表页面</span><br>
                    <br>
                </nav>
            </div>
            <div class="col-md-2">
                <div class="text-center ">
                    <a class="btn btn-success btn-fill btn-wd"
                       href="javascript:history.go(-1)">返回</a>
                </div>
                <br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="content">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <c:forEach items="${ppclist}" var="pc1">
                                <li role="presentation"
                                        <c:if test="${pc1.id==1}"> class="active" </c:if>  pid="${pc1.id}" ppid="0" pname="${pc1.name}"><a
                                        href="#ppc${pc1.id}" aria-controls="ppc${pc1.id}" role="tab"
                                        data-toggle="tab">${pc1.name}</a>
                                </li>
                            </c:forEach>
                            <%--00002  --%>
                            <%--00002 在foreach后添加两个按钮，添加一级标签和删除一级标签--%>
                            <li role="presentation" class="add-tab-button">
                                <a href="#" data-toggle="modal" data-target="#addP1Category">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加一级商品分类
                                </a>
                            </li>
                            <li role="presentation" class="delete-tab-button" id="deleteP1Category">
                                <a href="#" data-toggle="modal">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除一级商品分类
                                </a>
                            </li>
                        </ul>
                        <%-- 00000 --%>
                        <!-- Tab panes -->
                        <div class="tab-content" id="ppclist">
                            <c:forEach items="${ppclist}" var="p1">
                                <div role="tabpanel" class="tab-pane content" id="ppc${p1.id}">
                                    <div class="row">
                                        <c:forEach items="${p1.productCategories}" var="p2">
                                            <div class="col-xs-6 col-md-3">
                                                <ul class="list-group">
                                                    <c:if test="${not empty p2.id}">
                                                        <li class="list-group-item active dropdown">
                                                                <%-- 00002 --%>
                                                                <%-- zhr:修改每个二级标签面板原先的addp按钮，使其点击后弹出上面的pmenu弹窗 --%>
                                                            <div class="dropdown-menu pull-right pmenu"
                                                                 aria-labelledby="dropdownMenuButton">
                                                                <button class="dropdown-item" style="width: 100%"><font color="black">添加</font></button>
                                                                <button class="dropdown-item" style="width: 100%"><font color="black">删除</font></button>
                                                            </div>
                                                            <button type="button" id="dropdownMenuButton"
                                                                    class="close addmodal dropdown-toggle addp"
                                                                    data-toggle="dropdown" aria-label="Close"
                                                                    pid="${p2.id}" pname="${p2.name}"
                                                                    ppid="${p2.parentId}"
                                                                    ppname="${p1.name}">
                                                            <span class="glyphicon glyphicon-plus"
                                                                  aria-hidden="true"></span>
                                                            </button>
                                                                        <%-- 00000 --%>
                                                                ${p2.id} | ${p2.name}
                                                        </li>
                                                    </c:if>
                                                    <c:forEach items="${p2.productCategories}" var="p3">
                                                        <c:if test="${not empty p3.id}">
                                                            <li class="list-group-item">
                                                                <button type="button" class="close delete"
                                                                        aria-label="Close" pid="${p3.id}"
                                                                        pname="${p3.name}"
                                                                        ppid="${p3.parentId}" ppname="${p2.name}">
                                                                    <span aria-hidden="true">&times;</span></button>
                                                                    ${p3.id} | ${p3.name}</li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div style="text-align: center; margin-top: auto; margin-bottom: auto;">
                                            <%-- 00002 --%>
                                            <%-- zhr:每个一级页面都添加一个“添加二级商品分类”的按钮 --%>
                                        <button class="addmodal" type="button" data-toggle="modal"
                                                data-target="#addP2Category" style="margin: auto"
                                                ppid="${p1.id}" ppname="${p1.name}">
                                            <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                                            添加二级商品分类
                                        </button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/foot.jsp" %>
<!-- Modal -->
<div class="modal fade" id="addpp" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加商品类别</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3">
                        一级商品类别名称
                    </div>
                    <div class="col-md-8">
                        <input type="hidden" class="pp1id" name="pp1id" id="pp1id" value="">
                        <input class="form-control border-input pp1name" type="text" value="" readonly="readonly"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        二级商品类别名称
                    </div>
                    <div class="col-md-8">
                        <input type="hidden" name="pp2id" id="pp2id" value="">
                        <input class="form-control border-input" type="text" value="" readonly="readonly"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        <p>子级商品编号</p>
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="ppcode"
                               placeholder="请输入商品类别编码"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        <p>子级商品类别名称</p>
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="ppname"
                               placeholder="请输入商品类别名称"/>
                    </div>
                </div>
                <br>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary add">添加</button>
            </div>
        </div>
    </div>
</div>

<%-- 00002 --%>
<%-- zhr:新增模态对话框，用于添加一级标签 --%>
<div class="modal fade" id="addP1Category" tabindex="-1" role="dialog" aria-labelledby="addP1CategoryLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addP1CategoryLabel">添加一级商品类别</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3">
                        <p>一级商品编号</p>
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="p1CategoryId"
                               placeholder="请输入商品类别编码"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        <p>一级商品类别名称</p>
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="p1CategoryName"
                               placeholder="请输入商品类别名称"/>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default " data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary addp1">添加</button>
            </div>
        </div>
    </div>
</div>
<%-- 00000 --%>

<%-- 00002 --%>
<%-- zhr:新增模态对话框，用于添加二级标签 --%>
<div class="modal fade" id="addP2Category" tabindex="-1" role="dialog" aria-labelledby="addP2CategoryLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addP2CategoryLabel">添加二级商品类别</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3">
                        一级商品类别名称
                    </div>
                    <div class="col-md-8">
                        <input type="hidden" class="pp1id" name="pp1id" id="parentId" value=""/>
                        <input class="form-control border-input pp1name" type="text" value="" readonly="readonly"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        二级商品类别编号
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="p2CategoryId"
                               placeholder="请输入商品类别编码"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-3">
                        二级商品类别名称
                    </div>
                    <div class="col-md-8">
                        <input class="form-control border-input" type="text" id="p2CategoryName"
                               placeholder="请输入商品类别名称"/>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary addp2">添加</button>
            </div>
        </div>
    </div>
</div>
<%-- 00000 --%>

<script type="text/javascript">
    $(".sidebar-wrapper .nav li:eq(1)").addClass("active");
    $("#ppclist div:eq(0)").addClass("active");
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/statics/js/productcategorylist.js"></script>