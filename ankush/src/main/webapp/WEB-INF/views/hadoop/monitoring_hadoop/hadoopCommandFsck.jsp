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
<!-- Hadoop Fsck Command Page  -->

<%@ include file="../../layout/blankheader.jsp"%>
<script
	src="<c:out value='${baseUrl}' />/public/js/ankush.validation.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('#command_fsckPath').tooltip();
		$('#command_fsckGenericOptions').tooltip();
		$('#command_fsckOtherOptions').tooltip();
	});
</script>
<div class="section-header">
	<div class="row-fluid" style="margin-top: 20px;">
		<div class="span7">
			<h2 class="heading text-left left">Fsck</h2>
			<button class="btn-error" id="errorBtnHadoop"
				onclick="com.impetus.ankush.hadoopMonitoring.scrollToTop();"
				style="display: none; height: 29px; color: white; border: none; background-color: #EF3024 !important;padding:0 15px; left:15px; position:relative"></button>
			
		</div>
		
		<!--  
		<div class="span3 minhgt0">
			<button class="span3 btn-error" id="errorBtnHadoop"
				onclick="com.impetus.ankush.hadoopMonitoring.scrollToTop();"
				style="display: none; height: 29px; color: white; border: none; background-color: #EF3024 !important;"></button>
		</div>
		-->
		
		<div class="span5 text-right">
			<button class="btn" style="margin-right: 8px;"
				onclick="com.impetus.ankush.removeChild(3)">Cancel</button>
			<button class="btn" style="margin-right: 50px;"
				onclick="com.impetus.ankush.hadoopMonitoring.commandsData('fsck')">Run</button>
		</div>
	</div>
</div>
<div class="section-body content-body">
	<div class="container-fluid mrgnlft8">
		<div class="row-fluid">
			<div id="error-div-hadoop" class="span12 error-div-hadoop"
				style="display: none;">
				<span id="popover-content" style="color: red;"></span>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2 ">
				<label class="text-right form-label">Path:</label>
			</div>
			<div class="span9 text-left">
				<input type="text" class="input-large" id="command_fsckPath"
					placeholder="Path" data-toggle="tooltip" data-placement="right"
					title=""></input>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2 ">
				<label class="text-right form-label">Generic Options:</label>
			</div>
			<div class="span3 text-left">
				<select id="command_fsckOptions" onchange="">
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivHide('fsck')"></option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-conf</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-D</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-fs</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-jt</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-files</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-libjars</option>
					<option
						onclick="com.impetus.ankush.hadoopMonitoring.genericOptionsDivShow('fsck')">-archives</option>
				</select>
			</div>
			<div class="span6 text-left" id="command_fsckOptionsValueText"
				style="display: none;">
				<input type="text" class="input-large" id="command_fsckOptionsValue"></input>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2 ">
				<label class="text-right form-label">Other Options:</label>
			</div>
			<div class="span9 text-left">
				<select id="command_fsckOtherOptions" onchange="">
					<option></option>
					<option>-move</option>
					<option>-delete</option>
					<option>-openforwrite</option>
					<option>-files</option>
					<option>-blocks</option>
					<option>-locations</option>
					<option>-racks</option>
				</select>
			</div>
		</div>
	</div>
</div>
