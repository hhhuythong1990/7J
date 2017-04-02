<form id="editProjectForm" name="editProjectForm"
	class="jtable-dialog-form jtable-create-form"
	style="margin-top: 20px; width:400px">
	<div class="jtable-input-field-container">
		<div class="jtable-input-label">* Project Name (Important - Project Name should begin with Test if it is a Test Project)</div>
		<div class="jtable-input jtable-text-input">
			<input id="projectName" class="validate[required,maxSize[255]]" type="text"
				name="projectName" style="width:300px" maxlength="255" value="${project.projectName}">
			<input type="hidden" id="originalProjectCode" name="originalProjectCode" value="${project.projectId}"/>	
		</div>
	</div>
	
	<div class="jtable-input-field-container">
		<div class="jtable-input-label">* Peats # (maximum 15 characters)</div>
		<div class="jtable-input jtable-text-input">
			<input id="peat"
				class="validate[required,maxSize[15]" type="text"
				name="peat" maxlength="15" style="width:120px" value="${project.peat}" >
		</div>
	</div>
</form>
<script>
$(document).ready(function () {
	$('#originalProjectCode').val();
});
</script>
