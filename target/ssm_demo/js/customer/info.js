$(document).ready(function () {
    getLoginCustomerTelno();

    $("#headfile").change(function () {
        console.log("进入")
        $("#myPicForm").ajaxSubmit({
            type:"POST",
            url:"/ssm_demo/customer/uploadMyPic",
            dataType:"json",
            success:function (result) {
                var filePath = result.data.filePath;
                console.log("filePath="+filePath)
                var myPicUrl = "/ssm_demo/"+filePath;
                console.log("myPicUrl="+myPicUrl)
                $(".ceshi").attr('src',myPicUrl)
                console.log("上c成功")
                $("#MyImgPath").val(myPicUrl);
            }
        })
    })
})

function getLoginCustomerTelno() {
    $.get("/ssm_demo/customer/getLoginTelno",{},function (result) {
        var telno =  result .data.customerTelno;
        console.log("telno:" + telno);
        getCustomerInfo(telno);
    });
}
/*  2. 根据手机号查询用户信息*/
function getCustomerInfo(tel) {
    $.get("/ssm_demo/customer/info",{telno:tel},function (result) {
        var customer = result.data.customer;
        $("input[name=custName]").val(customer.custName);
        $("input[name=custCity]").val(customer.custCity);
        $("input[name=custBirth]").val(customer.custBirth);
        if(customer.custGender == "M"){
            console.log("页面默认为男性")
        }else{
            $("input[value=M]").removeAttr("checked");
            $("input[value=F]").attr("checked","checked");
        }
    });
}
