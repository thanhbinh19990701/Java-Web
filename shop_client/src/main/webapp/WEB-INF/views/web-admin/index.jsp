<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/headder/head-admin.jsp"%>
<main class="h-full overflow-y-auto">
	<div class="container px-6 mx-auto grid">
		<h2
			class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
			Tất cả Đơn Hàng</h2>
		<!-- New Table -->
		<div class="w-full overflow-hidden rounded-lg shadow-xs">
			<div class="w-full overflow-x-auto">
				<table class="w-full whitespace-no-wrap">
					<thead>
						<tr
							class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">

							<th class="px-4 py-3">ID Đơn Hàng</th>
							<th class="px-4 py-3">Trạng Thái</th>
							<th class="px-4 py-3">Ngày Đặt</th>
							<th class="px-4 py-3">Ngày Cập Nhật</th>
							<th class="px-4 py-3">Hành Động</th>
						</tr>
					</thead>
					<tbody
						class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
						<c:forEach items="${listOrder}" var="order" varStatus="status">
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3"><a
										href="/shop_client/admin/detail/${order.ordId}/${order.cusId}">DH-d${ order.ordId}</a>
									</td>
									<form action="/shop_client/admin/${order.ordId}" method="Post">
									<input type="hidden" name="cusId" value="${ order.getcusId()}">
									<td class="px-4 py-3 text-sm">
										<div class="form-group">
											<select class="form-control" name="ordStastus">
												<option value="${ order.getOrdStastus()}">
													<c:choose>
														<c:when test="${order.getOrdStastus() == 1}">
												            Chờ Xác Nhận
												        </c:when>
														<c:when test="${order.getOrdStastus() == 2}">
												            Chờ Lấy Hàng
												        </c:when>
														
														<c:when test="${order.getOrdStastus() == 3}">
												            Đang Giao
												        </c:when>
														
														<c:when test="${order.getOrdStastus() == 4}">
												            Đã Nhận
												        </c:when>
	
														<c:otherwise>
												            Đã Hủy
												         </c:otherwise>
													</c:choose>
												</option>
												<option value="2">Chờ Lấy Hàng</option>
												<option value="3">Đang Giao</option>
												<option value="4">Đã Nhận</option>
												<option value="5">Đã Hủy</option>
											</select>
										</div>
									</td>
									<td class="px-4 py-3 text-xs">${ order.getOrdCreateAt()}</td>
									<td class="px-4 py-3 text-sm">${ order.getOrdUpdateAt()}</td>
									<td class="px-4 py-3 text-sm">
										<button type="submit" class="btn btn-primary">
											<i class="fa-solid fa-pen-to-square"></i>
										</button>
									</form>
										<a href="/shop_client/admin/delete/${order.ordId}"><button class="btn btn-danger">
											<i class="fa-solid fa-trash"></i>
										</button></a>
									</td>
									
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>

<%@include file="/WEB-INF/views/footer/footer-admin.jsp"%>
