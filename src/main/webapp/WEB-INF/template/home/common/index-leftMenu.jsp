<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="/resources/home/js/index.js"></script>

			<div class="panel_left">
				<c:forEach var="item" items="${modules}">
					<dl>
						<dt><a href="/home/subIndex.shtml?moduleId=${item.id }">${item.name }</a></dt>
					</dl>
				</c:forEach>
			</div>

			
