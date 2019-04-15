// JavaScript Document
document.write("<script language=javascript src='../js/jquery.js'></script>")
function AddProblem(){
	var newProblem="<tr> <td><select name='problem'><option value='coreweighe'>筒芯重量</option><option value='shortweight'>短重</option><option value='gramweight'>克重</option><option value='width'>幅宽</option><option value='outerpackagingloss'>外包装损耗</option><option value='paperdamage'>纸张破损</option><option value='Moisture'>水分</option></select></td><td><input type='text' name='answer'></td><td><input type='text' name='problemStatement'></td><td><a href=''#' onclick='RemoveProblem(this);'>删除</a></td></tr>";
	$("#problemContainer").append(newProblem);
}
function submitForm(){
	$("#problemForm").submit();
}
function RemoveProblem(val){
	$(val).parent().parent().remove();
}