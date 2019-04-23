

    $.get("/ssm_demo/note/getTen",{},function (result){
        var noteList = result.data.notes;
        var customer = result.data.customer;
        console.log(noteList);
        console.log(customer);
        $.each(noteList,function (index,item) {
            var ele=" <li style=\"list-style: none; float: left\">\n" +
                "            <a href=\"javascript:;\">\n" +
                "                <div class=\"noteHeadImg\" style=\"width: 200px;height: 200px;background-repeat: no-repeat\"></div>\n" +
                "                <span class=\"noteTitle\"></span>\n" +
                "                <span class=\"noteCreateTime\"></span>\n" +
                "                <span class=\"custName\"></span>\n" +
                "                <span class=\"\">,坐标</span>\n" +
                "                <span class=\"custCity\"></span>\n" +
                "            </a>\n" +
                "        </li>";
            $("#note_list").append(ele);
            var li = $("#note_list").children().eq(index);

            var a=li.children().eq(0);
            a.attr("name",item.noteId)
            console.log(a)
            a.children().eq(0).css("backgroundImage","url(/ssm_demo/images/banner_1.jpg)");
            a.children().eq(1).html(item.noteTitle);
            a.children().eq(2).text(new Date());
            a.children().eq(3).text(customer.custName);
            a.children().eq(5).text(customer.custCity);
        })
    });


//事件冒泡
var ul = $("#note_list");
ul.click(function (event) {
    var ele = event.target;
    console.log(ele);
    console.log(ele.nodeName)
    console.log(ele.nodeName)
    if(ele.nodeName =="DIV" || ele.nodeName =="SPAN"){
        console.log("点击成功");
        var a = ele.parentNode;
        var noteId = a.getAttribute("name");
        window.location.href="/ssm_demo/note/goToDetail/"+noteId;
    }
})
