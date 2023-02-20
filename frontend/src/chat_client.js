var sock = new SockJS('http://localhost:8085/chatws');
var stomp = Stomp.over(sock);

// Elements
var tabTitle = document.getElementById('tab-title');
var appTitle = document.getElementById('app-title');
var messageField = document.getElementById('message-input');
var usernameField = document.getElementById('username-input');
var usersJoined = document.getElementById('users-joined');
var messagesList = document.getElementById('messages-list');
var sendButton = document.getElementById('send-button');
var joinButton = document.getElementById('join-button');

// forms
var joinForm = document.getElementById('join-form');
var chatForm = document.getElementById('chat-form');

// usernameField.value = "Thiago"
var currentUser = '';

// STOMP: Conectando cliente stomp ao servidor
stomp.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stomp.subscribe('/topic/messages', function (message) {
        let msg = JSON.parse(message.body);

        switch (msg.type) {
            case 'JOIN':
                usersJoined.appendChild(createJoinMessage(msg));
                join();
                break;
            case 'CHAT':
                messagesList.appendChild(createMessage(msg));
            default:
                break;
        }
    });
});

joinButton.addEventListener('click', function () {
    console.log('click: sockJs: join');

    let message = {
        sender: usernameField.value.trim(),
        type: 'JOIN'
    };

    currentUser = message.sender;
    tabTitle.innerText = 'Chat - ' + message.sender;

    stomp.send("/chat/join", {}, JSON.stringify(message));

    joinForm.classList.toggle('hidden');

    chatForm.classList.contains('hidden') ? chatForm.classList.toggle('hidden') : null;
});

sendButton.addEventListener('click', function () {
    console.log('click: sockJs: send');

    stomp.send("/chat/talk", {}, JSON.stringify({
        sender: usernameField.value.trim(),
        content: messageField.value.trim(),
        type: 'CHAT'
    }));

});

function createMessage(msg) {
    let li = document.createElement('li');
    li.innerHTML = '<b>' + msg.sender + '</b>: ' + msg.content;

    // li.appendChild(createPhoto(msg));

    return li;
}

function createPhoto(msg) {
    let photo = document.createElement('div');
    photo.innerText = msg.sender.substring(0, 2).toUpperCase();
    photo.style.backgroundColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
    return photo;
}

function createJoinMessage(msg) {
    let li = document.createElement('li');
    // li.innerHTML = '<b>✅' + msg.sender + ' entrou...</b>: ';
    li.innerText = msg.sender.substring(0, 2).toUpperCase();

    // Gerando uma cor aleatória para o usuário
    li.style.backgroundColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
    return li;
}

function join() {
    appTitle.innerText = 'Chat';
}