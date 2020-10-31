/**
 * @author Fengyi Quan
 */

var stompClient = null;

function connect() {
    var socket = new SockJS("/chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (success) {
        setConnected(true);
        stompClient.subscribe("/user/queue/chat", function (msg) {
            // console.log(JSON.parse(msg.body));
            showRecentMessageReceiver(JSON.parse(msg.body));
            markAllRead(receiver.email).catch(e => console.log(e));
        })
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
}



function sendMessage() {
    const msg = {
        receiver: receiver,
        content: $("#messageContent").val()
    };

    if ($("#messageContent").val() !== '') {
        stompClient.send("/app/chat", {}, JSON.stringify(msg));
        showRecentMessageSender(msg);
    }
}

function showRecentMessageSender(msg) {
    var today = new Date();
    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    var dateTime = date + ' ' + time;
    $("#conversation")
        .append(
            '<div class="d-flex mb-4 justify-content-end">'
            + '<div class="msg_container_current">'
            + '<span>' + msg.content + '</span>'
            + '<span class="msg_time">' + dateTime + '</span>'
            + '</div>'
            + '<div class="img_cont_msg">'
            + '<img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg" class="rounded-circle user_img_msg"/>'
            + '</div></div>'
        )
}

function showRecentMessageReceiver(msg) {
    var today = new Date();
    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    var dateTime = date + ' ' + time;
    $("#conversation")
        .append(
            '<div class="d-flex mb-4 justify-content-start">'
            + '<div class="img_cont_msg">'
            + '<img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg" class="rounded-circle user_img_msg"/>'
            + '</div>'
            + '<div class="msg_container_other">'
            + '<span>' + msg.content + '</span>'
            + '<span class="msg_time">' + dateTime + '</span>'
            + '</div></div>'
        )
}

// not necessary
function setConnected(flag) {
    $("#connect").prop("disabled", flag);
    $("#disconnect").prop("disabled", !flag);
    if (flag) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }

}