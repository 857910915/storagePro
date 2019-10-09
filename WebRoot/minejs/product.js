
var cur=1;//当前页数
var tb=document.getElementsByClassName("tt")[0];
//动态添加
function addElements(obj) {

	for(var i in obj){
		var mode=obj[i];//取出数组中的对象
		var row=tb.insertRow(i);
		var cell=row.insertCell(0);
		cell.innerHTML=mode.proid
		cell=row.insertCell(1);
		var img=document.createElement("img");
		img.setAttribute("src", mode.proimg);
		img.setAttribute("height", "70px");
		img.setAttribute("width", "50px");
		cell.appendChild(img);
		cell=row.insertCell(2);
		cell.innerHTML=mode.proname;
		cell=row.insertCell(3);
		cell.innerHTML=mode.procompany;
		cell=row.insertCell(4);
		cell.innerHTML=mode.protype;
		cell=row.insertCell(5);
		cell.innerHTML=mode.proprice;
		cell=row.insertCell(6);
		if(mode.prostyate==1){
			cell.innerHTML="有库存";
		}
		if(mode.prostyate==2){
			cell.innerHTML="无库存";
		}
		cell=row.insertCell(7);
		cell.innerHTML=mode.protime;
		cell=row.insertCell(8);
		if(mode.prosort==1){
			cell.innerHTML="置顶";
		}
		else{
			cell.innerHTML="未置顶";
		}
		cell=row.insertCell(9);
		var btn1=document.createElement("button");
		btn1.innerHTML="修改";
		btn1.setAttribute("onclick", "editpro("+mode.proid+")");
		btn1.setAttribute("name", "proid");
		cell.appendChild(btn1);
		cell=row.insertCell(10);
		var btn2=document.createElement("button");
		btn2.innerHTML="删除";
		btn2.setAttribute("onclick", "deletepro("+mode.proid+")");
		btn2.setAttribute("name", "proid1");
		cell.appendChild(btn2);
	}
}
//删除当前存在的元素
function removeElements(){

	var len=tb.rows.length;
	for(var i=0;i<len;i++){
		tb.deleteRow(0);
	}
}
//按钮不可用
function addDisableBtn(){
	var nextBtn=document.getElementsByClassName("next")[0];
	nextBtn.disabled=true;
}
//移除按钮不可用
function removeDisableBtn(){
	var nextBtn=document.getElementsByClassName("next")[0];
	nextBtn.disabled=false;
}
//获取当前信息
function getProInfo() {
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=showpro&page="+cur, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			if(obj.length<5){//到最后
				addDisableBtn();
			}else{
				removeDisableBtn();
			}
			removeElements();
			//将json中的数据动态添加
			addElements(obj);		
		}
	}
}
//下一页
function nextPage() {
	cur++;
	//getProInfo();
	changesearch1()
}
//上一页
function prePage(){
	if(cur==1){
		return;
	}
	cur--;	
	//getProInfo();
	changesearch1()
}
//修改商品信息
function editpro(proid){
	console.log(proid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=getone&proid="+proid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			window.location.href="editpro.html?proid="+proid;			
		}
	}
}
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	console.log(url);
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

function asspro(){
	var a=GetRequest();
	var proid=a['proid'];
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=getone&proid="+proid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			var proid=document.getElementById("proid1");
			proid.value=obj.proid;
			var proimg=document.getElementById("proimg1");
			//proimg1.value=obj.proimg;
			var proname=document.getElementById("proname1");
			proname.value=obj.proname;
			var procompany=document.getElementById("procompany1");
			procompany.value=obj.procompany;
			var protype=document.getElementById("protype1");
			protype.value=obj.protype;
			var proprice=document.getElementById("proprice1");
			proprice.value=obj.proprice;
			var prostyate=document.getElementsByName("prostyate");			
			if(prostyate[0].value==1){
				prostyate[0].setAttribute("checked", true);				 
			}
			if(prostyate[1].value==2){
				prostyate[1].setAttribute("checked", true);
			}			
			var protime=document.getElementById("protime1");
			protime.value=obj.protime;
			var prosort=document.getElementById("prosort1");
			prosort.value=obj.prosort;
		}
	}
}
//删除
function deletepro(proid){
	alert("确定删除？");
	//console.log("id==="+proid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=deletepro&proid="+proid, true);
	req.send();
	getProInfo();
}
//多条件查询
function changesearch1(){
	//获取前端信息
	var proprice=document.getElementById("proprice").value;
	console.log(proprice);
	var prostyate=document.getElementById("prostyate").value;
	console.log(prostyate);
	var prosort=document.getElementById("prosort").value;
	console.log(prosort);
	var protype=document.getElementById("protype").value;
	console.log(protype);
	var keywords=document.getElementById("keywords").value;
	console.log(keywords);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=getpro&page="+cur+"&proprice="+proprice+"&prostyate="+prostyate+"&prosort="+prosort+"&protype="+protype+"&keywords="+keywords, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			if(obj.length<5){//到最后
				addDisableBtn();
			}else{
				removeDisableBtn();
			}
			removeElements();
			//将json中的数据动态添加
			addElements(obj);		
		}
	}

}


