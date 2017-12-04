// $.ajax({
//     url: "test.html",
//     context: document.body
// }).done(function() {
//     $( this ).addClass( "done" );
// });

//
// http://13.124.207.144/lotto_rounds
// isSuccess
// result

//
// var settings = {
//     "async": true,
//     "crossDomain": true,
//     "url": "http://13.124.207.144/lotto_rounds",
//     "method": "GET",
//     "headers": {
//         "cache-control": "no-cache",
//         "postman-token": "8e5ae00f-a28f-73a8-6f7d-34276204ce9a"
//     }
// }
//
// $.ajax(settings).done(function (response) {
//     console.log(response);
// });


    //
    // $('.test').click(function(){
    //     // var rcvEmailValue = $("#rcv_email").val();
    //
    //     $.ajax({
    //         type : 'GET',
    //         url : url,
    //         dataType : 'json',
    //
    //         success : function(res) {
    //             if(res.isSuccess == true){
    //                 console.log('asdf');
    //             }
    //         }
    //
    //     }) // End Ajax Request
    // });


// (res.results[0].title == "Sample") asdfasdfsafsd
//
// results :
// [{
//     "title": "Sample",
//     "name": "main_window",
//     "width": 500,
//     "height": 500
//     },
//     {
//         "asdf":"asdf"
//     },
//     { "asdf":"asdf"
//     }]
//
//
$(document).ready(function() {
    $('.test').click(function() {
        $.getJSON('./assets/js/jsonData/test.json', function(data) {
            var html = '';
            $.each(data, function(entryIndex, entry) {
                html += '<div class="entry">asdfasdfa';
                html += '<h3 class="term">' + entry.num + '</h3>';
                html += '<div class="part">' + entry.name + '</div>';
                html += '</div>';
            });
            console.log(html);
            $('.result').html(html);
        });
        // return false;
    });
});


// console.log(window.location.pathname);
