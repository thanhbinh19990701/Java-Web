<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/headder/head-admin.jsp"%>
<main class="h-full overflow-y-auto">
	<div class="container px-6 mx-auto grid">
		<h2
			class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
			Tất cả Sản Phẩm</h2>
		<!-- New Table -->
		<div class="w-full overflow-hidden rounded-lg shadow-xs">
			<div class="w-full overflow-x-auto">
				<table class="w-full whitespace-no-wrap">
					<thead>
						<tr
							class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">

							<th class="px-4 py-3">Image Product</th>
							<th class="px-4 py-3">Name</th>
							<th class="px-4 py-3">Price</th>
							<th class="px-4 py-3">Quatity</th>
							<th class="px-4 py-3">Sale</th>
							<th class="px-4 py-3">Sold</th>
							<th class="px-4 py-3">Size</th>
							<th class="px-4 py-3" style="width:300px;">Describes</th>
							<th class="px-4 py-3">Date Create</th>
							<th class="px-4 py-3">Ngày Update</th>
							<th class="px-4 py-3">Action</th>
						</tr>
					</thead>
					<tbody
						class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
						<c:forEach items="${listProduct}" var="product" varStatus="status">
						<tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
							
							<td class="px-4 py-3">
								<img src="http://localhost:8080/shop_client/templates/images/${product.getProImage()}"
								alt="IMG-PRODUCT" style="width:120px; height:150px">
							 </td>
							<td class="px-4 py-3">${product.getProName()}</td>
							<td class="px-4 py-3">${product.getProPrice()}</td>
							<td class="px-4 py-3">${product.getProQuatity()}</td>
							<td class="px-4 py-3">${product.getProSale()}</td>
							<td class="px-4 py-3">${product.getProSold()}</td>
							<td class="px-4 py-3">${product.getProSize()}</td>
							<td class="px-4 py-3" style="width:300px;">${product.getProDescribes()}</td>
							<td class="px-4 py-3">${product.getProCreateAt()}</td>
							<td class="px-4 py-3">${product.getProUpdateAt()}</td>
							<td class="px-4 py-3">
								<button type="submit" class="btn btn-primary">
									<i class="fa-solid fa-pen-to-square"></i>
								</button>
								<a href="/shop_client/admin/deleteProduct/${product.getProId()}">
									<button class="btn btn-danger">
										<i class="fa-solid fa-trash"></i>
									</button>
								</a>
							</th>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>

<%@include file="/WEB-INF/views/footer/footer-admin.jsp"%>
