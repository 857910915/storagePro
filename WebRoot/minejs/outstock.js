//失去焦点事件，获取product
function getproinfo(){
	var proname=document.getElementById("proname");
	var req = new XMLHttpRequest();
	req.open("get", "ProductServlet.do?dowhat=showpro&page=0", true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			//console.log(data);
			for (var i in obj) {
				var opt = document.createElement("option");
				opt.innerHTML = obj[i].proname;
				opt.setAttribute("value", obj[i].proid);
				proname.appendChild(opt);
				opt = proname.children[0];
			}	
		}
	}
}
getproinfo();
//失去焦点事件，获取user
function getuserinfo(){
	var username=document.getElementById("username");
	//console.log(username);
	var req = new XMLHttpRequest();
	req.open("get", "UserServlet.do?dowhat=showuser", true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			//console.log(data);
			for (var i in obj) {
				var opt = document.createElement("option");
				opt.innerHTML = obj[i].username;
				opt.setAttribute("value", obj[i].userid);
				username.appendChild(opt);
				opt = username.children[0];
			}
		}
	}
}
getuserinfo();
//失去焦点事件，获取person
function getperinfo(){
	var pername=document.getElementById("pername");
	var req = new XMLHttpRequest();
	req.open("get", "PersonServlet.do?dowhat=showper&page=0", true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			var obj = eval(data);
			for (var i in obj) {
				var opt = document.createElement("option");
				opt.innerHTML = obj[i].pername;
				opt.setAttribute("value", obj[i].perid);
				pername.appendChild(opt);
				opt = pername.children[0];
			}
		}
	}
}
getperinfo()
//失去焦点，判断库存
function getnuminfo(obj1){
	var onumber=obj1.value;
	var proname=document.getElementById("proname").value;
	var req = new XMLHttpRequest();
	req.open("get", "OutServlet.do?dowhat=getnum&proname="+proname+"&onumber="+onumber, true);
	req.send();
	req.onreadystatechange = function() {		
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			//console.log(data);
			var tips=obj1.nextElementSibling;
			if(data==1001){//到最后		
				tips.innerHTML="库存不足";
				tips.setAttribute("style", "color:red;");
			}
			if(data==1000){
				tips.innerHTML="库存足够";
				tips.setAttribute("style", "color:green;;");
			}
			if(data==1002){

			}
		}
	}
}


//失去焦点事件，获取入库信息
function getwareinfo1(obj1){
	//var wid=document.getElementById("wid").value;
	var wid=obj1.value;	
	var req = new XMLHttpRequest();
	req.open("get", "WarehousServlet.do?dowhat=getone&wid="+wid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			//var tips=document.getElementsByClassName("tips")[0];
			var tips=obj1.nextElementSibling;
			if(data==1001){//到最后		
				tips.innerHTML="入库记录不存在";
				tips.setAttribute("style", "color:red;");
				var proid=document.getElementById("proid");
				proid.value="";
				var onumber=document.getElementById("onumber");
				onumber.value="";
			}else{
				tips.innerHTML="入库记录存在";
				tips.setAttribute("style", "color:green;");
				var wid=document.getElementById("wid");
				if(obj1==wid){
					var obj=JSON.parse(data);
					var proid=document.getElementById("proid");
					proid.value=obj.proid;
					var onumber=document.getElementById("onumber");
					onumber.value=obj.wnumber;
				}
			}	
		}
	}
}


var cur=1;//当前页数
var tb=document.getElementsByClassName("tt")[0];
//动态添加
function addElements(obj) {

	for(var i in obj){
		var mode=obj[i];//取出数组中的对象
		var row=tb.insertRow(i);
		var cell=row.insertCell(0);
		cell.innerHTML=mode.oid
		cell=row.insertCell(1);
//		cell.innerHTML=mode.wid;
//		cell=row.insertCell(2);
		cell.innerHTML=mode.userid;
		cell=row.insertCell(2);
		cell.innerHTML=mode.perid;
		cell=row.insertCell(3);
		cell.innerHTML=mode.proid;
		cell=row.insertCell(4);
		cell.innerHTML=mode.onumber;
		cell=row.insertCell(5);
		cell.innerHTML=mode.otime;
		cell=row.insertCell(6);
		var btn1=document.createElement("button");
		btn1.innerHTML="修改";
		btn1.setAttribute("onclick", "editout("+mode.oid+")");
		btn1.setAttribute("href", "#edit");
		btn1.setAttribute("name", "wid");
		btn1.setAttribute("disabled", "true")
		cell.appendChild(btn1);
		cell=row.insertCell(7);
		var btn2=document.createElement("button");
		btn2.innerHTML="删除";
		btn2.setAttribute("onclick", "deleteout("+mode.oid+")");
		btn2.setAttribute("name", "wid1");
		btn2.setAttribute("disabled", "true")
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
function getOutInfo() {
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "OutServlet.do?dowhat=showout&page="+cur, true);
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
	getOutInfo();
	//queryByMany();
}
//上一页
function prePage(){
	if(cur==1){
		return;
	}
	cur--;	
	getOutInfo();
	//queryByMany();
}
//修改学院信息
function editout(oid){
	//console.log(oid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "OutServlet.do?dowhat=getone&oid="+oid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			//console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			var oid=document.getElementById("oid1");
			oid.value=obj.oid;
			var wid=document.getElementById("wid1");
			wid.value=obj.wid;
			var uid=document.getElementById("uid1");
			uid.value=obj.userid;
			var proid=document.getElementById("proid1");
			proid1.value=obj.proid;
			var perid=document.getElementById("perid1");
			perid.value=obj.perid;
			var onumber=document.getElementById("onumber1");
			onumber.value=obj.onumber;
			var otime=document.getElementById("otime1");
			otime.value=obj.otime;

		}
	}
}

//function deleteout(wid){
//alert("确定删除？");
////console.log(wid);
////创建xmlHttprequest对象
//var req = new XMLHttpRequest();
//req.open("get", "WarehousServlet.do?dowhat=deleteware&wid="+wid, true);
//req.send();
//getWareInfo();
//}
