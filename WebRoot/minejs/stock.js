
var cur=1;//当前页数
var tb=document.getElementsByClassName("tt")[0];
//动态添加
function addElements(obj) {

	for(var i in obj){		
		var mode=obj[i];//取出数组中的对象
		if(mode.number>0){
			var row=tb.insertRow(0);
			var cell=row.insertCell(0);
			cell.innerHTML=mode.proid;
			cell=row.insertCell(1);
			cell.innerHTML=mode.proname;
			cell=row.insertCell(2);
			cell.innerHTML=mode.number;
//			cell=row.insertCell(2);
//			var btn=document.createElement("button");
//			btn.innerHTML="出库";
//			btn.setAttribute("onclick", "outt("+mode.proid+")");
//			btn.setAttribute("name", "proid1");
//			cell.appendChild(btn);
		}
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
function getStockInfo() {
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "StockServlet.do?dowhat=showsto&page="+cur, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			//console.log(obj);
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
	getStockInfo();
}
//上一页
function prePage(){
	if(cur==1){
		return;
	}
	cur--;	
	getStockInfo();
}

function outt(proid){
	
	window.location.href="outstock.html?proid="+proid;			
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
	console.log("pp===>"+proid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "StockServlet.do?dowhat=getone&proid="+proid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			var proid=document.getElementById("proid");
			proid.value=obj.proid;
			var number=document.getElementById("number");
			number.value=obj.number;
		}
	}
}
