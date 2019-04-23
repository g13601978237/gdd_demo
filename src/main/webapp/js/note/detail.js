

//加载我的最近的游记详情
var url = window.location.href;
var noteID = url.split('/')[url.split('/').length-1];
console.log("url ="+url);
console.log("noteId ="+noteID);

$.get("/ssm_demo/note/goTo/"+noteId),{},function (result) {
    var note = result.data.note;
    console.log("note"+note);
    console.log("note.noteHeadImg:"+note.noteHeadImg);
    console.log("note.noteCreateTime:"+note.noteCreateTime);
    console.log("note.noteCity:"+note.noteCity);
    console.log("note.noteTitle:"+note.noteTitle);
    console.log("note.noteContent:"+note.noteContent);
    $(".set-note-bg").css("backgroundImage:",note.noteHeadImg);
    $(".note_title").html(note.noteTitle );
    $(".noteTripDate").html(note.noteTripDate);  //日期获得的是毫秒数，所以要转换成日期格式显示在页面中
    $(".noteTripDays").html(note.noteTripDays  );
    $(".noteTripPartner").html(note.noteTripPartner  );
    $(".noteTripFeePerPeople").html(note.noteTripFeePerPeople  );
    $(".note_content").html(note.noteContent );
}
//加载我的最近的游记详情

$.get("/ssm_demo/note/myLastNote",{},function (result) {
    var note = result.data.myLastNote;
    console.log("note"+note);
    console.log("note.noteHeadImg:"+note.noteHeadImg);
    console.log("note.noteCreateTime:"+note.noteCreateTime);
    console.log("note.noteCity:"+note.noteCity);
    console.log("note.noteTitle:"+note.noteTitle);
    console.log("note.noteContent:"+note.noteContent);
    $(".set-note-bg").css("backgroundImage:",note.noteHeadImg);
    $(".note_title").html(note.noteTitle );
    $(".noteTripDate").html(note.noteTripDate);  //日期获得的是毫秒数，所以要转换成日期格式显示在页面中
    $(".noteTripDays").html(note.noteTripDays);
    $(".noteTripPartner").html(note.noteTripPartner);
    $(".noteTripFeePerPeople").html(note.noteTripFeePerPeople  );
    $(".note_content").html(note.noteContent );


} );

