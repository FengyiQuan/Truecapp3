/**
 * @author Fengyi Quan
 */



$(function () {
      if (typeof WebSocket != 'undefined') {
          $("#connect").click(() => connect());
          $("#disconnect").click(() => disconnect());
          $("#sendBtn").click(() => {
              if (receiver.id !== "" && receiver.email !== "") {
                  sendMessage();
                  if ($("#messageContent").val() !== '') {
                      createMessage({
                                        sender: currentUser,
                                        receiver: receiver,
                                        content: $("#messageContent").val()
                                    }).catch(e => console.log(e));
                  }
              }
          });
          connect();
          $(window).on("unload", function(e) {
              disconnect();
      });

      } else {
          alert("Your browser doesn't support Websocket，Please use another browser.")
      }
  }
);


