<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/headder/header.jsp"%>
		<div class="container bg0 p-t-75 p-b-85">
			<div class="row">
				<div class="col-lg-12 col-xl-12 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Image</th>
									<th class="column-2">Product</th>
									<th class="column-2">Price</th>
									<th class="column-2">Quantity</th>
									<th class="column-2">Size</th>
									<th class="column-2">Total</th>
									<th class="column-3">Action</th>
								</tr>
								<c:forEach var="map" items="${sessionScope.myCartItems}">
									<tr class="table_row">
										<td class="column-1">
											<div class="how-itemcart1">
												<img src="templates/images/${map.value.product.proImage}" alt="IMG">
											</div>
										</td>
										<td class="column-2">${map.value.product.proName}</td>
										<td class="column-2">${map.value.product.proPrice}</td>
										<form action="/shop_client/cart/update/${map.value.product.proId}" method="post">
										<td class="column-2	">
											<div class="wrap-num-product flex-w  m-r-0">
												<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-minus"></i>
												</div>
	
												<input class="mtext-104 cl3 txt-center num-product" type="number" name="quatity" value="${map.value.quatity}">
	
												<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-plus"></i>
												</div>
											</div>
										</td>
										
										<td class="column-2">
											<div class="size-204 ">
													<select class="js-select2" name="size">
														<option value="${map.value.size }">${map.value.size }   </option>
														<option value="S">S</option>
														<option value="M">M</option>
														<option value="L">L</option>
														<option value="XL">XL</option>
													</select>
													<div class="dropDownSelect2"></div>
												
											</div>
										</td>
										
										<td class="column-2">${map.value.product.proPrice * map.value.quatity}</td>
										
										
										<td class="column-3">
											<button class="btn btn-primary" type="submit"><i class="zmdi zmdi-edit"></i></button>
											</form>
											<a href="/shop_client/cart/remove/${map.value.product.proId}"><button class="btn btn-danger"><i class="zmdi zmdi-delete"></i></button></a>
										</td>
										
									</tr>
								</c:forEach>
							</table>
						</div>

						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
						
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<a href="/shop_client/home">
									<div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
										Back Home
									</div>
								</a>
							</div>
							<div class="flex-c-m stext-101 ">
								SubTotal:  ${sessionScope.myCartTotal}
							</div>
							<a href="/shop_client/cart/checkout">
								<div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
									Check Out
								</div>
							</a>
						</div>
					</div>
				</div>
		</div>
<%@include file="/WEB-INF/views/footer/footer.jsp"%>