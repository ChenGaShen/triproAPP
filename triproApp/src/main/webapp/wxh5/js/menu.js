$(document).ready(function(){
	// $(".menu_home").click().addClass("menu_home_click").removeClass("menu_home");
	// $(".menu_car").click().addClass("menu_car_click").removeClass("menu_car");
	// $(".menu_my").click().addClass("menu_my_click").removeClass("menu_my");

	$("li:eq(0)").click(function(){
			$(" li:eq(0)").find(".menu_home").removeClass("menu_home").addClass("menu_home_click");
			$(" li:eq(1)").find(".menu_car").removeClass("menu_car_click").addClass("menu_car");
			$(" li:eq(2)").find(".menu_my").removeClass("menu_my_click").addClass("menu_my");
		});
		$("li:eq(1)").click(function(){
			$(" li:eq(0)").find(".menu_home").removeClass("menu_home_click").addClass("menu_home");
			$(" li:eq(1)").find(".menu_car").removeClass("menu_car").addClass("menu_car_click");
			$(" li:eq(2)").find(".menu_my").removeClass("menu_my_click").addClass("menu_my");
		});
		$("li:eq(2)").click(function(){
			$(" li:eq(0)").find(".menu_home").removeClass("menu_home_click").addClass("menu_home");
			$(" li:eq(1)").find(".menu_car").removeClass("menu_car_click").addClass("menu_car");
			$(" li:eq(2)").find(".menu_my").removeClass("menu_my").addClass("menu_my_click");
		});
});