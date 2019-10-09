
var cur=1;//当前页数
var tb=document.getElementsByClassName("tt")[0];
//var tb=$(".tt")[0];
//动态添加
function addElements(obj) {

	for(var i in obj){
		var mode=obj[i];//取出数组中的对象
		var row=tb.insertRow(i);
		var cell=row.insertCell(0);
		cell.innerHTML=mode.wid
		cell=row.insertCell(1);
		cell.innerHTML=mode.proid;
		cell=row.insertCell(2);
		cell.innerHTML=mode.wnumber;
		cell=row.insertCell(3);
		cell.innerHTML=mode.wtime;
		cell=row.insertCell(4);
		cell.innerHTML=mode.lasttime;
		cell=row.insertCell(5);
		cell.innerHTML=mode.uid;
		cell=row.insertCell(6);
		var btn1=document.createElement("button");
		btn1.innerHTML="修改";
		btn1.setAttribute("onclick", "editware("+mode.wid+")");
		btn1.setAttribute("href", "#edit");
		btn1.setAttribute("name", "wid");
		btn1.setAttribute("disabled", "true");
		cell.appendChild(btn1);
		cell=row.insertCell(7);
		var btn2=document.createElement("button");
		btn2.innerHTML="删除";
		btn2.setAttribute("onclick", "deleteware("+mode.wid+")");
		btn2.setAttribute("name", "wid1");
		btn2.setAttribute("disabled", "true");
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
function getWareInfo() {
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "WarehousServlet.do?dowhat=showware&page="+cur, true);
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
	getWareInfo();
	//queryByMany();
}
//上一页
function prePage(){
	if(cur==1){
		return;
	}
	cur--;	
	getWareInfo();
	//queryByMany();
}
//修改学院信息
function editware(wid){
	console.log(wid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "WarehousServlet.do?dowhat=getone&wid="+wid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			var wid=document.getElementById("wid1");
			wid.value=obj.wid;
			var proid=document.getElementById("proid1");
			proid1.value=obj.proid;
			var wnumber=document.getElementById("wnumber1");
			wnumber.value=obj.wnumber;
			var wtime=document.getElementById("wtime1");
			wtime.value=obj.wtime;
			var lasttime=document.getElementById("lasttime1");
			lasttime.value=obj.lasttime;
			var uid=document.getElementById("uid1");
			uid.value=obj.uid;
		}
	}
}

function deleteware(wid){
	alert("确定删除？");
	console.log(wid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "WarehousServlet.do?dowhat=deleteware&wid="+wid, true);
	req.send();
	getWareInfo();
}
