document.write("<script language=javascript src='../js/jquery.js'></script>")
function request(url,data,target){
    $.post(url,{"data":data},function (data, status, req) {
        console.log(status);
        if(status=="success"){
            $(target).html(req.responseText);
        }else{
            window.open("./view/false.html");
        }
    })
}