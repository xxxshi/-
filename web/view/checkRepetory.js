document.write("<script src='../js/jquery.js'></script>")

function checkRepetoryNum(){
    $.post("/checkRepetory",null,function (data, status, req) {
        if(status=="success"){
            var isSuccess=req.responseText;
            if(isSuccess=="success"){
                alert("库存充足");
            }else{
                alert(isSuccess+"库存不足，将进行采购");
                window.open("/input_view/purchaseplanAdd.html","_self");
            }
        }
    })

}
