<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BELL CBTTR07J Rate Table Mechanization - Login to BELL
	CBTTR07J Rate Table Mechanization</title>
<link rel="stylesheet" type="text/css" href="css/general.css">
<link rel="stylesheet" type="text/css" href="css/window.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body onload="document.loginData.username.focus();">
	<div id="outer">
		<div id="content">
			<div id="logo">
				<img id="bell-title" src="images/ConsoleTitle2l.gif" alt=" "
					height="50" width="400">
			</div>
			<div id="login">
				<div class="bell-frame">
					<div class="top">
						<div>
							<div>&nbsp;</div>
						</div>
					</div>
					<div class="middle">
						<div class="r">
							<div class="c">
								<div class="c2">
									<div class="bell-titlebar">
										<div class="float-container">
											<div class="bell-titlebar-title-panel">Welcome</div>
											<div class="bell-titlebar-button-panel">&#160;</div>
										</div>
									</div>
									<div class="bell-window-content">
										<form id="loginData" name="loginData" method="post" action="login.htm">
											<div class="message-row">
												<p>Log in to the BELL CBTTR07J Rate Table Mechanization</p>
												<c:if test="${ not empty errorMessage }">
												  <p style="color:red">${errorMessage}</p>
												</c:if>
											</div>
											<div class="input-row">
												<label for="j_username">User name:</label> 
												<span class="ctrl"> 
													<input class="textinput" type="text" name="username" id="username" style="width:150px;">
												</span>
											</div>
											<div class="input-row">
												<label for="j_password">Password:</label>
												 <span class="ctrl">
													<input class="textinput" type="password" name="password" id="password" style="width:150px;">
												</span>
											</div>
											<div class="button-row">
												<span class="ctrl"> <input class="formButton"
													value="Log In" type="submit"
													onClick="form.submit();this.disabled=true;document.body.style.cursor = 'wait'; this.className='formButton-disabled';" />
												</span> <input type="hidden" name="j_character_encoding"
													value="UTF-8">
											</div>
										</form>
									</div>
									<div class="notice-row">
										<p>It is recommended that users log out when finished with
											the BELL CBTTR07J Rate Table Mechanization or when visiting
											an untrusted site</p>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="bottom">
						<div>
							<div>&nbsp;</div>
						</div>
					</div>
				</div>

				<div class="login-footer">
					<div class="info">
						<p id="footerVersion">BELL CBTTR07J Rate Table Mechanization
							version: 1.0</p>
						<p id="copyright">Copyright © 2014. All rights reserved.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>