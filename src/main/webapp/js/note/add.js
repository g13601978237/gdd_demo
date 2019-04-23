//上传头图
$("#myfile").change(function () {
    console.log("000000000")
   $("#headImgForm").ajaxSubmit({
       type:"POST",
       url:"/ssm_demo/note/uploadHeadImg",
       dataType:"json",
       success:function (result) {
           var filePath = result.data.filePath;
           console.log(filePath)
           var imgUrl = "url(/ssm_demo/"+filePath+")";
           $(".set-note-bg").css("background",imgUrl);
           console.log("上窜成功");
           $(".setNoteHeaderImgForm").hide();
           $(".setNoteHeaderImgTip").hide();
           $("#headImgPath").val(imgUrl);
       }
   })

});

//添加游记
$("#NoteBtn").click(function () {
    $.ajax({
        type:"POST",
        url:"/ssm_demo/note/addNote" ,
        data:$("#noteForm").serialize(),
        success:function (result) {
            console.log(result)
            if(result.status == "SUCCESS"){
                window.location.href = "/ssm_demo/note/goToDetail"
            }else{
                $("#note_tip").text(result.data.reason);
            }
        }
        }
    )

})