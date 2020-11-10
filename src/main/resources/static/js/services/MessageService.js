/**
 * @author Fengyi Quan
 */


const url = 'http://localhost:8080';

const createMessage = (msg) => {
    return fetch(`${url}/api/messages`,
                 {
                     method: 'POST',
                     body: JSON.stringify(msg),
                     headers: {
                         'content-type': 'application/json'
                     },
                     credentials: 'include'
                 }).then(response => response.json());
};

const markAllRead = (receiver) => {
    if (receiver != null && receiver !== ''){
        // console.log('call mark all read from js')
        return fetch(`${url}/api/messages/markAllRead/${receiver}`,
                     {
                         method: 'POST',
                         headers: {
                             'content-type': 'application/json'
                         },
                         credentials: 'include'
                     });
    }

}

