// JavaScript Document
document.write("<script language=javascript src='/view/jquery.js'></script>")
document.write("<script language=javascript src='/view/common.js'></script>")
function AddMaterial(){
	var newMaterial="<tr>" +
		"<td><select onclick='getMaterialCodes(this)' name='itemid'></select></td>" +
		"<td><input type='text' name='quantity'></td>" +
		"<td><a href='#' onclick='RemoveMaterial(this);'>删除</a></td></tr>";
	$("#materialContainer").append(newMaterial);
}
function submitForm(){
	$("#ordermaster").submit();
}
function RemoveMaterial(val){
	$(val).parent().parent().remove();
}
function getMaterialCodes(target) {
    if($(target).html()==""){
        request("/getMaterialMesByHtml",null,target);
    }
}