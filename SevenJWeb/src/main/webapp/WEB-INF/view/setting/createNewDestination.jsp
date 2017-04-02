<form id="createNewProjectForm" name="createNewProjectForm"
	class="jtable-dialog-form jtable-create-form"
	style="margin-top: 20px; width:400px">
	<div class="jtable-input-field-container">
		<div class="jtable-input-label">Destination Code</div>
		<div class="jtable-input jtable-text-input">
			<input id="destinationCode" class="validate[required,maxSize[10]]" type="text"
				name="destinationCode" maxlength="10" style="width:100px">
		</div>
	</div>
	<div class="jtable-input-field-container">
		<div class="jtable-input-label">Destination Description</div>
		<div class="jtable-input jtable-text-input">
			<input id="destinationDescription"
				class="validate[required,maxSize[255]]" type="text"
				name="destinationDescription" maxlength="255" style="width:300px">
		</div>
	</div>	
	<div class="jtable-input-field-container">
		<div class="jtable-input-label">Sort Order</div>
		<div class="jtable-input jtable-text-input">
			<input id="sortOrder"
				class="validate[required,maxSize[11]]" type="text"
				name="sortOrder" maxlength="11" style="width:100px">
		</div>
	</div>
</form>