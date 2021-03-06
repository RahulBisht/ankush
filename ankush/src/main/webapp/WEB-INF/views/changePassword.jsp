<!------------------------------------------------------------------------------
-===========================================================
-Ankush : Big Data Cluster Management Solution
-===========================================================
-
-(C) Copyright 2014, by Impetus Technologies
-
-This is free software; you can redistribute it and/or modify it under
-the terms of the GNU Lesser General Public License (LGPL v3) as
-published by the Free Software Foundation;
-
-This software is distributed in the hope that it will be useful, but
-WITHOUT ANY WARRANTY; without even the implied warranty of
-MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
-See the GNU Lesser General Public License for more details.
-
-You should have received a copy of the GNU Lesser General Public License 
-along with this software; if not, write to the Free Software Foundation, 
-Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
------------------------------------------------------------------------------->
<!-- Change Password page  -->

<%@ include file="layout/blankheader.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$('#existingPassword').focus();
	});
</script>
<div class="section-header">
	<div  class="row-fluid mrgt20">
	  	<div class="span6"><h2 class="heading text-left  left">Profile</h2>
	  	<button  id="errorBtn" class="btn-error" style="height: 29px; color: white; border: medium none; background-color: rgb(239, 48, 36) ! important;  display:none;position: relative; padding: 0px 15px; left: 15px;">Error</button>
	  	
	  	</div>
	  	
	  	<!--  
	  	<div class="span2 text-center"><button class="btn btnAlert"  id="errorBtn" style="margin-right:8px;display:none;">Error</button></div>
	  	-->
	  	<div class="span6 text-right">
	  		<div class="span2 offset10">
				<!-- <div class="span6 text-right" style="padding-top:10px;"><a href="#" style="text-align:center;color:#0088CC;">Learn More</a></div> -->
				<div class="span12 text-right"><button id="changePasswordButton" form="changePasswordForm" type="submit" data-loading-text="Applying..." class="btn">Apply</button></div>
			</div>
		</div>	
	</div>
</div>

<div class="section-body content-body mrgnlft8">
	<div >
		<div id="error-div" class="row-fluid" style="display:none;">
			<div id="popover-content" class="commonErrorDiv"></div>
		</div>
		<form id="changePasswordForm" onsubmit="return com.impetus.ankush.password.changePassword();">
			<div class="row-fluid"><div class="span12 section-heading"><h4>Change Password</h4></div></div>
			<div class="row-fluid">
				<div class="span2"><label class="form-label text-right">Existing:</label></div>
				<div class="span3 text-left"><input data-placement="right" data-toggle="tooltip" title="Existing Password" id="existingPassword" type="password" placeholder="Existing Password"/></div>
			</div>
			<div class="row-fluid">
				<div class="span2"><label class="form-label text-right">New:</label></div>
				<div class="span3 text-left"><input id="newPassword" type="password" placeholder="New Password" data-placement="right" title="New Password"/></div>
			</div>
			<div class="row-fluid">
			<div class="span2">&nbsp;</div>
				<div class="span3 text-left"><input id="confirmPassword" type="password" placeholder="Confirm Password" data-placement="right" title="Confirm Password"/></div>
			</div>
		</form>		
		<!-- <div class="row-fluid"><h4>Time Zone</h4></div>
		<div class="row-fluid">
			<div class="span2 text-right"><label class="form-label">Time Zone:</label></div>
			<div class="span7 text-left"><select><option>Time Zone1</option></select></div>
		</div> -->
	</div>
</div>
