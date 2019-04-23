/**
 * Created by Administrator on 2019/3/7 0007.
 */
document.querySelector(".nav-tabs").onclick=function(event){
    var ele = event.target;
    var all = document.querySelectorAll(".nav-tabs li a");
        for(var i=0;i<all.length;i++){
            all[i].style.backgroundColor="#ffffff";
            all[i].style.color = "#000000";
            /**console.log(all[i].style.backgroundColor);**/
        }
    ele.style.backgroundColor = "#ffa800";
    ele.style.color = "#ffffff";
}
/**document.querySelector(".aa").onclick=function(){
    $(".nav-tabs li a").removeClass('aaa').addClass('aa');
}**/
document.querySelector(" .passport").onclick= function () {
    $(" .update").toggle();
}

$(" .head-nav-down").hover(function(){
    $(" .head-nav-down").css("display","block");
},function(){
    $(" .head-nav-down").css("display","none");
})

$(" .head-nav>li:nth-child(4)").hover(function(){
    $(" .head-nav-down").css("display","block");
},function(){
    $(" .head-nav-down").css("display","none");
})







