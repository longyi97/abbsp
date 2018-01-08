<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <title>签到</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/resources/home/css/common.css" />
    <link rel="stylesheet" href="/resources/home/css/member.css" />
    <link rel="stylesheet" href="/resources/home/css/style.css" />
    <link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
    <link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
    <script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="/resources/home/js/layer.js" ></script>
    <script type="text/javascript" src="/resources/home/js/canlendar.js" ></script>
</head>
<body class="body">
<!--头部-->
<%@ include file="../../common/index-top.jsp"%>
<!--头部-->
<!--头部-->
<!--中间-->
<div class="content">
<%@ include file="../../common/index-leftMenu.jsp"%>
    <!--右边-->
    <!--1200内容-->
    <div class="row">
        <div class="col-md-12 signContent">
            <div class="sign">
                <div class="signMain">
                    <div class="signLeft">
                        <div class="signTop">
                            <div class="signLTitle">
                                <span>共签到<i>${userSignCount==null?0:userSignCount}</i>天</span>
                                <span>获得<i>${userRulePoints==null?0:userRulePoints}</i>积分</span>
                            </div>
                            <img src="/resources/home/images/fixed.png" alt="">
                            <div class="activity14" id="activity14">
                                <div class="qd_box">
                                    <div class="change_date" style="display: none">
                                        <em id="today_js">2017-11-29</em>
                                    </div>
                                    <div class="qd_cont">
                                        <table cellpadding="0" class="date_show" cellspacing="2" border="0px">
                                            <thead>
                                            <tr>
                                                <th>周日</th>
                                                <th>周一</th>
                                                <th>周二</th>
                                                <th>周三</th>
                                                <th>周四</th>
                                                <th>周五</th>
                                                <th>周六</th>
                                            </tr>
                                            </thead>
                                            <tbody id="qd_date">
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td><div class="date">1</div></td>
                                                <td><div class="date">2</div></td>
                                                <td><div class="date">3</div></td>
                                                <td><div class="date">4</div></td>
                                            </tr>
                                            <tr>
                                                <td><div class="date">5</div></td>
                                                <td><div class="date">6</div></td>
                                                <td><div class="date">7</div></td>
                                                <td><div class="date">8</div></td>
                                                <td><div class="date">9</div></td>
                                                <td><div class="date">10</div></td>
                                                <td><div class="date">11</div></td>
                                            </tr>
                                            <tr>
                                                <td><div class="date">12</div></td>
                                                <td><div class="date">13</div></td>
                                                <td><div class="date">14</div></td>
                                                <td><div class="date">15</div></td>
                                                <td><div class="date">16</div></td>
                                                <td><div class="date">17</div></td>
                                                <td><div class="date">18</div></td>
                                            </tr>
                                            <tr>
                                                <td><div class="date">19</div></td>
                                                <td><div class="date">20</div></td>
                                                <td><div class="date">21</div></td>
                                                <td><div class="date">22</div></td>
                                                <td><div class="date">23</div></td>
                                                <td><div class="date">24</div></td>
                                                <td><div class="date">25</div></td>
                                            </tr>
                                            <tr>
                                                <td><div class="date">26</div></td>
                                                <td><div class="date">27</div></td>
                                                <td><div class="date">28</div></td>
                                                <td><div class="date">29</div></td>
                                                <td><div class="date">30</div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                            </tr>
                                            <tr>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                                <td><div class="date"></div></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>

                            <div class="signButtom">
                                <div class="progressBarCont"><div class="bar"></div> </div>
                                <div class="bimap" id="ruleList">
                                   <c:forEach var="list" items="${signRule}" varStatus="stop">
                                    <div class="bitext">
                                        <i>5</i>
                                        <span>${list.continuousDays}天<br>送${list.rewardPoints}</span>
                                    </div>
                                   </c:forEach>
                                </div>
                                <!--<span>共签到<i>10</i>天</span>
                                <span>获得<i>200</i>积分</span>-->
                            </div>
                        </div>
                        <div class="signRule">
                            <div class="ruleHead">
                                <i class="fl"></i>
                                <i class="fr"></i>
                            </div>
                            <div class="ruleMain">
                                <h4>签到赠送积分规则：</h4>
                                <p>
                                    <span>1.每天签到赠送${oneSign}积分</span>
                                    <c:forEach var="list" items="${signRule}" varStatus="stop">
                                     <c:if test="${stop.index==0}"> 
                                         <span>	${stop.index+2}.连续${list.continuousDays}天签到额外赠送${rewardPoints[stop.index]}积分</span>
                                       </c:if>
                                     </c:forEach>
                                </p>
                                  <c:forEach var="list" items="${signRule}" varStatus="stop">
                                  <c:if test="${stop.index>0}"> 
				                 <c:if test="${stop.index%2==1}">  <p> </c:if>    	<span>	${stop.index+2}.连续${list.continuousDays}天签到额外赠送${rewardPoints[stop.index]}积分</span> <c:if test="${stop.index%2==0}"> </p></c:if> 
				                   </c:if> 
				                  <c:if test="${signRuleSize==stop.index+1&&signRuleSize%2==0}">${signRuleSize==0?2:signRuleSize+2}.本次活动的最终解释权属本平台所有</c:if> 
				                  <c:if test="${signRuleSize==stop.index+1&&signRuleSize%2==1}"> <p>${signRuleSize==0?2:signRuleSize+2}.本次活动的最终解释权属本平台所有</p></c:if> 
				                  </c:forEach>
                               <!--  <p>
                                    <span>3.连续7天签到额外赠送10积分</span>
                                    <span>4.连续15天签到额外赠送50积分</span>
                                </p>
                                <p>5.连续30天签到额外赠送100积分</p> -->
                               <%--  <p>${signRuleSize==0?2:signRuleSize+2}.本次活动的最终解释权属本平台所有</p> --%>
                            </div>
                        </div>
                    </div>
                    <div class="signRight">
                        <div class="signRightT">
                            <div class="ruleHead">
                                <i class="fl"></i>
                                <i class="fr"></i>
                            </div>
                            <span class="signin">
                                <a href="javascript:void(0);" class="today_qd_btn"> 立即签到</a>
                            </span>
                            <div class="circles">
                                <i class="fl"></i>
                                <i class="fl"></i>
                                <i class="fr"></i>
                                <i class="fr"></i>
                            </div>
                        </div>
                        <div class="signRightM">
                            <div class="circles">
                                <i class="fl"><em></em></i>
                                <i class="fl"><em></em></i>
                                <i class="fr"><em></em></i>
                                <i class="fr"><em></em></i>
                            </div>
                            <h4>签到排名：</h4>
                            <dl>
                              <c:forEach var="list" items="${uSign}" varStatus="stop">
				                       		<c:if test="${stop.index<7}">
				                       		 <dd><a href="javascript:void(0);"><span>${stop.index+1}</span>${list.username }<em>${list.signCount}天</em></a></dd>
				                       		</c:if>
				              </c:forEach>
                             <!--    <dd><a href="#"><span>1</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>2</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>3</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>4</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>5</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>6</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>7</span>用户名显示 <em>100天</em></a></dd> -->
                            </dl>
                            <div class="circles">
                                <i class="fl"></i>
                                <i class="fl"></i>
                                <i class="fr"></i>
                                <i class="fr"></i>
                            </div>
                        </div>
                        <div class="signRightB">
                            <div class="circles">
                                <i class="fl"><em></em></i>
                                <i class="fl"><em></em></i>
                                <i class="fr"><em></em></i>
                                <i class="fr"><em></em></i>
                            </div>
                            <h4>连续签到排名：</h4>
                            <dl>
                            <c:forEach var="list" items="${sSign}" varStatus="stop">
				                       		<c:if test="${stop.index<5}">
				                       		   <dd><a href="javascript:void(0);"><span>${stop.index+1}</span>${list.username } <em>${list.signContinuousCount} 天</em></a></dd>
				                       		</c:if>
				            </c:forEach>
                              <!--   <dd><a href="#"><span>1</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>2</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>3</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>4</span>用户名显示 <em>100天</em></a></dd>
                                <dd><a href="#"><span>5</span>用户名显示 <em>100天</em></a></dd> -->
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!--1200内容-->
</div>
<!--中间-->
</body>
</html>
