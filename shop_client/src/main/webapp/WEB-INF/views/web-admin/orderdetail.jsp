
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

							<th class="px-4 py-3">Hình ảnh</th>
							<th class="px-4 py-3">Size</th>
							<th class="px-4 py-3">Giá</th>
							<th class="px-4 py-3">Số Lượng</th>
							<th class="px-4 py-3">Tổng giá</th>
						</tr>
					</thead>
					<tbody
						class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
						<c:forEach items="${listOrderdetail}" var="detail" varStatus="status">
							<tr class="text-gray-700 dark:text-gray-400">
								<td class="px-4 py-3">
									<img src="http://localhost:8080/shop_client/templates/images/${detail.product.getProImage()}"
								alt="IMG-PRODUCT" style="width:120px; height:150px"> 
								</td>
								<td class="px-4 py-3 text-sm">${ detail.getOdtSize()}</td>
								<td class="px-4 py-3 text-xs">${ detail.product.getProPrice()}</td>
								<td class="px-4 py-3 text-sm">${ detail.getOdtQuatity()}</td>
								<td class="px-4 py-3 text-sm">${ detail.getOdtQuatity() * detail.product.getProPrice()}</td>
							</tr>
						</c:forEach>
						<tr class="text-gray-700 dark:text-gray-400">
								<h3>Thông tin Đặt Hàng</h3>
								<span>Người Đặt Hàng:${user.getTenKh() }</span><br/>
								<span>Số Điện Thoại:${user.getSdt() }</span><br/>
								<span>Địa Chỉ:${user.getAddress() }</span><br/>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>

<%@include file="/WEB-INF/views/footer/footer-admin.jsp"%>
