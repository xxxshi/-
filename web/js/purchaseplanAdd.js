// JavaScript Document
document.write("<script language=javascript src='../js/jquery.js'></script>")
document.write("<script language=javascript src='../js/common.js'></script>")
function AddMaterial(){
	var newMaterial="<tr><td><select onclick='getMaterialCodes(this)' name='itemId'></select></td><td><input type='text' name='itemnum' " +
		"onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d]/g,'')\"></td>" +
		"<td><input type='text' name='transportationFee' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d.]/g,'')\">" +
		"</td><td><input type='text' name='weight' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d.]/g,'')\">" +
		"</td><td><a href='#' onclick='RemoveMaterial(this);'>删除</a></td></tr>";
	$("#materialContainer").append(newMaterial);
}
function submitForm(){
	$("#purchaseplanForm").submit();
}
function RemoveMaterial(val){
	$(val).parent().parent().remove();
}
function getMaterialCodes(target) {
	if($(target).html()==""){
        request("/getMaterialMesByHtml",null,target);
	}
}