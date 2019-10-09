/**
 * 
 */
function reusername(obj1){
	var username=obj1.value;
	console.log(username);
	var req = new XMLHttpRequest();
	req.open("get", "UserServlet.do?dowhat=getuname&username="+username, true);
	req.send();
	req.onreadystatechange = function() {		
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			if(data==1001){
				var tips=obj1.nextElementSibling;
				tips.innerHTML="用户名不存在";
				tips.setAttribute("style", "color:red;")
			}
			if(data==1000){
				var tips=obj1.nextElementSibling;
				tips.innerHTML="";
			}
		}
	}
}


function login() {
	//获取输入框的信息
	var username=document.getElementById("username").value;
	var userpwd=document.getElementById("userpwd").value;
	var usercode=document.getElementById("usercode").value;
	console.log(usercode);
	//创建xmlHttprequest对象
	var req = new XMLHttpRequest();
	req.open("get", "UserServlet.do?dowhat=login&username="+username+"&userpwd="+userpwd+"&usercode="+usercode, true);
	req.send();
	req.onreadystatechange = function() {		
		console.log(req.readyState+"  "+req.status);
		if (req.readyState == 4 && req.status == 200) {
			//接收json并解析
			var data = req.responseText;
			console.log(data);
			if(data==1000){
				window.location.href="index.html";
			}	
			if(data==1001){
				var tips=document.getElementById("usercode").nextElementSibling;
				tips.innerHTML="验证码错误";
				tips.setAttribute("style", "color:red;")
			}
			if(data==1002){
				var tips=document.getElementById("userpwd").nextElementSibling;
				tips.innerHTML="密码错误";
				tips.setAttribute("style", "color:red;")
			}

		}
	}
}
//获取修改密码界面的用户名
function getuser(){
	var req = new XMLHttpRequest();
	req.open("get", "UserServlet.do?dowhat=getuser", true);
	req.send();
	req.onreadystatechange = function() {		
		var data = req.responseText;
		if (req.readyState == 4 && req.status == 200) {
			if(data!=1001){
				var username=document.getElementById("username");
				username.innerHTML=data;
				username.setAttribute("style", "color:red;font-size:20px;")
			}
		}
	}
}

//修改密码操作
function editpass(){
	var mpass=document.getElementById("mpass").value;
	console.log(mpass);
	var newpass=document.getElementById("newpass").value;
	console.log(newpass);
	var req = new XMLHttpRequest();
	req.open("get", "UserServlet.do?dowhat=editpwd&mpass="+mpass+"&newpass="+newpass, true);
	req.send();
	req.onreadystatechange = function() {		
		var data = req.responseText;
		console.log(data);
		if (req.readyState == 4 && req.status == 200) {
			if(data==1000){
				alert("修改密码成功！");
				window.location.href="pass.html";
			}	
			if(data==1002){
				var tips=document.getElementById("newpass").nextElementSibling;
				tips.innerHTML="不能和旧密码一致！";
				tips.setAttribute("style", "color:red;")
			}
			if(data==1001){
				var tips=document.getElementById("mpass").nextElementSibling;
				tips.innerHTML="密码错误";
				tips.setAttribute("style", "color:red;")
			}
		}
	}

}

