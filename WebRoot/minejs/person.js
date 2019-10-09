//判断手机号是否符合
function rephone(obj){
	var perphone=obj.value;
	console.log(perphone);
	var phoneReg = /^[1][3,4,5,7,8][0-9]{8}$/;  
	var tips=obj.nextElementSibling;
	if (phoneReg.test(perphone)) {  
		tips.innerHTML="手机号正确";
		tips.setAttribute("style", "color:green;");
	} else {  
		tips.innerHTML="手机号不正确";
		tips.setAttribute("style", "color:red;");
	}  
}
//判断邮箱
function reemail(obj){
	var peremail=obj.value;
	var reemail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	var tips=obj.nextElementSibling;
	if(reemail.test(peremail)){
		tips.innerHTML="邮箱正确";
		tips.setAttribute("style", "color:green;");
	} else {  
		tips.innerHTML="邮箱不正确";
		tips.setAttribute("style", "color:red;");
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
		cell.innerHTML=mode.perid
		cell=row.insertCell(1);
		var img=document.createElement("img");
		img.setAttribute("src", mode.perimg);
		img.setAttribute("height", "70px");
		img.setAttribute("width", "50px");
		cell.appendChild(img);
		cell=row.insertCell(2);
		cell.innerHTML=mode.pername;
		cell=row.insertCell(3);
		cell.innerHTML=mode.perage;
		cell=row.insertCell(4);
		cell.innerHTML=mode.persex;
		cell=row.insertCell(5);
		cell.innerHTML=mode.depart;
		cell=row.insertCell(6);
		cell.innerHTML=mode.perphone;
		cell=row.insertCell(7);
		cell.innerHTML=mode.peremail;
		cell=row.insertCell(8);
		//cell.innerHTML="<div class='button-group'><a class='button border-main' onclick='editper('+mode.perid+')'><span class='icon-edit'></span> 修改</a> <a class='button border-red' href='javascript:void(0)' onclick='deleteper()'><span class='icon-trash-o'></span> 删除</a></div>"
		var btn1=document.createElement("button");
		//btn1.setAttribute("value", "修改")
		btn1.innerHTML="修改";
		btn1.setAttribute("onclick", "editper("+mode.perid+")");
		btn1.setAttribute("href", "#edit");
		btn1.setAttribute("name", "perid");
		//btn1.setAttribute("style", "height:20px;width:40px;");
		cell.appendChild(btn1);
		cell=row.insertCell(9);
		var btn2=document.createElement("button");
		//btn2.setAttribute("value", "删除")
		btn2.innerHTML="删除";
		btn2.setAttribute("onclick", "deleteper("+mode.perid+")");
		btn2.setAttribute("name", "perid1");
		//btn2.setAttribute("style", "height:20px;width:40px;");
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
function getPerInfo() {
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "PersonServlet.do?dowhat=showper&page="+cur, true);
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
	getPerInfo();
	//queryByMany();
}
//上一页
function prePage(){
	if(cur==1){
		return;
	}
	cur--;	
	getPerInfo();
	//queryByMany();
}
//修改学院信息
function editper(perid){
	console.log(perid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "PersonServlet.do?dowhat=getone&perid="+perid, true);
	req.send();
	req.onreadystatechange = function() {		
		//动态添加数据
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			var obj = JSON.parse(data);
			//给html页面赋值
			var perid1=document.getElementById("perid1");
			perid1.value=obj.perid;
			var perimg1=document.getElementById("perimg1");
			//perimg1.value=obj.perimg;
			var pername1=document.getElementById("pername1");
			pername1.value=obj.pername;
			var perage1=document.getElementById("perage1");
			perage1.value=obj.perage;
			var persex1=document.getElementsByName("persex1");			
			if(persex1[0].value==obj.persex){
				persex1[0].setAttribute("checked", true);				 
				//persex1[1].removeAttribute("checked");
			}
			if(persex1[1].value==obj.persex){
				persex1[1].setAttribute("checked", true);
				//persex1[0].removeAttribute("checked");
			}
			var depart1=document.getElementById("depart1");
			depart1.value=obj.depart;
			var perphone1=document.getElementById("perphone1");
			perphone1.value=obj.perphone;
			var peremail1=document.getElementById("peremail1");
			peremail1.value=obj.peremail;
		}
	}
}

function deleteper(perid){
	alert("确定删除？");
	console.log(perid);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "PersonServlet.do?dowhat=deleteper&perid="+perid, true);
	req.send();
	getPerInfo();
}
